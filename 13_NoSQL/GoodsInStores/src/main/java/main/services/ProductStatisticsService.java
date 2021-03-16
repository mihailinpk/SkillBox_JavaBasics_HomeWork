package main.services;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import main.repositories.GoodsInStoresRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class ProductStatisticsService {

    GoodsInStoresRepository repository;

    @Autowired
    public void setRepository(GoodsInStoresRepository repository) {
        this.repository = repository;
    }

    public void showTotalNumberOfGoods() throws InterruptedException {
        MongoCollection collectionStores = repository.getCollectionStores();
        AggregateIterable<Document> resultList = collectionStores.aggregate(Arrays.asList(new Document(
            "$project", new Document("_id", 0).append("name","$name").append("count",new Document("$size", "$goods")
        ))));
        for (Document dbObject : resultList)    {
            System.out.println(
                    "Магазин: " + dbObject.get("name") + " | количество товаров: " + dbObject.get("count"));
        }
        System.out.println();
    }

    public void showAveragePriceOfProduct() {
        MongoCollection collectionStores = repository.getCollectionStores();
        AggregateIterable<Document> resultList = collectionStores.aggregate(Arrays.asList(
            new Document(
                "$lookup", new Document()
                    .append("from","products")
                    .append("localField","goods")
                    .append("foreignField","name")
                    .append("as","products_list")
            ),
            new Document(
                "$unwind", "$products_list"
            ),
            new Document(
                "$group",
                new Document("_id", "$name")
                .append("average_price", new Document("$avg", "$products_list.price"))
            )
        ));
        for (Document dbObject : resultList)    {
            System.out.println(
                    "Магазин: " + dbObject.get("_id") +
                            " | средняя цена товара: " + dbObject.get("average_price"));
        }
        System.out.println();
    }

    public void showMostExpensiveAndCheapestProduct() {
        MongoCollection collectionStores = repository.getCollectionStores();
        AggregateIterable<Document> resultList = collectionStores.aggregate(Arrays.asList(
                new Document(
                        "$lookup", new Document()
                        .append("from","products")
                        .append("localField","goods")
                        .append("foreignField","name")
                        .append("as","products_list")
                ),
                new Document("$unwind", "$products_list"),
                new Document(
                        "$project", new Document()
                        .append("name", "$name")
                        .append("product_name", "$products_list.name")
                        .append("product_price", "$products_list.price")
                ),
                new Document("$sort", new Document("product_price",1)),
                new Document(
                        "$group", new Document()
                        .append("_id", "$name")
                        .append("max_price_product", new Document("$last", "$product_name"))
                        .append("min_price_product", new Document("$first","$product_name"))
                )
        ));
        for (Document dbObject : resultList)    {
            System.out.println(
                    "Магазин: " + dbObject.get("_id") +
                    " | самый дорогой товар: " + dbObject.get("max_price_product") +
                    " | самый дешевый товар: " + dbObject.get("min_price_product")

            );
        }
        System.out.println();
    }

    public void showQuantityOfGoodsCheaper100rubles() {
        MongoCollection collectionStores = repository.getCollectionStores();
        AggregateIterable<Document> resultList = collectionStores.aggregate(Arrays.asList(
                new Document(
                        "$lookup", new Document()
                        .append("from","products")
                        .append("localField","goods")
                        .append("foreignField","name")
                        .append("as","products_list")
                ),
                new Document("$unwind", "$products_list"),
                new Document("$project", new Document()
                        .append("name", "$name")
                        .append("product_prices", "$products_list.price")
                ),

                new Document("$match", new Document(
                        new Document("product_prices", new Document("$lt", 100))
                    )
                ),
                new Document("$group", new Document()
                        .append("_id", "$name")
                        .append("sum_prices_lt_100_array", new Document("$push",
                                    new Document("$slice", Arrays.asList(Arrays.asList("$product_prices"), 1))
                                )
                        )
                ),
                new Document("$project", new Document()
                        .append("name", "$name")
                        .append("count_prices_lt_100",
                                new Document("$size", "$sum_prices_lt_100_array")
                        )
                )
        ));
        for (Document dbObject : resultList) {
            System.out.println("Магазин: " + dbObject.get("_id") + " | количество товаров дешевле 100 рублей: " +
                    dbObject.get("count_prices_lt_100"));
        }
        System.out.println();
    }

}
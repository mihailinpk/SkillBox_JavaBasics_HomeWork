package main.repositories;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.mongodb.client.model.Aggregates.*;

@Component
public class GoodsInStoresRepository {

    private final String DATABASE_NAME = "goods_in_stores";
    private final String COLLECTION_ONE_NAME = "storages";
    private final String COLLECTION_TWO_NAME = "products";

    private final String FIELD_NAME_FOR_CHECK_AND_SEARCH = "name";

    private MongoClient client;
    private MongoCollection collectionProducts;
    private MongoCollection collectionStores;

    public GoodsInStoresRepository() {
        client = MongoClients.create();
        collectionStores = client.getDatabase(DATABASE_NAME).getCollection(COLLECTION_ONE_NAME);
        collectionProducts = client.getDatabase(DATABASE_NAME).getCollection(COLLECTION_TWO_NAME);
    }

    public void insertProduct(Document document)    {
        collectionProducts.insertOne(document);
    }

    public void insertStore(Document document)  {
        collectionStores.insertOne(document);
    }

    public void deleteProduct(Document document)    {
        collectionProducts.deleteOne(document);
    }

    public void deleteStore(Document document)  {
        collectionStores.deleteOne(document);
    }

    public Document getProductDocumentByName(String valueOfNameField)   {
        return getDocumentByName(valueOfNameField, collectionProducts);
    }

    public Document getStoreDocumentByName(String valueOfNameField) {
        return getDocumentByName(valueOfNameField, collectionStores);
    }

    public boolean productIsInBase(String valueOfNameField) {
        return documentInBaseByName(valueOfNameField, collectionProducts);
    }

    public boolean storeIsInBase(String valueOfNameField)   {
        return documentInBaseByName(valueOfNameField, collectionStores);
    }

    private boolean documentInBaseByName(String valueOfNameField, MongoCollection collection) {
        Document document = getDocumentByName(valueOfNameField, collection);
        if (document == null)   {
            return false;
        } else {
            return (document.get(FIELD_NAME_FOR_CHECK_AND_SEARCH).equals(valueOfNameField));
        }
    }

    private Document getDocumentByName(String valueOfNameField, MongoCollection collection) {
        Document document = (Document) collection.find(
                new Document(FIELD_NAME_FOR_CHECK_AND_SEARCH, valueOfNameField)).first();
        return document;
    }


    //ниже временные методы на время разработки выборки (перед отправкой ДЗ на проверку - удалить)

    public MongoCollection getCollectionStores()    {
        return collectionStores;
    }

    public MongoCollection getCollectionProducts()  {
        return collectionProducts;
    }

}
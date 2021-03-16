package main.controllers;

import main.entities.Product;
import main.entities.Store;
import main.repositories.GoodsInStoresRepository;
import main.services.GoodsInStoresService;
import main.services.ProductStatisticsService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodsInStoresController {

    private GoodsInStoresService goodsInStoresService;
    private ProductStatisticsService productStatisticsService;
    private GoodsInStoresRepository repository;

    @Autowired
    public void setGoodsInStoresService(GoodsInStoresService goodsInStoresService) {
        this.goodsInStoresService = goodsInStoresService;
    }

    @Autowired
    public void setProductStatisticsService(ProductStatisticsService productStatisticsService) {
        this.productStatisticsService = productStatisticsService;
    }

    @Autowired
    public void setGoodsInStoresRepository(GoodsInStoresRepository goodsInStoresRepository) {
        this.repository = goodsInStoresRepository;
    }

    public void setProduct(List<String> argumentsList) throws Exception {
        if (!goodsInStoresService.checkCorrectnessOfProductArguments(argumentsList))    {
            throw new Exception();
        } else  {
            Product product = goodsInStoresService.parseStringListToProduct(argumentsList);
            repository.insertProduct(goodsInStoresService.parseProductToDocument(product));
        }
    }

    public void setStore(List<String> argumentsList) throws Exception   {
        if(!goodsInStoresService.checkCorrectnessOfStoreArguments(argumentsList))   {
            throw new Exception();
        } else  {
            Store store = goodsInStoresService.parseLineToStore(argumentsList.get(0));
            repository.insertStore(goodsInStoresService.parseStoreToDocument(store));
        }
    }

    public void putProductToStore(List<String> argumentsList) throws Exception  {
        if (!goodsInStoresService.checkPutProductToStoreArguments(argumentsList))    {
            throw new Exception();
        } else  {
            String argumentProductName = argumentsList.get(0);
            String argumentStoreName = argumentsList.get(1);
            Document storeDocumentBeforeChange = repository.getStoreDocumentByName(argumentStoreName);
            Product product = goodsInStoresService.parseDocumentToProduct(
                    repository.getProductDocumentByName(argumentProductName)
            );
            Store store = goodsInStoresService.parseDocumentToStore(storeDocumentBeforeChange);
            List<Product> productList = store.getProductList();
            if (!productList.contains(product)) {
                productList.add(product);
            }
            store.setProductList(productList);
            repository.deleteStore(storeDocumentBeforeChange);
            repository.insertStore(goodsInStoresService.parseStoreToDocument(store));
        }

    }

    public void showProductStatistics() throws Exception {
        productStatisticsService.showTotalNumberOfGoods();
        productStatisticsService.showAveragePriceOfProduct();
        productStatisticsService.showMostExpensiveAndCheapestProduct();
        productStatisticsService.showQuantityOfGoodsCheaper100rubles();
    }

}
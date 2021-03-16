package main.services;

import main.entities.Product;
import main.entities.Store;
import main.repositories.GoodsInStoresRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsInStoresService {

    private GoodsInStoresRepository repository;

    private final String REGEX_FOR_NAME_MATCH = "[\\wА-Яа-я]+";
    private final String REGEX_FOR_PRICE_MATCH = "\\d+.\\d{2}";

    private final String FIELD_NAME_UNIVERSAL = "name";
    private final String FIELD_PRICE_OF_PRODUCT = "price";
    private final String FIELD_GOODS_OF_STORE = "goods";

    @Autowired
    public void setRepository(GoodsInStoresRepository repository) {
        this.repository = repository;
    }

    public boolean checkCorrectnessOfProductArguments(List<String> argumentsList) {
        if (argumentsList.size() != 2)  {
            return false;
        } else  {
            String argumentProductName = argumentsList.get(0);
            String argumentProductPrice = argumentsList.get(1);
            return argumentProductName.matches(REGEX_FOR_NAME_MATCH) && argumentProductPrice.matches(REGEX_FOR_PRICE_MATCH);
        }
    }

    public boolean checkCorrectnessOfStoreArguments(List<String> argumentsList)   {
        if (argumentsList.size() != 1)  {
            return false;
        } else  {
            return argumentsList.get(0).matches(REGEX_FOR_NAME_MATCH);
        }
    }

    public boolean checkPutProductToStoreArguments(List<String> argumentsList)  {
        if (argumentsList.size() != 2)  {
            return false;
        } else  {
            String argumentProductName = argumentsList.get(0);
            String argumentStoreName = argumentsList.get(1);
            if (argumentProductName.matches(REGEX_FOR_NAME_MATCH) && argumentStoreName.matches(REGEX_FOR_NAME_MATCH))   {
                return (repository.productIsInBase(argumentProductName) && repository.storeIsInBase(argumentStoreName));
            } else  {
                return false;
            }
        }
    }

    public Product parseStringListToProduct(List<String> argumentsList)  {
        Product product = new Product();
        product.setName(argumentsList.get(0));
        product.setPrice(Double.parseDouble(argumentsList.get(1)));
        return product;
    }

    public Product parseDocumentToProduct(Document document)    {
        Product product = new Product();
        product.setName(document.getString(FIELD_NAME_UNIVERSAL));
        product.setPrice(document.getDouble(FIELD_PRICE_OF_PRODUCT));
        return product;
    }

    public Store parseLineToStore(String line)  {
        Store store = new Store();
        store.setName(line);
        store.setProductList(new ArrayList<>());
        return store;
    }

    public Store parseDocumentToStore(Document document)    {
        Store store = new Store();
        store.setName(document.getString(FIELD_NAME_UNIVERSAL));
        List<String> listProductNames = document.getList(FIELD_GOODS_OF_STORE, String.class);
        List<Product> productList = new ArrayList<>();
        for(String productNameLine : listProductNames)  {
            Product product = parseDocumentToProduct(repository.getProductDocumentByName(productNameLine));
            productList.add(product);
        }
        store.setProductList(productList);
        return store;
    }

    public Document parseProductToDocument(Product product) {
        Document document = new Document();
        document.put(FIELD_NAME_UNIVERSAL, product.getName());
        document.put(FIELD_PRICE_OF_PRODUCT, product.getPrice());
        return document;
    }

    public Document parseStoreToDocument(Store store)   {
        Document document = new Document();
        document.put(FIELD_NAME_UNIVERSAL, store.getName());
        List<Product> productList = store.getProductList();
        List<String> productNameList = new ArrayList<>();
        productList.forEach(o -> productNameList.add(o.getName()));
        document.put(FIELD_GOODS_OF_STORE, productNameList);
        return document;
    }

}
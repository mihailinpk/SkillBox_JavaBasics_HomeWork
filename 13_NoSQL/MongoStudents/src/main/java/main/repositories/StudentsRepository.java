package main.repositories;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.springframework.stereotype.Component;

import org.bson.Document;

@Component
public class StudentsRepository {

    private final String DATABASE_NAME = "skillbox_homeworks";
    private final String COLLECTION_NAME = "students";

    private MongoClient client;
    private MongoCollection collection;

    public StudentsRepository() {
        client = MongoClients.create();
        collection = client.getDatabase(DATABASE_NAME).getCollection(COLLECTION_NAME);
    }

    public void insertDocument(Document document)   {
        collection.insertOne(document);
    }

    public FindIterable<Document> getFindingResultByConditionalOperator(
            String field, String conditionalOperator, Integer number
    )   {
        return collection.find(new Document(field, new Document(conditionalOperator, number)));
    }

    public long getCountResultsByConditionalOperator(
            String field, String conditionalOperator, Integer number
    )   {
        return collection.countDocuments(new Document(field, new Document(conditionalOperator, number)));
    }

    public Document getSingleDocumentBySort(String fieldForSearch, String fieldForSort, int sortingDirection) {
        return (Document) collection.find().sort(new Document(fieldForSort, sortingDirection)).limit(1).first();
    }

    public long getTotalNumberOfDocuments() {
        return collection.countDocuments();
    }

    public void dropCollection()  {
        collection.drop();
    }

}
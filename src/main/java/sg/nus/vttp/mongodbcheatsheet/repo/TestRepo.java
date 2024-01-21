package sg.nus.vttp.mongodbcheatsheet.repo;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.nus.vttp.mongodbcheatsheet.model.Demo;

@Repository
public class TestRepo {
    @Autowired
    MongoTemplate mongoTemplate;


    // to retrieve everything from the collection
    public List<Document> test(){

        // db.democollection.find()

        Query query = new Query();
        List<Document> result = mongoTemplate.find(query,Document.class,"democollection");
        return result;
    }

    public void addDocument(){
        
        //         db.democollection.insert(
        // {
        //     _id:12345,
        //     title: "demo_title_numeric_id",
        //     description: "this is a demo description with a numeric id"
        // }
        // );
        Demo demo = createJsonDocument();
        mongoTemplate.insert(demo, "democollection");
    }

    // only use ObjectId if you want to generate custom ObjectId class
    public Demo createJsonDocument(){
        Demo demo = new Demo();
        demo.set_id(new ObjectId());
        demo.setTitle("demo_java_numeric");
        demo.setDescription("This is a demo using java in a numeric form");
        return demo;
    }

    // performing a simple query with criteria
    public void getDocumentByQuery(){
        // db.democollection.find(
        // {
        //     _id:12345
        // }
        // )
        Criteria criteria = Criteria.where("_id").is("65ac789cab6b8a40d0be6012");
        Query query = Query.query(criteria);
        List<Document> result = mongoTemplate.find(query,Document.class,"democollection");
        for(Document d:result){
            System.out.println(d.toString());
        }
    }

    // use this if you want to find by ID
    public void getDocumentById(){
        Document d = mongoTemplate.findById("demo@demo.com", Document.class, "democollection");
        System.out.println(d.toString());
    }

    // to query by looking at multiple fields in criteria
    public void getDocumentByIdAndTitle(){
    // db.democollection.find(
    // {
    //     _id:"java_id",
    //     title:"demo_java"
    // }
    // )


        Criteria criteria = Criteria.where("_id").is("java_id").and("title").is("demo_java");
        Query query = Query.query(criteria);
        List<Document> result = mongoTemplate.find(query,Document.class,"democollection");
    }

    // query by regex
    public void getDocumentsByRegex(){
        // db.democollection.find(
        //     {
        //         title:{
        //             $regex : "demo",
            
        //         }
        //     }
        //     )

        // the "i" means it's case-insensitive
        // returns all documents where the title has the regex "demo"
        Criteria criteria = Criteria.where("title").regex("demo","i");
        Query query = Query.query(criteria);
        List<Document> result = mongoTemplate.find(query,Document.class,"democollection");
        for(Document d:result){
            System.out.println(d.toString());
        }
    }

    // query by conditional
    public void getDocumentByComparison(){

        // db.democollection.find(
        // {
        //     _id:{$gte: 12346}
        // }
        // )

        // find documents where id is greater than 12346
        Criteria criteria = Criteria.where("_id").gte(12346);
        Query query = Query.query(criteria);
        List<Document> results = mongoTemplate.find(query,Document.class,"democollection");
        for (Document d:results){
            System.out.println(d.toString());
        }
    }
    
}

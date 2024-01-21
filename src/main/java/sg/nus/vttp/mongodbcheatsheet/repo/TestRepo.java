package sg.nus.vttp.mongodbcheatsheet.repo;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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

    public Demo createJsonDocument(){
        Demo demo = new Demo();
        demo.set_id(new ObjectId());
        demo.setTitle("demo_java_numeric");
        demo.setDescription("This is a demo using java in a numeric form");
        return demo;
    }
    
}

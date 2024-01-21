package sg.nus.vttp.mongodbcheatsheet;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.nus.vttp.mongodbcheatsheet.repo.TestRepo;

@SpringBootApplication
public class MongodbcheatsheetApplication implements CommandLineRunner{

	@Autowired
	TestRepo testRepo;

	public static void main(String[] args) {
		SpringApplication.run(MongodbcheatsheetApplication.class, args);
	}

	@Override
	public void run(String... args){
		// get the entire collection
		//testRepo.addDocument();
		// List<Document> result = testRepo.test();
		// for(Document d: result){
		// 	System.out.println("_id: " + d.get("_id") + ", class: " + (d.get("_id")).getClass());
		// 	//System.out.println(d.toString());
		// }

		//testRepo.getDocumentById();
		//testRepo.getDocumentsByRegex();
		testRepo.getDocumentByComparison();
	}

}

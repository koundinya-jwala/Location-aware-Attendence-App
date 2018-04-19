/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import com.mongodb.client.*; 
import com.mongodb.*; 
import org.bson.*;
import org.bson.conversions.Bson;
import java.util.*;

public class Database { 
    
    MongoClient mongo;
    MongoCredential credential;
    MongoDatabase database;
    
    public Database()
    {
        mongo = new MongoClient( "localhost" , 27017 ); 
  
        credential = MongoCredential.createCredential("sampleUser", "myDb","password".toCharArray()); 
        System.out.println("Connected to the database successfully");   
        database = mongo.getDatabase("Project");
    }
    
    public String  checkUsername(String username,String password)
    {
        MongoCollection<Document> userCollection=database.getCollection("User");
        BasicDBObject where=BasicDBObject.parse("{\"username\":\""+username+"\"}");
        FindIterable<Document> itr=userCollection.find(where);
        
        if(password.equals(itr.first().get("password")))
        {
            String roll=(String)itr.first().get("rollno");
            String role=(String)itr.first().get("role");
            String result=role+"@"+roll;
            if(role.equals("0"))
            {
                MongoCollection<Document> courseCollection=database.getCollection("Course");
                where=BasicDBObject.parse("{\"facultyroll\":\""+roll+"\"}");
                FindIterable<Document> course_itr=courseCollection.find(where);
                String coursenum=(String)course_itr.first().get("coursenumber");
                result=result+"@"+coursenum+":";
                List<Document> studentlist=(List)course_itr.first().get("studentlist"); 
                System.out.println();
                
                
                for(Document doc:studentlist)
                {
                    String sturoll=(String)doc.get("rollno");
                    String name=(String)doc.get("name");
                    result=result+sturoll+"#"+name+";";
                }
                return result;
            }
            else 
                return role+"@"+roll;
        }
        else
            return null;
        
    }
    
    public void giveAttendence(String coursenum, String studentroll)
    {
        MongoCollection<Document> courseCollection=database.getCollection("Course");
        BasicDBObject where=where=BasicDBObject.parse("{\"coursenumber\":\""+coursenum+"\"}");
        FindIterable<Document> itr=courseCollection.find(where);
        
    }

    
    public static void main(String[] args) {
        System.out.println(new Database().checkUsername("Professor1", "123"));
    }
   
   public  void main1( String args[] ) {  
      
      // Creating a Mongo client 
      
      
      
      BasicDBObject doc = new BasicDBObject();
      FindIterable<Document> itr=null;
      for(Document docs:itr)
      {
          System.out.println(((Document)docs.get("studentlist")));
      }

       //System.out.println(collection.find());
      
      //System.out.println("Credentials ::"+ credential);     
   }
   
   
}

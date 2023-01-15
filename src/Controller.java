import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.*;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Controller {
	
	//Funcion para logearte en la aplicacion y poder utilizar las funciones
	public boolean login(String json, String user, String pwd) throws NoSuchAlgorithmException {
		
		JSONObject obj = new JSONObject(json);
		
		String ip = obj.getString("ip");
		int port = obj.getInt("port");
		String db = obj.getString("db");
		String collection = obj.getString("collection");
		
		MongoClient mongoClient = new MongoClient(ip, port);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> colection = database.getCollection(collection);
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(pwd.getBytes(StandardCharsets.UTF_8));
		String encoded = Base64.getEncoder().encodeToString(hash);
				
		BasicDBObject query = new BasicDBObject();
		query.put("user", user);
		query.put("pass", encoded);
		
		if (colection.find(query).first() != null) {
			return true;
		} else {
			return false;
		}
	}
	
	//Funcion para saber cuantos elementos hay en la base de datos y mostrar la ID y el titulo de cada uno
	public JSONArray getBooks(String json) {
		JSONObject obj = new JSONObject(json);
		
		String ip = obj.getString("ip");
		int port = obj.getInt("port");
		String db = obj.getString("db");
		String collection = obj.getString("collection");
		
		MongoClient mongoClient = new MongoClient(ip, port);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> colection = database.getCollection(collection);
		
		MongoCursor<Document> cursor = colection.find().iterator();
		
		JSONArray books = new JSONArray();
		
		while (cursor.hasNext()) {
			JSONObject book = new JSONObject(cursor.next().toJson());
			books.put(book);
		}
		return books;
	}
	
	//Muestra toda la informacion del libro mediante la ID
	public JSONObject getBookInfo(String json, String id) {
		
		JSONObject obj = new JSONObject(json);
		
		String ip = obj.getString("ip");
		int port = obj.getInt("port");
		String db = obj.getString("db");
		String collection = obj.getString("collection");
		
		MongoClient mongoClient = new MongoClient(ip, port);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> colection = database.getCollection(collection);
						
		BasicDBObject query = new BasicDBObject();
		System.out.println(Integer.valueOf(id));
		query.put("Id", Integer.valueOf(id));
		
		Document cursor = colection.find(query).first();
		
		JSONObject book = new JSONObject(cursor.toJson());
		
		return book;
		
	}
	
	public void createBooks(String json, String id, String titulo, String autor, int anyo_nacimiento, int anyo_publicacion, String editorial, int no_paginas, String thumbnail ) {
		
		JSONObject obj = new JSONObject(json);
		
		String ip = obj.getString("ip");
		int port = obj.getInt("port");
		String db = obj.getString("db");
		String collection = obj.getString("collection");
		
		MongoClient mongoClient = new MongoClient(ip, port);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> colection = database.getCollection(collection);
						
		BasicDBObject query = new BasicDBObject();
		System.out.println(Integer.valueOf(id));
		
		Document doc = new Document();
		doc.append("Id", Integer.valueOf(id) );
		doc.append("Autor", autor);
		doc.append("Anyo Nacimiento", Integer.valueOf(anyo_nacimiento));
		doc.append("Anyo Publicacion", Integer.valueOf(anyo_publicacion));
		doc.append("Editorial", editorial);
		doc.append("Numero Paginas", Integer.valueOf(no_paginas));
		doc.append("Thumbnail", thumbnail);
		colection.insertOne(doc);
		
	}
	
public void updateBooks(String json, String id, String titulo, String autor, int anyo_nacimiento, int anyo_publicacion, String editorial, int no_paginas, String thumbnail ) {
		
		JSONObject obj = new JSONObject(json);
		
		String ip = obj.getString("ip");
		int port = obj.getInt("port");
		String db = obj.getString("db");
		String collection = obj.getString("collection");
		
		MongoClient mongoClient = new MongoClient(ip, port);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> colection = database.getCollection(collection);
						
		BasicDBObject query = new BasicDBObject();
		System.out.println(Integer.valueOf(id));
		
		Document doc = new Document();
		doc.append("Id", Integer.valueOf(id) );
		doc.append("Autor", autor);
		doc.append("Anyo Nacimiento", Integer.valueOf(anyo_nacimiento));
		doc.append("Anyo Publicacion", Integer.valueOf(anyo_publicacion));
		doc.append("Editorial", editorial);
		doc.append("Numero Paginas", Integer.valueOf(no_paginas));
		doc.append("Thumbnail", thumbnail);
		colection.insertOne(doc);
		
	}
	
	//Funcion para borrar un libro mediante la ID
	public void removeBook(String json, String id) {
		
		JSONObject obj = new JSONObject(json);
		
		String ip = obj.getString("ip");
		int port = obj.getInt("port");
		String db = obj.getString("db");
		String collection = obj.getString("collection");
		
		MongoClient mongoClient = new MongoClient(ip, port);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> colection = database.getCollection(collection);
						
		BasicDBObject query = new BasicDBObject();
		System.out.println(Integer.valueOf(id));
		query.put("Id", Integer.valueOf(id));
		
		colection.deleteOne(query);
		
	}
	
}

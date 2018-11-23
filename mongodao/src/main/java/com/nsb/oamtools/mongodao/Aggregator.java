package com.nsb.oamtools.mongodao;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Aggregator {
	
	private MongoClient client = ClientManager.manager.getClient();
	private MongoDatabase db = client.getDatabase("controller_1");
	private MongoCollection<Document> coll = db.getCollection("ne");
	
	public List<Document> aggregate(List<Document> pipeline) {
		List<Document> ret = new LinkedList<Document>();
		coll.aggregate(pipeline).into(ret);
		return ret;
	}

}

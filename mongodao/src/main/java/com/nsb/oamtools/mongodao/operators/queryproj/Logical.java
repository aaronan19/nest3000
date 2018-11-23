package com.nsb.oamtools.mongodao.operators.queryproj;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;

public class Logical {
	
	public static Document not(String fieldName, Document expression) {
		return new Document(fieldName, new Document("$not", expression));
	}
	
	public static Document or(List<Document> expressions) {
		return new Document("$or", expressions);
	}
	
	public static Document nor(List<Document> expressions) {
		return new Document("$nor", expressions);
	}
	
	public static Document and(List<Document> expressions) {
		return new Document("$and", expressions);
	}
	
	public static Document mergeAnd(List<Document> expressions) {
		Document ret = new Document();
		String key = null;
		for(Document expression : expressions) {
			for(String docKey : expression.keySet()) {
				if(key == null) key = docKey;
				if(!docKey.equals(key)) {
					throw new IllegalStateException("All documents must have same key!");
				}
				if(!(expression.get(docKey) instanceof Document)) {
					throw new IllegalStateException("The value to " + docKey + "  must be a document!");
				}
				Document innerDoc = (Document) expression.get(docKey);
				for(String innerKey : innerDoc.keySet()) {
					ret.put(innerKey, innerDoc.get(innerKey));
				}
			}
		}
		return new Document(key, ret);
	}
	
	public static Document mergeAnd(Document...expressions) {
		return mergeAnd(Arrays.asList(expressions));
	}

}
package com.nsb.oamtools.mongodao.operators.queryproj;

import org.bson.Document;

public class Evaluation {
	
	public static Document where(String jsExp) {
		return new Document("$where", jsExp);
	}
	
	public static Document regex(String fieldName, String regexExp) {
		return new Document(fieldName, new Document("$regex", regexExp));
	}
	
	public static Document mod(String fieldName, long divisor, long remainder) {
		return new Document(fieldName, new Document("$mod", new long[]{divisor, remainder}));
	}
	
	public static Document text() {
		//TODO
		return null;
	}
	
	public static Document expr(Object expression) {
		return new Document("$expr", expression);
	}
	
	public static Document jsonSchema() {
		//TODO
		return null;
	}

}

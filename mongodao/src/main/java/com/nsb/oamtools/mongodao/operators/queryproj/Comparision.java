package com.nsb.oamtools.mongodao.operators.queryproj;

import org.bson.Document;

public class Comparision {
	
	public static <T> Document eq(String fieldName, T val) {
		return new Document(fieldName, new Document("$eq", val));
	}
	
	public static <T> Document ne(String fieldName, T val) {
		return new Document(fieldName, new Document("$ne", val));
	}
	
	public static <T> Document findLessers(String fieldName, T val) {
		return new Document(fieldName, new Document("$lt", val));
	}
	
	public static <T> Document findLesserOrEquals(String fieldName, T val) {
		return new Document(fieldName, new Document("$lte", val));
	}
	
	public static <T> Document findGraters(String fieldName, T val) {
		return new Document(fieldName, new Document("$gt", val));
	}
	
	public static <T> Document findGraterOrEquals(String fieldName, T val) {
		return new Document(fieldName, new Document("$gte", val));
	}
	
	public static <T> Document in(String fieldName, Iterable<T> set) {
		return new Document(fieldName, new Document("$in", set));
	}
	
	public static <T> Document nin(String fieldName, Iterable<T> set) {
		return new Document(fieldName, new Document("$nin", set));
	}

}

package com.allen.nest.mongodao.operators.queryproj;

import java.util.Arrays;

import org.bson.Document;

public class Array {
	
	public static <T> Document all(String fieldName, Iterable<T> set) {
		return new Document(fieldName, new Document("$all", set));
	}
	
	public static Document all(String fieldName, Object...set) {
		return all(fieldName, Arrays.asList(set));
	}
	
	public static Document elemMatch(String arrayName, Document conditions) {
		return new Document(arrayName, new Document("$elemMatch", conditions));
	}
	
	public static Document size(String arrayName, int size) {
		return new Document(arrayName, new Document("$size", size));
	}

}

package com.allen.nest.mongodao.operators.queryproj;

import java.util.Arrays;
import java.util.List;
import org.bson.Document;

public class Element {
	
	public static Document exists(String fieldName, boolean ifExists) {
		return new Document(fieldName, new Document("$exists", ifExists));
	}
	
	public static Document type(String fieldName, String alia) {
		return new Document(fieldName, new Document("$type", alia));
	}
	
	public static Document type(String fieldName, String...alias) {
		return type(fieldName, Arrays.asList(alias));
	}
	
	public static Document type(String fieldName, int alia) {
		return new Document(fieldName, new Document("$type", alia));
	}
	
	public static Document type(String fieldName, int...alias) {
		return type(fieldName, Arrays.asList(alias));
	}
	
	public static Document type(String fieldName, List<? extends Object> alias) {
		for(Object alia: alias) {
			if(!(alia instanceof String) || !(alia instanceof Integer)) {
				throw new IllegalStateException("Bson type value must be string or integer.");
			}
		}
		return new Document(fieldName, new Document("$type", alias));
	}

}

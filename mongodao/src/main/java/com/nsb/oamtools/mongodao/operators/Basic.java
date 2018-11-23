package com.nsb.oamtools.mongodao.operators;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bson.Document;

public class Basic {
	
	public static Document empty() {
		return new Document();
	}
	
	public static <T> Document kv(String fieldName, T value) {
		return new Document(fieldName, value);
	}
	
	public static Document kk(String fieldName, String referencedField) {
		return new Document(fieldName, "$" + referencedField);
	}
	
	public static Document kd(String fieldName, Document expression) {
		return new Document(fieldName, expression);
	}
	
	public static Document ok(String operator, String referencedField) {
		return new Document("$" + operator, "$" + referencedField);
	}
	
	public static Document merge(List<Document> documents) {
		Document ret = new Document();
		for(Document doc : documents) {
			for(String key : doc.keySet()) {
				ret.put(key, doc.get(key));
			}
		}
		return ret;
	}
	
	public static Document merge(Document...documents) {
		return merge(Arrays.asList(documents));
	}
	
	public static Document merge(Map<String, ? extends Object> map) {
		Document ret = new Document();
		map.entrySet().stream().forEach(entry -> {ret.put(entry.getKey(), entry.getValue());});
		return ret;
	}
}

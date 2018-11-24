package com.allen.nest.mongodao.operators.update;

import static com.allen.nest.mongodao.operators.aggregation.Operator.*;
import static com.allen.nest.mongodao.operators.Basic.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bson.Document;

public class Field {
	
	public static Document max(String resultName, String fieldName) {
		return new Document(resultName, new Document("$max", fieldName));
	}
	
	public static Document max(String resultName, List<? extends Object> fieldNames) {
		return new Document(resultName, new Document("$max", multiply(fieldNames)));
	}
	
	public static Document min(String resultName, String fieldName) {
		return new Document(resultName, new Document("$min", fieldName));
	}
	
	public static Document min(String resultName, List<? extends Object> fieldNames) {
		return new Document(resultName, new Document("$min", multiply(fieldNames)));
	}
	
	public static Document currentDate(Document expression) {
		return new Document("$currentDate", expression);
	}
	
	public static Document inc(Map<String, Integer> fieldAmont) {
		return new Document("$inc", merge(fieldAmont));
	}
	
	public static Document inc(String fieldName, Integer amount) {
		return new Document("$inc", new Document(fieldName, amount));
	}
	
	public static Document mul(Map<String, Object> fieldAmont) {
		return new Document("$mul", merge(fieldAmont));
	}
	
	public static Document mul(String fieldName, Object amount) {
		return new Document("$mul", new Document(fieldName, amount));
	}
	
	public static Document mul(String fieldName, String dbFunc) {
		return new Document("$mul", new Document(fieldName, dbFunc));
	}
	
	public static Document rename(Map<String, String> fieldNewName) {
		return new Document("$rename", merge(fieldNewName));
	}
	
	public static Document rename(String fieldName, String newName) {
		return new Document("$rename", new Document(fieldName, newName));
	}
	
	public static Document set(Map<String, Object> fieldAmont) {
		return new Document("$set", merge(fieldAmont));
	}
	
	public static Document set(String fieldName, Object amount) {
		return new Document("$set", new Document(fieldName, amount));
	}
	
	public static Document set(String fieldName, String dbFunc) {
		return new Document("$set", new Document(fieldName, dbFunc));
	}
	
	public static Document setOnInsert(Map<String, Object> fieldValue) {
		return new Document("$setOnInsert", merge(fieldValue));
	}
	
	public static Document setOnInsert(String fieldName, String value) {
		return new Document("$setOnInsert", new Document(fieldName, value));
	}
	
	public static Document unset(List<String> fields) {
		Document unset = new Document();
		fields.stream().forEach(filed -> {unset.put(filed, "");});
		return new Document("$unset", unset);
	}
	
	public static Document unset(String...fields) {
		Document unset = new Document();
		Arrays.stream(fields).forEach(filed -> {unset.put(filed, "");});
		return new Document("$unset", unset);
	}

}

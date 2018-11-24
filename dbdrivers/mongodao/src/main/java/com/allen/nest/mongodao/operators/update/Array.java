package com.allen.nest.mongodao.operators.update;

import static com.allen.nest.mongodao.operators.Basic.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bson.Document;

public class Array {
	
	public static Document $() {
		//TODO
		return null;
	}
	
	public static <T> Document addToSet(Map<String, T> fieldValue) {
		return new Document("$addToSet", merge(fieldValue));
	}
	
	public static <T> Document addToSet(String fieldName, T value) {
		return new Document("$addToSet", new Document(fieldName, value));
	}
	
	public static Document $array() {
		//TODO
		return null;
	}
	
	public static Document $identifier() {
		//TODO
		return null;
	}
	
	public static Document pop(Map<String, Integer> fieldValue) {
		return new Document("$pop", merge(fieldValue));
	}
	
	public static Document pop(String fieldName, Integer value) {
		return new Document("$pop", new Document(fieldName, value));
	}
	
	public static <T> Document pull(Map<String, T> fieldValue) {
		return new Document("$pull", merge(fieldValue));
	}
	
	public static <T> Document pull(String fieldName, T value) {
		return new Document("$pull", new Document(fieldName, value));
	}
	
	public static <T> Document pullAll(Map<String, List<T>> fieldValue) {
		return new Document("$pullAll", merge(fieldValue));
	}
	
	public static <T> Document pullAll(String fieldName, List<T> value) {
		return new Document("$pullAll", new Document(fieldName, value));
	}
	
	public static <T> Document push(Map<String, T> fieldValue) {
		return new Document("$push", merge(fieldValue));
	}
	
	public static <T> Document push(String fieldName, T value) {
		return new Document("$push", new Document(fieldName, value));
	}
	
	/*
	 * modifiers
	 */
	
	public static <T> Document each(List<T> values) {
		return new Document("$each", values);
	}
	
	public static <T> Document each(Object...values) {
		return each(Arrays.asList(values));
	}
	
	public static Document position(int position) {
		return new Document("$position", position);
	}
	
	public static Document slice(String fieldName, int keepCount) {
		return new Document("$slice", keepCount);
	}
	
	public static <T> Document sort(T direction) {
		return new Document("$sort", direction);
	}

}

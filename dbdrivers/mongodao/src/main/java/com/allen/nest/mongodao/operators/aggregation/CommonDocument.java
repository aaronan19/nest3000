package com.allen.nest.mongodao.operators.aggregation;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;

public class CommonDocument {
	
	static Document one(String operator, Object expression) {
		return new Document("$" + operator, expression);
	}
	
	static Document list(String operator, List<? extends Object> list) {
		return new Document("$" + operator, list);
	}
	
	static Document nInList(String operator, Object...expressions) {
		return list(operator, Arrays.asList(expressions));
	}
	
	static Document time(String operator, Object dateExpr, String timezone) {
		Document time = new Document("date", dateExpr);
		if(timezone != null) {
			time.put("timezone", timezone);
		}
		return new Document("$operator", time);
	}

}

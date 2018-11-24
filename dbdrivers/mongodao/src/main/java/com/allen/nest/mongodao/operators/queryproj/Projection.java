package com.allen.nest.mongodao.operators.queryproj;

import org.bson.Document;

public class Projection {
	
	public static Document $() {
		//TODO
		return null;
	}
	
	public static Document elemMatch(String arrayName, Document conditions) {
		return new Document(arrayName, new Document("$elemMatch", conditions));
	}
	
	public static Document meta(String metaDataKeyword) {
		return new Document("$meta", metaDataKeyword);
	}
	
	public static Document slice(String fieldName, int keepCount) {
		return new Document(fieldName, new Document("$slice", keepCount));
	}

}

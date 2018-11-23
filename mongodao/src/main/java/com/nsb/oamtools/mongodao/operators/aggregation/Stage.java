package com.nsb.oamtools.mongodao.operators.aggregation;

import static com.nsb.oamtools.mongodao.operators.Basic.*;
import static com.nsb.oamtools.mongodao.operators.aggregation.CommonDocument.*;

import java.util.List;
import java.util.Map;

import org.bson.Document;

public class Stage {
	
	public static Document addFields(Map<String, ? extends Object> fields) {
		return one("addFields", merge(fields));
	}
	
	public static Document addFields(String fieldName, Object expression) {
		return one("addFields", new Document(fieldName, expression));
	}
	
	public static Document bucket(Object groupBy, List<? extends Object> boundaries, Object defaultVal, Document output) {
		Document bucket = new Document();
		if(groupBy != null) {
			bucket.put("groupBy", groupBy);
		}
		if(boundaries != null) {
			bucket.put("boundaries", boundaries);
		}
		if(defaultVal != null) {
			bucket.put("default", defaultVal);
		}
		if(output != null) {
			bucket.put("output", output);
		}
		return one("bucket", bucket);
	}
	
	public static Document bucketAuto(Object groupBy, int buckets, Document output, String granularity) {
		Document bucketAuto = new Document();
		if(groupBy != null) {
			bucketAuto.put("groupBy", groupBy);
		}
		bucketAuto.put("buckets", buckets);
		if(output != null) {
			bucketAuto.put("output", output);
		}
		if(granularity != null) {
			bucketAuto.put("granularity", granularity);
		}
		return one("bucketAuto", bucketAuto);
	}
	
	public static Document collStats(Document latencyStats, Document storageStats, Document count) {
		Document collStats = new Document();
		if(latencyStats != null) {
			collStats.put("latencyStats", latencyStats);
		}
		if(storageStats != null) {
			collStats.put("storageStats", storageStats);
		}
		if(count != null) {
			collStats.put("count", count);
		}
		return one("collStats", collStats);
	}
	
	public static Document count(String fieldName) {
		return one("count", fieldName);
	}
	
	public static Document currentOp(boolean allUsers, boolean idleConnections) {
		return one("currentOp", 
				new Document().append("allUsers", allUsers).append("idleConnections", idleConnections));
	}
	
	public static Document facet(Map<String, List<Document>> fieldsAndPipelines) {
		return one("facet", merge(fieldsAndPipelines));
	}
	
	public static Document geoNear() {
		//TODO
		return null;
	}
	
	public static Document graphLookup() {
		//TODO
		return null;
	}
	
	public static Document group(Map<String, ? extends Object> fieldsMap) {
		return one("group", merge(fieldsMap));
	}
	
	public static Document group(String field, Object expression) {
		return one("group", new Document(field, expression));
	}
	
	public static Document group(Document expression) {
		return one("group", expression);
	}
	
	public static Document indexStats() {
		return one("indexStats", empty());
	}
	
	public static Document limit(int keepCount) {
		return one("limit", keepCount);
	}
	
	public static Document listLocalSessions() {
		//TODO
		return null;
	}
	
	public static Document listSessions() {
		//TODO
		return null;
	}
	
	public static Document lookUp(String from, String localField, String foreignField, String as) {
		return one("lookup", new Document()
							 .append("from", from)
							 .append("localField", localField)
							 .append("foreignField", foreignField)
							 .append("as", as));
	}
	
	public static Document lookUp(String from, String let, List<Document> pipeline, String as) {
		return one("lookup", new Document()
							 .append("from", from)
							 .append("let", let)
							 .append("pipeline", pipeline)
							 .append("as", as));
	}
	
	public static Document match(Document query) {
		return one("match", query);
	}
	
	public static Document out(String collectionName) {
		return one("out", collectionName);
	}
	
	public static Document project(Document expression) {
		return one("project", expression);
	}
	
	public static Document redact() {
		//TODO
		return null;
	}
	
	public static Document replaceRoot(Object newRootDocument) {
		return one("replaceRoot", new Document("newRoot", newRootDocument));
	}
	
	public static Document sample(int size) {
		return one("sample", new Document("size", size));
	}
	
	public static Document skip(int skipCount) {
		return one("skip", skipCount);
	}
	
	public static Document sort(Map<String, ? extends Object> fieldNames) {
		return one("sort", merge(fieldNames));
	}
	
	public static Document sortByCount(Object expression) {
		return one("sortByCount", expression);
	}
	
	public static Document unWind(String fieldName) {
		return one("unwind", "$" + fieldName);
	}
	
	public static Document unWind(String fieldName, String includeArrayIndex, boolean preserveNullAndEmptyArrays) {
		Document doc = new Document();
		doc.put("path", "$" + fieldName);
		if(includeArrayIndex != null) {
			doc.put("includeArrayIndex", includeArrayIndex);
		}
		if(preserveNullAndEmptyArrays == true) {
			doc.put("preserveNullAndEmptyArrays", true);
		}
		return one("unwind", doc);
	}

}

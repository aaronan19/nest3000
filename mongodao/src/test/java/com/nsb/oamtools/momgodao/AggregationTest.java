package com.nsb.oamtools.momgodao;

import static com.nsb.oamtools.mongodao.operators.Basic.*;
import static com.nsb.oamtools.mongodao.operators.queryproj.Comparision.*;
import static com.nsb.oamtools.mongodao.operators.queryproj.Array.*;
import static com.nsb.oamtools.mongodao.operators.aggregation.Stage.*;
import static com.nsb.oamtools.mongodao.operators.aggregation.Operator.*;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.nsb.oamtools.mongodao.ClientManager;

public class AggregationTest {
	
	private static MongoClient localClient = ClientManager.manager.getClient();
	private static MongoDatabase localController = localClient.getDatabase("tools_bak");
	private static MongoCollection<Document> localPg = localController.getCollection("pg");
	private static MongoCollection<Document> localNe = localController.getCollection("ne");
	private static MongoCollection<Document> localDomain = localController.getCollection("domain");
	private static MongoCollection<Document> localConnection = localController.getCollection("connection");
	
	public static List<Document> getApsStatusListByNeIds(List<String> neIds) {
		List<Document> result = new LinkedList<Document>();
		List<Document> pipeline = new LinkedList<Document>();
		pipeline.add(match(in("neId", neIds)));
		pipeline.add(unWind("tpIds", "tpIndex", false));
		pipeline.add(addFields("tpStatusInfo", arrayElemAt("$switchData.tpStatus", "$tpIndex")));
		pipeline.add(lookUp("tp", "tpIds", "_id", "tpInfo"));
		pipeline.add(unWind("tpInfo"));
		pipeline.add(match(ne("tpInfo", null)));
		List<String> concatStrings = new LinkedList<String>();
		concatStrings.add("$tpStatusInfo.txStatus");
		concatStrings.add("|");
		concatStrings.add("$tpStatusInfo.rxStatus");
		pipeline.add(project(empty()
				.append("neId", 1)
				.append("pgName", "$userLabel")
				.append("tpId", "$tpIds")
				.append("tpName", "$tpInfo.userLabel")
				.append("tpNativeName", "$tpInfo.nativeName")
				.append("threshold", "$neInfo.params.opsSwtichThreshold")
				.append("switchStatus", "$switchData.switchStatus")
				.append("mode", "$type")
				.append("role", "$tpIndex")
				.append("state", concat(concatStrings))));
		pipeline.add(group(empty()
				.append("_id", "$_id")
				.append("tps", push("$$ROOT"))
				.append("pgName", addToSet("$pgName"))
				.append("neId", addToSet("$neId"))));
		pipeline.add(project(empty()
				.append("pgId", "$_id")
				.append("tps", 1)
				.append("pgName", 1)
				.append("neId", 1)
				.append("_id", 0)));
		pipeline.add(unWind("neId"));
		pipeline.add(unWind("pgName"));
		pipeline.add(group(empty()
				.append("_id", "$neId")
				.append("pgs", push("$$ROOT"))));
		pipeline.add(lookUp("ne", "_id", "_id", "neInfo"));
		pipeline.add(unWind("neInfo"));
		pipeline.add(project(empty()
				.append("neId", "$_id")
				.append("pgs", 1)
				.append("neName", "$neInfo.userLabel")
				.append("neNativeName", "$neInfo.nativeName")
				.append("_id", 0)));
		for(Document doc : pipeline) {
			System.out.println(doc.toJson());
		}
		for(Document doc : localPg.aggregate(pipeline)) {
			result.add(doc);
		}
		return result;
	}
	
	private static MongoClient client140 = ClientManager.manager.getClient("135.251.103.140", 27017, "enms", "enms");
	private static MongoDatabase controller140 = client140.getDatabase("controller_1");
	private static MongoCollection<Document> connection140 = controller140.getCollection("connection");
	
	public static List<Document> getFreeBandwidth(String managedObjectId) {
		List<Document> result = getOmsFreeResource(managedObjectId);
		return result;
	}
	
	private static List<Document> getOmsFreeResource(String omsId) {
		List<Document> result = new LinkedList<Document>();
		List<Document> pipeline = new LinkedList<Document>();
		pipeline.add(match(eq("_id", omsId)));
		pipeline.add(project(empty()
				.append("freeResources", 1)
				.append("aendPoints", 1)
				.append("zendPoints", 1)));
		pipeline.add(addFields("OCHFreeResources", split("$freeResources.OCH", ",")));
		pipeline.add(project(empty().append("freeResources", 0)));
		pipeline.add(addFields("frequencyCounts", size("$OCHFreeResources")));
		pipeline.add(addFields("connectionId", literal(omsId)));
		Document temp = new Document();
		for(Document doc : pipeline) {
			System.out.println(doc.toJson());
		}
		for(Document doc : localConnection.aggregate(pipeline)) {
			System.out.println(doc.toJson());
			temp = doc;
		}
		
		temp.put("freeResources", temp.get("OCHFreeResources"));
		temp.remove("OCHFreeResources");
		temp.remove("_id");
		@SuppressWarnings("unchecked")
		List<String> ochFreeResources = (List<String>) temp.get("freeResources");
		int freeFrequencyCounts = 0;
		int occupiedFrequencyCounts = 0;
		for(String frequency : ochFreeResources) {
			if(frequency.endsWith("=1")) freeFrequencyCounts++;
			else if(frequency.endsWith("=0")) occupiedFrequencyCounts++;
		}
		temp.put("freeFrequencyCounts", freeFrequencyCounts);
		temp.put("occupiedFrequencyCounts", occupiedFrequencyCounts);
		double usedFrequencyRatio = (double)occupiedFrequencyCounts / (double)ochFreeResources.size() * 100;
		temp.put("occupiedFrequencyRatio", usedFrequencyRatio + "%");
		result.add(temp);
		System.out.println(temp.toJson());
		return result;
	}
	
	private static List<Document> getOduResourceOnOch(String ochId) {
		List<Document> pipeline = new LinkedList<Document>();
		pipeline.add(match(eq("_id", ochId)));
		pipeline.add(project(empty()
				.append("_id", 0)
				.append("aendPoints", 1)
				.append("zendPoints", 1)
				.append("clientList", 1)));
		pipeline.add(unWind("clientList"));
		pipeline.add(lookUp("connection", "clientList", "_id", "otu"));
		pipeline.add(unWind("otu"));
		pipeline.add(unWind("otu.clientList"));
		pipeline.add(lookUp("connection", "otu.clientList", "_id", "oduk"));
		pipeline.add(unWind("oduk"));
		pipeline.add(unWind("oduk.clientList"));
		pipeline.add(lookUp("connection", "oduk.clientList", "_id", "oduj"));
		pipeline.add(unWind("oduj"));
		pipeline.add(addFields("omsId", literal(ochId)));
		pipeline.add(project(empty()
				.append("aendPoints", 1)
				.append("zendPoints", 1)
				.append("oduk.layerRates", 1)
				.append("oduj.layerRates", 1)
				.append("oduj.freeResources", 1)
				.append("omsId", 1)));
		for(Document doc : pipeline) {
			System.out.println(doc.toJson());
		}
		for(Document doc : connection140.aggregate(pipeline)) {
			System.out.println(doc.toJson());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private static List<Document> getSpecMoTypeClientConnFreeResource(String parentConnId, String moType) {
		List<Document> result = new LinkedList<Document>();
		List<String> queryIds = new LinkedList<String>();
		queryIds.add(parentConnId);
		String queryMoType = null;
		while(!moType.equals(queryMoType)) {
			result.clear();
			Document query = in("_id", queryIds);
			Document filter = merge(kv("moType", 1), kv("freeResources", 1), kv("clientList", 1));
			localConnection.find(query).projection(filter).into(result);
			if(result.isEmpty()) break;
			queryMoType = result.get(0).getString("moType");
			queryIds.clear();
			for(Document doc : result) {
				queryIds.addAll(doc.get("clientList", List.class));
			}
		}
		return result;
	}
	
	private static List<Document> getOduResourceOnOms(String omsId) {
		List<Document> result = new LinkedList<Document>();
		List<Document> pipeline = new LinkedList<Document>();
		pipeline.add(match(eq("_id", omsId)));
		pipeline.add(project(empty()
				.append("_id", 0)
				.append("aendPoints", 1)
				.append("zendPoints", 1)
				.append("clientList", 1)));
		pipeline.add(unWind("clientList"));
		pipeline.add(lookUp("connection", "clientList", "_id", "och"));
		pipeline.add(unWind("och"));
		pipeline.add(unWind("och.clientList"));
		pipeline.add(lookUp("connection", "clientList", "_id", "otu"));
		pipeline.add(unWind("otu"));
		pipeline.add(unWind("otu.clientList"));
		pipeline.add(lookUp("connection", "otu.clientList", "_id", "oduk"));
		pipeline.add(unWind("oduk"));
		pipeline.add(unWind("oduk.clientList"));
		pipeline.add(lookUp("connection", "oduk.clientList", "_id", "oduj"));
		pipeline.add(unWind("oduj"));
		pipeline.add(addFields("omsId", literal(omsId)));
		pipeline.add(project(empty()
				.append("aendPoints", 1)
				.append("zendPoints", 1)
				.append("oduk.layerRates", 1)
				.append("oduj.layerRates", 1)
				.append("oduj.freeResources", 1)
				.append("omsId", 1)));
		pipeline.add(out("zzz"));
		for(Document doc : pipeline) {
			System.out.println(doc.toJson());
		}
		for(Document doc : localConnection.aggregate(pipeline)) {
			System.out.println(doc.toJson());
		}
		return null;
	}
	
	public static List<String> getSpecConnectionIdsForNe(String neId, String moType) {
		List<String> result = new LinkedList<String>();
		List<Document> pipeline = new LinkedList<Document>();
		pipeline.add(match(and(eq("moType", moType), or(eq("aendPoints.neId", neId), eq("zendPoints.neId", neId)))));
		pipeline.add(project(empty().append("_id", 1)));
		for(Document doc : pipeline) {
			System.out.println(doc.toJson());
		}
		for(Document doc : localConnection.aggregate(pipeline)) {
			System.out.println(doc.toJson());
		}
		return result;
	}
	
	public static List<String> getNeIdsForDomain(String domainId) {
		List<String> result = new LinkedList<String>();
		for(Document doc : localNe.find(all("domains", domainId))) {
			result.add(doc.getString("_id"));
		}
		return result;
	}
	
	public static List<String> getAllConnectionIds() {
		List<String> result = new LinkedList<String>();
		List<Document> pipeline = new LinkedList<Document>();
		pipeline.add(project(empty().append("_id", 1)));
		for(Document doc : localConnection.aggregate(pipeline)) {
			System.out.println(doc.toJson());
		}
		return result;
	}
	
	public static void main(String[] args) {
//		List<String> ids = new LinkedList<String>();
//		ids.add("5afce64c9fd683157c48bee7");
//		System.out.println(getApsStatusListByNeIds(ids));
//		getFreeBandwidth("5b220ab19fd683128cdcbd88_419507456_SFD44-25-1-OMD;/oms=1_5b220acb9fd683128cdcbd89_17039616_WR8-88AF-1-4-SIG;/oms=1");
		getSpecMoTypeClientConnFreeResource("5b188def9fd6830ae4875267_17301760_260SCX2-1-8-L1;/frequency=/tunable-number=1_5b188e169fd6830ae4875269_17301760_260SCX2-1-8-L1;/frequency=/tunable-number=1", "WDM_C_ODU");
//		getOduResourceOnOms("5b1e281a9fd683239805f6d7_419507456_SFD44-25-1-OMD;/oms=1_5b1e282b9fd683239805f6d8_17039616_WR8-88AF-1-4-SIG;/oms=1");
//		getAllConnectionIds();
//		getSpecConnectionIdsForNe("5b220ab19fd683128cdcbd88", "WDM_OMS");
//		getNeIdsForDomain("5b2209719fd68311887dde68");
	}

}

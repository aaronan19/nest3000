package com.nsb.oamtools.mongodao.operators.aggregation;

import static com.nsb.oamtools.mongodao.operators.aggregation.Operator.*;
import static com.nsb.oamtools.mongodao.operators.aggregation.Stage.*;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.junit.Test;

public class StageTest {

	@Test
	public void testAddFieldsMapOfStringObject() {
		Map<String, Document> fields = new HashMap<String, Document>();
		fields.put("totalHomework", sum("$homework"));
		fields.put("totalQuiz", sum("$quiz"));
		System.out.println(addFields("totalHomework", sum("$homework")).toJson());
		System.out.println(addFields(fields).toJson());
	}

//	@Test
//	public void testAddFieldsStringObject() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testBucket() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testBucketAuto() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCollStats() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCount() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCurrentOp() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFacet() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGeoNear() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGraphLookup() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGroupMapOfStringObject() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGroupDocument() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIndexStats() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testLimit() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testListLocalSessions() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testListSessions() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testLookUpStringStringStringString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testLookUpStringStringListOfDocumentString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testMatch() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testOut() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testProject() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRedact() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testReplaceRoot() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSample() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSkip() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSort() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSortByCount() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUnWindString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUnWindStringStringBoolean() {
//		fail("Not yet implemented");
//	}

}

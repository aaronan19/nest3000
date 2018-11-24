package com.allen.nest.mongodao.operators.aggregation;

import static com.allen.nest.mongodao.operators.aggregation.CommonDocument.*;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

public class Operator {
	
	public static Object differExpression(Object expression) {
		if(expression instanceof String) {
			SystemVariable sysVar = SystemVariable.getSysVarByString((String)expression);
			if(sysVar != null) {
				expression += "$$";
			} else {
				expression += "$";
			}
		}
		return expression;
	}
	
	public static Document abs(Object number) {
		return one("abs", number);
	}
	
	public static Document add(List<? extends Object> expressions) {
		return list("add", expressions);
	}
	
	public static Document add(Object... expressions) {
		return nInList("add", expressions);
	}
	
	public static Document addToSet(Object variable) {
		return one("addToSet", variable);
	}
	
	public static Document allElementsTrue(List<? extends Object> expressions) {
		return list("allElementsTrue", expressions);
	}
	
	public static Document and(List<? extends Object> expressions) {
		return list("and", expressions);
	}
	
	public static Document and(Object...expressions) {
		return nInList("and", expressions);
	}
	
	public static Document anyElementTrue(List<? extends Object> expressions) {
		return list("anyElementTrue", expressions);
	}
	
	public static Document arrayElemAt(Object arrayName, Object idx) {
		return nInList("arrayElemAt", arrayName, idx);
	}
	
	public static Document arrayToObject(Object array) {
		return one("arrayToObject", array);
	}
	
	public static Document avg(Object expression) {
		return one("avg", expression);
	}
	
	public static Document avg(List<? extends Object> expressions) {
		return list("avg", expressions);
	}
	
	public static Document ceil(Object number) {
		return one("ceil", number);
	}
	
	public static Document cmp(List<? extends Object> expressions) {
		return list("cmp", expressions);
	}
	
	public static Document concat(List<? extends Object> strings) {
		return list("concat", strings);
	}
	
	public static Document concatArrays(List<? extends Object> arrays) {
		return list("concatArrays", arrays);
	}
	
	public static Document cond(Object ifVal, Object then, Object elseVal) {
		Document cond = new Document();
		if(ifVal != null) {
			cond.put("if", ifVal);
		}
		if(then != null) {
			cond.put("then", then);
		}
		if(elseVal != null) {
			cond.put("else", elseVal);
		}
		return new Document("$cond", cond);
	}
	
	public static Document dateFromParts(int year, Integer month, Integer day, 
			Integer hour, Integer minute, Integer second, Integer milliseconds, Object timezone) {
		Document dateFromParts = new Document();
		if(year < 0 || year > 9999) throw new IllegalStateException();
		dateFromParts.put("year", year);
		if(month != null) {
			dateFromParts.put("month", month);
		}
		if(day != null) {
			dateFromParts.put("day", day);
		}
		if(hour != null) {
			dateFromParts.put("hour", hour);
		}
		if(minute != null) {
			dateFromParts.put("minute", minute);
		}
		if(second != null) {
			dateFromParts.put("second", second);
		}
		if(milliseconds != null) {
			dateFromParts.put("milliseconds", milliseconds);
		}
		if(timezone != null) {
			dateFromParts.put("timezone", timezone);
		}
		return new Document("$dateFromParts", dateFromParts);
	}
	
	public static Document isoWeekdateFromParts(int isoWeekYear, Integer isoWeek, Integer isoDayOfWeek, 
			Integer hour, Integer minute, Integer second, Integer milliseconds, Object timezone) {
		Document dateFromParts = new Document();
		if(isoWeekYear < 0 || isoWeekYear > 9999) throw new IllegalStateException();
		dateFromParts.put("isoWeekYear", isoWeekYear);
		if(isoWeek != null) {
			dateFromParts.put("isoWeek", isoWeek);
		}
		if(isoDayOfWeek != null) {
			dateFromParts.put("isoDayOfWeek", isoDayOfWeek);
		}
		if(hour != null) {
			dateFromParts.put("hour", hour);
		}
		if(minute != null) {
			dateFromParts.put("minute", minute);
		}
		if(second != null) {
			dateFromParts.put("second", second);
		}
		if(milliseconds != null) {
			dateFromParts.put("milliseconds", milliseconds);
		}
		if(timezone != null) {
			dateFromParts.put("timezone", timezone);
		}
		return new Document("$dateFromParts", dateFromParts);
	}
	
	public static Document dateToParts(Document date, Object timezone, Boolean iso8601) {
		Document dateToParts = new Document();
		dateToParts.put("date", date);
		if(timezone != null) {
			dateToParts.put("timezone", timezone);
		}
		if(iso8601 != null) {
			dateToParts.put("iso8601", iso8601);
		}
		return new Document("$dateToParts", dateToParts);
	}
	
	public static Document dateFromString(Object date, String timezone) {
		return time("dateFromString", date, timezone);
	}
	
	public static Document dateToString(String format, Object date, Object timezone) {
		Document dateToString = new Document();
		dateToString.put("format", format);
		dateToString.put("date", date);
		if(timezone != null) {
			dateToString.put("timezone", timezone);
		}
		return new Document("$dateToString", dateToString);
	}
	
	public static Document dayOfMonth(Object dateExpression) {
		return one("dayOfMonth", dateExpression);
	}
	
	public static Document dayOfWeek(Object dateExpression) {
		return one("dayOfMonth", dateExpression);
	}
	
	public static Document dayOfYear(Object dateExpression) {
		return one("dayOfYear", dateExpression);
	}
	
	public static Document divide(Object dividend, Object divisor) {
		return nInList("divide", dividend, divisor);
	}
	
	public static Document eq(Object expression1, Object expression2) {
		return nInList("eq", expression1, expression2);
	}
	
	public static Document exp(Object exponent) {
		return one("exp", exponent);
	}
	
	public static Document filter(Object input, String as, Document cond) {
		Document filter = new Document();
		filter.put("input", input);
		if(as != null) {
			filter.put("as", as);
		}
		filter.put("cond", cond);
		return new Document("$filter", filter);
	}
	
	public static Document first(Object expression) {
		return one("first", expression);
	}
	
	public static Document floor(Object number) {
		return one("floor", number);
	}
	
	public static Document gt(Object expression1, Object expression2) {
		return nInList("gt", expression1, expression2);
	}
	
	public static Document gte(Object expression1, Object expression2) {
		return nInList("gte", expression1, expression2);
	}
	
	public static Document hour(Object dateExpression, String timezone) {
		return time("$hour", dateExpression, timezone);
	}
	
	public static Document ifNull(Object expression, Object replacementExpr) {
		return nInList("ifNull", expression, replacementExpr);
	}
	
	public static Document in(Object expression, Object arrayExpr) {
		return nInList("in", expression, arrayExpr);
	}
	
	public static Document indexOfArray(Object arrayExpr, Object searchExpr, Integer start, Integer end) {
		List<Object> expressions = new LinkedList<Object>();
		expressions.add(arrayExpr);
		expressions.add(searchExpr);
		if(start != null) {
			expressions.add(start);
		}
		if(end != null) {
			expressions.add(end);
		}
		return new Document("$indexOfArray", expressions);
	}
	
	public static Document indexOfBytes(Object stringExpr, Object substringExpr, Integer start, Integer end) {
		List<Object> expressions = new LinkedList<Object>();
		expressions.add(stringExpr);
		expressions.add(substringExpr);
		if(start != null) {
			expressions.add(start);
		}
		if(end != null) {
			expressions.add(end);
		}
		return new Document("$indexOfBytes", expressions);
	}
	
	public static Document indexOfCP(Object stringExpr, Object substringExpr, Integer start, Integer end) {
		List<Object> expressions = new LinkedList<Object>();
		expressions.add(stringExpr);
		expressions.add(substringExpr);
		if(start != null) {
			expressions.add(start);
		}
		if(end != null) {
			expressions.add(end);
		}
		return new Document("$indexOfCP", expressions);
	}
	
	public static Document isArray(Object expression) {
		return one("isArray", expression);
	}
	
	public static Document isoDayOfWeek(Object dateExpr, String timezone) {
		return time("isoDayOfWeek", dateExpr, timezone);
	}
	
	public static Document isoWeek(Object dateExpr, String timezone) {
		return time("isoWeek", dateExpr, timezone);
	}
	
	public static Document isoWeekYear(Object dateExpr, String timezone) {
		return time("isoWeekYear", dateExpr, timezone);
	}
	
	public static Document last(Object expression) {
		return one("last", expression);
	}
	
	public static Document let(Document vars, Object in) {
		Document let = new Document("vars", vars);
		let.put("in", in);
		return new Document("$let", let);
	}
	
	public static Document literal(Object val) {
		return one("literal", val);
	}
	
	public static Document ln(Object number) {
		return one("ln", number);
	}
	
	public static Document log(Object number, Object base) {
		return nInList("log", number, base);
	}
	
	public static Document log10(Object number) {
		return one("log10", number);
	}
	
	public static Document lt(Object expression1, Object expression2) {
		return nInList("lt", expression1, expression2);
	}
	
	public static Document lte(Object expression1, Object expression2) {
		return nInList("lte", expression1, expression2);
	}
	
	public static Document map(Object input, String as, Object in) {
		Document map = new Document("input", input);
		if(as != null) {
			map.put("as", as);
		}
		map.put("in", in);
		return map;
	}
	
	public static Document max(Object expression) {
		return one("max", expression);
	}
	
	public static Document max(List<? extends Object> expressions) {
		return list("max", expressions);
	}
	
	public static Document mergeObjects(Document document) {
		return one("mergeObjects", document);
	}
	
	public static Document mergeObjects(List<Document> documents) {
		return list("mergeObjects", documents);
	}
	
	public static Document meta(String metaDataKeyword) {
		return one("mergeObjects", metaDataKeyword);
	}
	
	public static Document min(Object expression) {
		return one("min", expression);
	}
	
	public static Document min(List<? extends Object> expressions) {
		return list("min", expressions);
	}
	
	public static Document millisecond(Object dateExpr, String timezone) {
		return time("millisecond", dateExpr, timezone);
	}
	
	public static Document minute(Object dateExpr, String timezone) {
		return time("minute", dateExpr, timezone);
	}
	
	public static Document mod(Object expression1, Object expression2) {
		return nInList("mod", expression1, expression2);
	}
	
	public static Document month(Object dateExpr, String timezone) {
		return time("month", dateExpr, timezone);
	}
	
	public static Document multiply(List<? extends Object> fieldNames) {
		return list("multiply", fieldNames);
	}
	
	public static Document ne(Object expression1, Object expression2) {
		return nInList("ne", expression1, expression2);
	}
	
	public static Document not(Object expression) {
		return one("not", expression);
	}
	
	public static Document objectToArray(Object expression) {
		return one("objectToArray", expression);
	}
	
	public static Document or(List<? extends Object> expressions) {
		return nInList("or", expressions);
	}
	
	public static Document or(Object...expressions) {
		return nInList("or", expressions);
	}
	
	public static Document pow(Object expression1, Object expression2) {
		return nInList("pow", expression1, expression2);
	}
	
	public static Document push(Object expression) {
		return one("push", expression);
	}
	
	public static Document range(Object start, Object end, Object nonZeroStep) {
		List<Object> range = new LinkedList<Object>();
		range.add(start);
		range.add(end);
		if(nonZeroStep != null) {
			range.add(nonZeroStep);
		}
		return new Document("$range", range);
	}
	
	public static Document reduce(Object input, Object initialValue, Object in) {
		return nInList("reduce", input, initialValue, in);
	}
	
	public static Document reverseArray(Object array) {
		return one("reverseArray", array);
	}
	
	public static Document second(Object dateExpr, String timezone) {
		return time("second", dateExpr, timezone);
	}
	
	public static Document setDifference(Object expression1, Object expression2) {
		return nInList("setDifference", expression1, expression2);
	}
	
	public static Document setEquals(List<? extends Object> expressions) {
		return list("setEquals", expressions);
	}
	
	public static Document setIntersection(List<? extends Object> expressions) {
		return list("setIntersection", expressions);
	}
	
	public static Document setIsSubset(Object expression1, Object expression2) {
		return nInList("setIsSubset", expression1, expression2);
	}
	
	public static Document setUnion(List<? extends Object> expressions) {
		return list("setUnion", expressions);
	}
	
	public static Document size(Object expression) {
		return one("size", expression);
	}
	
	public static Document slice(Object array, Object position, Object n) {
		List<Object> slice = new LinkedList<Object>();
		slice.add(array);
		if(position != null) {
			slice.add(position);
		}
		slice.add(n);
		return new Document("$slice", slice);
	}
	
	public static Document split(Object string, Object delimiter) {
		return nInList("split", string, delimiter);
	}
	
	public static Document sqrt(Object number) {
		return one("sqrt", number);
	}
	
	public static Document stdDevPop(Object expression) {
		return one("stdDevPop", expression);
	}
	
	public static Document stdDevPop(List<? extends Object> expressions) {
		return list("stdDevPop", expressions);
	}
	
	public static Document stdDevSamp(Object expression) {
		return one("stdDevSamp", expression);
	}
	
	public static Document stdDevSamp(List<? extends Object> expressions) {
		return list("stdDevSamp", expressions);
	}
	
	public static Document strcasecmp(Object expression1, Object expression2) {
		return nInList("stdDevSamp", expression1, expression2);
	}
	
	public static Document strLenBytes(Object expression) {
		return one("strLenBytes", expression);
	}
	
	public static Document strLenCP(Object expression) {
		return one("strLenCP", expression);
	}
	
	public static Document substr(Object string, Object start, Object length) {
		return nInList("substr", string, start, length);
	}
	
	public static Document substrBytes(Object string, Object byteIndex, Object byteCount) {
		return nInList("substrBytes", string, byteIndex, byteCount);
	}
	
	public static Document substrCP(Object string, Object cpIndex, Object cpCount) {
		return nInList("substrCP", string, cpIndex, cpCount);
	}
	
	public static Document subtract(Object expression1, Object expression2) {
		return nInList("subtract", expression1, expression2);
	}
	
	public static Document sum(Object expression) {
		return one("sum", expression);
	}
	
	public static Document sum(List<? extends Object> expressions) {
		return list("sum", expressions);
	}
	
	public static Document switchOp(List<Document> branches, Object defaultOp) {
		Document sw = new Document();
		sw.put("branches", branches);
		if(defaultOp != null) {
			sw.put("default", defaultOp);
		}
		return new Document("$switch", sw);
	}
	
	public static Document branche(Object caseExpr, Object thenExpr) {
		Document branche = new Document();
		branche.put("case", caseExpr);
		branche.put("then", thenExpr);
		return branche;
	}
	
	public static Document toLower(Object expression) {
		return one("toLower", expression);
	}
	
	public static Document toUpper(Object expression) {
		return one("toUpper", expression);
	}
	
	public static Document trunc(Object number) {
		return one("trunc", number);
	}
	
	public static Document type(Object expression) {
		return one("type", expression);
	}
	
	public static Document week(Object dateExpr, String timezone) {
		return time("week", dateExpr, timezone);
	}
	
	public static Document year(Object dateExpr, String timezone) {
		return time("year", dateExpr, timezone);
	}
	
	public static Document zip(Object inputs, Boolean useLongestLength, Object defaults) {
		Document zip = new Document();
		zip.put("inputs", inputs);
		if(useLongestLength != null) {
			zip.put("useLongestLength", useLongestLength);
		}
		if(defaults != null) {
			zip.put("defaults", defaults);
		}
		return new Document("$zip", zip);
	}

}

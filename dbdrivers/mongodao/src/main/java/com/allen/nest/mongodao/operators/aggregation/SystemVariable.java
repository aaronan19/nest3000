package com.allen.nest.mongodao.operators.aggregation;

public enum SystemVariable {
	
	ROOT, CURRENT, REMOVE, DESCEND, PRUNE, KEEP;
	
	static SystemVariable getSysVarByString(String s) {
		for(SystemVariable sysVar : SystemVariable.values()) {
			if(sysVar.name().equals(s)) {
				return sysVar;
			}
		}
		return null;
	}

	String getSysVarExprKey() {
		return "$$" + this.name();
	}

}

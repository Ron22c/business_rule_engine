package com.cranajit.business_rule_engine.model;

import java.util.Map;

public class Action {
	private ActionType type;
	private Map<String, String> params;
	public Action(ActionType type, Map<String, String> params) {
		this.type = type;
		this.params = params;
	}
	public Action() {
	}
	public ActionType getType() {
		return type;
	}
	public void setType(ActionType type) {
		this.type = type;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	@Override
	public String toString() {
		return "Result [type=" + type + ", params=" + params + "]";
	}
}

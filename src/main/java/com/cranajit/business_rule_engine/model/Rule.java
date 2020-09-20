package com.cranajit.business_rule_engine.model;

public class Rule {
	private Conditions conditions;
	private Action action;
	public Rule(Conditions conditions, Action action) {
		this.conditions = conditions;
		this.action = action;
	}
	public Rule() {
	}
	public Conditions getConditions() {
		return conditions;
	}
	public void setConditions(Conditions conditions) {
		this.conditions = conditions;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	@Override
	public String toString() {
		return "Rule [conditions=" + conditions + ", action=" + action + "]";
	}
}

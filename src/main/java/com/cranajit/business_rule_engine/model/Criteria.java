package com.cranajit.business_rule_engine.model;

public class Criteria {
	private String fact;
	private String operator;
	private String value;
		
	public Criteria() {
	}
	public Criteria(String fact, String operator, String value) {
		super();
		this.fact = fact;
		this.operator = operator;
		this.value = value;
	}
	public String getFact() {
		return fact;
	}
	public void setFact(String fact) {
		this.fact = fact;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

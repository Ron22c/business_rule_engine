package com.cranajit.business_rule_engine.model;

import java.util.List;

public class Conditions {
	private List<Criteria> anyOf;
	private List<Criteria> allOf;
	private List<Criteria> noneOf;

	public Conditions(List<Criteria> anyOf, List<Criteria> allOf, List<Criteria> noneOf) {
		this.anyOf = anyOf;
		this.allOf = allOf;
		this.noneOf = noneOf;
	}

	public Conditions() {
	}

	public List<Criteria> getAnyOf() {
		return anyOf;
	}

	public void setAnyOf(List<Criteria> anyOf) {
		this.anyOf = anyOf;
	}

	public List<Criteria> getAllOf() {
		return allOf;
	}

	public void setAllOf(List<Criteria> allOf) {
		this.allOf = allOf;
	}

	public List<Criteria> getNoneOf() {
		return noneOf;
	}

	public void setNoneOf(List<Criteria> noneOf) {
		this.noneOf = noneOf;
	}

	@Override
	public String toString() {
		return "Conditions [anyOf=" + anyOf + ", allOf=" + allOf + ", noneOf=" + noneOf + "]";
	}
}

package com.cranajit.business_rule_engine.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cranajit.business_rule_engine.model.Action;
import com.cranajit.business_rule_engine.model.Rule;

public class Engine {
	List<Rule> rules;
	
	public Engine() {
		this.rules = new ArrayList<Rule>();
	}
	
	public Engine addRule(Rule rule) {
		this.rules.add(rule);
		return this;
	}
	
	public List<Action> run(Map<String, String> input) {
		List<Action> res =  CriteriaExecutor.execute(rules, input);
		if(res.size()<1) throw new RuntimeException("NO RULE MATCHED");
		return res;
	}
}

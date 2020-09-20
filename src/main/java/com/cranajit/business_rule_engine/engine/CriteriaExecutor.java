package com.cranajit.business_rule_engine.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cranajit.business_rule_engine.model.Action;
import com.cranajit.business_rule_engine.model.Conditions;
import com.cranajit.business_rule_engine.model.Criteria;
import com.cranajit.business_rule_engine.model.Rule;


public class CriteriaExecutor {
	public static List<Action> execute(List<Rule> rules, Map<String, String> inputMap) {
		List<Action> res = new ArrayList<Action>();
		for(Rule rule: rules) {
			Conditions conditions = rule.getConditions();
			if(conditions.getAnyOf().size()!=0) {
				for(Criteria c : conditions.getAnyOf()) {
					try {
						if(c.getValue().equals(inputMap.get(c.getFact()))) {
							res.add(rule.getAction());
						}
					} catch (Exception e) {
						System.out.println(c.getFact() + " RULE DID NOT MATCHED");
					}
				}
			}
			
			if(conditions.getAllOf().size()!=0) {
				List<Action> temp = new ArrayList<Action>();
				Boolean allMatched = true;
				for(Criteria c : conditions.getAllOf()) {
					try {
						if(c.getValue().equals(inputMap.get(c.getFact()))) {
							res.add(rule.getAction());
						}
					} catch (Exception e) {
						System.out.println(c.getFact() + " RULE DID NOT MATCHED");
						allMatched = false;
					}
				}
				if(allMatched) res.addAll(temp);
			}
		}
		return res;
	}
}

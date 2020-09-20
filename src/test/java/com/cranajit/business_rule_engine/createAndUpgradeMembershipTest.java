package com.cranajit.business_rule_engine;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.cranajit.business_rule_engine.engine.Engine;
import com.cranajit.business_rule_engine.model.Action;
import com.cranajit.business_rule_engine.model.ActionType;
import com.cranajit.business_rule_engine.model.Rule;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateAndUpgradeMembershipTest {
	private Engine engine;
	
	@Test
	public void createMembershipRuleTest() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
    	Rule rule = mapper.readValue(new File("src/main/resources/Rule3.json"), Rule.class);
    	Rule rule5 = mapper.readValue(new File("src/main/resources/Rule5.json"), Rule.class);

    	engine = new Engine();
    	engine.addRule(rule)
    		.addRule(rule5);
    	
    	Map<String, String> query = new HashMap<String, String>();
    	query.put("PAYMENT_TYPE", "CREATE_MEMBERSHIP");
    	
    	List<Action> res = engine.run(query);
    	assertTrue(res.size() == 2);
    	assertTrue(res.get(0).getType().equals(ActionType.ACTIVATE_MEMBERSHIP));
    	assertTrue(res.get(1).getType().equals(ActionType.EMAIL_OWNER));
	}
	
	@Test
	public void upgradeMembershipRuleTest() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
    	Rule rule = mapper.readValue(new File("src/main/resources/Rule4.json"), Rule.class);
    	Rule rule5 = mapper.readValue(new File("src/main/resources/Rule5.json"), Rule.class);

    	engine = new Engine();
    	engine.addRule(rule)
    		.addRule(rule5);
    	
    	Map<String, String> query = new HashMap<String, String>();
    	query.put("PAYMENT_TYPE", "UPGRADE_MEMBERSHIP");
    	
    	List<Action> res = engine.run(query);
      	assertTrue(res.size() == 2);
    	assertTrue(res.get(0).getType().equals(ActionType.UPGRADE_MEMBERSHIP));
    	assertTrue(res.get(1).getType().equals(ActionType.EMAIL_OWNER));
	}
}

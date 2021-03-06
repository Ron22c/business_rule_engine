package com.cranajit.business_rule_engine;

import static org.junit.Assert.assertFalse;
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

public class PaymentTypePhysicalProductTest {
private Engine engine;
	
	@Test
	public void paymentTypePhysicalProductHappyCase() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
    	Rule rule = mapper.readValue(new File("src/main/resources/Rule1.json"), Rule.class);
    	Rule rule6 = mapper.readValue(new File("src/main/resources/Rule6.json"), Rule.class);

    	engine = new Engine();
    	engine.addRule(rule)
    			.addRule(rule6);
    	
    	Map<String, String> query = new HashMap<String, String>();
    	query.put("PAYMENT_TYPE", "PHYSICAL_PRODUCT");
    	
    	List<Action> res = engine.run(query);

    	assertTrue(res.size()==2);
    	assertTrue(res.get(0).getType().equals(ActionType.GENERATE_PACKING_SLIP));
    	assertTrue(res.get(1).getType().equals(ActionType.GENERATE_AGENT_COMISSION));
    	assertTrue(res.get(0).getParams().keySet().size()==0);
    	assertTrue(res.get(1).getParams().keySet().size()==0);
	}
	
	@Test
	public void paymentTypePhysicalProductWithoutRuleOne() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
    	Rule rule6 = mapper.readValue(new File("src/main/resources/Rule6.json"), Rule.class);

    	engine = new Engine();
    	engine.addRule(rule6);
    	
    	Map<String, String> query = new HashMap<String, String>();
    	query.put("PAYMENT_TYPE", "PHYSICAL_PRODUCT");
    	
    	List<Action> res = engine.run(query);

    	assertTrue(res.size()==1);
    	assertTrue(res.get(0).getType().equals(ActionType.GENERATE_AGENT_COMISSION));
    	assertTrue(res.get(0).getParams().keySet().size()==0);
	}
	
	@Test
	public void paymentTypePhysicalProductWithoutRuleSix() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
    	Rule rule = mapper.readValue(new File("src/main/resources/Rule1.json"), Rule.class);

    	engine = new Engine();
    	engine.addRule(rule);
    	
    	Map<String, String> query = new HashMap<String, String>();
    	query.put("PAYMENT_TYPE", "PHYSICAL_PRODUCT");
    	
    	List<Action> res = engine.run(query);

    	assertTrue(res.size()==1);
    	assertTrue(res.get(0).getType().equals(ActionType.GENERATE_PACKING_SLIP));
    	assertTrue(res.get(0).getParams().keySet().size()==0);
	}
	
	@Test
	public void paymentTypePhysicalProductExpectingEmail() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
    	Rule rule = mapper.readValue(new File("src/main/resources/Rule1.json"), Rule.class);
    	Rule rule6 = mapper.readValue(new File("src/main/resources/Rule6.json"), Rule.class);
    	Rule rule5 = mapper.readValue(new File("src/main/resources/Rule5.json"), Rule.class);

    	engine = new Engine();
    	engine.addRule(rule)
    			.addRule(rule5)
    			.addRule(rule6);
    	
    	Map<String, String> query = new HashMap<String, String>();
    	query.put("PAYMENT_TYPE", "PHYSICAL_PRODUCT");
    	
    	List<Action> res = engine.run(query);

    	assertFalse(res.size()==3);
    	assertTrue(res.get(0).getType().equals(ActionType.GENERATE_PACKING_SLIP));
    	assertTrue(res.get(1).getType().equals(ActionType.GENERATE_AGENT_COMISSION));
    	assertTrue(res.get(0).getParams().keySet().size()==0);
    	assertTrue(res.get(1).getParams().keySet().size()==0);
    	assertFalse(res.get(0).getType().equals(ActionType.EMAIL_OWNER));
	}
}

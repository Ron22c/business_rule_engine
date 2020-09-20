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

public class PaymentTypeVideoTest {
private Engine engine;
	
	@Test
	public void videoPaymentRuleTest() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
    	Rule rule = mapper.readValue(new File("src/main/resources/Rule1.json"), Rule.class);
    	Rule rule7 = mapper.readValue(new File("src/main/resources/Rule7.json"), Rule.class);

    	engine = new Engine();
    	engine.addRule(rule)
    		.addRule(rule7);
    	
    	Map<String, String> query = new HashMap<String, String>();
    	query.put("PAYMENT_TYPE", "VIDEO");
    	
    	List<Action> res = engine.run(query);
    	assertTrue(res.size() == 1);
    	assertTrue(res.get(0).getType().equals(ActionType.GENERATE_PACKING_SLIP));
	}
	
	@Test
	public void videoLearningToSkiRuleTest() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
    	Rule rule = mapper.readValue(new File("src/main/resources/Rule1.json"), Rule.class);
    	Rule rule7 = mapper.readValue(new File("src/main/resources/Rule7.json"), Rule.class);

    	engine = new Engine();
    	engine.addRule(rule)
    		.addRule(rule7);
    	
    	Map<String, String> query = new HashMap<String, String>();
    	query.put("PAYMENT_TYPE", "VIDEO_LEARNING_TO_SKI");
    	
    	List<Action> res = engine.run(query);
      	assertTrue(res.size() == 1);
    	assertTrue(res.get(0).getType().equals(ActionType.GENERATE_PACKING_SLIP));
    	assertTrue(res.get(0).getParams().get("FREE").equals("ADD_FIRST_AID_VIDEO_TO_PACKING_SLIP"));
	}
}

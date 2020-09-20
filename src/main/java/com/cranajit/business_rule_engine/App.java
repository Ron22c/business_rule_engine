package com.cranajit.business_rule_engine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cranajit.business_rule_engine.engine.Engine;
import com.cranajit.business_rule_engine.model.Action;
import com.cranajit.business_rule_engine.model.Rule;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
    public static void main(String[] args ) throws JsonParseException, JsonMappingException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
    	Rule rule = mapper.readValue(new File("src/main/resources/Rule1.json"), Rule.class);
    	Rule rule2 = mapper.readValue(new File("src/main/resources/Rule2.json"), Rule.class);
    	Rule rule3 = mapper.readValue(new File("src/main/resources/Rule3.json"), Rule.class);
    	Rule rule4 = mapper.readValue(new File("src/main/resources/Rule4.json"), Rule.class);
    	Rule rule5 = mapper.readValue(new File("src/main/resources/Rule5.json"), Rule.class);
    	Rule rule6 = mapper.readValue(new File("src/main/resources/Rule6.json"), Rule.class);
    	Rule rule7 = mapper.readValue(new File("src/main/resources/Rule7.json"), Rule.class);

    	Engine engine = new Engine();
    	engine.addRule(rule)
    		.addRule(rule2)
    		.addRule(rule3)
    		.addRule(rule4)
    		.addRule(rule5)
    		.addRule(rule6)
    		.addRule(rule7);
    	    	
    	Map<String, String> query = new HashMap<String, String>();
    	query.put("PAYMENT_TYPE", "VIDEO_LEARNING_TO_SKI");

    	
    	List<Action> res = engine.run(query);
    	for(Action r: res) {
    		System.out.println("Action to take: " + r.getType());
    		System.out.println("Action params: " + r.getParams());
    	}
    }
}

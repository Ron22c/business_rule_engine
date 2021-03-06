package com.cranajit.business_rule_engine;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.cranajit.business_rule_engine.engine.Engine;
import com.cranajit.business_rule_engine.model.Rule;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NoRulesMatchedTest {
private Engine engine;
	
	@Test
	public void noRulesMatchedTest() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
    	Rule rule = mapper.readValue(new File("src/main/resources/Rule1.json"), Rule.class);

    	engine = new Engine();
    	engine.addRule(rule);
    	
    	Map<String, String> query = new HashMap<String, String>();
    	query.put("PAYMENT_TYPE", "CREATE_MEMBERSHIP");
    	
    	try {
        	engine.run(query);
            fail("Expected a RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertTrue(e.getLocalizedMessage().equals("NO RULE MATCHED"));
        }
	}
}

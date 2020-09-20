## Business Rule Engine

# How to run
* Pull the project
* open it in Eclipse/IntelliJ as a maven project
* dependencies will be resolved by maven(only 2)
* Run App class as a java application
* Run junit tests in the test package


# Apis
* Rule Engine has two methods
 * addRule(): this method will take a rule Object and return an instance of Engine class.
 
 * run(): this method will take a query which contains which payment_type is received in a Map<String, String> and return a List of Actions based on rules matched to that payment_type so that client could take appropriate actions for that payment_type
 * in case of no rules found run() method will throw a RuntimeException("NO RULES FOUND")

# Rule
* Rule is a Json object which contains conditions and actions.
 * Example:
```{
    "conditions": {
    	"anyOf":[
	    	{
	    		"fact":"PAYMENT_TYPE",
	    		"operator":"EQUALS",
	    		"value":"PHYSICAL_PRODUCT"
	    	},
	    	{
	    		"fact":"PAYMENT_TYPE",
	    		"operator":"EQUALS",
	    		"value":"BOOK"
	    	},
	    	{
	    		"fact":"PAYMENT_TYPE",
	    		"operator":"EQUALS",
	    		"value":"VIDEO"
	    	}
    	],
    	"allOf":[],
    	"noneOf":[]
    },
    "action": {
        "type": "GENERATE_PACKING_SLIP",
        "params": {}
    }
}
```
 * Schema
```
{
    "conditions": {
    	"anyOf":[
	    	{
	    		"fact":"String",
	    		"operator":"String",
	    		"value":"String"
	    	},
.
.
.

    	],
    	"allOf":[
                {
	    		"fact":"String",
	    		"operator":"String",
	    		"value":"String"
	    	},
.
.
.
        ],
    	"noneOf":[
                {
	    		"fact":"String",
	    		"operator":"String",
	    		"value":"String"
	    	},
.
.
.
        ]
    },
    "action": {
        "type": "ActionType(Enum)",
        "params": {Map<String, String>}
    }
}
```

# Input Query

* input query will be Map<String, String> type
 * Example
```
Map<String, String> query = new HashMap<String, String>();
query.put("PAYMENT_TYPE", "CREATE_MEMBERSHIP");
```

# How to add rules to the engine and execute query
```
ObjectMapper mapper = new ObjectMapper();

// Load rule from a separate JSON file, it should be ideally fetched from a DB

Rule rule = mapper.readValue(new File("src/main/resources/Rule1.json"), Rule.class);

Engine engine = new Engine();
engine.addRule(rule)

Map<String, String> query = new HashMap<String, String>();
query.put("PAYMENT_TYPE", "CREATE_MEMBERSHIP");

List<Action> res = engine.run(query);
for(Action r: res) {
   System.out.println("Action to take: " + r.getType());
   System.out.println("Action params: " + r.getParams());
}

// Output: 
Action to take: EMAIL_OWNER
Action params: {}
Action to take:ACTIVATE_MEMBERSHIP
Action params:{}
```

# Features
* addRule() method supports builder design pattern so you can add more then one rule in a single statement using that pattern
 * Exaple:
```
Engine engine = new Engine();
engine.addRule(rule)
      .addRule(rule2)
      .addRule(rule3);
```

* Engine supports allof, anyOf and noneOf matches
* Engine can support equals, grater then, less then comparisons but based on the use case only implemented Equals operator for all the rules
* More rules can be added in future

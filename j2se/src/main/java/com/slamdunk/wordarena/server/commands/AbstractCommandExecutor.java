package com.slamdunk.wordarena.server.commands;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Cette classe se charge de la création du résultat
 */
public abstract class AbstractCommandExecutor implements CommandExecutor {
	public static final String PARAM_RESULT = "result";
	public static final String PARAM_DETAILS = "details";
	
	private JsonObject result;
	
	@Override
	public JsonObject getResult() {
		return result;
	}

	protected void buildResult(boolean result) {
		buildResult(result, null);
	}
	
	protected void buildResult(boolean result, String details) {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add(PARAM_RESULT, result);
		if (details != null) {
			builder.add(PARAM_DETAILS, details);
		}
		this.result = builder.build();
	}
}

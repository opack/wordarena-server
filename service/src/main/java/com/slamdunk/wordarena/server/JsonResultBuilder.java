package com.slamdunk.wordarena.server;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class JsonResultBuilder {
	public static final String PARAM_RESULT = "result";
	public static final String PARAM_DETAILS = "details";
	
	public static JsonObject build(boolean result) {
		return build(result, (String)null);
	}
	
	public static JsonObject build(boolean result, JsonObject details) {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add(PARAM_RESULT, result);
		if (details != null) {
			builder.add(PARAM_DETAILS, details);
		}
		return builder.build();
	}
	
	public static JsonObject build(boolean result, String details) {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add(PARAM_RESULT, result);
		if (details != null) {
			builder.add(PARAM_DETAILS, details);
		}
		return builder.build();
	}
}

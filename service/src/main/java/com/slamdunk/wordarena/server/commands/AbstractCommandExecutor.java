package com.slamdunk.wordarena.server.commands;

import javax.json.JsonObject;

import com.slamdunk.wordarena.server.JsonResultBuilder;

/**
 * Cette classe se charge de la création du résultat
 */
public abstract class AbstractCommandExecutor implements CommandExecutor {
	private JsonObject result;
	
	@Override
	public JsonObject getResult() {
		return result;
	}

	protected void buildResult(boolean result) {
		this.result = JsonResultBuilder.build(result, (String)null);
	}
	
	protected void buildResult(boolean result, JsonObject details) {
		this.result = JsonResultBuilder.build(result, details);
	}
	
	protected void buildResult(boolean result, String details) {
		this.result = JsonResultBuilder.build(result, details);
	}
}

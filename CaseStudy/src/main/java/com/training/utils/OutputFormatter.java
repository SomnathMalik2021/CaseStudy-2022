package com.training.utils;

import com.google.gson.Gson;
import com.training.factory.GsonFactory;

public final class OutputFormatter {

	private Gson gson;

	public OutputFormatter() {
		super();
		gson = GsonFactory.getGsonObject();
	}
	
	
	public String getJsonFormat(Object object) {
		return gson.toJson(object);
	}
	
	
}

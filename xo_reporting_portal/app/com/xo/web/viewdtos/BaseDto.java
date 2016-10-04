package com.xo.web.viewdtos;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

import java.io.Serializable;

@SuppressWarnings({"unchecked", "serial"})
public class BaseDto<T extends BaseDto<T>> implements Serializable{

	public JsonNode toJson() {
		return Json.toJson(this);
	}

	public T fromJson(JsonNode jsonObject) {
		return (T) Json.fromJson(jsonObject, this.getClass());
	}
}

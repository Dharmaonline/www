package org.eprophet.jsonservice.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonFormat {
String json;

public String getJson() throws JsonProcessingException {
  ObjectMapper objectMapper=new ObjectMapper();
  objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
  return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
}

public void setJson(String json) {
  this.json = json;
}
}

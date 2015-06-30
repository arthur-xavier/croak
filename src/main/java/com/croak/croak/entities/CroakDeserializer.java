package com.croak.croak.entities;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class CroakDeserializer extends JsonDeserializer<Croak> {

  @Override
  public Croak deserialize(JsonParser jp, DeserializationContext ctxt)
    throws IOException, JsonProcessingException
  {
    JsonNode node = jp.getCodec().readTree(jp);
    String text = node.get("text").asText();
    String color = node.get("color").asText();
    String username = node.get("author").get("username").asText();
    String firstName = node.get("author").get("firstName").asText();
    String lastName = node.get("author").get("lastName").asText();
    String avatar = node.get("author").get("avatar").asText();

    return new Croak(text, color, new User(username, firstName, lastName, avatar));
  }
}

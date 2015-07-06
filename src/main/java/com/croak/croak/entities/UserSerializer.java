package com.croak.croak.entities;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.JsonSerializer;

public class UserSerializer extends JsonSerializer<User> {

  @Override
  public void serialize(User user, JsonGenerator json, SerializerProvider provider)
    throws IOException, JsonProcessingException
  {
    json.writeStartObject();
    json.writeNumberField("id", user.getId());
    json.writeStringField("username", user.getUsername());
    json.writeStringField("firstName", user.getFirstName());
    json.writeStringField("lastName", user.getLastName());
    json.writeStringField("avatar", user.getAvatar());
    json.writeStringField("email", user.getEmail());
    json.writeStringField("quote", user.getQuote());
    json.writeEndObject();
  }
}

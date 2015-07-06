package com.croak.croak.entities;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.JsonSerializer;

public class CroakSerializer extends JsonSerializer<Croak> {

  @Override
  public void serialize(Croak croak, JsonGenerator json, SerializerProvider provider)
    throws IOException, JsonProcessingException
  {
    json.writeStartObject();
    json.writeNumberField("id", croak.getId());
    json.writeStringField("text", croak.getText());
    json.writeStringField("color", croak.getColor());
      json.writeObjectFieldStart("author");
      json.writeStringField("username", croak.getAuthor().getUsername());
      json.writeStringField("firstName", croak.getAuthor().getFirstName());
      json.writeStringField("lastName", croak.getAuthor().getLastName());
      json.writeStringField("avatar", croak.getAuthor().getAvatar());
      json.writeEndObject();
    json.writeEndObject();
  }
}

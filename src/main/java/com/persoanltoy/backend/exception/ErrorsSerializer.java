package com.persoanltoy.backend.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import java.io.IOException;

@JsonComponent
public class ErrorsSerializer extends JsonSerializer<Errors> {

    @Override
    public void serialize(Errors value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        value.getFieldErrors().forEach(error -> {
            try {
                gen.writeStartObject();
                gen.writeStringField("objectName", error.getObjectName());
                gen.writeStringField("field", error.getField());
                gen.writeStringField("code", error.getCode());
                gen.writeStringField("defaultMessage", error.getDefaultMessage());
                gen.writeStringField("rejectedValue", error.getRejectedValue() != null ? error.getRejectedValue().toString() : null);
                gen.writeEndObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        value.getGlobalErrors().forEach(error -> {
            try {
                gen.writeStartObject();
                gen.writeStringField("objectName", error.getObjectName());
                gen.writeStringField("field", null);
                gen.writeStringField("code", error.getCode());
                gen.writeStringField("defaultMessage", error.getDefaultMessage());
                gen.writeStringField("rejectedValue", null);
                gen.writeEndObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        gen.writeEndArray();
    }

}

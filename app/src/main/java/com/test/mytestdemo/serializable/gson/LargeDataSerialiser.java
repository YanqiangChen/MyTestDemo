package com.test.mytestdemo.serializable.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class LargeDataSerialiser implements JsonSerializer<LargeData> {
    @Override
    public JsonElement serialize(LargeData data, Type typeOfSrc, JsonSerializationContext context) {
        final JsonArray jsonNumbers = new JsonArray();
        for (final long number : data.getNumbers()) {
            jsonNumbers.add(new JsonPrimitive(number));
        }

        final JsonObject jsonObject = new JsonObject();
        jsonObject.add("numbers", jsonNumbers);
        return jsonObject;
    }
}

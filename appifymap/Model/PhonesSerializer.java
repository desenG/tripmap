package com.djcanadastudio.appifymap.Model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by desenguo on 2016-09-08.
 */
public class PhonesSerializer implements JsonSerializer<Phones> {
    @Override
    public JsonElement serialize(Phones phones, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject phonesJson = new JsonObject();

        phonesJson.addProperty("main", phones.getMain());
        phonesJson.addProperty("mobile_phone", phones.getMobile_phone());
        return phonesJson;
    }
}

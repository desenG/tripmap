package com.djcanadastudio.appifymap.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by desenguo on 2016-09-08.
 */
public class TripSerializer implements JsonSerializer<Trip> {
    @Override
    public JsonElement serialize(Trip trip, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject tripJson = new JsonObject();

        tripJson.addProperty("pickup_address", trip.getPickup_address());
        tripJson.addProperty("pickup_city", trip.getPickup_city());
        tripJson.addProperty("pickup_country", trip.getPickup_country());
        tripJson.addProperty("pickup_time", trip.getPickup_time());
        tripJson.addProperty("pickup_time_zone", trip.getPickup_time_zone());
        tripJson.addProperty("pickup_latitude", trip.getPickup_latitude());
        tripJson.addProperty("pickup_longitude", trip.getPickup_longitude());
        tripJson.addProperty("num_passengers", trip.getNum_passengers());
        tripJson.addProperty("payment_method", trip.getPayment_method());

        Gson phonesGson = new GsonBuilder().registerTypeAdapter(Phones.class, new PhonesSerializer()).create();
        tripJson.add("phones", phonesGson.toJsonTree(trip.getPhones()).getAsJsonObject());

        tripJson.addProperty("notes", trip.getNotes());
        tripJson.addProperty("account_number", trip.getAccount_number());
        tripJson.addProperty("passenger_name", trip.getPassenger_name());
        return tripJson;
    }
}

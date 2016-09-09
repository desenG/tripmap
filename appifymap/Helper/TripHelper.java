package com.djcanadastudio.appifymap.Helper;

import android.os.AsyncTask;

import com.djcanadastudio.appifymap.Model.Trip;
import com.djcanadastudio.appifymap.Model.TripSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Created by desenguo on 2016-09-08.
 */
public class TripHelper {
    public static void createTripRequest(IConnector<JsonObject> connector,Trip trip) {
        new AsyncTripRequest<>(connector,trip).execute();
    }

    private static class AsyncTripRequest <T> extends AsyncTask<Void, Void, T> {
        IConnector<T> connector;
        Trip trip;

        public AsyncTripRequest(IConnector<T> connector, Trip trip) {
            this.connector=connector;
            this.trip=trip;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected T doInBackground(Void... params) {
            Gson tripGson = new GsonBuilder().registerTypeAdapter(Trip.class, new TripSerializer()).create();

            return (T)tripGson.toJsonTree(trip).getAsJsonObject();
        }

        @Override
        protected void onPostExecute(T res) {
            super.onPostExecute(res);
            try {
                this.connector.done(res);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

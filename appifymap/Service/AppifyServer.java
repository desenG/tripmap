package com.djcanadastudio.appifymap.Service;

import com.djcanadastudio.appifymap.Helper.IConnector;
import com.djcanadastudio.appifymap.Helper.TripHelper;
import com.djcanadastudio.appifymap.Model.Trip;
import com.djcanadastudio.appifymap.Service.Rest.BusProvider;
import com.djcanadastudio.appifymap.Service.Rest.ServiceGenerator;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by desenguo on 2016-09-08.
 */
public class AppifyServer {
    private static final AppifyServer server = new AppifyServer();
    TripService tripService;
    public static AppifyServer getInstance() {
        return server;
    }

    private AppifyServer() {
        tripService= ServiceGenerator.createService(TripService.class);

        BusProvider.getInstance().register(this);
    }

    public void createNewTrip(Trip trip,final DoneCallback<String> successHandler,final DoneCallback<String> failedHandler)
    {
        TripHelper.createTripRequest(new IConnector<JsonObject>() {
            @Override
            public void done(JsonObject result) throws Exception {
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (result.toString()));
                Call<JsonObject> syncCall = tripService.createNewTrip(body);
                syncCall.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {
                            if (successHandler != null) {
                                successHandler.done("Success");
                            }
                        } else {
                            // error response, no access to resource?
                            if (failedHandler != null) {
                                failedHandler.done("Failed");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        if (failedHandler != null) {
                            failedHandler.done("Failed");
                        }
                    }
                });
            }
        }, trip);

    }
}

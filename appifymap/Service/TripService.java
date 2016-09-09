package com.djcanadastudio.appifymap.Service;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by desenguo on 2016-08-28.
 */
public interface TripService {
    @POST("v1/services/create_new_trip")
    Call createNewTrip(@Body RequestBody body);

    @POST("v1/services/modify_trip")
    Call modifyTrip(@Body RequestBody body);
}

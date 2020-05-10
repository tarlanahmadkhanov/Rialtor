package com.ta.rialtor.retrofit;

import com.ta.rialtor.model.RealEstateAPIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/getEstates")
    Call<List<RealEstateAPIService>> getEstates(@Query("scope") int scope);
}

package com.mubin.quranapp.network;

import com.mubin.quranapp.response.QuranResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search")
    Call<QuranResponse> getQuran(@Query("q") String q, @Query("page") int page);
}

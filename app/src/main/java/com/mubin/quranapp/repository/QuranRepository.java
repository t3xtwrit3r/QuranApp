package com.mubin.quranapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mubin.quranapp.network.ApiClient;
import com.mubin.quranapp.network.ApiService;
import com.mubin.quranapp.response.QuranResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuranRepository {

    private ApiService apiService;

    public QuranRepository(){
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<QuranResponse> getQuran(String q, int page){
        MutableLiveData<QuranResponse> data = new MutableLiveData<>();
        apiService.getQuran(q,page).enqueue(new Callback<QuranResponse>() {
            @Override
            public void onResponse(@NonNull Call<QuranResponse> call, @NonNull Response<QuranResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<QuranResponse> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

}

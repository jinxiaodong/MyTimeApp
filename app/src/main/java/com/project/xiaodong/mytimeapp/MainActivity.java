package com.project.xiaodong.mytimeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.project.xiaodong.mytimeapp.network.ApiManager;
import com.project.xiaodong.mytimeapp.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiService apiService = (ApiService) ApiManager.getInstance().getApiService("");
        Call<String> login = apiService.login();

        login.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}

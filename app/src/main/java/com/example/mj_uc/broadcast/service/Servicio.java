package com.example.mj_uc.broadcast.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.mj_uc.broadcast.Tools;
import com.example.mj_uc.broadcast.data.model.DetailsCall;
import com.example.mj_uc.broadcast.data.remote.APIService;
import com.example.mj_uc.broadcast.data.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class Servicio extends Service {

    private APIService mAPIService;

    public Servicio() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String telefono = intent.getStringExtra("telefono");
        String estado = intent.getStringExtra("estado");

        mAPIService = ApiUtils.getAPIService();

        sendDetail(new DetailsCall(telefono, Tools.getCurrentDate(), Tools.getCurrentTime(), estado));
        return START_REDELIVER_INTENT;
    }

    private void sendDetail(DetailsCall detailsCall) {
        mAPIService.saveDetailCall(detailsCall).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    Log.i(TAG, "Sucessful connection to the BBDD.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
//llamar a un servicio startService()

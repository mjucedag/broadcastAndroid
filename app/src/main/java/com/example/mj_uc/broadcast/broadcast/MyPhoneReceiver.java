package com.example.mj_uc.broadcast.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.mj_uc.broadcast.service.Servicio;

public class MyPhoneReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String phoneNumber = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            String state = extras.getString(TelephonyManager.EXTRA_STATE);

            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

                Intent paramStartService = getParamStartService(phoneNumber,"ENTRANTE", context);
                context.startService(paramStartService); //inicia un servicio

            }else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {

                Intent paramStartService = getParamStartService(phoneNumber,"SALIENTE", context);
                context.startService(paramStartService); //inicia un servicio
            }
        }
    }

    public Intent getParamStartService(String telefono, String estado, Context context){
        Intent intent = new Intent(context, Servicio.class);
        intent.putExtra("telefono", telefono);
        intent.putExtra("estado", estado);
        intent.setAction("com.example.mj_uc.broadcast.broadcast.MyPhoneReceiver");
        return intent;
    }
}

package com.example.mj_uc.broadcast.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.mj_uc.broadcast.service.Servicio;

import java.util.Date;

public class MyPhoneReceiver extends BroadcastReceiver {

    private static int lastState = TelephonyManager.CALL_STATE_IDLE;
    private static boolean isIncoming;
    private static String savedNumber;  //esta variable se utiliza, en gris??? | porque la llamada entrante solo es valida en después de sonar

    @Override
    public void onReceive(Context context, Intent intent) {
        //primer intento = comienzo de la llamada, contemplando los 3 estados posibles en Android
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            savedNumber = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
        } else{
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            int state = 0;
            if(stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                state = TelephonyManager.CALL_STATE_IDLE;
            }
            else if(stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                state = TelephonyManager.CALL_STATE_OFFHOOK;
            }
            else if(stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                state = TelephonyManager.CALL_STATE_RINGING;
            }
            //segundo intento = final de la llamada
            onCallStateChanged(context, state, number);
        }
    }

    //Llamada entrante - va desde IDLE a RINGING cuando suena, a OFFHOOK cuando se contesta, a IDLE cuando se cuelga.
    //Llamada saliente - va desde IDLE a OFFHOOK cuando marca, a IDLE cuando cuelga
    public void onCallStateChanged(Context context, int state, String number) {
        if (lastState == state) {
            //sin cambios, aún está en el estado inicial de la llamada
            return;
        }
        if (state == TelephonyManager.CALL_STATE_IDLE) {
            //Este es el final de la llamada. El tipo de final de llamada depende de state (estado inicial)
            if (lastState == TelephonyManager.CALL_STATE_RINGING) {
                Intent paramStartService = getParamStartService(number, "PERDIDA", context);
                context.startService(paramStartService); //inicia un servicio
            } else if (isIncoming) {
                Intent paramStartService = getParamStartService(number, "ENTRANTE", context);
                context.startService(paramStartService); //inicia un servicio
            } else {
                Intent paramStartService = getParamStartService(number, "SALIENTE", context);
                context.startService(paramStartService); //inicia un servicio
            }
        } else if (state == TelephonyManager.CALL_STATE_RINGING){
            isIncoming = true;
            savedNumber = number;
        } else if (state == TelephonyManager.CALL_STATE_OFFHOOK){
            // Transicion al sonar -> si se decuelga es la recogida de la llamada entrante
            if(lastState != TelephonyManager.CALL_STATE_RINGING) {
                isIncoming = false;
            }
        }
        lastState = state;
    }

    public Intent getParamStartService(String telefono, String estado, Context context){
        Intent intent = new Intent(context, Servicio.class);
        intent.putExtra("telefono", telefono);
        intent.putExtra("estado", estado);
        intent.setAction("com.example.mj_uc.broadcast.broadcast.MyPhoneReceiver");
        return intent;
    }
}


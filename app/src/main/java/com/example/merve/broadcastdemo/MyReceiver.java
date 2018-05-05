package com.example.merve.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("onrecive:",intent.getAction());

        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        Log.d("chargePlug:",String.valueOf(chargePlug));

        Intent newIntent=new Intent("batarya-degisti");

        intent.putExtra("usbCharge",usbCharge)
                .putExtra("acCharge",acCharge);

        LocalBroadcastManager.getInstance(context).sendBroadcast(newIntent);



    }
}

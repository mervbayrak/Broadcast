package com.example.merve.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static android.R.attr.filter;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver mReceiver ;
    boolean isRegistered=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReceiver = new MyReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(bataryaDegistiginde,new IntentFilter("batarya-degisti"));
    }


    BroadcastReceiver bataryaDegistiginde=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            TextView tvState= (TextView) findViewById(R.id.tvState);
            tvState.setText("USB:"+ intent.getBooleanExtra("usbCharge",false)+"\nAC:"+intent.getBooleanExtra("acCharge",false));

        }
    };

    public void registerBroadcast(View view) {

        if(isRegistered)
        {
            Log.d("state","unregistered");
            unregisterReceiver(mReceiver);
        }
        else {
            Log.d("state","registered");
            IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
           // filter.addAction(Intent.ACTION_SCREEN_OFF);
            registerReceiver(mReceiver, filter);
        }
        isRegistered=!isRegistered;
    }
}

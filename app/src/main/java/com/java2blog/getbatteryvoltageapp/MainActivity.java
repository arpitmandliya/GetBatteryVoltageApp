package com.java2blog.getbatteryvoltageapp;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.java2blog.getbatteryvoltageapp.R;

public class MainActivity extends Activity {

    TextView volTextView;
    Button button;
    IntentFilter intentfilter;
    int batteryVol;
    float fullVoltage;
    String currentBatteryVol="Current Battery Voltage :";
    int batteryLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.buttonBatteryVol);
        volTextView = (TextView)findViewById(R.id.textViewBatteryVol);

        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.this.registerReceiver(broadcastreceiver,intentfilter);

            }
        });

    }

    private BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            batteryVol = (int)(intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0));
            fullVoltage = (float) (batteryVol * 0.001);
            volTextView.setText(currentBatteryVol +" "+fullVoltage+" volt");

        }
    };


}
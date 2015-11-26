package com.localisation.indoorlocalisation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Praveen on 11/25/2015.
 */
public class WifiListActivity extends AppCompatActivity {
    WifiReceiver w;
    List<ScanResult> wifiList;
    WifiManager mWifi;
    ListView wifiListView;
    WifiListAdapter wifiListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifi_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E43F3F")));
        wifiListView=(ListView)findViewById(R.id.wifiList);
        mWifi= (WifiManager)getSystemService(Context.WIFI_SERVICE);
        registerReceiver(w, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        if(!mWifi.isWifiEnabled())
            Toast.makeText(getApplicationContext(),"please enable wifi",Toast.LENGTH_LONG).show();
            mWifi.startScan();
            addItemsToList();
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(getApplication(),"",Toast.LENGTH_SHORT).show();
    }

    public void startScan(View v){
        if(!mWifi.isWifiEnabled())
            Toast.makeText(getApplicationContext(),"please enable wifi",Toast.LENGTH_LONG).show();
            mWifi.startScan();
            addItemsToList();
    }
    public void addItemsToList(){

        wifiList=mWifi.getScanResults();
        wifiListAdapter = new WifiListAdapter(this, wifiList);
        wifiListView.setAdapter(wifiListAdapter);
    }

    class WifiReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            wifiList=mWifi.getScanResults();
            for (int i = 0; i < wifiList.size(); i++) {
//                sensorList.add(""+wifiList.get(i).toString());
                Log.d("Wifi", wifiList.get(i).toString());
            }
        }
    }
}

package com.localisation.indoorlocalisation;

import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> sensorList;
    CompleteListAdapter sensorListAdapter;
    ListView sensorListView;
    WifiReceiver w;
    List<android.net.wifi.ScanResult> wifiList;
    WifiManager mWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager sMgr;
        sMgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        Log.d("Sensor", "aa" + sMgr.getSensorList(Sensor.TYPE_ALL));
        List<Sensor> sensorLists = (List) sMgr.getSensorList(Sensor.TYPE_ALL);
        mWifi= (WifiManager)getSystemService(Context.WIFI_SERVICE);
        registerReceiver(w,new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        mWifi.startScan();
        sensorList = new ArrayList<String>();
        for (int i = 0; i < sensorLists.size(); i++) {
            sensorList.add(""+sensorLists.get(i).getName());
        }
        sensorListView = (ListView) findViewById(R.id.sensorList);
        sensorListAdapter = new CompleteListAdapter(this, sensorList);
        sensorListView.setAdapter(sensorListAdapter);
        wifiList=mWifi.getScanResults();
        sensorList.add("Network Below");
        for (int i = 0; i < wifiList.size(); i++) {
            sensorList.add(""+wifiList.get(i).toString());
            Toast.makeText(getApplicationContext(), "" + wifiList.get(i).SSID, Toast.LENGTH_LONG).show();

        }
    }
class WifiReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        wifiList=mWifi.getScanResults();
        for (int i = 0; i < wifiList.size(); i++) {
            sensorList.add(""+wifiList.get(i).toString());
            Log.d("Wifi",wifiList.get(i).toString());
        }
    }
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

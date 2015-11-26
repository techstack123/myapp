package com.localisation.indoorlocalisation;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Praveen on 11/26/2015.
 */
public class WifiListAdapter  extends BaseAdapter{
    private Activity mContext;
    private List<ScanResult> mList;

    private LayoutInflater mLayoutInflater = null;

    public WifiListAdapter(Activity context, List<ScanResult> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int pos) {
        return mList.get(pos);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        WifiListViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.list_item, null);
            viewHolder = new WifiListViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (WifiListViewHolder) v.getTag();
        }
        viewHolder.SSID.setText(mList.get(position).SSID);
        viewHolder.frequency.setText("Frequency " + mList.get(position).frequency);
        viewHolder.bssid.setText("BSSID :" + mList.get(position).BSSID+"  Level :  " + mList.get(position).level +"dBm");
        return v;
    }
}

class WifiListViewHolder {
    public TextView SSID, frequency, bssid;

    public WifiListViewHolder(View base) {
        SSID = (TextView) base.findViewById(R.id.sensorName);
        frequency = (TextView) base.findViewById(R.id.sensorVersion);
        bssid = (TextView) base.findViewById(R.id.sensorVendor);

    }
}
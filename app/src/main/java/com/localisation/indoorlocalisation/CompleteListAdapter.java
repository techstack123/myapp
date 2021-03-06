package com.localisation.indoorlocalisation;

/**
 * Created by Praveen on 7/18/2015.
 */
import java.util.List;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CompleteListAdapter extends BaseAdapter {
    private Activity mContext;
    private List<Sensor> mList;

    private LayoutInflater mLayoutInflater = null;
    public CompleteListAdapter(Activity context, List<Sensor> list) {
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
        CompleteListViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.list_item, null);
            viewHolder = new CompleteListViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (CompleteListViewHolder) v.getTag();
        }
        viewHolder.sensorName.setText(mList.get(position).getName().toUpperCase().charAt(0)+""+mList.get(position).getName().substring(1));
        viewHolder.sensorVersion.setText("Version : "+mList.get(position).getVersion());
        viewHolder.sensorVendor.setText("Vendor :" + mList.get(position).getVendor());
        return v;
    }
}
class CompleteListViewHolder {
    public TextView sensorName,sensorVersion,sensorVendor;
    public CompleteListViewHolder(View base) {
        sensorName = (TextView) base.findViewById(R.id.sensorName);
        sensorVersion = (TextView) base.findViewById(R.id.sensorVersion);
        sensorVendor=(TextView)base.findViewById(R.id.sensorVendor);

    }
}
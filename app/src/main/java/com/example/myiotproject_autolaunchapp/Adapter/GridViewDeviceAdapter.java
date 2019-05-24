package com.example.myiotproject_autolaunchapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myiotproject_autolaunchapp.R;
import com.example.myiotproject_autolaunchapp.StringUtils;

import java.util.ArrayList;

public class GridViewDeviceAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<String> dataGridView;
    ViewHolder viewHolder;

    public GridViewDeviceAdapter(Context context, int layout, ArrayList<String> dataGridView) {
        this.context = context;
        this.layout = layout;
        this.dataGridView = dataGridView;
    }

    @Override
    public int getCount() {
        return dataGridView.size();
    }

    @Override
    public Object getItem(int position) {
        return dataGridView.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_gridview_device, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.iconDevice = view.findViewById(R.id.imgView);
            viewHolder.txtDeviceName = view.findViewById(R.id.txt);
            view.setTag(viewHolder);
        }else viewHolder = (ViewHolder) view.getTag();
        String nameDevice = dataGridView.get(position);
        viewHolder.txtDeviceName.setText(dataGridView.get(position));
        //viewHolder.iconDevice.setImageResource(R.drawable.light1);
        if (StringUtils.removeAccent(nameDevice.trim()).toLowerCase().contains("đen"))
            viewHolder.iconDevice.setImageResource(R.drawable.light1);
        if (StringUtils.removeAccent(nameDevice.trim()).toLowerCase().contains("đen tran"))
            viewHolder.iconDevice.setImageResource(R.drawable.lampflo);
        if (StringUtils.removeAccent(nameDevice.trim()).toLowerCase().contains("quat"))
            viewHolder.iconDevice.setImageResource(R.drawable.fan);
        if (StringUtils.removeAccent(nameDevice.trim()).toLowerCase().contains("quat tran"))
            viewHolder.iconDevice.setImageResource(R.drawable.lamp);
//        if (StringUtils.removeAccent(nameDevice.trim()).toLowerCase().contains("bep"))
//            viewHolder.iconDevice.setImageResource(R.drawable.gas2);
//        if (nameDevice.trim().contains("Rem"))
//            viewHolder.iconDevice.setImageResource(R.drawable.bathroom2);
        if (StringUtils.removeAccent(nameDevice.trim()).toLowerCase().contains("tv"))
            viewHolder.iconDevice.setImageResource(R.drawable.television1);
        if (StringUtils.removeAccent(nameDevice.trim()).toLowerCase().contains("diêu hoa"))
            viewHolder.iconDevice.setImageResource(R.drawable.airconditioner);
        return view;

    }

    public class ViewHolder {
        ImageView iconDevice;
        TextView txtDeviceName;
        TextView state;
    }
}

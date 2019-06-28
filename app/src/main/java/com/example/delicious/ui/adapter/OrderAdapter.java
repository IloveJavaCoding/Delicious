package com.example.delicious.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.delicious.R;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String [] order_number;

    public OrderAdapter(Context context, String [] order_number) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.order_number = order_number;
    }

    @Override
    public int getCount() {
        return order_number.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{//contain the attrs in the layout_listview;
        public TextView Torder;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.layout_order_list, null);
            holder = new ViewHolder();
            holder.Torder = (TextView)convertView.findViewById(R.id.tv);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.Torder.setText(order_number[position]);

        return convertView;
    }
}

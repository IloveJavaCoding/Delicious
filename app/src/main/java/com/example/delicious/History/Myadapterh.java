package com.example.delicious.History;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.delicious.R;
import com.example.delicious.Self_class.Order_record;

public class Myadapterh extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private Order_record[] orders;

    public Myadapterh(Context context, Order_record[] orders){
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.orders = orders;
    }
    @Override
    public int getCount() {
        return orders.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        public TextView Tshopname,Tfoodname,Tprice,Tnum,Tdate;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.layout_history,null);
            holder = new ViewHolder();
            holder.Tshopname = (TextView)convertView.findViewById(R.id.Tshop);
            holder.Tfoodname = (TextView)convertView.findViewById(R.id.Tfname);
            holder.Tprice = (TextView)convertView.findViewById(R.id.Tprice);
            holder.Tnum = (TextView)convertView.findViewById(R.id.Tnum);
            holder.Tdate = (TextView)convertView.findViewById(R.id.Tdate);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //set relative value;
        holder.Tshopname.setText(orders[position].getShop_name());
        holder.Tfoodname.setText(orders[position].getIteam_name());
        holder.Tprice.setText(Double.toString(orders[position].getPrice()));
        holder.Tnum.setText(Integer.toString(orders[position].getNumber()));
        holder.Tdate.setText(orders[position].getReceive_time());

        return convertView;
    }
}

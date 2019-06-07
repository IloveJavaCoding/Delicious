package com.example.foodui.History;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.foodui.R;

public class Myadapterh extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;

    public Myadapterh(Context context){
        this.context=context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 10;
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
        public TextView Tsname,Tfname,Tprice,Tnum,Tdate;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.layout_history,null);
            holder = new ViewHolder();
            holder.Tsname = (TextView)convertView.findViewById(R.id.Tshop);
            holder.Tfname = (TextView)convertView.findViewById(R.id.Tfname);
            holder.Tprice = (TextView)convertView.findViewById(R.id.Tprice);
            holder.Tnum = (TextView)convertView.findViewById(R.id.Tnum);
            holder.Tdate = (TextView)convertView.findViewById(R.id.Tdate);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //set relative value;
        holder.Tsname.setText("Shop "+(position+1));
        holder.Tfname.setText("Food "+(position+1));

        return convertView;
    }
}

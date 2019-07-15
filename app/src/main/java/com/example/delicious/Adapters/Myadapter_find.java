package com.example.delicious.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.delicious.R;
import com.example.delicious.Self_class.Item_info;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Myadapter_find extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private Item_info[] items;

    public Myadapter_find(Context context, Item_info[] items){//
        this.context=context;
        inflater = LayoutInflater.from(context);
        List<Item_info> list = Arrays.asList(items);
        Collections.shuffle(list);
        this.items = (Item_info[]) list.toArray();
    }
    @Override
    public int getCount() {
        return 6;
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
        public ImageView I_food;
        public TextView Tname, Tshopname, Tprice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.layout_find,null);
            holder = new ViewHolder();
            holder.I_food = convertView.findViewById(R.id.I_food);
            holder.Tname  = convertView.findViewById(R.id.tv_name);
            holder.Tshopname = convertView.findViewById(R.id.tv_shopname);
            holder.Tprice = convertView.findViewById(R.id.tv_price);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.Tname.setText(items[position].getIteam_name());
        holder.Tshopname.setText(items[position].getShop_name());
        holder.Tprice.setText(Double.toString(items[position].getPrice()));

        Glide.with(context).load(items[position].getLink().trim()).into(holder.I_food);

        return convertView;
    }
}

package com.example.delicious.ui.adapter;

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

public class MyOrderAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private Item_info[] items;
    Class<com.example.delicious.R.drawable> cla = R.drawable.class;

    public MyOrderAdapter(Context context, Item_info[] items){
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.length;//set
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
        public ImageView Img;
        public TextView Tsname,Tfname,Tprice,Tnum;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.layout_order,null);
            holder = new ViewHolder();
            holder.Img = (ImageView)convertView.findViewById(R.id.Ifood);
            holder.Tsname = (TextView)convertView.findViewById(R.id.Tshop);
            holder.Tfname = (TextView)convertView.findViewById(R.id.Tname);
            holder.Tprice = (TextView)convertView.findViewById(R.id.Tprice);
            holder.Tnum = (TextView)convertView.findViewById(R.id.Tnum);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //set relative value
        holder.Tsname.setText(items[position].getShop_name());
        holder.Tfname.setText(items[position].getIteam_name());
        holder.Tprice.setText(Double.toString(items[position].getPrice()));
        holder.Tnum.setText(Integer.toString(items[position].getNumber()));

        String pic = items[position].getTag().toLowerCase();
        try {
            int id = cla.getDeclaredField(pic).getInt(null);
            holder.Img.setBackgroundResource(id);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        //Glide.with(context).load("https://pokemon.gameinfo.io/images/pokemon-go/470-00.png").into(holder.Img);
        return convertView;
    }
}

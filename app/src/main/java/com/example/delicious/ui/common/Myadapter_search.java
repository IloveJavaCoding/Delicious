package com.example.delicious.ui.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.delicious.R;
import com.example.delicious.Restaurant_2.Myadapter2;
import com.example.delicious.Self_class.Item_info;

public class Myadapter_search extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private Item_info[] items;

    Class<com.example.delicious.R.drawable> cla = R.drawable.class;
    public Myadapter_search(Context context, Item_info[] item){
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.items = item;
    }
    @Override
    public int getCount() {
        return items.length;
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
        public ImageView Img;
        public TextView Tshopname,Tprice,Tfoodname;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.layout_search,null);
            holder = new ViewHolder();
            holder.Img = (ImageView)convertView.findViewById(R.id.Ifood);
            holder.Tshopname = (TextView)convertView.findViewById(R.id.Tshopname);
            holder.Tprice = (TextView)convertView.findViewById(R.id.Tprice);
            holder.Tfoodname  =(TextView)convertView.findViewById(R.id.Tfoodname);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.Tshopname.setText(items[position].getShop_name());
        holder.Tfoodname.setText(items[position].getIteam_name());
        holder.Tprice.setText(Double.toString(items[position].getPrice()));

        String pic = items[position].getTag().toLowerCase();
        try {
            int id = cla.getDeclaredField(pic).getInt(null);
            holder.Img.setBackgroundResource(id);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        //Glide.with(context).load("https://pokemon.gameinfo.io/images/pokemon-go/470-00.png").into(holder.IV);
        return convertView;
    }
}

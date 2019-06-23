package com.example.foodui.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodui.R;
import com.example.foodui.Self_class.Shop_Info;

public class Homeadapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private Shop_Info[] sh;
    Class<com.example.foodui.R.drawable> cla = R.drawable.class;

    public Homeadapter(Context context, Shop_Info[] sh){//
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.sh = sh;
    }
    @Override
    public int getCount() {
        return sh.length;
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
        public ImageView Logo;
        public TextView Tg;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.layout_shop,null);
            holder = new ViewHolder();
            holder.Logo = (ImageView)convertView.findViewById(R.id.Ilogo);
            holder.Tg  =(TextView)convertView.findViewById(R.id.Tg);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.Tg.setText(sh[position].getGenre());

        String pic = sh[position].getGenre().toLowerCase();
        try {
            int id = cla.getDeclaredField(pic).getInt(null);
            holder.Logo.setBackgroundResource(id);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        //Glide.with(context).load("https://pokemon.gameinfo.io/images/pokemon-go/470-00.png").into(holder.Logo);
        return convertView;
    }

}

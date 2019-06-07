package com.example.foodui.Myorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodui.R;

public class Myadapterorder extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;

    public Myadapterorder(Context context){
        this.context=context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 3;//set
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
            holder.Tfname = (TextView)convertView.findViewById(R.id.Tfname);
            holder.Tprice = (TextView)convertView.findViewById(R.id.Tprice);
            holder.Tnum = (TextView)convertView.findViewById(R.id.Tnum);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //set relative value

        Glide.with(context).load("https://pokemon.gameinfo.io/images/pokemon-go/470-00.png").into(holder.Img);

        return convertView;
    }
}

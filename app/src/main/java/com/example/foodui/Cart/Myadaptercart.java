package com.example.foodui.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodui.Noodle.Myadapter2;
import com.example.foodui.R;

public class Myadaptercart extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;

    public Myadaptercart(Context context){
        this.context=context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 3;
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
        public ImageView IV,Iadd,Imin;
        public TextView Tfname,Tprice,Tnum;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.layout_cart,null);
            holder = new ViewHolder();
            holder.IV = (ImageView)convertView.findViewById(R.id.Ifood);
            holder.Imin = (ImageView)convertView.findViewById(R.id.Imin);
            holder.Iadd = (ImageView)convertView.findViewById(R.id.Iadd);
            holder.Tfname = (TextView)convertView.findViewById(R.id.Tname);
            holder.Tprice = (TextView)convertView.findViewById(R.id.Tprice);
            holder.Tnum = (TextView)convertView.findViewById(R.id.Tnum);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.Tfname.setText("Food "+(position+1));

        Glide.with(context).load("https://pokemon.gameinfo.io/images/pokemon-go/470-00.png").into(holder.IV);
        return convertView;
    }
}

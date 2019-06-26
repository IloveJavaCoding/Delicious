package com.example.delicious.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.delicious.R;

public class MyadaptermyCart extends BaseAdapter {
    private Context cont;
    private LayoutInflater layf;

    public MyadaptermyCart(Context content){
        this.cont=content;
        layf = LayoutInflater.from(content);
    }
    @Override
    public int getCount() {
        return 5;
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
        public ImageView Img, Imin, Iadd;
        public TextView Tname,Tprice,Tnum;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView=layf.inflate(R.layout.layout_cart,null);
            holder = new ViewHolder();
            holder.Img = (ImageView)convertView.findViewById(R.id.Ifood);
            holder.Imin = (ImageView)convertView.findViewById(R.id.Imin);
            holder.Iadd = (ImageView)convertView.findViewById(R.id.Iadd);
            holder.Tname = (TextView)convertView.findViewById(R.id.Tfname);
            holder.Tprice = (TextView)convertView.findViewById(R.id.Tprice);
            holder.Tnum = (TextView)convertView.findViewById(R.id.Tnum);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //set value;
        holder.Tprice.setText(Integer.toString(position*3+10));//can not be pure int;

        if(position%3==0){
            Glide.with(cont).load("https://pokemon.gamerpub.net/img/pkm/tb/130.png").into(holder.Img);
            holder.Tname.setText("暴鲤龙"+(position/3+1));
            holder.Tnum.setText(Integer.toString(position%3+1));
        }else if(position%3==1){
            Glide.with(cont).load("https://pokemon.gamerpub.net/img/pkm/383.png").into(holder.Img);
            holder.Tname.setText("哥拉顿"+(position/3+1));
            holder.Tnum.setText(Integer.toString(position%3+1));
        }else if(position%3==2){
            Glide.with(cont).load("https://pokemon.gamerpub.net/img/pkm/tb/384.png").into(holder.Img);
            holder.Tname.setText("列克萨"+(position/3+1));
            holder.Tnum.setText(Integer.toString(position%3+1));
        }

        holder.Iadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.Tnum.setText(Integer.parseInt(holder.Tnum.getText())+1);
            }
        });

        holder.Imin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.Tnum.setText(Integer.parseInt(holder.Tnum.getText())+1);
            }
        });

        return convertView;
    }
}

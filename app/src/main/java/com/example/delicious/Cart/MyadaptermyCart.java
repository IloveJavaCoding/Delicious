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
import com.example.delicious.Self_class.Item_info;

public class MyadaptermyCart extends BaseAdapter implements View.OnClickListener{
    private Context cont;
    private LayoutInflater layf;
    private Item_info[] items;
    private InListener mlistener;

    public MyadaptermyCart(Context content, Item_info[] items, InListener listener){
        this.cont=content;
        layf = LayoutInflater.from(content);
        this.items = items;
        this.mlistener = listener;
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

    @Override
    public void onClick(View v) {
        mlistener.itemClick(v);
    }

    public interface InListener{
        void itemClick(View v);
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
        holder.Tname.setText(items[position].getIteam_name());
        holder.Tprice.setText(Double.toString(items[position].getPrice()));
        holder.Tnum.setText(Integer.toString(items[position].getNumber()));

        holder.Imin.setOnClickListener(this);
        holder.Imin.setTag(position);
        holder.Iadd.setOnClickListener(this);
        holder.Iadd.setTag(position);

        Glide.with(cont).load("https://pokemon.gameinfo.io/images/pokemon-go/470-00.png").into(holder.Img);
        return convertView;
    }
}

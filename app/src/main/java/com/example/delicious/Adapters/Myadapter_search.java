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

public class Myadapter_search extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private Item_info[] items;
    private String def_food_link = "https://m.qpic.cn/psb?/V11RgLFS05qG0c/xo6sdlGdTkU2E4k3jc0BOTGu3VMGBWr9qVCa32cuONc!/b/dE0BAAAAAAAA&bo=8AC4APAAuAADByI!&rf=viewer_4";

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

        if(!items[position].getLink().equals("")){
            Glide.with(context).load(items[position].getLink().trim()).into(holder.Img);
        }
        else{
            Glide.with(context).load(def_food_link.trim()).into(holder.Img);
        }

        return convertView;
    }
}

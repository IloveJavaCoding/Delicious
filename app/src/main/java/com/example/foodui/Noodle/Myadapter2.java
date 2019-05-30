package com.example.foodui.Noodle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodui.Milktea.Myadapter;
import com.example.foodui.R;

public class Myadapter2 extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;

    public Myadapter2(Context context){
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
    static class ViewHolder{//contain the attrs in the layout_listview;
        public ImageView IV;
        public TextView Tname,Tprice;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.layout_foods2,null);
            holder = new ViewHolder();
            holder.IV = (ImageView)convertView.findViewById(R.id.Ifood);
            holder.Tname = (TextView)convertView.findViewById(R.id.Tname);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.Tname.setText("Food "+(position+1));

        Glide.with(context).load("https://pokemon.gameinfo.io/images/pokemon-go/470-00.png").into(holder.IV);
        return convertView;
    }
}

package com.example.delicious.Restaurant_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.delicious.Self_class.Item_info;
import com.example.delicious.R;

public class Myadapter2 extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private Item_info[] items;
    private ViewHolder holder = null;

    public Myadapter2(Context context, Item_info[] item){
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
        public ImageView IV, Min,Add;
        public TextView Tname,Tprice,Tnum;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=inflater.inflate(R.layout.layout_foods2,null);
            holder = new ViewHolder();
            holder.IV = (ImageView)convertView.findViewById(R.id.Ifood);
            holder.Min = (ImageView)convertView.findViewById(R.id.Imin);
            holder.Add = (ImageView)convertView.findViewById(R.id.Iadd);
            holder.Tname = (TextView)convertView.findViewById(R.id.Tname);
            holder.Tprice = (TextView)convertView.findViewById(R.id.Tprice);
            holder.Tnum  =(TextView)convertView.findViewById(R.id.Tnum);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.Tname.setText(items[position].getIteam_name());
        holder.Tprice.setText(Double.toString(items[position].getPrice()));
        holder.Tnum.setText("0");

        holder.Min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(holder.Tnum.getText().toString());
                if(num>0){
                    num--;
                    holder.Tnum.setText(num);
                }
            }
        });

        holder.Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(holder.Tnum.getText().toString());
                num++;
                holder.Tnum.setText(num);
            }
        });

        Glide.with(context).load("https://pokemon.gameinfo.io/images/pokemon-go/470-00.png").into(holder.IV);
        return convertView;
    }
}

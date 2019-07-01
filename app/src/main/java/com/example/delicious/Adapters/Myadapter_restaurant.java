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

public class Myadapter_restaurant extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private LayoutInflater inflater;
    private Item_info[] items;
    private InListener mlistener;
    private String def_link = "https://m.qpic.cn/psb?/V11RgLFS05qG0c/hVu76vCbtl9TRGVm8NxZiF4WQF33.wR2cO8soZ*.DGI!/b/dL4AAAAAAAAA&bo=AAEAAQABAAEDByI!&rf=viewer_4";

    public Myadapter_restaurant(Context context, Item_info[] item, InListener mlistener){
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.items = item;
        this.mlistener= mlistener;
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
        public ImageView IV, Min,Add;
        public TextView Tname,Tprice,Tnum;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.layout_foods_restaurent,null);
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
        holder.Tnum.setText(Integer.toString(items[position].getNumber()));

        holder.Min.setOnClickListener(this);
        holder.Min.setTag(position);
        holder.Add.setOnClickListener(this);
        holder.Add.setTag(position);

        //food img

        if(!items[position].getLink().equals("")){
            Glide.with(context).load(items[position].getLink().trim()).into(holder.IV);
        }else{
            Glide.with(context).load(def_link.trim()).into(holder.IV);
        }

        return convertView;
    }
}

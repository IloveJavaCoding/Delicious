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
import com.example.delicious.Self_class.Shop_Info;

public class Myadapter_home extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private Shop_Info[] sh;
    private String def_link = "https://m.qpic.cn/psb?/V11RgLFS05qG0c/hVu76vCbtl9TRGVm8NxZiF4WQF33.wR2cO8soZ*.DGI!/b/dL4AAAAAAAAA&bo=AAEAAQABAAEDByI!&rf=viewer_4";

    public Myadapter_home(Context context, Shop_Info[] sh){//
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

        if(!sh[position].getGenre_link().trim().equals("")){
            Glide.with(context).load(sh[position].getGenre_link().trim()).into(holder.Logo);
        }else{
            Glide.with(context).load(def_link).into(holder.Logo);
        }

        return convertView;
    }
}

package com.example.delicious.Activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.delicious.Adapters.Myadapter_restaurant;
import com.example.delicious.R;
import com.example.delicious.Self_class.Controls;
import com.example.delicious.Self_class.Item_info;
import com.example.delicious.Self_class.Shop_Info;

public class Restaurant_page extends AppCompatActivity implements Myadapter_restaurant.InListener {
    private ListView listView;
    private Myadapter_restaurant myadapterRestaurant;
    private ImageView Iback, Iuser, Ilogo;
    private Button Bdetail;
    private TextView Sname,Srate,Stime;
    private TextView Titems, TRM;
    private RatingBar starbar;
    private LinearLayout linearLayout;
    private final double discount = 0.9;
    Shop_Info sh;
    Item_info[] items;
    Controls Lock;
    private String def_link = "https://m.qpic.cn/psb?/V11RgLFS05qG0c/hVu76vCbtl9TRGVm8NxZiF4WQF33.wR2cO8soZ*.DGI!/b/dL4AAAAAAAAA&bo=AAEAAQABAAEDByI!&rf=viewer_4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Get_data();
        Init();
        Update_pictures();
        setListener();
    }

    private void Get_data(){
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Shop_Info [] shop = (Shop_Info[])bundle.getSerializable("shop");
        int index = bundle.getInt("index");

        sh = shop[index];
        items  = (Item_info[])bundle.getSerializable("item") ;
    }

    private void Init(){
        //-------------------------------------------------------
        listView = findViewById(R.id.LV);
        myadapterRestaurant = new Myadapter_restaurant(Restaurant_page.this, items,this);
        listView.setAdapter(myadapterRestaurant);

        Iback = findViewById(R.id.Iback);
        Iuser = findViewById(R.id.Iuser);
        Bdetail = findViewById(R.id.Bdetail);

        //--------------------------------------------------
        linearLayout = findViewById(R.id.ly);
        Ilogo= findViewById(R.id.Ilogo);
        Sname = findViewById(R.id.Tname);
        Srate = findViewById(R.id.Tvalue);
        Stime = findViewById(R.id.Ttime);
        Titems = findViewById(R.id.Titems);
        TRM = findViewById(R.id.TRM);
        starbar = findViewById(R.id.bar);

        Sname.setText(sh.getShop_name());
        Srate.setText(Double.toString(sh.getRate()));
        Stime.setText(sh.getOpen_time());
        starbar.setRating((float)sh.getRate());
    }

    private void Update_pictures(){
        Glide.with(this).load(sh.getBg_link().trim()).into(new CustomViewTarget(linearLayout) {
            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {

            }

            @Override
            public void onResourceReady(@NonNull Object resource, @Nullable Transition transition)
            {
                this.view.setBackground((Drawable) resource);
            }
            @Override
            protected void onResourceCleared(@Nullable Drawable placeholder) {

            }

        });

        if(!sh.getLogo_link().trim().equals("")){
            Glide.with(this).load(sh.getLogo_link().trim()).into(Ilogo);
        }else{
            Glide.with(this).load(def_link.trim()).into(Ilogo);
        }

    }

    private void setListener(){
        Iback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restaurant_page.this, Homepage.class);
                startActivity(intent);
                finish();
            }
        });
        Iuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restaurant_page.this, User_page.class);
                startActivity(intent);
                finish();
            }
        });
        Bdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lock.setLock2(0);
                Intent intent = new Intent(Restaurant_page.this, MyCart_page.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("food",items);
                intent.putExtras(bundle);

                startActivity(intent);
                finish();
            }
        });
    }

    private void Flash(){
        int total_items = 0;
        double total_price = 0;
        for(int i=0; i<items.length; i++){
            total_items+=items[i].getNumber();
            total_price+=(items[i].getPrice() * items[i].getNumber());
        }
        //discount
        if(total_items>=2){
            if(total_price>50){
                total_price *= discount;
            }
            total_price = Math.floor(total_price);
        }

        Titems.setText(Integer.toString(total_items));
        TRM.setText(Double.toString(total_price));
    }

    @Override
    public void itemClick(View v) {
        int position = (Integer) v.getTag();
        switch(v.getId()){
            case R.id.Imin:
                int temp =  items[position].getNumber();
                if(temp>0){
                    items[position].setNumber(items[position].getNumber()-1);
                    myadapterRestaurant.notifyDataSetChanged();
                    Flash();
                }else{
                    Toast.makeText(getApplicationContext(),"You haven't choose anyone!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Iadd:
                items[position].setNumber(items[position].getNumber()+1);
                myadapterRestaurant.notifyDataSetChanged();
                Flash();
                break;
            default:
                break;
        }
    }
}

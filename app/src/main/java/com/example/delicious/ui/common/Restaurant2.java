package com.example.delicious.ui.common;

import android.content.Intent;
import android.os.Build;
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

import com.example.delicious.Cart.Cart;
import com.example.delicious.Self_class.Controls;
import com.example.delicious.Self_class.Item_info;
import com.example.delicious.ui.adapter.RestaurantAdapter_2;
import com.example.delicious.ui.common.sign_out.Logout1;
import com.example.delicious.R;
import com.example.delicious.Self_class.Shop_Info;

public class Restaurant2 extends AppCompatActivity implements RestaurantAdapter_2.InListener {
    private ListView listView;
    private RestaurantAdapter_2 myadapter2;
    private ImageView Iback, Iuser, Ilogo;
    private Button Bdetail;
    private TextView Sname,Srate,Stime;
    private TextView Titems, TRM;
    private RatingBar starbar;
    private LinearLayout linearLayout;
    Shop_Info sh;
    Item_info[] items;
    Controls Lock;

    Class<com.example.delicious.R.drawable> cla = R.drawable.class;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant2);

        Get_data();
        Init();
        Update_pictures();
        setListener();
        Toast.makeText(getApplicationContext(), Integer.toString(Lock.getLock2()), Toast.LENGTH_SHORT).show();
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
        myadapter2 = new RestaurantAdapter_2(Restaurant2.this, items,this);
        listView.setAdapter(myadapter2);

        Iback = (ImageView)findViewById(R.id.Iback);
        Iuser = (ImageView)findViewById(R.id.Iuser);
        Bdetail = (Button)findViewById(R.id.Bdetail);

        //--------------------------------------------------
        linearLayout = (LinearLayout)findViewById(R.id.ly);
        Ilogo= (ImageView)findViewById(R.id.Ilogo);
        Sname = (TextView)findViewById(R.id.Tname);
        Srate = (TextView)findViewById(R.id.Tvalue);
        Stime = (TextView)findViewById(R.id.Ttime);
        Titems = (TextView)findViewById(R.id.Titems);
        TRM = (TextView)findViewById(R.id.TRM);
        starbar = (RatingBar)findViewById(R.id.bar);

        Sname.setText(sh.getShop_name());
        Srate.setText(Double.toString(sh.getRate()));
        Stime.setText(sh.getOpen_time());
        starbar.setRating((float)sh.getRate());
    }

    private void Update_pictures(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String bg = sh.getId().toLowerCase()+ "_bg";
            try {
                int id = cla.getDeclaredField(bg).getInt(null);
                linearLayout.setBackgroundResource(id);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        String pic = sh.getId().toLowerCase();
        try {
            int id = cla.getDeclaredField(pic).getInt(null);
            Ilogo.setBackgroundResource(id);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void setListener(){
        Iback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restaurant2.this, Homepage.class);
                startActivity(intent);
                finish();
            }
        });
        Iuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restaurant2.this, Logout1.class);
                startActivity(intent);
                finish();
            }
        });
        Bdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lock.setLock2(0);
                Intent intent = new Intent(Restaurant2.this, Cart.class);
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
                    myadapter2.notifyDataSetChanged();
                    Flash();
                }else{
                    Toast.makeText(getApplicationContext(),"You haven't choose anyone!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Iadd:
                //Toast.makeText(getApplicationContext(),"add",Toast.LENGTH_SHORT).show();
                items[position].setNumber(items[position].getNumber()+1);
                myadapter2.notifyDataSetChanged();
                Flash();
                break;
            default:
                break;
        }
    }
}

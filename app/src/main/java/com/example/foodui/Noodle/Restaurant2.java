package com.example.foodui.Noodle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodui.Cart.Cart;
import com.example.foodui.Homepage;
import com.example.foodui.Item_info;
import com.example.foodui.Logout1;
import com.example.foodui.Milktea.Restaurant1;
import com.example.foodui.R;
import com.example.foodui.Shop_Info;

public class Restaurant2 extends AppCompatActivity {
    private ListView listView;
    private GridView gridView;
    private ImageView Iback, Isearch, Iuser, Ilogo;
    private Button Bdetail;
    private TextView Sname,Srate,Stime;
    Shop_Info sh;
    Item_info[] items;
    Class<com.example.foodui.R.drawable> cla = R.drawable.class;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant2);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Shop_Info [] shop = (Shop_Info[])bundle.getSerializable("shop");
        int index = (int)bundle.getInt("index");

        sh = shop[index];
        items  = (Item_info[])bundle.getSerializable("item") ;
        //------------------------------------------------
        listView = (ListView) findViewById(R.id.LV);
        listView.setAdapter(new Myadapter2(Restaurant2.this, items));

        Iback = (ImageView)findViewById(R.id.Iback);
        Iuser = (ImageView)findViewById(R.id.Iuser);
        Bdetail = (Button)findViewById(R.id.Bdetail);

        Ilogo= (ImageView)findViewById(R.id.Ilogo);
        Sname = (TextView)findViewById(R.id.Tname);
        Srate = (TextView)findViewById(R.id.Tvalue);
        Stime = (TextView)findViewById(R.id.Ttime);

        Sname.setText(sh.getShop_name());
        Srate.setText(Double.toString(sh.getRate()));
        Stime.setText(sh.getOpen_time());
        String pic = sh.getId().toLowerCase();
        try {
            int id = cla.getDeclaredField(pic).getInt(null);
            Ilogo.setBackgroundResource(id);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //----------------------------------------------------------------------

        Iback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restaurant2.this, Homepage.class);
                startActivity(intent);
            }
        });
        Iuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restaurant2.this, Logout1.class);
                startActivity(intent);
            }
        });
        Bdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restaurant2.this, Cart.class);
                startActivity(intent);
            }
        });
    }
}

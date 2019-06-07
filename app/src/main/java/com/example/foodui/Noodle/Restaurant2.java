package com.example.foodui.Noodle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.foodui.Cart.Cart;
import com.example.foodui.Homepage;
import com.example.foodui.Logout1;
import com.example.foodui.Milktea.Restaurant1;
import com.example.foodui.R;

public class Restaurant2 extends AppCompatActivity {
    private ListView listView;
    private GridView gridView;
    private ImageView Iback, Isearch, Iuser;
    private Button Bdetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant2);
        listView = (ListView) findViewById(R.id.LV);
        listView.setAdapter(new Myadapter2(Restaurant2.this));

        Iback = (ImageView)findViewById(R.id.Iback);
        Iuser = (ImageView)findViewById(R.id.Iuser);
        Bdetail = (Button)findViewById(R.id.Bdetail);

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

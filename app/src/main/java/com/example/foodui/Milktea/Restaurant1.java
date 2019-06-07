package com.example.foodui.Milktea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.foodui.Cart.Cart;
import com.example.foodui.Homepage;
import com.example.foodui.Logout1;
import com.example.foodui.R;

public class Restaurant1 extends AppCompatActivity {
    private GridView gridView;
    private ImageView Iback, Isearch, Iuser;
    private Button Bdetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant1);
        gridView =(GridView)findViewById(R.id.GV);
        gridView.setAdapter(new Myadapter(Restaurant1.this));

        Iback = (ImageView)findViewById(R.id.Iback);
        Iuser = (ImageView)findViewById(R.id.Iuser);
        Bdetail = (Button)findViewById(R.id.Bdetail);

        Iback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restaurant1.this, Homepage.class);
                startActivity(intent);
            }
        });
        Iuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restaurant1.this, Logout1.class);
                startActivity(intent);
            }
        });
        Bdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restaurant1.this, Cart.class);
                startActivity(intent);
            }
        });
    }
}

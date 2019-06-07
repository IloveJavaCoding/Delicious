package com.example.foodui.Cart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodui.Check.Check;
import com.example.foodui.Homepage;
import com.example.foodui.Logout1;
import com.example.foodui.R;

public class Cart extends AppCompatActivity {
    private ListView listView;
    private ImageView Iback;
    private Button BAll;
    private TextView Tmore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        listView = (ListView) findViewById(R.id.LVc);
        listView.setAdapter(new MyadaptermyCart(Cart.this));

        Iback = (ImageView)findViewById(R.id.Iback);
        Iback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, Homepage.class);
                startActivity(intent);
            }
        });

        BAll = (Button) findViewById(R.id.Ball);
        BAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, Check.class);
                startActivity(intent);
            }
        });

        Tmore = (TextView) findViewById(R.id.tv5);
        Tmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, Homepage.class);
                startActivity(intent);
            }
        });
    }
}

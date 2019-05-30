package com.example.foodui.Cart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.foodui.R;

public class cart extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        listView = (ListView) findViewById(R.id.LV);
        listView.setAdapter(new Myadaptercart(cart.this));
    }
}

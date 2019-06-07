package com.example.foodui.History;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.foodui.Logout1;
import com.example.foodui.R;

public class Order_history extends AppCompatActivity {
    private ListView listView;
    private ImageView Iback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        listView = (ListView) findViewById(R.id.Lvh);
        listView.setAdapter(new Myadapterh(Order_history.this));

        Iback = (ImageView)findViewById(R.id.Iback);
        Iback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Order_history.this, Logout1.class);
               startActivity(intent);
            }
        });

    }
}

package com.example.foodui.History;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.foodui.R;

public class Order_history extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        listView = (ListView) findViewById(R.id.Lvh);
        listView.setAdapter(new Myadapterh(Order_history.this));

    }
}

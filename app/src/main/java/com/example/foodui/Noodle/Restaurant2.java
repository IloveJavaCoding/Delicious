package com.example.foodui.Noodle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.foodui.R;

public class Restaurant2 extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant2);
        listView = (ListView) findViewById(R.id.LV);
        listView.setAdapter(new Myadapter2(Restaurant2.this));
    }
}

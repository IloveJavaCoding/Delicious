package com.example.foodui.Check;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.foodui.R;

public class Check extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        listView = (ListView)findViewById(R.id.LV2);
        listView.setAdapter(new Myadaptercheck(Check.this));
    }
}

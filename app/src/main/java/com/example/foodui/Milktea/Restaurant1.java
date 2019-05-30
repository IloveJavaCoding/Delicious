package com.example.foodui.Milktea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.foodui.R;

public class Restaurant1 extends AppCompatActivity {
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant1);
        gridView =(GridView)findViewById(R.id.GV);
        gridView.setAdapter(new Myadapter(Restaurant1.this));
    }
}

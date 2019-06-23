package com.example.foodui.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodui.Homepage;
import com.example.foodui.R;

public class Search_page extends AppCompatActivity {
    private ImageView Iback;
    private TextView Tsearch;
    private EditText EInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        Iback = (ImageView) findViewById(R.id.Iback);
        Tsearch = (TextView) findViewById(R.id.Tsearch);
        EInput = (EditText) findViewById(R.id.Esearch);

        Iback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search_page.this, Homepage.class);
                startActivity(intent);
            }
        });

        Tsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search_page.this, Homepage.class);
                startActivity(intent);
            }
        });
    }
}

package com.example.delicious.ui.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.delicious.Homepage;
import com.example.delicious.R;
import com.example.delicious.function.common.KeywordSearch;

public class SearchPage extends AppCompatActivity {
    private ImageView Iback;
    private TextView Tsearch;
    private EditText EInput;
    public String keyword;

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
                Intent intent = new Intent(SearchPage.this, Homepage.class);
                startActivity(intent);
            }
        });

        Tsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPage.this, Homepage.class);
                startActivity(intent);

                keyword = EInput.getText().toString().toLowerCase().trim();
                KeywordSearch.keywordSearch("item","item_name",keyword);
            }
        });
    }
}

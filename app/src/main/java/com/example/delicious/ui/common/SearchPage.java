package com.example.delicious.ui.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.delicious.Homepage;
import com.example.delicious.R;
import com.example.delicious.Self_class.MySingleton;
import com.example.delicious.database.Connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AppCompatActivity {
    private ImageView Iback;
    private TextView Tsearch;
    private EditText EInput;
    public String keyword;
    private static List<Integer> result = new ArrayList<>();
    private static JSONArray array;

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
                keywordSearch("item","item_name",keyword);
            }
        });
    }

    public void keywordSearch(String table, String field, String keyword) {
        JSONObject request = new JSONObject();
        try {
            request.put("table", table);
            request.put("field", field);
            request.put("keyword", keyword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(Request.Method.POST, Connection.search_url, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    array = response.getJSONArray("result");
                    for (int i = 0; i < array.length(); i++) {
                        result.add(array.getInt(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }
}

package com.example.delicious.function.common;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.delicious.database.Connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KeywordSearch {
    private static List<Integer> result = new ArrayList<>();

    public static List<Integer> keywordSearch(String table, String field, String keyword) {
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
                for(int i =0;i<response.length();i++){
                    result.add(response.getInt);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        return result;
    }
}

package com.example.delicious.function.common;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.delicious.database.Connection;

import org.json.JSONException;
import org.json.JSONObject;

public class KeywordSearch {
    private static int[] result;

    public static int[] keywordSearch(String table, String field, String keyword) {
        JSONObject request = new JSONObject();
        try {
            request.put("table", table);
            request.put("field", field);
            request.put("keyword", keyword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(Request.Method.POST, Connection.search_url, request, new Response.Listener<JSONObject>() {

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        return result;
    }
}

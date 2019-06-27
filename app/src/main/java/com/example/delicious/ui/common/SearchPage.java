package com.example.delicious.ui.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.delicious.Homepage;
import com.example.delicious.R;
import com.example.delicious.Restaurant_2.Restaurant2;
import com.example.delicious.Self_class.Item_info;
import com.example.delicious.Self_class.MySingleton;
import com.example.delicious.Self_class.Shop_Info;
import com.example.delicious.function.common.KeywordSearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AppCompatActivity {
    private ImageView Iback;
    private ListView listView;
    private TextView Tsearch;
    private EditText EInput;
    public String keyword;
    Item_info[] items,item,item_one;
    Shop_Info[] shops;

    private final String root1 = "http://10.66.93.27:80/delicious/db/";
    private final String root2 = "http://10.71.0.203:80/delicious/db/";

    private String path = root1 + "get_all_item.php";
    private String path2 = root1 + "get_all_shop.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        Get_all_item(path);
        Get_all_shop(path2);
        Init();
        SetListener();
    }

    private void Init(){
        listView = findViewById(R.id.list);
        Iback =  findViewById(R.id.Iback);
        Tsearch =  findViewById(R.id.Tsearch);
        EInput =  findViewById(R.id.Esearch);
    }

    private void SetListener(){
        Iback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPage.this, Homepage.class);
                startActivity(intent);
                finish();
            }
        });

        Tsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword = EInput.getText().toString().toLowerCase().trim();

                if(!keyword.equals("")){
                    item = Get_items_keyword(keyword,items);
                    if(item.length<1){
                        Toast.makeText(getApplicationContext(),"No march!!!!", Toast.LENGTH_SHORT).show();
                    }
                    listView.setAdapter(new Myadapter_search(SearchPage.this,item));
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String shop_name = item[position].getShop_name();
                int index = 0;
                for(int i=0; i<shops.length; i++){
                    if(shops[i].getShop_name().equals(shop_name)){
                        index = i;
                        break;
                    }
                }
                item_one = Get_items_oneshop(shop_name,items);

                Intent intent = new Intent(getApplicationContext(), Restaurant2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("shop",shops);
                bundle.putInt("index",index);
                bundle.putSerializable("item",item_one);
                intent.putExtras(bundle);

                startActivity(intent);
                finish();
            }
        });
    }

    private Item_info[] Get_items_keyword(String keyword,Item_info[] item ){
        List<Item_info> list = new ArrayList<Item_info>();
        int num=0;
        for(int i=0; i<item.length; i++){
            if(item[i].getIteam_name().toLowerCase().contains(keyword)){
                list.add(item[i]);
                num++;
            }
        }
        Item_info[] ite = list.toArray(new Item_info[num]);

        return ite;
    }

    private void Get_all_item(String path){
        final JSONObject request = null;
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(Request.Method.GET, path, request,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray acc = null;
                try {
                    if (response.getInt("state") ==1) {
                        acc = response.getJSONArray("item");
                        items = new Item_info[acc.length()];
                        for(int i=0;i<acc.length();i++){
                            items[i] = new Item_info();
                            JSONObject c = acc.getJSONObject(i);
                            String shop_name = c.getString("shop_name");
                            String item_name = c.getString("item_name");
                            double price = c.getDouble("price");
                            String tag = c.getString("tag");

                            items[i].setIteam_name(item_name);
                            items[i].setShop_name(shop_name);
                            items[i].setPrice(price);
                            items[i].setTag(tag);
                            items[i].setNumber(0);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }

    private Item_info[] Get_items_oneshop(String name,Item_info[] item ){
        List<Item_info> list = new ArrayList<Item_info>();
        int num=0;
        for(int i=0; i<item.length; i++){
            if(item[i].getShop_name().equals(name)){
                list.add(item[i]);
                num++;
            }
        }
        Item_info[] ite = list.toArray(new Item_info[num]);

        return ite;
    }

    private void Get_all_shop(String path){
        final JSONObject request = null;
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(Request.Method.GET, path, request,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray acc = null;
                try {
                    if (response.getInt("state") ==1) {
                        acc = response.getJSONArray("shop");
                        shops = new Shop_Info[acc.length()];
                        for(int i=0;i<acc.length();i++){
                            JSONObject c = acc.getJSONObject(i);

                            shops[i] = new Shop_Info();
                            String id = c.getString("id");
                            String shop_name = c.getString("shop_name");
                            String genre = c.getString("genre");
                            double rate =  c.getDouble("rate");
                            String open_time = c.getString("open_time");

                            shops[i].setId(id);
                            shops[i].setShop_name(shop_name);
                            shops[i].setGenre(genre);
                            shops[i].setRate(rate);
                            shops[i].setOpen_time(open_time);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"No internet",Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }
}

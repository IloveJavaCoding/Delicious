package com.example.delicious.Activities;

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
import com.example.delicious.Adapters.Myadapter_search;
import com.example.delicious.R;
import com.example.delicious.Self_class.Controls;
import com.example.delicious.Self_class.Item_info;
import com.example.delicious.Self_class.MySingleton;
import com.example.delicious.Self_class.Shop_Info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Search_Page extends AppCompatActivity {
    private ImageView Iback;
    private View view1, view2;
    private ListView listView;
    private TextView Tsearch;
    private EditText EInput;
    public String keyword;
    Item_info[] items,item,item_one;
    Shop_Info[] shops;

    Controls Lock;
    private String path;
    private String path2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        Init();
        SetListener();
    }

    private void Init(){
        path = Lock.getRoot() + "get_all_item.php";
        path2 = Lock.getRoot() + "get_all_shop.php";
        Get_all_item(path);
        Get_all_shop(path2);

        listView = findViewById(R.id.list);
        Iback =  findViewById(R.id.Iback);
        Tsearch =  findViewById(R.id.Tsearch);
        EInput =  findViewById(R.id.Esearch);
        view1 = findViewById(R.id.view);
        view2 = findViewById(R.id.view2);
    }

    private void SetListener(){
        Iback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search_Page.this, Homepage.class);
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
                    }else{
                        view1.setVisibility(View.VISIBLE);
                        view2.setVisibility(View.VISIBLE);
                        listView.setAdapter(new Myadapter_search(Search_Page.this,item));
                    }
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

                Intent intent = new Intent(getApplicationContext(), Restaurant_page.class);
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
            if(item[i].getIteam_name().toLowerCase().contains(keyword) || item[i].getShop_name().toLowerCase().contains(keyword)){
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
                            String link = c.getString("link");

                            items[i].setIteam_name(item_name);
                            items[i].setShop_name(shop_name);
                            items[i].setPrice(price);
                            items[i].setLink(link);
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
                            String logo_link = c.getString("logo_link");
                            String bg_link = c.getString("bg_link");
                            String genre_link = c.getString("genre_link");

                            shops[i].setId(id);
                            shops[i].setShop_name(shop_name);
                            shops[i].setGenre(genre);
                            shops[i].setRate(rate);
                            shops[i].setOpen_time(open_time);
                            shops[i].setLogo_link(logo_link);
                            shops[i].setBg_link(bg_link);
                            shops[i].setGenre_link(genre_link);
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

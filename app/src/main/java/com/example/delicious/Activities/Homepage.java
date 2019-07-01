package com.example.delicious.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.delicious.Adapters.Myadapter_home;
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

public class Homepage extends AppCompatActivity {
    private ImageView Isearch, Iuser;
    private GridView gridView;
    private static Shop_Info[] sh;
    Item_info[] items,item;
    Controls Lock;

    private final String root1 = "http://10.66.93.27:80/delicious/db/";
    private final String root2 = "http://10.71.0.203:80/delicious/db/";

    private String url = root1 + "get_all_shop.php";
    private String url2 = root1 + "get_item_by_name.php";
    private String url3 = root1 + "get_all_item.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Get_data();
        Init();
        SetListener();
    }

    private void Get_data(){
        Get_all_item(url3);

        if(Lock.getLock()==0){
            Intent intent = this.getIntent();
            Bundle bundle = intent.getExtras();
            sh = (Shop_Info[])bundle.getSerializable("shops");
            Lock.setLock(1);
        }
    }

    private void Init(){
        Isearch = (ImageView) findViewById(R.id.Isearch);
        Iuser = (ImageView) findViewById(R.id.Iusername);

        gridView =(GridView)findViewById(R.id.gar);
        gridView.setAdapter( new Myadapter_home(Homepage.this,sh));
    }

    private void SetListener() {
        OnClick onclick = new OnClick();
        Isearch.setOnClickListener(onclick);
        Iuser.setOnClickListener(onclick);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = null;
            Bundle bundle = null;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*
                if(position%2 ==0){
                    intent = new Intent(Homepage.this, Restaurant1.class);
                }else{
                    intent = new Intent(Homepage.this, Restaurant_page.class);
                }
                */
                intent = new Intent(Homepage.this, Restaurant_page.class);
                item = Get_items_oneshop(sh[position].getShop_name(),items);
                bundle = new Bundle();
                bundle.putSerializable("shop",sh);
                bundle.putInt("index",position);
                bundle.putSerializable("item",item);
                intent.putExtras(bundle);

                startActivity(intent);
                finish();
            }
        });
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            Bundle bundle;
            switch (v.getId()) {
                case R.id.Isearch:
                    intent = new Intent(Homepage.this, Search_Page.class);
                    break;
                case R.id.Iusername:
                    intent = new Intent(Homepage.this, User_page.class);
                    break;
            }
            startActivity(intent);
            finish();
        }
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
                        Toast.makeText(Homepage.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Homepage.this,"Error",Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);//continue other things
    }
}

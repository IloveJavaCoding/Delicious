package com.example.foodui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.foodui.Milktea.Restaurant1;
import com.example.foodui.Noodle.Restaurant2;
import com.example.foodui.Sign_In.Loginpag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Homepage extends AppCompatActivity {
    private ImageView Isearch, Iuser, Iad;
    private ImageView[] Img = new ImageView[4];
    private TextView TV1, TV2, TV3, TV4;
    Shop_Info[] sh;
    Item_info[] items;
    private int len = 4;
    private ProgressBar dialog;
    private String url = "http://10.66.93.27:80/delicious/db/get_all_shop.php";
    private String url2 = "http://10.66.93.27:80/delicious/db/get_item_by_name.php";
    private String url3 = "http://10.66.93.27:80/delicious/db/get_all_item.php";
    Class<com.example.foodui.R.drawable> cla1 = R.drawable.class;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        dialog =(ProgressBar)findViewById(R.id.bar);

        Isearch = (ImageView) findViewById(R.id.Isearch);
        Iuser = (ImageView) findViewById(R.id.Iusername);
        Iad = (ImageView) findViewById(R.id.Iad);

        Img[0] = (ImageView) findViewById(R.id.IR1);
        TV1 = (TextView) findViewById(R.id.TR1);
        Img[1] = (ImageView) findViewById(R.id.IR2);
        TV2 = (TextView) findViewById(R.id.TR2);
        Img[2] = (ImageView) findViewById(R.id.IR3);
        TV3 = (TextView) findViewById(R.id.TR3);
        Img[3] = (ImageView) findViewById(R.id.IR4);
        TV4 = (TextView) findViewById(R.id.TR4);

        SetListener();
        Get_all_shop(url);
        Get_all_item(url3);

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                    dialog.setVisibility(View.INVISIBLE);

                    TV1.setText(sh[0].getGenre());
                    TV2.setText(sh[1].getGenre());
                    TV3.setText(sh[2].getGenre());
                    TV4.setText(sh[3].getGenre());

                    //Toast.makeText(Homepage.this, items.length, Toast.LENGTH_SHORT).show();

                    /*
                    String[] pic = new String[len];
                    int[] id = new int[len];
                    for(int i=0; i<len; i++){
                        pic[i] = sh[i].getGenre().toLowerCase();
                        try {
                            id[i] = cla1.getDeclaredField(pic[i]).getInt(null);
                            Img[i].setBackgroundResource(id[i]);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                    }
                    */

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void SetListener() {
        OnClick onclick = new OnClick();
        Isearch.setOnClickListener(onclick);
        Iuser.setOnClickListener(onclick);

        for(int i= 0; i<len; i++){
            Img[i].setOnClickListener(onclick);
        }
        TV1.setOnClickListener(onclick);
        TV2.setOnClickListener(onclick);
        TV3.setOnClickListener(onclick);
        TV4.setOnClickListener(onclick);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            Bundle bundle = null;
            switch (v.getId()) {
                case R.id.Isearch:
                    intent = new Intent(Homepage.this, Search_page.class);
                    break;
                case R.id.Iusername:
                    intent = new Intent(Homepage.this, Logout1.class);
                    break;
                case R.id.IR1:
                case R.id.TR1:
                    //Get_item_by_shopname(sh[0].getShop_name(),url2);
                    intent = new Intent(Homepage.this, Restaurant1.class);
                    bundle = new Bundle();
                    bundle.putSerializable("shop",sh);
                    bundle.putInt("index",0);
                    bundle.putSerializable("item",items);
                    intent.putExtras(bundle);
                    break;
                case R.id.IR2:
                case R.id.TR2:
                    //Get_item_by_shopname(sh[1].getShop_name(),url2);
                    intent = new Intent(Homepage.this, Restaurant1.class);
                    bundle = new Bundle();
                    bundle.putSerializable("shop",sh);
                    bundle.putInt("index",1);
                    bundle.putSerializable("item",items);
                    intent.putExtras(bundle);
                    break;
                case R.id.IR3:
                case R.id.TR3:
                    //Get_item_by_shopname(sh[2].getShop_name(),url2);
                    intent = new Intent(Homepage.this, Restaurant2.class);
                    bundle = new Bundle();
                    bundle.putSerializable("shop",sh);
                    bundle.putInt("index",2);
                    bundle.putSerializable("item",items);
                    intent.putExtras(bundle);
                    break;
                case R.id.IR4:
                case R.id.TR4:
                    //Get_item_by_shopname(sh[3].getShop_name(),url2);
                    intent = new Intent(Homepage.this, Restaurant2.class);
                    bundle = new Bundle();
                    bundle.putSerializable("shop",sh);
                    bundle.putInt("index",3);
                    bundle.putSerializable("item",items);
                    intent.putExtras(bundle);
                    break;
            }
            startActivity(intent);
        }
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
                        sh = new Shop_Info[acc.length()];
                        for(int i=0;i<acc.length();i++){
                            JSONObject c = acc.getJSONObject(i);

                            sh[i] = new Shop_Info();
                            String id = c.getString("id");
                            String shop_name = c.getString("shop_name");
                            String genre = c.getString("genre");
                            double rate = c.getDouble("rate");
                            String open_time = c.getString("open_time");

                            sh[i].setId(id);
                            sh[i].setShop_name(shop_name);
                            sh[i].setGenre(genre);
                            sh[i].setRate(rate);
                            sh[i].setOpen_time(open_time);
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

                            //items[i] = new Item_info(shop_name,item_name,price,tag);
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

    private void Get_item_by_shopname(String name,String path) {
        final JSONObject request = new JSONObject();
        try {
            request.put("shopname", name);
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(Request.Method.POST, path, request, new Response.Listener<JSONObject>() {
            final JSONObject request = null;
            @Override
            public void onResponse(JSONObject response) {
                JSONArray acc = null;
                try {
                    if (response.getInt("state") ==1) {
                        acc = response.getJSONArray("shop");
                        items = new Item_info[acc.length()];
                        for(int i=0;i<acc.length();i++){
                            JSONObject c = acc.getJSONObject(i);

                            String shop_name = c.getString("shop_name");
                            String item_name = c.getString("item_name");
                            double price = c.getDouble("price");
                            String tag = c.getString("tag");

                            items[i] = new Item_info(shop_name,item_name,price,tag);
                        }
                        //Toast.makeText(MainActivity.this, response.getString("Email"), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
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

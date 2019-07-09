package com.example.delicious.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.delicious.Adapters.Myadapter_find;
import com.example.delicious.R;
import com.example.delicious.Self_class.Controls;
import com.example.delicious.Self_class.Item_info;
import com.example.delicious.Self_class.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

public class Find_page extends AppCompatActivity {
    private LinearLayout l_home, l_my, l_cart;
    private Myadapter_find myadapter_find;
    private GridView gridView;
    private ImageView I_find;
    private TextView tv_find;
    private Button button;
    private Item_info[] items;
    Controls Lock;

    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_page);

        Init();
        SetListener();
    }

    private void Init(){
        path = Lock.getRoot() + "get_all_item.php";
        I_find = findViewById(R.id.icon_find);
        I_find.setImageDrawable(getResources().getDrawable(R.drawable.icon_find2));
        tv_find = findViewById(R.id.tv_find);
        tv_find.setTextColor(Color.argb(255,26,132,216));

        l_home = findViewById(R.id.l_home);
        l_cart = findViewById(R.id.l_cart);
        l_my = findViewById(R.id.l_my);

        button = findViewById(R.id.b1);
        gridView = findViewById(R.id.gridview);
        Get_all_item(path);
    }

    private void SetListener(){
        OnClick onclick = new OnClick();
        l_home.setOnClickListener(onclick);
        l_cart.setOnClickListener(onclick);
        l_my.setOnClickListener(onclick);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myadapter_find = new Myadapter_find(Find_page.this, items);
                gridView.setAdapter(myadapter_find);
            }
        });
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            Bundle bundle;
            switch (v.getId()) {
                case R.id.l_home:
                    intent = new Intent(Find_page.this, Homepage.class);
                    break;
                case R.id.l_my:
                    intent = new Intent(Find_page.this, User_page.class);
                    break;
                case R.id.l_cart:
                    intent = new Intent(Find_page.this, MyCart_page.class);
                    if(Lock.getLock2()==0){
                        items = new Item_info[0];
                        bundle = new Bundle();
                        bundle.putSerializable("food",items);
                        intent.putExtras(bundle);
                    }
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
                JSONArray acc;
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);//continue other things
    }
}

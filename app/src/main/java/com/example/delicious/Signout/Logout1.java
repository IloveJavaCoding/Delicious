package com.example.delicious.Signout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.delicious.Cart.Cart;
import com.example.delicious.History.Order_history;
import com.example.delicious.Homepage;
import com.example.delicious.Order_List.Order_list;
import com.example.delicious.R;
import com.example.delicious.Self_class.Controls;
import com.example.delicious.Self_class.Item_info;
import com.example.delicious.Self_class.MySingleton;
import com.example.delicious.Self_class.Order_record;
import com.example.delicious.Sign_In.SessionHandler;
import com.example.delicious.Sign_In.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Logout1 extends AppCompatActivity {
    private ImageView Iexit, Iback;
    private TextView TUsername, TLogout;
    private TextView Torder, Tcart, Thistory;
    private SessionHandler session;
    private User user;
    private String[] order_number_unchecked;
    private Order_record[] orders, order_by_user, order_unchecked, order_checked;
    private Item_info[] items;
    Controls Lock;

    private final String root1 = "http://10.66.93.27:80/delicious/db/";
    private final String root2 = "http://10.71.0.203:80/delicious/db/";

    private String path = root1 + "get_all_order.php";
    private String path2 = root1 + "get_all_item.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout1);

        session = new SessionHandler(Logout1.this);
        user = session.getUserDetails();

        Init();
        SetListener();
    }
    private void Init(){
        Get_all_order(path);
        Get_all_item(path2);

        Iback = findViewById(R.id.Iback);
        Iexit = (ImageView)findViewById(R.id.Iexit);
        TUsername = (TextView)findViewById(R.id.Tuser);
        Torder = (TextView)findViewById(R.id.Torder);
        Tcart = (TextView)findViewById(R.id.Tcart);
        Thistory = (TextView)findViewById(R.id.Thistory);
        TLogout= (TextView)findViewById(R.id.logout);

        TUsername.setText(user.getUsername());
    }

    private void Flash(){
        order_by_user = Get_order_by_username(user.getUsername(), orders);
        order_unchecked = Get_order_unchecked(order_by_user);
        order_number_unchecked = Get_ordernum_list(order_unchecked);
        if(order_number_unchecked.length<1){
            Toast.makeText(getApplicationContext(), "You haven't made any order yet!", Toast.LENGTH_SHORT).show();
        }
    }

    private void SetListener(){
        OnClick onclick = new OnClick();
        Iback.setOnClickListener(onclick);
        Iexit.setOnClickListener(onclick);
        Torder.setOnClickListener(onclick);
        Tcart.setOnClickListener(onclick);
        Thistory.setOnClickListener(onclick);
        TLogout.setOnClickListener(onclick);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            Bundle bundle;
            switch (v.getId()) {
                case R.id.Iback:
                    intent = new Intent(Logout1.this, Homepage.class);
                    break;
                case R.id.Iexit:
                case R.id.logout:
                    intent = new Intent(Logout1.this, Logout2.class);
                    break;
                case R.id.Tcart:
                    intent = new Intent(Logout1.this, Cart.class);
                    if(Lock.getLock2()==0){
                        bundle = new Bundle();
                        bundle.putSerializable("food",items);
                        intent.putExtras(bundle);
                    }
                    break;
                case R.id.Torder:
                    Flash();
                    intent = new Intent(Logout1.this, Order_list.class);
                    bundle = new Bundle();
                    bundle.putStringArray("array", order_number_unchecked);
                    bundle.putSerializable("orders", order_unchecked);
                    intent.putExtras(bundle);
                    break;
                case R.id.Thistory:
                    order_by_user = Get_order_by_username(user.getUsername(), orders);
                    order_checked = Get_order_checked(order_by_user);

                    intent = new Intent(Logout1.this, Order_history.class);
                    bundle = new Bundle();
                    bundle.putSerializable("orders_checked", order_checked);
                    intent.putExtras(bundle);
                    break;
            }
            startActivity(intent);
            finish();
        }
    }

    private void Get_all_order(String path){
        final JSONObject request = null;
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(Request.Method.GET, path, request,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray acc = null;
                try {
                    if (response.getInt("state") ==1) {
                        acc = response.getJSONArray("order");
                        if(acc.length()>0){
                            orders = new Order_record[acc.length()];
                            for(int i=0;i<acc.length();i++){
                                orders[i] = new Order_record();
                                JSONObject c = acc.getJSONObject(i);

                                String order_number = c.getString("order_number");
                                String username = c.getString("username");
                                String shop_name = c.getString("shop_name");
                                String item_name = c.getString("item_name");
                                double price = c.getDouble("price");
                                int number = c.getInt("number");
                                String time = c.getString("time");
                                int tag = c.getInt("tag");
                                String item_tag = c.getString("item_tag");

                                orders[i].setIteam_name(item_name);
                                orders[i].setUsername(username);
                                orders[i].setOrder_number(order_number);
                                orders[i].setShop_name(shop_name);
                                orders[i].setPrice(price);
                                orders[i].setNumber(number);
                                orders[i].setReceive_time(time);
                                orders[i].setTag(tag);
                                orders[i].setItem_tag(item_tag);
                            }
                        }else{
                            orders = new Order_record[0];
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
                Toast.makeText(getApplicationContext(),"Not internet",Toast.LENGTH_SHORT).show();
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
                            items[i].setNumber(0);

                            //items[i] = new Item_info(shop_name,item_name,price,tag);
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
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }

    private Order_record[] Get_order_by_username(String username,Order_record[] orders){
        List<Order_record> list = new ArrayList<>();
        int num=0;
        for(int i=0; i<orders.length; i++){
            if(orders[i].getUsername().equals(username)){
                list.add(orders[i]);
                num++;
            }
        }
        Order_record[] order = list.toArray(new Order_record[num]);

        return order;
    }

    private Order_record[] Get_order_unchecked(Order_record[] orders){
        List<Order_record> list = new ArrayList<>();
        int num=0;
        for(int i=0; i<orders.length; i++){
            if(orders[i].getTag() == 0){
                list.add(orders[i]);
                num++;
            }
        }
        Order_record[] order = list.toArray(new Order_record[num]);

        return order;
    }

    private Order_record[] Get_order_checked(Order_record[] orders){
        List<Order_record> list = new ArrayList<>();
        int num=0;
        for(int i=0; i<orders.length; i++){
            if(orders[i].getTag() == 1){
                list.add(orders[i]);
                num++;
            }
        }
        Order_record[] order = list.toArray(new Order_record[num]);

        return order;
    }

    private String[] Get_ordernum_list(Order_record[] orders){
        List<String> list = new ArrayList<>();
        int num=0;
        for(int i=0; i<orders.length; i++){
            if(!list.contains(orders[i].getOrder_number())){
                list.add(orders[i].getOrder_number());
                num++;
            }
        }
        String[] order_numbers = list.toArray(new String[num]);

        return order_numbers;
    }
}

package com.example.delicious.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.delicious.R;
import com.example.delicious.Self_class.Controls;
import com.example.delicious.Self_class.Item_info;
import com.example.delicious.Self_class.MySingleton;
import com.example.delicious.Self_class.Order_record;
import com.example.delicious.Self_class.SessionHandler;
import com.example.delicious.Self_class.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class User_page extends AppCompatActivity {
    private ImageView Iexit, I_my;
    private LinearLayout l_home, l_find, l_cart;
    private TextView TUsername, TLogout, tv_my;
    private TextView Torder, Tcart, Thistory;
    private SessionHandler session;
    private User user;
    private String[] order_number_unchecked;
    private Order_record[] orders, order_by_user, order_unchecked, order_checked;
    private Item_info[] items;
    Controls Lock;

    private String path;
    private String path2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        session = new SessionHandler(User_page.this);
        user = session.getUserDetails();

        Init();
        SetListener();
    }
    private void Init(){
        path = Lock.getRoot() + "get_all_order.php";
        path2 = Lock.getRoot() + "get_all_item.php";
        Get_all_order(path);
        Get_all_item(path2);

        Iexit = findViewById(R.id.Iexit);
        TUsername = findViewById(R.id.Tuser);
        Torder = findViewById(R.id.Torder);
        Tcart = findViewById(R.id.Tcart);
        Thistory = findViewById(R.id.Thistory);
        TLogout= findViewById(R.id.logout);

        I_my = findViewById(R.id.icon_my);
        I_my.setImageDrawable(getResources().getDrawable(R.drawable.icon_my2));
        tv_my = findViewById(R.id.tv_my);
        tv_my.setTextColor(Color.argb(255,26,132,216));

        l_find = findViewById(R.id.l_find);
        l_cart = findViewById(R.id.l_cart);
        l_home = findViewById(R.id.l_home);

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
        Torder.setOnClickListener(onclick);
        Tcart.setOnClickListener(onclick);
        Thistory.setOnClickListener(onclick);
        TLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });
        Iexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });

        l_home.setOnClickListener(onclick);
        l_find.setOnClickListener(onclick);
        l_cart.setOnClickListener(onclick);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            Bundle bundle;
            switch (v.getId()) {
                case R.id.l_home:
                    intent = new Intent(User_page.this, Homepage.class);
                    break;
                case R.id.Tcart:
                case R.id.l_cart:
                    intent = new Intent(User_page.this, MyCart_page.class);
                    if(Lock.getLock2()==0){
                        bundle = new Bundle();
                        bundle.putSerializable("food",items);
                        intent.putExtras(bundle);
                    }
                    break;
                case R.id.Torder:
                    Flash();
                    intent = new Intent(User_page.this, Order_list_page.class);
                    bundle = new Bundle();
                    bundle.putStringArray("array", order_number_unchecked);
                    bundle.putSerializable("orders", order_unchecked);
                    intent.putExtras(bundle);
                    break;
                case R.id.Thistory:
                    order_by_user = Get_order_by_username(user.getUsername(), orders);
                    order_checked = Get_order_checked(order_by_user);
                    if(order_checked.length<1){
                        Toast.makeText(getApplicationContext(), "You haven't made any order yet!", Toast.LENGTH_SHORT).show();
                    }
                    intent = new Intent(User_page.this, Order_history_page.class);
                    bundle = new Bundle();
                    bundle.putSerializable("orders_checked", order_checked);
                    intent.putExtras(bundle);
                    break;
                case R.id.l_find:
                    intent = new Intent(User_page.this, Find_page.class);
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
                    }
                    else {
                        orders = new Order_record[0];
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

    private void ShowDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sign out!");
        builder.setMessage("Do you sure to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //-----------------------------
                session.logoutUser();
                Intent intent = new Intent(User_page.this, Bye_page.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //--------------------------

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);//continue other things
    }
}

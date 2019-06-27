package com.example.delicious.Myorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.UpdateAppearance;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.delicious.Homepage;
import com.example.delicious.Order_List.Order_list;
import com.example.delicious.R;
import com.example.delicious.Self_class.Item_info;
import com.example.delicious.Self_class.MySingleton;
import com.example.delicious.Self_class.Order_record;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Myorder extends AppCompatActivity {
    private ListView listView;
    private ImageView Iback;
    private Button Breceived;
    private RatingBar Bt, Bs;
    private TextView Ordernum, Titems, Ttotal_price, Tori_price;

    private String order_number;
    private Item_info[] items;
    private String currdate;

    private String[] ordernumber;
    private Order_record[] orders_all;

    private final String root1 = "http://10.66.93.27:80/delicious/db/";
    private final String root2 = "http://10.71.0.203:80/delicious/db/";

    private String path = root1 + "update_order.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);

        Get_data();
        Init();
        Flash();
        SetListener();
    }

    private void Get_data(){
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        items = (Item_info[]) bundle.getSerializable("items");
        order_number = bundle.getString("number");

        ordernumber = bundle.getStringArray("number_list");
        orders_all = (Order_record[]) bundle.getSerializable("order_all");
    }

    private void Init(){
        listView = (ListView)findViewById(R.id.Lv1);
        listView.setAdapter(new Myadapterorder(Myorder.this, items));

        Ordernum = (TextView)findViewById(R.id.num);
        Titems = findViewById(R.id.Tnum);
        Ttotal_price = findViewById(R.id.Tprice);
        Tori_price = findViewById(R.id.Torip);

        Iback = (ImageView) findViewById(R.id.Iback);
        Breceived = (Button) findViewById(R.id.Breceived);
        Bt = (RatingBar) findViewById(R.id.bar1);
        Bs = (RatingBar) findViewById(R.id.bar2);
    }

    private void Flash(){
        Ordernum.setText(order_number);

        int total_items = 0;
        double total_price = 0;
        for(int i=0; i<items.length; i++){
            total_items+=items[i].getNumber();
            total_price+=(items[i].getPrice() * items[i].getNumber());
        }
        Titems.setText(Integer.toString(total_items));
        Ttotal_price.setText(Double.toString(Math.floor(total_price)));
        Tori_price.setText(Double.toString(total_price));
    }

    private void SetListener() {
        OnClick onclick = new OnClick();
        Iback.setOnClickListener(onclick);
        Breceived.setOnClickListener(onclick);

        Bt.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(), Float.toString(rating), Toast.LENGTH_SHORT).show();
            }
        });

        Bs.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(), Float.toString(rating), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String Get_current_date(){
        String date = "";
        Date dt = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");
        date = simpleDateFormat.format(dt);

        return date;
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            Bundle bundle;
            switch (v.getId()) {
                case R.id.Iback:
                    intent = new Intent(Myorder.this, Order_list.class);
                    bundle = new Bundle();
                    bundle.putStringArray("array", ordernumber);
                    bundle.putSerializable("orders", orders_all);
                    intent.putExtras(bundle);
                    break;
                case R.id.Breceived:
                    //update database;
                    currdate = Get_current_date();
                    Update_order_state(order_number,currdate, path);
                    intent = new Intent(Myorder.this, Homepage.class);
                    break;
            }
            startActivity(intent);
            finish();
        }
    }

    private void Update_order_state(String order_number, String currdate, String path) {
        final JSONObject request = new JSONObject();
        try {
            request.put("ordernumber", order_number);
            request.put("date", currdate);
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(Request.Method.POST, path, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("state") ==1) {
                        Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getApplicationContext(),"No Internet!",Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }
}

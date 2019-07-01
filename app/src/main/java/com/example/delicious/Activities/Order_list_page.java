package com.example.delicious.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.delicious.Adapters.Myadapter_order_list;
import com.example.delicious.R;
import com.example.delicious.Self_class.Item_info;
import com.example.delicious.Self_class.Order_record;

import java.util.ArrayList;
import java.util.List;

public class Order_list_page extends AppCompatActivity {
    private View view;
    private ImageView Back;
    private ListView listView;
    private String[] order_number;
    private Order_record[] orders_all;
    private Item_info[] items_by_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        Get_data();
        Init();
        SetListener();
    }

    private void Get_data(){
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        order_number =  bundle.getStringArray("array");
        orders_all = (Order_record[]) bundle.getSerializable("orders");
    }

    private void Init(){
        view = findViewById(R.id.view1);
        Back = findViewById(R.id.Iback);
        listView = findViewById(R.id.lorder);
        listView.setAdapter(new Myadapter_order_list(Order_list_page.this, order_number));

        if(order_number.length>0){
            view.setVisibility(View.VISIBLE);
        }
    }

    private void SetListener(){
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Order_list_page.this, User_page.class);
                startActivity(intent);
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = null;
            Bundle bundle = null;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                items_by_order = Get_items_by_order(order_number[position], orders_all);
                intent = new Intent(Order_list_page.this, Order_page.class);
                bundle = new Bundle();
                bundle.putStringArray("number_list",order_number);
                bundle.putSerializable("order_all",orders_all);

                bundle.putString("number",order_number[position]);
                bundle.putSerializable("items",items_by_order);
                intent.putExtras(bundle);

                startActivity(intent);
                finish();
                //Toast.makeText(getApplicationContext(), items_by_order[0].getShop_name(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Item_info[] Get_items_by_order(String number, Order_record[] orders){
        List<Item_info> list = new ArrayList<>();
        Item_info item;
        int num=0;
        for(int i=0; i<orders.length; i++){
            item = new Item_info();
            if(orders[i].getOrder_number().equals(number)){
                item.setNumber(orders[i].getNumber());
                item.setIteam_name(orders[i].getIteam_name());
                item.setPrice(orders[i].getPrice());
                item.setShop_name(orders[i].getShop_name());
                item.setLink(orders[i].getItem_tag());

                list.add(item);
                num++;
            }
        }
        Item_info[] items = list.toArray(new Item_info[num]);

        return items;
    }
}

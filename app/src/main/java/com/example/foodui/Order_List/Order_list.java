package com.example.foodui.Order_List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.foodui.Myorder.Myorder;
import com.example.foodui.R;
import com.example.foodui.Signout.Logout1;

public class Order_list extends AppCompatActivity {
    private ImageView Back;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        Back = (ImageView)findViewById(R.id.Iback);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Order_list.this, Logout1.class);
                startActivity(intent);
            }
        });

        listView = (ListView)findViewById(R.id.lorder);
        listView.setAdapter(new Myadapterorder(Order_list.this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = null;
            Bundle bundle = null;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               intent = new Intent(Order_list.this, Myorder.class);
               bundle = new Bundle();
               bundle.putInt("index",position);
               intent.putExtras(bundle);

               startActivity(intent);
            }
        });
    }
}

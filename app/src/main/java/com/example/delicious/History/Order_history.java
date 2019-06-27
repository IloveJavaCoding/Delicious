package com.example.delicious.History;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.delicious.Self_class.Order_record;
import com.example.delicious.Signout.Logout1;
import com.example.delicious.R;

public class Order_history extends AppCompatActivity {
    private ListView listView;
    private ImageView Iback;

    private Order_record[] orders_checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        Get_data();
        Init();
        SetListener();
    }

    private void Get_data(){
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        orders_checked = (Order_record[])bundle.getSerializable("orders_checked");
    }

    private void Init(){
        listView = (ListView) findViewById(R.id.Lvh);
        listView.setAdapter(new Myadapterh(Order_history.this, orders_checked));

        Iback = (ImageView)findViewById(R.id.Iback);
    }

    private void SetListener(){
        Iback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Order_history.this, Logout1.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

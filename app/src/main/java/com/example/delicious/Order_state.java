package com.example.delicious;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Order_state extends AppCompatActivity {
    private Button Bhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_state);

        Bhome = (Button)findViewById(R.id.Btohome);
        Bhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Order_state.this, Homepage.class);
                startActivity(intent);
            }
        });
    }
}

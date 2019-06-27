package com.example.delicious.Check;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.delicious.Homepage;
import com.example.delicious.Cart.Generate_Order;
import com.example.delicious.R;

public class Check extends AppCompatActivity {
    private ListView listView;
    private Button Byes ,Bno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        listView = (ListView)findViewById(R.id.LV2);
        listView.setAdapter(new Myadaptercheck(Check.this));

        Byes = (Button) findViewById(R.id.Byes);
        Bno = (Button) findViewById(R.id.Bno);

        Byes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Check.this, Generate_Order.class);
                startActivity(intent);
            }
        });
        Bno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Check.this, Homepage.class);
                startActivity(intent);
            }
        });
    }
}

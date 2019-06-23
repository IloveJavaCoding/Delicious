package com.example.delicious.Myorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.delicious.Homepage;
import com.example.delicious.Order_List.Order_list;
import com.example.delicious.R;

public class Myorder extends AppCompatActivity {
    private ListView listView;
    private ImageView Iback;
    private Button Breceived;
    private RatingBar Bt, Bs;
    private TextView Ordernum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        int index = bundle.getInt("index");

        listView = (ListView)findViewById(R.id.Lv1);
        listView.setAdapter(new Myadapterorder(Myorder.this));

        Ordernum = (TextView)findViewById(R.id.num);
        Ordernum.setText(Integer.toString(index));

        //----------------------------------------------------------
        Iback = (ImageView) findViewById(R.id.Iback);
        Breceived = (Button) findViewById(R.id.Breceived);
        Bt = (RatingBar) findViewById(R.id.bar1);
        Bs = (RatingBar) findViewById(R.id.bar2);

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
        SetListener();
    }
    private void SetListener() {
        OnClick onclick = new OnClick();
        Iback.setOnClickListener(onclick);
        Breceived.setOnClickListener(onclick);
    }
    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.Iback:
                    intent = new Intent(Myorder.this, Order_list.class);
                    break;
                case R.id.Breceived:
                    //update database;
                    intent = new Intent(Myorder.this, Homepage.class);
                    break;

            }
            startActivity(intent);
        }
    }
}

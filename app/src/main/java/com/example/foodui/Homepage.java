package com.example.foodui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodui.Milktea.Restaurant1;
import com.example.foodui.Noodle.Restaurant2;

public class Homepage extends AppCompatActivity {
    private ImageView Isearch, Iuser;
    private ImageView Miketea, Coffee, Restaurant, Noodle;
    private TextView TMiketea, TCoffee, TRestaurant, TNoodle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Isearch = (ImageView) findViewById(R.id.Isearch);
        Iuser = (ImageView) findViewById(R.id.Iusername);

        Miketea = (ImageView) findViewById(R.id.IR1);
        TMiketea = (TextView) findViewById(R.id.TR1);
        Coffee = (ImageView) findViewById(R.id.IR2);
        TCoffee = (TextView) findViewById(R.id.TR2);
        Restaurant = (ImageView) findViewById(R.id.IR3);
        TRestaurant = (TextView) findViewById(R.id.TR3);
        Noodle = (ImageView) findViewById(R.id.IR4);
        TNoodle = (TextView) findViewById(R.id.TR4);

        SetListener();
    }
    private void SetListener() {
        OnClick onclick = new OnClick();
        Isearch.setOnClickListener(onclick);
        Iuser.setOnClickListener(onclick);

        Miketea.setOnClickListener(onclick);
        TMiketea.setOnClickListener(onclick);
        Coffee.setOnClickListener(onclick);
        TCoffee.setOnClickListener(onclick);
        Restaurant.setOnClickListener(onclick);
        TRestaurant.setOnClickListener(onclick);
        Noodle.setOnClickListener(onclick);
        TNoodle.setOnClickListener(onclick);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.Isearch:
                    intent = new Intent(Homepage.this, Search_page.class);
                    break;
                case R.id.Iusername:
                    intent = new Intent(Homepage.this, Logout1.class);
                    break;
                case R.id.IR1:
                case R.id.TR1:
                case R.id.IR2:
                case R.id.TR2:
                    intent = new Intent(Homepage.this, Restaurant1.class);
                    break;
                case R.id.IR3:
                case R.id.TR3:
                case R.id.IR4:
                case R.id.TR4:
                    intent = new Intent(Homepage.this, Restaurant2.class);
                    break;
            }
            startActivity(intent);
        }
    }
}

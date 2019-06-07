package com.example.foodui.Myorder;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.foodui.Homepage;
import com.example.foodui.Logout1;
import com.example.foodui.R;

public class Myorder extends AppCompatActivity {
    private ListView listView;
    private ImageView Iback;
    private View It1, It2, It3, It4, It5;//Taste star
    private View Is1, Is2, Is3, Is4, Is5;//server star
    private Button Breceived;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        listView = (ListView)findViewById(R.id.Lv1);
        listView.setAdapter(new Myadapterorder(Myorder.this));

        Iback = (ImageView) findViewById(R.id.Iback);
        It1 = (View) findViewById(R.id.vt1);
        It2 = (View) findViewById(R.id.vt2);
        It3 = (View) findViewById(R.id.vt3);
        It4 = (View) findViewById(R.id.vt4);
        It5 = (View) findViewById(R.id.vt5);

        Is1 = (View) findViewById(R.id.vs1);
        Is2 = (View) findViewById(R.id.vs2);
        Is3 = (View) findViewById(R.id.vs3);
        Is4 = (View) findViewById(R.id.vs4);
        Is5 = (View) findViewById(R.id.vs5);
        Breceived = (Button) findViewById(R.id.Breceived);

        SetListener();
    }
    private void SetListener() {
        OnClick onclick = new OnClick();
        Iback.setOnClickListener(onclick);
        Breceived.setOnClickListener(onclick);
        It1.setOnClickListener(onclick);
        It2.setOnClickListener(onclick);
        It3.setOnClickListener(onclick);
        It4.setOnClickListener(onclick);
        It5.setOnClickListener(onclick);
        Is1.setOnClickListener(onclick);
        Is2.setOnClickListener(onclick);
        Is3.setOnClickListener(onclick);
        Is4.setOnClickListener(onclick);
        Is5.setOnClickListener(onclick);

    }
    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.Iback:
                    intent = new Intent(Myorder.this, Logout1.class);
                    break;
                case R.id.Breceived:
                    //update database;

                    intent = new Intent(Myorder.this, Homepage.class);
                    break;
                case R.id.vt1:
                    //It1.setBackgroundColor(1);
            }
            startActivity(intent);
        }
    }
}

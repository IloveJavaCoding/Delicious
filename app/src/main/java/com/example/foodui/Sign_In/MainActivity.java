package com.example.foodui.Sign_In;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ProgressBar;

import com.example.foodui.R;

public class MainActivity extends AppCompatActivity {
    private ProgressBar dia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dia = (ProgressBar)findViewById(R.id.bar);

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                    Intent intent = new Intent(MainActivity.this, Loginpag.class);
                    startActivity(intent);
                    //dia.setVisibility(View.INVISIBLE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String tag = intent.getStringExtra("EXIT_TAG");
        if(tag!=null&!TextUtils.isEmpty(tag)){
            if("SINGLETASK".equals(tag)){
                finish();
            }
        }
    }
}

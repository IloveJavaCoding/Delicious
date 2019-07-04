package com.example.delicious.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.delicious.R;
import com.example.delicious.Self_class.Controls;

public class MainActivity extends AppCompatActivity {
    Controls Lock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
    }

    private void Init(){
        Lock = new Controls();

        //Lock.setRoot("http://10.66.93.27:80/delicious/db/");
        Lock.setRoot("http://10.71.0.203:80/delicious/db/");//wireless
        Lock.setLock(0);
        Lock.setLock2(0);

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1500);
                    Intent intent = new Intent(MainActivity.this, Login_page.class);
                    startActivity(intent);
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

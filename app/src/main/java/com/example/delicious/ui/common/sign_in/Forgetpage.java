package com.example.delicious.ui.common.sign_in;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.delicious.R;

public class Forgetpage extends AppCompatActivity {
    private Button BLogin, Bregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpage);

        BLogin = (Button)findViewById(R.id.BTologin);
        BLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forgetpage.this, Loginpag.class);
                startActivity(intent);
                finish();
            }
        });
        Bregister = (Button)findViewById(R.id.Bregister);
        Bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forgetpage.this, Registerpag.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

package com.example.delicious.ui.common.sign_out;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.delicious.R;
import com.example.delicious.ui.common.sign_in.SessionHandler;
import com.example.delicious.ui.common.sign_in.User;

public class Logout2 extends AppCompatActivity {
    private Button Byes, Bno;
    private SessionHandler session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout2);

        session = new SessionHandler(Logout2.this);
        User user = session.getUserDetails();

        Byes = (Button)findViewById(R.id.Lyes);
        Bno = (Button)findViewById(R.id.Lno);

        Byes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
                Intent intent = new Intent(Logout2.this, Logout3.class);
                startActivity(intent);
                finish();
            }
        });

        Bno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Logout2.this, Logout1.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

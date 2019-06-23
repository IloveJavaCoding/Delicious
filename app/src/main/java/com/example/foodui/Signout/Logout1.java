package com.example.foodui.Signout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodui.Cart.Cart;
import com.example.foodui.History.Order_history;
import com.example.foodui.Homepage;
import com.example.foodui.Order_List.Order_list;
import com.example.foodui.R;
import com.example.foodui.Sign_In.SessionHandler;
import com.example.foodui.Sign_In.User;

public class Logout1 extends AppCompatActivity {
    private View Iback;
    private ImageView Iexit;
    private TextView TUsername, TLogout;
    private TextView Torder, Tcart, Thistory;
    private SessionHandler session;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout1);

        session = new SessionHandler(Logout1.this);
        user = session.getUserDetails();

        Iback = (View)findViewById(R.id.Lback);
        Iexit = (ImageView)findViewById(R.id.Iexit);
        TUsername = (TextView)findViewById(R.id.Tuser);
        Torder = (TextView)findViewById(R.id.Torder);
        Tcart = (TextView)findViewById(R.id.Tcart);
        Thistory = (TextView)findViewById(R.id.Thistory);
        TLogout= (TextView)findViewById(R.id.logout);

        TUsername.setText(user.getUsername());
        SetListener();
    }
    private void SetListener(){
        OnClick onclick = new OnClick();
        Iback.setOnClickListener(onclick);
        Iexit.setOnClickListener(onclick);
        Torder.setOnClickListener(onclick);
        Tcart.setOnClickListener(onclick);
        Thistory.setOnClickListener(onclick);
        TLogout.setOnClickListener(onclick);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.Lback:
                    intent = new Intent(Logout1.this, Homepage.class);
                    break;
                case R.id.Iexit:
                case R.id.logout:
                    intent = new Intent(Logout1.this, Logout2.class);
                    break;
                case R.id.Tuser:
                    //
                    break;
                case R.id.Torder:
                    intent = new Intent(Logout1.this, Order_list.class);
                    break;
                case R.id.Tcart:
                    intent = new Intent(Logout1.this, Cart.class);
                    break;
                case R.id.Thistory:
                    intent = new Intent(Logout1.this, Order_history.class);
                    break;

            }
            startActivity(intent);
        }
    }
}

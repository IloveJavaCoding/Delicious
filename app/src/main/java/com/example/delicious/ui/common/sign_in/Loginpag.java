package com.example.delicious.ui.common.sign_in;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.delicious.Home.Loading;
import com.example.delicious.Self_class.MySingleton;
import com.example.delicious.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Loginpag extends AppCompatActivity {
    private TextView ToRegister, ToForget;
    private Button Login;

    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMPTY = "";
    private EditText etUsername;
    private EditText etPassword;
    private String username;
    private String password;
    private ProgressDialog pDialog;
    private SessionHandler session;

    private final String root1 = "http://10.66.93.27:80/delicious/db/";
    private final String root2 = "http://10.71.0.203:80/delicious/db/";
    //"http://10.66.93.241:8080/member/db/login.php";
    private String login_url = root1 + "login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionHandler(getApplicationContext());
        if(session.isLoggedIn()){
            loadDashboard();
        }
        setContentView(R.layout.activity_loginpag);

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);

        ToRegister = (TextView)findViewById(R.id.tvRegister);
        ToForget = (TextView)findViewById(R.id.tvForget);
        Login = (Button)findViewById(R.id.login);

        ToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Loginpag.this, Registerpag.class);
                startActivity(intent);
                finish();
            }
        });

        ToForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Loginpag.this, Forgetpage.class);
                startActivity(intent);
                finish();
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //-----------action------------
                username = etUsername.getText().toString().toLowerCase().trim();
                password = etPassword.getText().toString().trim();
                if(validateInputs()){
                    login();
                }
            }
        });
    }

    private void loadDashboard(){
        Intent intent = new Intent(Loginpag.this, Loading.class);
        startActivity(intent);
        finish();
    }

    private void displayLoader(){
        pDialog = new ProgressDialog(Loginpag.this);
        pDialog.setMessage("Logging In.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void login(){
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            request.put(KEY_USERNAME, username);
            request.put(KEY_PASSWORD, password);
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(Request.Method.POST, login_url, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt(KEY_STATUS) == 0) {
                        session.loginUser(username, response.getString(KEY_FULL_NAME));
                        loadDashboard();
                    } else {
                        Toast.makeText(getApplicationContext(), response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();

                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }

    private boolean validateInputs(){
        if(KEY_EMPTY.equals(username)){
            etUsername.setError("Username cannot be empty.");
            etUsername.requestFocus();
            return false;
        }
        if(KEY_EMPTY.equals(password)){
            etPassword.setError("Password cannot be empty.");
            etPassword.requestFocus();
            return false;
        }
        return true;
    }

    //forbide the back operation;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);//continue other things
    }
}

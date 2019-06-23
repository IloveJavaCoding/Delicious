package com.example.foodui.Sign_In;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.foodui.Home.Loading;
import com.example.foodui.Self_class.MySingleton;
import com.example.foodui.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Registerpag extends AppCompatActivity {
    private Button BLogin, Bregister;

    public static final String KEY_STATUS = "status";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_FULL_NAME = "full_name";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD ="password";
    public static final String KEY_EMPTY = "";
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etFullName;
    private String username;
    private String password;
    private String confirmPassword;
    private String fullName;
    private ProgressDialog pDialog;
    private String register_url = "http://10.66.93.27:80/delicious/db/register.php";
   // private String register_url = "http://10.71.0.203:80/delicious/db/register.php";
    private SessionHandler session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionHandler(getApplicationContext());
        setContentView(R.layout.activity_registerpag);

        etUsername = findViewById(R.id.Rusername);
        etPassword = findViewById(R.id.password1);
        etConfirmPassword = findViewById(R.id.password2);
        etFullName = findViewById(R.id.email);

        Bregister = (Button)findViewById(R.id.Bregister);
        BLogin = (Button)findViewById(R.id.BTologin);
        BLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registerpag.this, Loginpag.class);
                startActivity(intent);
                finish();
            }
        });
        Bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString().toLowerCase().trim();
                password = etPassword.getText().toString().trim();
                confirmPassword = etConfirmPassword.getText().toString().trim();
                fullName = etFullName.getText().toString().trim();
                if(validateInputs()){
                    registerUser();
                }
            }
        });
    }

    private void displayLoader(){
        pDialog = new ProgressDialog(Registerpag.this);
        pDialog.setMessage("Signing up.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void loadDashboard(){
        Intent intent = new Intent(Registerpag.this, Loading.class);
        Toast.makeText(Registerpag.this,"register successfully!",Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }

    private void registerUser(){
        displayLoader();
        JSONObject request = new JSONObject();
        try{
            request.put(KEY_USERNAME,username);
            request.put(KEY_PASSWORD,password);
            request.put(KEY_FULL_NAME,fullName);
        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(Request.Method.POST, register_url, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();
                try {
                    if (response.getInt(KEY_STATUS) == 0) {
                        session.loginUser(username, fullName);
                        loadDashboard();
                    } else if (response.getInt(KEY_STATUS) == 1) {
                        etUsername.setError("Username already taken");
                        etUsername.requestFocus();
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
        if(KEY_EMPTY.equals(fullName)){
            etUsername.setError("Email cannot be empty.");
            etUsername.requestFocus();
            return false;
        }
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
        if(password.length()<8){
            etPassword.setError("Password cannot be less then 8.");
            etPassword.requestFocus();
            return false;
        }
        if(KEY_EMPTY.equals(confirmPassword)){
            etPassword.setError("Confirm Password cannot be empty.");
            etPassword.requestFocus();
            return false;
        }
        return true;
    }
}

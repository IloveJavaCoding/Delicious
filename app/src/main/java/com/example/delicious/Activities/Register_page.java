package com.example.delicious.Activities;

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
import com.example.delicious.R;
import com.example.delicious.Self_class.MySingleton;
import com.example.delicious.Self_class.SessionHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class Register_page extends AppCompatActivity {
    private Button BLogin, Bregister;

    public static final String KEY_STATUS = "status";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD ="password";
    public static final String KEY_EMPTY = "";
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etEmail;
    private EditText etAnswer;
    private String username;
    private String password;
    private String confirmPassword;
    private String eMail;
    private String answer;
    private ProgressDialog pDialog;
    private SessionHandler session;

    private final String root1 = "http://10.66.93.27:80/delicious/db/";
    private final String root2 = "http://10.71.0.203:80/delicious/db/";
    private String register_url = root1 + "register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionHandler(getApplicationContext());
        setContentView(R.layout.activity_registerpag);

        Init();
        SetListener();
    }
    private void Init(){
        etUsername = findViewById(R.id.Rusername);
        etPassword = findViewById(R.id.password1);
        etConfirmPassword = findViewById(R.id.password2);
        etEmail = findViewById(R.id.email);
        etAnswer = findViewById(R.id.answer);

        Bregister = (Button)findViewById(R.id.Bregister);
        BLogin = (Button)findViewById(R.id.BTologin);
    }

    private void SetListener(){
        BLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register_page.this, Login_page.class);
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
                eMail = etEmail.getText().toString().trim();
                answer = etAnswer.getText().toString().toLowerCase().trim();

                if(validateInputs()){
                    registerUser();
                }
            }
        });
    }

    private void displayLoader(){
        pDialog = new ProgressDialog(Register_page.this);
        pDialog.setMessage("Signing up.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void loadDashboard(){
        Intent intent = new Intent(Register_page.this, Loading_page.class);
        Toast.makeText(Register_page.this,"register successfully!",Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }

    private void registerUser(){
        displayLoader();
        JSONObject request = new JSONObject();
        try{
            request.put(KEY_USERNAME,username);
            request.put(KEY_PASSWORD,password);
            request.put(KEY_EMAIL,eMail);
            request.put("answer",answer);
        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(Request.Method.POST, register_url, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();
                try {
                    if (response.getInt(KEY_STATUS) == 0) {
                        session.loginUser(username, eMail);
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
        if(KEY_EMPTY.equals(eMail)){
            etEmail.setError("Email cannot be empty.");
            etEmail.requestFocus();
            return false;
        }
        if(KEY_EMPTY.equals(answer)){
            etAnswer.setError("answer cannot be empty.");
            etAnswer.requestFocus();
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
            etPassword.setError("Password length cannot be less then 8.");
            etPassword.requestFocus();
            return false;
        }
        if(KEY_EMPTY.equals(confirmPassword)){
            etConfirmPassword.setError("Confirm Password cannot be empty.");
            etConfirmPassword.requestFocus();
            return false;
        }
        return true;
    }
}

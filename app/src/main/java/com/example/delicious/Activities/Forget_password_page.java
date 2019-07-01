package com.example.delicious.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.delicious.R;
import com.example.delicious.Self_class.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

public class Forget_password_page extends AppCompatActivity {
    private final static String KEY_EMPTY = "";
    private Button BLogin, Bchange;
    private TextView Result;
    private EditText Eusername, Eanswer, Epassword, Econfirm_password;

    private final String root1 = "http://10.66.93.27:80/delicious/db/";
    private final String root2 = "http://10.71.0.203:80/delicious/db/";

    private String path = root1 + "forget_password.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpage);

        Init();
        SetListenr();
    }

    private void Init(){
        BLogin = findViewById(R.id.BTologin);
        Bchange = findViewById(R.id.Bchange);
        Result = findViewById(R.id.result);
        Eusername = findViewById(R.id.username);
        Eanswer = findViewById(R.id.answer);
        Epassword = findViewById(R.id.password1);
        Econfirm_password = findViewById(R.id.password2);
    }

    private void SetListenr(){
        BLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToLogin();
            }
        });

        Bchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= Eusername.getText().toString().toLowerCase().trim();
                String answer= Eanswer.getText().toString().toLowerCase().trim();
                String password= Epassword.getText().toString().trim();
                String cpassword = Econfirm_password.getText().toString().trim();

                if(validateInputs(name, answer,password, cpassword)){
                    Change_password(name, answer,password,path);
                    Clear();
                    ToLogin();
                }
            }
        });
    }

    private void Clear() {
        Eusername.setText("");
        Eanswer.setText("");
        Epassword.setText("");
        Econfirm_password.setText("");
    }

    private void Change_password(String name, String answer, String password, String path){
        final JSONObject request = new JSONObject();
        try {
            request.put("username", name);
            request.put("answer", answer);
            request.put("password", password);
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(Request.Method.POST, path, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("state") ==1) {
                        Result.setText(response.getString("message"));
                    }
                    else {
                        Result.setText(response.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }

    private boolean validateInputs(String name, String answer, String password, String cpassword){
        if(KEY_EMPTY.equals(name)){
            Eusername.setError("Username cannot be empty.");
            Eusername.requestFocus();
            return false;
        }
        if(KEY_EMPTY.equals(answer)){
            Eanswer.setError("answer cannot be empty.");
            Eanswer.requestFocus();
            return false;
        }
        if(KEY_EMPTY.equals(password)){
            Epassword.setError("Password cannot be empty.");
            Epassword.requestFocus();
            return false;
        }
        if(password.length()<8){
            Epassword.setError("Password length cannot be less then 8.");
            Epassword.requestFocus();
            return false;
        }
        if(KEY_EMPTY.equals(cpassword)){
            Econfirm_password.setError("Confirm Password cannot be empty.");
            Econfirm_password.requestFocus();
            return false;
        }
        return true;
    }

    private void ToLogin(){
        Intent intent = new Intent(Forget_password_page.this, Login_page.class);
        startActivity(intent);
        finish();
    }
}

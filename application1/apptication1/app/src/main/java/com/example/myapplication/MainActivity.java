package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mobile, password, login;
    EditText textmobile, textpassword;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.textlogin);
        mobile = findViewById(R.id.textmobile);
        password = findViewById(R.id.textpassword);

        textmobile = findViewById(R.id.editTextMobile);
        textpassword = findViewById(R.id.editTextpassword);

        btnlogin = findViewById(R.id.buttonlogin);

        btnlogin.setOnClickListener(this);

        SharedPreferences spf = getSharedPreferences("waiter", MODE_PRIVATE);
        if(spf.contains("mobile") && spf.contains("password")) {
            startActivity(new Intent(this, ActivityTakeOrder.class));
        }
    }

    @Override
    public void onClick(View v) {
        if(v == btnlogin) {
            SharedPreferences spf = getSharedPreferences("waiter", MODE_PRIVATE);
            SharedPreferences.Editor editor = spf.edit();
            editor.putString("mobile", textmobile.getText().toString());
            editor.putString("password", textpassword.getText().toString());
            editor.commit();
            finish();
            startActivity(new Intent(this, ActivityTakeOrder.class));
        }
    }
}
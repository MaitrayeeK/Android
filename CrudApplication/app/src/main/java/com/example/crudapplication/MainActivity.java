package com.example.crudapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView email, password;
    EditText emailt, passwordt;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        emailt = findViewById(R.id.editTextEmail);
        passwordt = findViewById(R.id.editTextpassword);

        btnLogin = findViewById(R.id.button);

        btnLogin.setOnClickListener(this);

        SharedPreferences spf = getSharedPreferences("logindata", MODE_PRIVATE);
        if(spf.contains("email") && spf.contains("password")){
            startActivity(new Intent(this, ActivityList.class));
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin) {
            SharedPreferences spf = getSharedPreferences("logindata", MODE_PRIVATE);
            SharedPreferences.Editor editor = spf.edit();
            editor.putString("email", emailt.getText().toString());
            editor.putString("password", passwordt.getText().toString());
            editor.commit();

            finish();

            startActivity(new Intent(this, ActivityList.class));
        }
    }
}
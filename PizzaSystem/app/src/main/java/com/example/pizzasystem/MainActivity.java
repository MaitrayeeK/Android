package com.example.pizzasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView login, mobileno, password;
    EditText editTextmobile, editTextpassword;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.textlogin);
        mobileno = findViewById(R.id.textmobileno);
        password = findViewById(R.id.textpassword);

        editTextmobile = findViewById(R.id.editTextmobileno);
        editTextpassword = findViewById(R.id.editTextpassword);

        btnlogin = findViewById(R.id.buttonlogin);

        btnlogin.setOnClickListener(this);

        SharedPreferences sh = getSharedPreferences("login", MODE_PRIVATE);
        if(sh.contains("mobile") && sh.contains("password")) {
            startActivity(new Intent(this, TakeOrderActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        if(view == btnlogin) {
            if(editTextmobile.getText().toString().isEmpty()) {
                editTextmobile.setError("Mobile number cannot be null.");
                editTextmobile.setFocusable(true);
            } else if (editTextpassword.getText().toString().isEmpty()) {
                editTextpassword.setError("Password cannot be null.");
                editTextpassword.setFocusable(true);
            } else {
                SharedPreferences sh = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor edit = sh.edit();
                edit.putString("mobile", editTextmobile.getText().toString());
                edit.putString("password", editTextpassword.getText().toString());
                edit.commit();
                startActivity(new Intent(this, TakeOrderActivity.class));

                finish();
            }
        }
    }
}
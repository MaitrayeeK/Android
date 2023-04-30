package com.example.crudapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityAdd extends AppCompatActivity implements View.OnClickListener {

    TextView name, dept;
    EditText namet, deptt;
    Button btnadd;
    DatabaseHandler db = new DatabaseHandler(this, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = findViewById(R.id.Name);
        dept = findViewById(R.id.dept);
        namet = findViewById(R.id.editTextname);
        deptt = findViewById(R.id.editTextdept);

        btnadd = findViewById(R.id.button2);
        btnadd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnadd) {
            Employee employee = new Employee();
            employee.setName(namet.getText().toString());
            employee.setDepartment(deptt.getText().toString());
            db.adddata(employee);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Record Added!")
                    .setCancelable(true);
            AlertDialog  dlg = builder.create();
            dlg.show();
            startActivity(new Intent(this, ActivityList.class));
        }
    }
}
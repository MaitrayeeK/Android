package com.example.pizzasystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TakeOrderActivity extends AppCompatActivity implements View.OnClickListener {

    TextView table, customer, mobile, date;
    EditText texttable, textcustomer, textmobile, textdate;
    LinearLayout llayout;
    Button takeorder;
    DatabaseHandler db = new DatabaseHandler(this, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        llayout = findViewById(R.id.llayout);

        takeorder = findViewById(R.id.btntakeorder);

        table = findViewById(R.id.texttableno);
        customer = findViewById(R.id.textcustomer);
        mobile = findViewById(R.id.textmobileno);
        date = findViewById(R.id.textdatetime);

        texttable = findViewById(R.id.editTexttableno);
        textcustomer = findViewById(R.id.editTextcustomer);
        textmobile = findViewById(R.id.editTextmobileno);
        textdate = findViewById(R.id.editTextDate);

        takeorder.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == takeorder) {
            if(texttable.getText().toString().isEmpty()) {
                texttable.setFocusable(true);
                texttable.setError("Please enter table number.");
            } else if (textcustomer.getText().toString().isEmpty()) {
                textcustomer.setFocusable(true);
                textcustomer.setError("Please enter customer name.");
            } else {
                OrderMaster orderMaster = new OrderMaster();
                orderMaster.setCustomer(textcustomer.getText().toString());
                orderMaster.setDate(textdate.getText().toString());
                orderMaster.setMobile(textmobile.getText().toString());
                orderMaster.setTableno(Integer.parseInt(texttable.getText().toString()));
                long result = db.adddata(orderMaster);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                if(result > 0) {
                    builder.setMessage("Record added!")
                            .setCancelable(true);
                } else {
                    builder.setMessage("Error in record insertion")
                            .setCancelable(true);
                }
                AlertDialog dlg = builder.create();
                dlg.show();
                Intent i = new Intent(this, OrderActivity.class);
                i.putExtra("orderid", result);
                i.putExtra("customer", textcustomer.getText().toString());
                startActivity(i);
            }
        }
    }
}
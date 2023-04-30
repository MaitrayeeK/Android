package com.example.pizzasystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    TextView category, size, qty;
    Spinner cat;
    Button add;
    String customer;
    int price;
    long oid;
    RadioGroup rg;
//    RadioButton rdb1, rdb2, rdb3;
    EditText textqty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        category = findViewById(R.id.textcategory);
        size = findViewById(R.id.textsize);
        qty = findViewById(R.id.textquantity);
        rg = findViewById(R.id.rdg);

        textqty = findViewById(R.id.editTextqty);
//        rdb1 = findViewById(R.id.radioButton);
//        rdb2 = findViewById(R.id.radioButton2);
//        rdb3 = findViewById(R.id.radioButton3);

        cat = findViewById(R.id.spinnercategory);

        add = findViewById(R.id.btnaddorder);

        String[] categories = {"VEG PIZZA", "BURGER PIZZA", "ITALIAN PIZZA", "CHEESE PIZZA"};

        ArrayAdapter adapterCategory = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, categories);

        cat.setAdapter(adapterCategory);
        textqty.setText(String.valueOf(1));

        Intent i = getIntent();
        oid = i.getLongExtra("orderid", 0);
        customer = i.getStringExtra("customer");

        add.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.vieworder){
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.custome_dialog);

            DatabaseHandler db = new DatabaseHandler(this, 1);
            ArrayList<OrderDetail> orderDetails = db.getorderdetailbyorderid(String.valueOf(oid));

            RecyclerView recyclerView = dialog.findViewById(R.id.rview2);
            OrderDetailAdapter adapter = new OrderDetailAdapter(this, orderDetails);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(linearLayoutManager);

            dialog.show();
        }

        if(item.getItemId() == R.id.allorder) {
            finish();
            startActivity(new Intent(this, OrderListActivity.class));
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view == add) {

            String c = cat.getSelectedItem().toString();
            RadioButton rb = findViewById(rg.getCheckedRadioButtonId());
            String ps = rb.getText().toString();
            System.out.println("pizza size "+ps);
            if(c == "VEG PIZZA" && ps == "SMALL") {
                price = 100;
            } else if (c == "VEG PIZZA" && ps == "LARGE") {
                price = 200;
            } else if (c == "VEG PIZZA" && ps == "JUMBO") {
                price = 500;
            } else if(c == "BURGER PIZZA" && ps == "SMALL") {
                price = 150;
            } else if (c == "BURGER PIZZA" && ps == "LARGE") {
                price = 360;
            } else if (c == "BURGER PIZZA" && ps == "JUMBO") {
                price = 490;
            } else if(c == "ITALIAN PIZZA" && ps == "SMALL") {
                price = 200;
            } else if (c == "ITALIAN PIZZA" && ps == "LARGE") {
                price = 450;
            } else if (c == "ITALIAN PIZZA" && ps == "JUMBO") {
                price = 680;
            } else if(c == "CHEESE PIZZA" && ps == "SMALL") {
                price = 120;
            } else if (c == "CHEESE PIZZA" && ps == "LARGE") {
                price = 290;
            } else if (c == "CHEESE PIZZA" && ps == "JUMBO") {
                price = 410;
            }
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setCategory(c);
            orderDetail.setSize(ps);
            orderDetail.setOid(oid);
            orderDetail.setPrice(price);
            orderDetail.setQty(Integer.parseInt(textqty.getText().toString()));
            DatabaseHandler db = new DatabaseHandler(this, 1);
            long result = db.addoddata(orderDetail);

            if(result > 0) {
                Toast toast = Toast.makeText(this, "Record added!", Toast.LENGTH_LONG);
                toast.show();
            } else {
                Toast toast = Toast.makeText(this, "Error in record insertion!", Toast.LENGTH_LONG);
                toast.show();
            }

            textqty.setText("");
        }
    }
}
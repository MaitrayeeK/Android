package com.example.pizzasystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class OrderListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RecyclerView rview;
    Spinner spinner;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        spinner = findViewById(R.id.spinnersearch);
        rview = findViewById(R.id.rview);

        db = new DatabaseHandler(this, 1);

        ArrayAdapter adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, db.getallorder());
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        OrderDetailAdapter adapter = new OrderDetailAdapter(this, db.getorderdetailsbycustomer(spinner.getSelectedItem().toString()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rview.setAdapter(adapter);
        rview.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
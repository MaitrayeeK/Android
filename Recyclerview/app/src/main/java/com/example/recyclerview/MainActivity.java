package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.recyclerview.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    ArrayList<DeshbordModel> list = new ArrayList<>();
    DeshborList listAdapter;


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        list.add(new DeshbordModel(R.drawable.ic_analytics,"Brake boss portable","The Brake-Boss portable air brake tester is a flexibile solution for your yard. Controlled directly on the portable device or with the Brake-Boss app."));
        list.add(new DeshbordModel(R.drawable.ic_analytics,"Customizable","We fully customize all of our\n" +
                "products specifically to fit the needs\n" +
                "of your rail yard."));
        list.add(new DeshbordModel(R.drawable.ic_analytics,"Analytics & reporting","Real-time reporting via the web or\n" +
                "your mobile device."));


        listAdapter = new DeshborList(list);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.recyclerview.setAdapter(listAdapter);
    }
}
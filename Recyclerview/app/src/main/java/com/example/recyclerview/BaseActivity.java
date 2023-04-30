package com.example.recyclerview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity {

   public DB binding;
   public BaseActivity mActivity;
   public abstract int getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        binding = DataBindingUtil.setContentView(this,getLayout());
        initViews();
    }

    protected abstract void initViews();
}

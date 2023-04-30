package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


public abstract class BaseRVAdapter<DB extends ViewDataBinding> extends RecyclerView.Adapter<BaseViewHolder<DB>> {

    protected abstract int getLayout();

    @NonNull
    @Override
    public BaseViewHolder<DB> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DB _binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                getLayout(),
                parent,
                false);
        return new MyViewHolder(_binding);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private class MyViewHolder extends BaseViewHolder<DB> {
        private MyViewHolder(@NonNull DB _binding) {
            super(_binding);
        }
    }
}

package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<DB extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public DB binding;

    protected BaseViewHolder(@NonNull DB _binding) {
        super(_binding.getRoot());
        binding = _binding;
    }
}

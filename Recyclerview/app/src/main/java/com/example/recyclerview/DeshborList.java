package com.example.recyclerview;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.recyclerview.databinding.DeshborListBinding;

import java.util.ArrayList;

public class DeshborList extends BaseRVAdapter<DeshborListBinding> {

    ArrayList<DeshbordModel> list;

    public DeshborList(ArrayList<DeshbordModel> list) {
        this.list = list;
    }

    @Override
    protected int getLayout() {
        return R.layout.deshbor_list;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<DeshborListBinding> holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImage())
                .into(holder.binding.imageview);

        holder.binding.tvOne.setText(list.get(position).getTitle());
        holder.binding.tvTwo.setText(list.get(position).getDesc());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
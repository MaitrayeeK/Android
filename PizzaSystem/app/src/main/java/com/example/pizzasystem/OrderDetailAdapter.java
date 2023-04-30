package com.example.pizzasystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderDetailAdapter extends  RecyclerView.Adapter<OrderDetailAdapter.MyViewHolder>{
    Context context;
    ArrayList<OrderDetail> orderDetails;

    public OrderDetailAdapter(Context context, ArrayList<OrderDetail> orderDetails) {
        this.context = context;
        this.orderDetails = orderDetails;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OrderDetail orderDetail = orderDetails.get(position);
        holder.id.setText(String.valueOf(orderDetail.getId()));
        holder.cat.setText(orderDetail.getCategory());
        holder.size.setText(orderDetail.getSize());
        holder.price.setText(String.valueOf(orderDetail.getPrice()));
        holder.qty.setText(String.valueOf(orderDetail.getQty()));
    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, cat, size, qty, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.no);
            cat = itemView.findViewById(R.id.cat);
            size = itemView.findViewById(R.id.size);
            qty = itemView.findViewById(R.id.qty);
            price = itemView.findViewById(R.id.price);
        }
    }
}

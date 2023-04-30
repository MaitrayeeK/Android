package com.example.crudapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>{
    Context context;
    ArrayList<Employee> employees;

    public EmployeeAdapter(Context context ,ArrayList<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    public EmployeeAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.no.setText(String.valueOf(employee.getId()));
        holder.name.setText(employee.getName());
        holder.dept.setText(employee.getDepartment());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView no, name, dept;
        Button edit, delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            no = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            dept = itemView.findViewById(R.id.dept);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);

            edit.setOnClickListener(this);
            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            DatabaseHandler db = new DatabaseHandler(context, 1);
            if(view == edit) {

            } else if(view == delete) {
                db.delete(Integer.parseInt(no.getText().toString()));
            }
        }
    }
}

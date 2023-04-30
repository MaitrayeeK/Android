package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    Context context;
    int resources;
    ArrayList<Student> students = new ArrayList<Student>();

    public StudentAdapter(Context context, int resources, ArrayList<Student> students) {
        this.context = context;
        this.resources = resources;
        this.students = students;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resources, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student student = students.get(position);
        holder.rno.setText(student.getRno());
        holder.name.setText(student.getName());
        holder.total.setText(student.getTotal());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView rno, name, total;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            rno = itemView.findViewById(R.id.textView);
            name = itemView.findViewById(R.id.textView2);
            total = itemView.findViewById(R.id.textView3);
        }
    }
}

package com.example.database.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList id, name, number;

    public CustomAdapter(Context context, ArrayList id, ArrayList name, ArrayList number) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.number = number;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.data_card,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.out_id.setText(String.valueOf(id.get(position)));
        holder.out_name.setText(String.valueOf(name.get(position)));
        holder.out_number.setText(String.valueOf(number.get(position)));

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView out_id, out_name, out_number;

        public MyViewHolder(View itemView) {
            super(itemView);
            out_id = itemView.findViewById(R.id.out_id);
            out_name = itemView.findViewById(R.id.out_name);
            out_number = itemView.findViewById(R.id.out_number);
        }
    }
}
package com.example.firetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class MyAdaptar extends FirebaseRecyclerAdapter<FetcherBase,MyAdaptar.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyAdaptar(@NonNull FirebaseRecyclerOptions<FetcherBase> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int i, @NonNull FetcherBase fetcherBase) {
        holder.email.setText(fetcherBase.getEmail());
        holder.phone.setText(fetcherBase.getPhone());
        holder.text.setText(fetcherBase.getText());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new myViewHolder(v);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView email,phone,text;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.email_out);
            phone = itemView.findViewById(R.id.phone_out);
            text = itemView.findViewById(R.id.text_out);
        }
    }
}


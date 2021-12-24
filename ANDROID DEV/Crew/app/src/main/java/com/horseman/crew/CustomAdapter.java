package com.horseman.crew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<PersonModel> {
    private Context context;
    private List<PersonModel> personModelList;

    public CustomAdapter(Context context, List<PersonModel> personModelList) {
        super(context, R.layout.list_custom_person, personModelList);
        this.context = context;
        this.personModelList = personModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_person, null, true);
        TextView name = view.findViewById(R.id.out_Name);
        ImageView imageView = view.findViewById(R.id.personImg);

        name.setText(personModelList.get(position).getName());
        String url = personModelList.get(position).getImage();
        Glide.with(context).load(url).into(imageView);

        return view;
    }
}

package com.example.health965.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.health965.Common.Common;
import com.example.health965.Models.Category.Row;
import com.example.health965.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterForSpinner extends ArrayAdapter<Row> {

    public AdapterForSpinner(@NonNull Context context, int resource, @NonNull List<Row> list) {
        super(context, resource, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_model,parent,false);
        Row item = getItem(position);
        TextView Title = convertView.findViewById(R.id.Title);
        ImageView image = convertView.findViewById(R.id.Image);
        if (item != null) {
            Title.setText(item.getName());
            if (!item.getName().equals("اختر الفئة")) {
                String path = Common.BaseURLForImage + "images/" + item.getIcon().getFor() + "/" + Uri.encode(item.getIcon().getName());
                Picasso.with(parent.getContext()).load(path).into(image);
            }
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_model,parent,false);
        Row item = getItem(position);
        TextView Title = convertView.findViewById(R.id.Title);
        ImageView image = convertView.findViewById(R.id.Image);
        if (item != null) {
            if (!item.getName().equals("اختر الفئة")) {
                Title.setText(item.getName());
                String path = Common.BaseURLForImage + "images/" + item.getIcon().getFor() + "/" + Uri.encode(item.getIcon().getName());
                Picasso.with(parent.getContext()).load(path).into(image);
            }else {
                Title.setText(" ");
                //String path = Common.BaseURLForImage + "images/" + item.getIcon().getFor() + "/" + Uri.encode(item.getIcon().getName());
                Picasso.with(parent.getContext()).load(Common.BaseURLForImage).into(image);
            }
        }
        return convertView;
    }
}

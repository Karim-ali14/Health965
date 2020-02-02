package com.example.health965.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.R;

public class ViewHolderFor extends RecyclerView.ViewHolder {
    public TextView Name,Dec;
    public ImageView Image;
    public ViewHolderFor(@NonNull View itemView) {
        super(itemView);
        Name = itemView.findViewById(R.id.Name);
        Dec = itemView.findViewById(R.id.Dec);
        Image = itemView.findViewById(R.id.Image);
    }
}

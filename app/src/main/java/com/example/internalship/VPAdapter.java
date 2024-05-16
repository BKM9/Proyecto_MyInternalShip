package com.example.internalship;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VPAdapter extends RecyclerView.Adapter<VPAdapter.ViewHolder> {

    ArrayList<ViewPageItem> viewPageItems;


    public VPAdapter(ArrayList<ViewPageItem> viewPageItems) {
        this.viewPageItems = viewPageItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewpager_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewPageItem viewPageItem = viewPageItems.get(position);

        holder.imageView.setImageResource(viewPageItem.imageID);
        holder.tvHeading.setText(viewPageItem.heading);
        holder.tvDescription.setText(viewPageItem.imageID);
    }

    @Override
    public int getItemCount() {
        return viewPageItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvHeading, tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivimage);
            tvHeading = itemView.findViewById(R.id.tvHeading);
            tvDescription = itemView.findViewById(R.id.tvDesc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), FullScreenImageActivity.class);
                    intent.putExtra("IMAGE_ID", viewPageItems.get(getAdapterPosition()).imageID);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

}

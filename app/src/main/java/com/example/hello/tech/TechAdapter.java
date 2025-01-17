package com.example.hello.tech;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.example.hello.R;
import com.example.hello.recyclerView.RecyclerDescriptionActivity;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TechAdapter extends RecyclerView.Adapter<TechAdapter.holder> {

    ArrayList<TechModel> data;
    Context context;

    public TechAdapter(ArrayList<TechModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.sample_view, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        final TechModel model = data.get(position);

        holder.title.setText(model.getTitle());
        holder.subtitle.setText(model.getSubtitle());
        Picasso.get().load(model.getImageUrl()).into(holder.imageView);
//        Glide.with(holder.imageView).load(model.getImageUrl()).error(R.drawable.android).into(holder.imageView);
        holder.itemView.setOnLongClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(holder.imageView.getContext(), v);
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.edit:
                            Intent intent = new Intent(holder.itemView.getContext(), EditTechActivity.class);
                            intent.putExtra("title", model.getTitle());
                            intent.putExtra("subtitle", model.getSubtitle());
                            intent.putExtra("image", model.getImageUrl());
                            intent.putExtra("key", model.getKey());
                            holder.itemView.getContext().startActivity(intent);
                            break;

                        case R.id.delete:
                            AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                            builder.setTitle("Are you sure?").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    FirebaseDatabase.getInstance().getReference().child("Tech Items").child(model.getKey()).removeValue();
                                    Toast.makeText(holder.itemView.getContext(), "Deleted", Toast.LENGTH_SHORT).show();

                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(holder.itemView.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                    }
                    return false;
                }
            });
            return false;
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class holder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title, subtitle;
        CardView cardView;
        public holder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.main);
            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
        }
    }
}

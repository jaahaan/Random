package com.example.hello.C;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello.R;
import com.example.hello.recyclerView.RecyclerDescriptionActivity;
import com.example.hello.tech.EditTechActivity;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapterC extends RecyclerView.Adapter<CustomAdapterC.holder> {

    ArrayList<ModelC> data;
    Context context;

    public CustomAdapterC(ArrayList<ModelC> data, Context context) {
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
        ModelC temp = data.get(position);
        Picasso.get().load(temp.getImageUrl()).into(holder.imageView);
        holder.title.setText(temp.getTitle());
        holder.subtitle.setText(temp.getSubtitle());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecyclerDescriptionActivity.class);
                intent.putExtra("title", temp.getTitle());
                intent.putExtra("subtitle", temp.getSubtitle());
//                intent.putExtra("imageId", temp.getImageID());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(holder.itemView.getContext(), v);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.edit:
                                Intent intent = new Intent(holder.itemView.getContext(), EditTechActivity.class);
                                intent.putExtra("title", temp.getTitle());
                                intent.putExtra("subtitle", temp.getSubtitle());
                                intent.putExtra("image", temp.getImageUrl());
                                intent.putExtra("key", temp.getKey());
                                holder.itemView.getContext().startActivity(intent);
                                break;

                            case R.id.delete:
                                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                                builder.setTitle("Are you sure?").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FirebaseDatabase.getInstance().getReference().child("Tech Items").child(temp.getKey()).removeValue();
                                        Toast.makeText(holder.itemView.getContext(), "Deleted", Toast.LENGTH_SHORT).show();

                                    }
                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(holder.itemView.getContext(), "Canceled", Toast.LENGTH_SHORT).show();

                                    }
                                });

                        }

                        return false;
                    }
                });
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title, subtitle;

        public holder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);

        }
    }
}

package com.example.hello.B;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello.R;
import com.example.hello.tech.EditTechActivity;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapterB extends RecyclerView.Adapter<CustomAdapterB.holder> {

    ArrayList<ModelB> list;

    public CustomAdapterB(ArrayList<ModelB> list) {
        this.list = list;
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
        ModelB model = list.get(position);
        Picasso.get().load(model.getImageUrl()).into(holder.imageView);
        holder.title.setText(model.getTitle());
        holder.subtitle.setText(list.get(position).getSubtitle());
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
                                intent.putExtra("title", model.getTitle());
                                intent.putExtra("subtitle", model.getSubtitle());
                                intent.putExtra("image", model.getImageUrl());
                                intent.putExtra("key", model.getKey());
                                holder.itemView.getContext().startActivity(intent);
                                break;

                            case R.id.delete:
                                FirebaseDatabase.getInstance().getReference().child("Tech Items").child(model.getKey()).removeValue();
                                Toast.makeText(holder.itemView.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
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
        return list.size();
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

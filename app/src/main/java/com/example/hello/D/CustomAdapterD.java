package com.example.hello.D;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello.R;
import com.example.hello.recyclerView.RecyclerDescriptionActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapterD  extends RecyclerView.Adapter<CustomAdapterD.holder>{

    ArrayList<ModelD> list;
    Context context;

    public CustomAdapterD(ArrayList<ModelD> list, Context context) {
        this.list = list;
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
        ModelD data = list.get(position);
        holder.title.setText(data.getTitle());
        holder.subtitle.setText(data.getSubtitle());
        Picasso.get().load(data.getImageUrl()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecyclerDescriptionActivity.class);
                intent.putExtra("title", data.getTitle());
                intent.putExtra("subtitle", data.getSubtitle());
                intent.putExtra("imgId", data.getImageUrl());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, subtitle;
        LinearLayout layout;
        public holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            layout = itemView.findViewById(R.id.main);
        }
    }
}

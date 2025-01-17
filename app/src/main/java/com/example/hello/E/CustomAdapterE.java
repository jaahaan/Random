package com.example.hello.E;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello.R;
import com.example.hello.recyclerView.RecyclerDescriptionActivity;

import java.util.ArrayList;

public class CustomAdapterE extends RecyclerView.Adapter<CustomAdapterE.holder> {

    ArrayList<ModelE> data;
    Context context;

    public CustomAdapterE(ArrayList<ModelE> data, Context context) {
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
        ModelE temp = data.get(position);
        holder.title.setText(temp.getTitle());
        holder.subtitle.setText(temp.getSubtitle());
//        holder.imageView.setImageResource(temp.getImageId());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecyclerDescriptionActivity.class);
                intent.putExtra("title", temp.getTitle());
                intent.putExtra("subtitle", temp.getSubtitle());
//                intent.putExtra("imageId", temp.getImageId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class holder extends RecyclerView.ViewHolder {

        TextView title, subtitle;
        ImageView imageView;
        CardView cardView;

        public holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.main);
        }
    }
}

package com.example.salesapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesapp.R;
import com.example.salesapp.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ItemViewHolder> {
    Context context;
    List<User> items;

    public UserAdapter(Context context, List<User> items) {
        this.context = context;
       this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View shopItems = LayoutInflater.from(context).inflate(R.layout.user_message, parent, false);
        return new ItemViewHolder(shopItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.message.setText(items.get(position).getMessage());
//        holder.name.setText(items.get(position).getName());
//        holder.rating.setText("Рейтинг: "+String.valueOf(items.get(position).getRating())+"/5");
        holder.rating.setRating(items.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView message, name;
//                ,rating;
RatingBar rating;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            message =itemView.findViewById(R.id.message);
            rating =itemView.findViewById(R.id.rating);

        }
    }

}

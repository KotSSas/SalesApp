package com.example.salesapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesapp.MainActivity;
import com.example.salesapp.R;
import com.example.salesapp.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItems = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(categoryItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {

        int img_id = context.getResources().getIdentifier(
                "ic_" + categories.get(position).getImg(),
                "drawable", context.getPackageName());
        holder.img.setImageResource(img_id);

        holder.category_title.setText(categories.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // holder.img.setImageResource(context.getResources().getIdentifier("ic_category","drawable", context.getPackageName()));

                MainActivity.show_category_shops(categories.get(position).getId());
                holder.img.setImageResource(context.getResources().getIdentifier("ic_category_on","drawable", context.getPackageName()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView category_title;
        ImageView img;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            category_title = itemView.findViewById(R.id.category_title);
            img= itemView.findViewById(R.id.category_bg);
        }
    }

}

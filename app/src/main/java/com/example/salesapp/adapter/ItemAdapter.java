package com.example.salesapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesapp.ItemPage;
import com.example.salesapp.R;
import com.example.salesapp.ShopPage;
import com.example.salesapp.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    Context context;
    List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View shopItems = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ItemAdapter.ItemViewHolder(shopItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.price1.setText(items.get(position).getPrice1());
        holder.price2.setText(items.get(position).getPrice2());

        //int img_id = context.getResources().getIdentifier("ic_" + items.get(position).getImg(), "drawable", context.getPackageName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemPage.class);

                intent.putExtra("image",items.get(position).getImg());
                intent.putExtra("title",items.get(position).getTitle());
                intent.putExtra("pr1",items.get(position).getPrice1());
                intent.putExtra("pr2",items.get(position).getPrice2());
                intent.putExtra("id",items.get(position).getId());
                intent.putExtra("link",items.get(position).getLink());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView title, id, price1, price2;
        ImageView img;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

           // id = itemView.findViewById(R.id.item_view);
            title = itemView.findViewById(R.id.item_title);
            price1 = itemView.findViewById(R.id.item_p1);
            price2 = itemView.findViewById(R.id.item_p2);

        }
    }

}

package com.example.salesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesapp.R;
import com.example.salesapp.model.Item;
import com.example.salesapp.model.Shop;

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
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.price1.setText(items.get(position).getPrice1());
        holder.price2.setText(items.get(position).getPrice2());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView title, text, price1, price2;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title);
            price1 = itemView.findViewById(R.id.item_p1);
            price2 = itemView.findViewById(R.id.item_p2);

        }
    }

}

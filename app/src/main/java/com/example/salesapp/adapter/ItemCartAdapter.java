package com.example.salesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesapp.R;
import com.example.salesapp.model.CartItem;
import com.example.salesapp.model.Shop;

import java.util.List;

public class ItemCartAdapter extends RecyclerView.Adapter<ItemCartAdapter.ItemCartViewHolder> {

    Context context;
    List<CartItem> cartItems;

    public ItemCartAdapter(Context context, List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public ItemCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cartItems = LayoutInflater.from(context).inflate(R.layout.items_cart, parent, false);
        return new ItemCartAdapter.ItemCartViewHolder(cartItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCartViewHolder holder, int position) {

        holder.title.setText(cartItems.get(position).getTitle());
        holder.new_price.setText(cartItems.get(position).getNew_price());

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static final class ItemCartViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView new_price;

        public ItemCartViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title_cart);
            new_price = itemView.findViewById(R.id.item_price_cart);

        }
    }

}

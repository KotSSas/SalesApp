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
import com.example.salesapp.model.Item;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>{

    Context context;
    List<CartItem> cart_items;

    public CartItemAdapter(Context context, List<CartItem> cart_items) {
        this.context = context;
        this.cart_items = cart_items;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cartItems = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartItemAdapter.CartItemViewHolder(cartItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        holder.title.setText(cart_items.get(position).getTitle());
        holder.new_price.setText(cart_items.get(position).getNew_price());
    }

    @Override
    public int getItemCount() {
        return cart_items.size();
    }

    public static final class CartItemViewHolder extends RecyclerView.ViewHolder{

        TextView title, new_price;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.cart_item_title);
            new_price = itemView.findViewById(R.id.cart_item_pr);
        }
    }
}

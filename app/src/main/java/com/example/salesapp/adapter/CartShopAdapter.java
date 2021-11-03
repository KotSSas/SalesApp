package com.example.salesapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesapp.CartShopPage;
import com.example.salesapp.R;
import com.example.salesapp.ShopPage;
import com.example.salesapp.model.CartShop;

import java.util.List;

public class CartShopAdapter extends RecyclerView.Adapter<CartShopAdapter.CartShopViewHolder>{

    Context context;
    List<CartShop> cart_shops;

    public CartShopAdapter(Context context, List<CartShop> cart_shops) {
        this.context = context;
        this.cart_shops = cart_shops;
    }

    @NonNull
    @Override
    public CartShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View shopItems = LayoutInflater.from(context).inflate(R.layout.cart_shop, parent, false);
        return new CartShopAdapter.CartShopViewHolder(shopItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CartShopViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(cart_shops.get(position).getTitle());
        holder.cat.setText(cart_shops.get(position).getCategory());

        int img_id= context.getResources().getIdentifier("ic_" + cart_shops.get(position).getImg(), "drawable", context.getPackageName());
        holder.img.setImageResource(img_id);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CartShopPage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                        new Pair<View, String>(holder.img, "cartImage")
                );

                intent.putExtra("image",img_id);
                intent.putExtra("title",cart_shops.get(position).getTitle());

                context.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cart_shops.size();
    }

    public static final class CartShopViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView title;
        TextView cat;

        public CartShopViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.image_cart);
            title = itemView.findViewById(R.id.shop_title_cart);
            cat = itemView.findViewById(R.id.shop_category_cart);
        }
    }
}

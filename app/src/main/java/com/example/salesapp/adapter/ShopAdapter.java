package com.example.salesapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.ImageView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesapp.R;
import com.example.salesapp.ShopPage;
import com.example.salesapp.model.Shop;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

    Context context;
    List<Shop> shops;

    public ShopAdapter(Context context, List<Shop> shops) {
        this.context = context;
        this.shops = shops;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View shopItems = LayoutInflater.from(context).inflate(R.layout.shop_item, parent, false);
        return new ShopAdapter.ShopViewHolder(shopItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.ShopViewHolder holder, @SuppressLint("RecyclerView") int position) {

            int img_id = context.getResources().getIdentifier("ic_" + shops.get(position).getImg(), "drawable", context.getPackageName());
            holder.img.setImageResource(img_id);




            holder.title.setText(shops.get(position).getTitle());
            holder.time_work.setText(shops.get(position).getWork_time());
            holder.cat.setText(shops.get(position).getCategory());
            holder.site.setText(shops.get(position).getSite());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ShopPage.class);

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                            new Pair<View, String>(holder.img, "shopImage")
                    );

                    intent.putExtra("image", img_id);
                    intent.putExtra("title", shops.get(position).getTitle());
                    intent.putExtra("category", shops.get(position).getCategory());

                    context.startActivity(intent, options.toBundle());
                }
            });

    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public static final class ShopViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView title;
        TextView time_work;
        TextView cat;
        TextView site;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.shop_title);
            cat = itemView.findViewById(R.id.shop_category);
            time_work = itemView.findViewById(R.id.shop_work);
            site = itemView.findViewById(R.id.shop_site);
            img = itemView.findViewById(R.id.image);
        }


    }

}

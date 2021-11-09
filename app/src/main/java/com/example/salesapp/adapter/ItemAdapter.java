package com.example.salesapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesapp.ItemPage;
import com.example.salesapp.R;
import com.example.salesapp.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

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
                v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_item));
                Vibrator v1 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }


                Intent intent = new Intent(context, ItemPage.class);

                intent.putExtra("image", items.get(position).

                        getImg());
                intent.putExtra("title", items.get(position).

                        getTitle());
                intent.putExtra("pr1", items.get(position).

                        getPrice1());
                intent.putExtra("pr2", items.get(position).

                        getPrice2());
                intent.putExtra("id", items.get(position).

                        getId());
                intent.putExtra("link", items.get(position).

                        getLink());

//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
////                        context.startActivity(intent, options.toBundle());
//
//                context.startActivity(intent);
//
//                    }
//                }, 3000);
                context.startActivity(intent);
            }
    });
}

    @Override
    public int getItemCount() {
        return items.size();
    }

public static final class ItemViewHolder extends RecyclerView.ViewHolder {

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

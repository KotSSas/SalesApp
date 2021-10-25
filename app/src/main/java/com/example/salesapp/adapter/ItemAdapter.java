package com.example.salesapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesapp.R;
import com.example.salesapp.ShopPage;
import com.example.salesapp.model.Item;
import com.example.salesapp.model.Order;
import com.example.salesapp.model.Shop;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    Context context;
    List<Item> items;
    long startTime1 = System.currentTimeMillis();
    long elapsedTime1 = 0;

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

        //вот этот код -> не трогать!

//        holder.itemView.setOnTouchListener(new View.OnTouchListener(){
//            public boolean onTouch(View v, MotionEvent event)
//            {
//                if (event.getAction() == MotionEvent.ACTION_DOWN)
//                {
//                    elapsedTime1 = System.currentTimeMillis() - startTime1;
//                    if (elapsedTime1 > 500)
//                    {
//                        startTime1 = System.currentTimeMillis();
//                        return false;
//                    }
//                    else
//                    {
//                        if (elapsedTime1 > 50)
//                        {
//                            Toast.makeText(v.getContext(),
//                                    "Добавлено в карзину! ;)",
//                                    Toast.LENGTH_LONG).show();
//
//                            Order.items_id.add(items.get(position).getId());
//
//                            startTime1 = System.currentTimeMillis();
//                            return true;
//                        }
//                    }
//                }
//                return false;
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView title, id, price1, price2;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

           // id = itemView.findViewById(R.id.item_view);
            title = itemView.findViewById(R.id.item_title);
            price1 = itemView.findViewById(R.id.item_p1);
            price2 = itemView.findViewById(R.id.item_p2);

        }
    }

}

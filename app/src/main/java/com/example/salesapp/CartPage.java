package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesapp.adapter.ItemCartAdapter;
import com.example.salesapp.adapter.ShopAdapter;
import com.example.salesapp.model.CartItem;
import com.example.salesapp.model.Item;
import com.example.salesapp.model.Order;
import com.example.salesapp.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends AppCompatActivity {

    TextView main_scene, about_us;
    RecyclerView recyclerView;
    static ItemCartAdapter itemCartAdapter;
    static List<CartItem> cartItemsList = new ArrayList<>();
    ImageView clean_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);

        main_scene = findViewById(R.id.main_scene);
        main_scene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        about_us = findViewById(R.id.about_us);
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutActivity();
            }
        });

        List<String> itemsTitle = new ArrayList<>();
        List<String> itemsPrice = new ArrayList<>();

        for(Item item: MainActivity.fullItemList){
            if(Order.items_id.contains(item.getId())){
                itemsTitle.add(item.getTitle());
                itemsPrice.add(item.getPrice2());
            }
        }
        //То-есть оно так должно было быть?
        cartItemsList.clear();


//        cartItemsList.add(new CartItem(1, itemsTitle.get(0), itemsPrice.get(0)));

        if(Order.items_id.size() > 0){
            for (int i = 0; i < Order.items_id.size(); i++) {
                cartItemsList.add(new CartItem(i, itemsTitle.get(i), itemsPrice.get(i)));
            }
        }else {
            cartItemsList.add(new CartItem(1, "Корзина пустая",":("));
        }

       //cartItemsList.add(new CartItem(1, "itemCartAdapteritemCartAdapteritemCartAdapteritemCartAdapteritemCartAdapter!", "23"));

//        clean_all.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               cartItemsList.clear();
//               // cartItemsList.add(new CartItem(1, "Козина пуста :(", " "));
//
//               setItemRecycler(cartItemsList);
//            }
//        });

        setItemRecycler(cartItemsList);

    }

    private void setItemRecycler(List<CartItem> cartItemsList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        recyclerView = findViewById(R.id.recycler_items_cart_view);
        recyclerView.setLayoutManager(layoutManager);

        itemCartAdapter = new ItemCartAdapter(this, cartItemsList);
        recyclerView.setAdapter(itemCartAdapter);
    }

    private void openAboutActivity() {
        startActivity(new Intent(this, AboutUsActivity.class));
    }

    private void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.salesapp.adapter.CartItemAdapter;
import com.example.salesapp.adapter.CartShopAdapter;
import com.example.salesapp.model.CartItem;
import com.example.salesapp.model.CartShop;
import com.example.salesapp.model.Order;

import java.util.ArrayList;
import java.util.List;

public class CartShopPage extends AppCompatActivity {

    TextView main_scene, about_us;
    RecyclerView itemRecycler;
    static CartItemAdapter cartItemAdapter;
    static List<CartItem> cartItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_shop_page);

        ImageView image = findViewById(R.id.shop_page_img_cart);
        TextView title = findViewById(R.id.shop_page_text_cart);

        image.setImageResource(getIntent().getIntExtra("image", 0));
        title.setText(getIntent().getStringExtra("title"));

        main_scene = findViewById(R.id.main_scene);
        main_scene.setOnClickListener(v -> openMainActivity());

        about_us = findViewById(R.id.about_us);
        about_us.setOnClickListener(v -> openAboutActivity());

        cartItemList.clear();

//        if (title.getText().equals("Tavriya")){
//            for (int i = 0; i < Order.tavr_id.size(); i++) {
//                cartItemList.add(new CartItem(i, ))
//            }
//        }
        cartItemList.add(new CartItem(1, "d", "f"));

        setItemRecycler(cartItemList);
    }

    private void setItemRecycler(List<CartItem> cartItemList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        itemRecycler = findViewById(R.id.item_recycler_cart);
        itemRecycler.setLayoutManager(layoutManager);

        cartItemAdapter = new CartItemAdapter(this, cartItemList);
        itemRecycler.setAdapter(cartItemAdapter);
    }
    private void openAboutActivity() {
        startActivity(new Intent(this, AboutUsActivity.class));
    }

    private void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
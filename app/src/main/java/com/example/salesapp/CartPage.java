package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.salesapp.model.Item;
import com.example.salesapp.model.Order;
import com.example.salesapp.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends AppCompatActivity {

    TextView main_scene, about_us;

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

        ListView cart_list = findViewById(R.id.cart_list);

        //дальше использую сущуствующий в проге адаптер для елементарного вывода без дизайна, дальше буду менять

        List<String> itemsTitle = new ArrayList<>();
        for(Item item: MainActivity.fullItemList){
            if(Order.items_id.contains(item.getId())){
                itemsTitle.add(item.getTitle());
            }
        }
        if(Order.items_id.size() == 0){
            List<String> if_null = new ArrayList<>();
            if_null.add("Кoрзина пуста! :(");
            cart_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, if_null));
        }else {
            cart_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsTitle));
        }
    }

    private void openAboutActivity() {
        startActivity(new Intent(this, AboutUsActivity.class));
    }

    private void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
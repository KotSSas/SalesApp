package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.salesapp.model.Item;
import com.example.salesapp.model.Order;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);

        text = findViewById(R.id.textView);
        List<String> itemsTitle = new ArrayList<>();
        List<String> itemsPrice = new ArrayList<>();

        for(Item item: ShopPage.fullItemList) {
            if (Order.items_id.contains(item.getId())) {
                itemsTitle.add(item.getTitle());
                itemsPrice.add(item.getPrice2());
            }
        }

        for (int i = 0; i < itemsTitle.size(); i++) {
            text.setText(text.getText() + itemsTitle.get(i) + "\n");
        }

    }
}
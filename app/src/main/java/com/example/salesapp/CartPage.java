package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.salesapp.adapter.ItemCartAdapter;
import com.example.salesapp.model.CartItem;
import com.example.salesapp.model.Item;
import com.example.salesapp.model.Order;
import com.example.salesapp.model.Shop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartPage extends AppCompatActivity {

    TextView main_scene, about_us;
//    RecyclerView recyclerView;
//    static ItemCartAdapter itemCartAdapter;
//    static List<CartItem> cartItemsList = new ArrayList<>();
//    static List<CartItem> fullCartItemsList = new ArrayList<>();
//    ImageView clean_all;
   // List<String> cit_list = new ArrayList<>();

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

//        ListView listView = findViewById(R.id.list_view);
//        List<String> itemsTitle = new ArrayList<>();

//        if(Order.items_id.size() >0){
//            for (Item item : MainActivity.fullItemList) {//                if (Order.items_id.contains(item.getId())) {
//                    itemsTitle.add(item.getTitle());
//                }
//            }
//
//            listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsTitle));
//
//        }else{
//            itemsTitle.add("Корзина пуста");
//            listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsTitle));
//
//        }

//        cartItemsList.clear();
//
//        List<String> itemsTitle = new ArrayList<>();
//        List<String> itemsPrice = new ArrayList<>();
//        int i = 0;
//        if(Order.items_id.size() > 0) {
//            for (Item item : MainActivity.fullItemList) {
//                if (Order.items_id.contains(item.getId()) && !(itemsTitle.contains(item.getId()))) {
//                    itemsTitle.add(item.getTitle());
//                    itemsPrice.add(item.getPrice2());
//                    cartItemsList.add(new CartItem(i, itemsTitle.get(i), itemsPrice.get(i)));
//                    setItemRecycler(cartItemsList);
//                }
//                i++;
//            }
//        }else{
//            //cartItemsList.add(new CartItem(1, "g", "g"));
//        }
//
//        clean_all.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               cartItemsList.clear();
//               // cartItemsList.add(new CartItem(1, "Козина пуста :(", " "));
//
//              setItemRecycler(cartItemsList);
//            }
//        });
   }

//    private void setItemRecycler(List<CartItem> cartItemsList) {
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//
//        recyclerView = findViewById(R.id.recycler_cart_view);
//        recyclerView.setLayoutManager(layoutManager);
//
//        itemCartAdapter = new ItemCartAdapter(this, cartItemsList);
//        recyclerView.setAdapter(itemCartAdapter);
//    }

    public void onClicked (View view){
        List<String> titles = new ArrayList<>();;
        CheckBox checkBox = (CheckBox) view;
        TextView text = findViewById(R.id.textCart);
        if (checkBox.getId() == R.id.cit_box) {
            if (checkBox.isChecked()) {
                text.setText("");
                for (Item item : ShopPage.fullItemList) {
                    if (Order.cit_list.contains(item.getId())){
                        titles.add(item.getTitle());
                        //text.setText(item.getTitle());
                    }
                }
                for (int i = 0; i < titles.size(); i++) {
                    text.setText(text.getText() + titles.get(i) + "\n");
                }
            } else {
                text.setText("Выберите магазин");
            }
        }
    }

    private void openAboutActivity() {
        startActivity(new Intent(this, AboutUsActivity.class));
    }

    private void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
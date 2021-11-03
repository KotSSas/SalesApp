package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.salesapp.adapter.CartItemAdapter;
import com.example.salesapp.model.CartItem;
import com.example.salesapp.model.Item;
import com.example.salesapp.model.Order;

import java.util.ArrayList;
import java.util.List;

public class CartShopPage extends AppCompatActivity {

    TextView main_scene, about_us;
    RecyclerView itemRecycler;
    static CartItemAdapter cartItemAdapter;

    static List<CartItem> tavrList = new ArrayList<>();
    static List<CartItem> citrList = new ArrayList<>();
    static List<CartItem> metrList = new ArrayList<>();
    static List<CartItem> wiakList = new ArrayList<>();
    static List<CartItem> sinList = new ArrayList<>();
    static List<CartItem> alloList = new ArrayList<>();
    static List<CartItem> staffList = new ArrayList<>();
    static List<CartItem> foxList = new ArrayList<>();
    static List<CartItem> atbList = new ArrayList<>();
    static List<CartItem> urbList = new ArrayList<>();
    static List<CartItem> acList = new ArrayList<>();
    static List<CartItem> ciberList = new ArrayList<>();
    static List<CartItem> sprList = new ArrayList<>();

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

        tavrList.clear();
        citrList.clear();
        metrList.clear();

        wiakList.clear();
        sinList.clear();
        alloList.clear();

        staffList.clear();
        foxList.clear();
        atbList.clear();

        urbList.clear();
        acList.clear();
        ciberList.clear();
        sprList.clear();

        if (title.getText().equals("Tavriya")){
            for (Item item : ShopPage.fullItemList) {
                if (Order.tavr_id.contains(item.getId())){

                    tavrList.add(new CartItem(item.getId(), item.getTitle(), item.getPrice2()));

                }
            }

            setItemRecycler(tavrList);
        }
        if (title.getText().equals("Citrus")){
            for (Item item : ShopPage.fullItemList) {
                if (Order.citr_id.contains(item.getId())){
                    citrList.add(new CartItem(item.getId(), item.getTitle(), item.getPrice2()));
                }
            }
            setItemRecycler(citrList);
        }
        if (title.getText().equals("Metro")){
            for (Item item : ShopPage.fullItemList) {
                if (Order.metr_id.contains(item.getId())){
                    metrList.add(new CartItem(item.getId(), item.getTitle(), item.getPrice2()));
                }
            }
            setItemRecycler(metrList);
        }
        if (title.getText().equals("LC Waikiki")){
            for (Item item : ShopPage.fullItemList) {
                if (Order.waikiki_id.contains(item.getId())){
                    wiakList.add(new CartItem(item.getId(), item.getTitle(), item.getPrice2()));
                }
            }
            setItemRecycler(wiakList);
        }
        if (title.getText().equals("Sinsey")){
            for (Item item : ShopPage.fullItemList) {
                if (Order.sin_id.contains(item.getId())){
                    sinList.add(new CartItem(item.getId(), item.getTitle(), item.getPrice2()));
                }
            }
            setItemRecycler(sinList);
        }
        if (title.getText().equals("Allo")){
            for (Item item : ShopPage.fullItemList) {
                if (Order.allo_id.contains(item.getId())){
                    alloList.add(new CartItem(item.getId(), item.getTitle(), item.getPrice2()));
                }
            }
            setItemRecycler(alloList);
        }
        if (title.getText().equals("Staff")){
            for (Item item : ShopPage.fullItemList) {
                if (Order.staff_id.contains(item.getId())){
                    staffList.add(new CartItem(item.getId(), item.getTitle(), item.getPrice2()));
                }
            }
            setItemRecycler(staffList);
        }
        if (title.getText().equals("Foxtrot")){
            for (Item item : ShopPage.fullItemList) {
                if (Order.fox_id.contains(item.getId())){
                    foxList.add(new CartItem(item.getId(), item.getTitle(), item.getPrice2()));
                }
            }
            setItemRecycler(foxList);
        }
        if (title.getText().equals("ATB")){
            for (Item item : ShopPage.fullItemList) {
                if (Order.atb_id.contains(item.getId())){
                    atbList.add(new CartItem(item.getId(), item.getTitle(), item.getPrice2()));
                }
            }
            setItemRecycler(atbList);
        }
        if (title.getText().equals("Urban Planet")){
            for (Item item : ShopPage.fullItemList) {
                if (Order.urb_id.contains(item.getId())){
                    urbList.add(new CartItem(item.getId(), item.getTitle(), item.getPrice2()));
                }
            }
            setItemRecycler(urbList);
        }
        if (title.getText().equals("Aviatsiya")){
            for (Item item : ShopPage.fullItemList) {
                if (Order.ac_id.contains(item.getId())){
                    acList.add(new CartItem(item.getId(), item.getTitle(), item.getPrice2()));
                }
            }
            setItemRecycler(acList);
        }
        if (title.getText().equals("Kibernetiki")){
            for (Item item : ShopPage.fullItemList) {
                if (Order.ciber_id.contains(item.getId())){
                    ciberList.add(new CartItem(item.getId(), item.getTitle(), item.getPrice2()));
                }
            }
            setItemRecycler(ciberList);
        }
        if (title.getText().equals("Sportmaster")){
            for (Item item : ShopPage.fullItemList) {
                if (Order.spr_id.contains(item.getId())){
                    sprList.add(new CartItem(item.getId(), item.getTitle(), item.getPrice2()));
                }
            }
            setItemRecycler(sprList);
        }
        //cartItemList.add(new CartItem(1, "d", "f"));


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
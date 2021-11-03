package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.salesapp.adapter.CartShopAdapter;
import com.example.salesapp.adapter.CategoryAdapter;
import com.example.salesapp.adapter.ShopAdapter;
import com.example.salesapp.model.CartShop;
import com.example.salesapp.model.Category;
import com.example.salesapp.model.Item;
import com.example.salesapp.model.Order;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends AppCompatActivity {

    TextView main_scene, about_us;
    RecyclerView shopRecycler;
    static CartShopAdapter cartShopAdapter;
    static   List<CartShop> cartShopList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);

        cartShopList.clear();

        if (Order.staff_id.size() > 0){
            cartShopList.add(new CartShop(1, "staff", "Staff", "Одежда"));
        }
        if (Order.allo_id.size() > 0){
            cartShopList.add(new CartShop(2, "allo", "Allo", "Техника"));
        }
        if (Order.citr_id.size() > 0){
            cartShopList.add(new CartShop(3, "citrus", "Citrus", "Техника"));
        }
        if (Order.waikiki_id.size() > 0){
            cartShopList.add(new CartShop(4, "lc", "LC Waikiki", "Одежда"));
        }
        if (Order.ciber_id.size() > 0){
            cartShopList.add(new CartShop(5, "ciber", "Kibernetiki", "Техника"));
        }
        if (Order.atb_id.size() > 0){
            cartShopList.add(new CartShop(6, "atb", "ATB", "Продукты"));
        }
        if (Order.urb_id.size() > 0){
            cartShopList.add(new CartShop(7, "urb", "Urban Planet", "Одежда"));
        }
        if (Order.fox_id.size() > 0){
            cartShopList.add(new CartShop(8, "fox", "Foxtrot", "Техника"));
        }
        if (Order.metr_id.size() > 0){
            cartShopList.add(new CartShop(9, "metr", "Metro", "Продукты"));
        }
        if (Order.sin_id.size() > 0){
            cartShopList.add(new CartShop(10, "sin", "Sinsey", "Одежда"));
        }
        if (Order.tavr_id.size() > 0){
            cartShopList.add(new CartShop(11, "tavr", "Tavriya", "Продукты"));
        }
        if (Order.ac_id.size() > 0){
            cartShopList.add(new CartShop(12, "ah", "Aviatsiya", "Одежда"));
        }

        setShopRecycler(cartShopList);

        main_scene = findViewById(R.id.main_scene);
        main_scene.setOnClickListener(v -> openMainActivity());

        about_us = findViewById(R.id.about_us);
        about_us.setOnClickListener(v -> openAboutActivity());


//        cartShopList.add(new CartShop(2, "citrus", "F", "F"));
//        cartShopList.add(new CartShop(3, "citrus", "F", "F"));
//        cartShopList.add(new CartShop(4, "citrus", "F", "F"));

       // text = findViewById(R.id.textView);
//        List<String> itemsTitle = new ArrayList<>();
//        List<String> itemsPrice = new ArrayList<>();

//        for(Item item: ShopPage.fullItemList) {
//            if (Order.items_id.contains(item.getId())) {
//                itemsTitle.add(item.getTitle());
//                itemsPrice.add(item.getPrice2());
//            }
//        }

//        for (int i = 0; i < itemsTitle.size(); i++) {
//            text.setText(text.getText() + itemsTitle.get(i) + "\n");
//        }

    }

    private void setShopRecycler(List<CartShop> cartShopList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        shopRecycler = findViewById(R.id.shop_recuycler_cart);
        shopRecycler.setLayoutManager(layoutManager);

        cartShopAdapter = new CartShopAdapter(this, cartShopList);
        shopRecycler.setAdapter(cartShopAdapter);
    }
//    public void onCheckboxClicked(View view) {
//        CheckBox checkBox = (CheckBox) view;
//        TextView textView = findViewById(R.id.textView);
//        List<String> itemsTitle = new ArrayList<>();
//        if(checkBox.isChecked()) {
//            if (checkBox.getText() == "Tavr"){
//                for(Item item: ShopPage.fullItemList) {
//                    if (Order.tavr_id.contains(item.getId())) {
//                        itemsTitle.add(item.getTitle());
//                        textView.setText(item.getTitle());
//                    }
//                }
//                textView.setText("");
//                for (int i = 0; i < itemsTitle.size(); i++) {
//                    textView.setText(text.getText() + itemsTitle.get(i) + "\n");
//                }
//            }else if (checkBox.getText() == "Citr"){
//                for(Item item: ShopPage.fullItemList) {
//                    if (Order.citr_id.contains(item.getId())) {
//                        itemsTitle.add(item.getTitle());
//                    }
//                }
//                textView.setText("");
//                for (int i = 0; i < itemsTitle.size(); i++) {
//                    textView.setText(text.getText() + itemsTitle.get(i) + "\n");
//                }
//            }
//        }
//        else {
//            textView.setText("Выберете магазин");
//        }
//    }
    private void openAboutActivity() {
    startActivity(new Intent(this, AboutUsActivity.class));
}

    private void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
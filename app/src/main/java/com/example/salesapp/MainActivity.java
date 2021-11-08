package com.example.salesapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesapp.adapter.CategoryAdapter;
import com.example.salesapp.adapter.ShopAdapter;
import com.example.salesapp.model.Category;
import com.example.salesapp.model.Shop;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView, shopRecycler;
    static CategoryAdapter categoryAdapter;
    static ShopAdapter shopAdapter;
    static List<Category> categoryList = new ArrayList<>();
    static List<Shop> shopList = new ArrayList<>();
    static List<Shop> fullShopsList = new ArrayList<>();
    TextView about_us;
    boolean connected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryList.clear();
        shopList.clear();
        fullShopsList.clear();

//        setCategotyRecycler(categoryList);
//        setShopRecycler(shopList);

        categoryList.add(new Category(1, "Всё", "category_on"));
        categoryList.add(new Category(2, "Техника", "category"));
        categoryList.add(new Category(3, "Одежда", "category"));
        categoryList.add(new Category(4, "Продукты", "category"));
        categoryList.add(new Category(5, "Спорт", "category"));
        // categoryList.add(new Category(5, "Разное", "category"));

        setCategotyRecycler(categoryList);

        shopList.add(new Shop(1, 3, "staff", "Staff", "Одежда", "10:00 - 20:00", "https://staff-clothes.com/"));
        //shopList.add(new Shop(2, 5,"roz","Rozetka", "Разное", "10:00 - 21:00", "https://rozetka.com.ua/" ));
        shopList.add(new Shop(2, 2, "allo", "Allo", "Техника", "09:00 - 20:00", "https://allo.ua/"));
        shopList.add(new Shop(3, 2, "citrus", "Citrus", "Техника", "10:00 - 20:00", "https://www.citrus.ua/")); //*
        shopList.add(new Shop(4, 3, "lc", "LC Waikiki", "Одежда", "10:00 - 21:00", "https://www.lcwaikiki.ua/"));//*
        shopList.add(new Shop(5, 2, "ciber", "Kibernetiki", "Техника", "10:00 - 21:00", "https://kibernetiki.com.ua"));//*
        shopList.add(new Shop(6, 4, "atb", "ATB", "Продукты", "00:00 - 24:00", "https://atbmarket.com/"));
        shopList.add(new Shop(7, 3, "urb", "Urban Planet", "Одежда", "10:00–20:00", "https://urbanplanet.com/"));//*
        shopList.add(new Shop(8, 2, "fox", "Foxtrot", "Техника", "09:00 - 21:00", "https://foxtrot.com.ua/"));
        shopList.add(new Shop(9, 5, "spr", "Sportmaster", "Спорт", "10:00 - 22:00", "https://sportmaster.ua/"));
        shopList.add(new Shop(10, 4, "metr", "Metro", "Продукты", "07:00 - 23:00", "https://www.metro.ua/"));//*
        shopList.add(new Shop(11, 3, "sin", "Sinsey", "Одежда", "10:00 - 21:00", "https://www.sinsay.com/ua"));//*
        shopList.add(new Shop(12, 4, "tavr", "Tavriya", "Продукты", "08:00 - 23:00", "https://www.tavriav.ua/"));//*
        shopList.add(new Shop(13, 3, "ah", "Aviatsiya", "Одежда", "10:00 - 21:00", "https://aviatsiyahalychyny.com"));//*

        fullShopsList.addAll(shopList);

//        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        NetworkInfo mob = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//        if (wifi!=null && wifi.isConnected()||(mob!=null && mob.isConnected())){
//            setShopRecycler(shopList);
//            System.out.println("Connected!");
//            //true
//        }else{
//            //false
//            showCustomDialog();
//            System.out.println("connect to the internet");
//        }
        setShopRecycler(shopList);

        about_us = findViewById(R.id.about_us);
        about_us.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

// Vibrate for 400 milliseconds

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }


                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_item));
                openAboutActivity();


            }
        });


    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please connect to the Internet ")
                .setCancelable(false)
                .setPositiveButton("Connect", (dialogInterface, i) -> startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)))
                .setNegativeButton("Exit", (dialogInterface, i) -> {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                })
                .show()
        ;
    }

    public void exitApp(View view) {
        finish();
    }
//    public void openCart(View view){
//        startActivity(new Intent(this, CartPage.class));
//    }

    private void openAboutActivity() {
        startActivity(new Intent(this, AboutUsActivity.class));
    }

    private void setShopRecycler(List<Shop> shopList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL
                , false);

        shopRecycler = findViewById(R.id.shops_recycler);
        shopRecycler.setLayoutManager(layoutManager);

        shopAdapter = new ShopAdapter(this, shopList);
//        shopRecycler.setAdapter(shopAdapter);


        AlphaInAnimationAdapter alpha = new AlphaInAnimationAdapter(shopAdapter);
        alpha.setDuration(1000);
        alpha.setInterpolator(new OvershootInterpolator());
        alpha.setFirstOnly(false);
        shopRecycler.setAdapter(alpha);
    }

    private void setCategotyRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        recyclerView = findViewById(R.id.category_recycle);
        recyclerView.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        recyclerView.setAdapter(new AlphaInAnimationAdapter(categoryAdapter));

    }

    public static void show_category_shops(int category) {

//            shopList.clear();
//            shopList.addAll(fullShopsList);

        if (category == 1) {

            categoryList.clear();
            shopList.clear();
            shopList.addAll(fullShopsList);

            categoryList.add(new Category(1, "Всё", "category_on"));
            categoryList.add(new Category(2, "Техника", "category"));
            categoryList.add(new Category(3, "Одежда", "category"));
            categoryList.add(new Category(4, "Продукты", "category"));
            // categoryList.add(new Category(5, "Разное", "category"));

            shopAdapter.notifyDataSetChanged();
            categoryAdapter.notifyDataSetChanged();
        } else {
            if (category == 2) {
                categoryList.clear();
                categoryList.add(new Category(1, "Всё", "category"));
                categoryList.add(new Category(2, "Техника", "category_on"));
                categoryList.add(new Category(3, "Одежда", "category"));
                categoryList.add(new Category(4, "Продукты", "category"));
                categoryList.add(new Category(5, "Спорт", "category"));
                //   categoryList.add(new Category(5, "Разное", "category"));
            } else if (category == 3) {
                categoryList.clear();
                categoryList.add(new Category(1, "Всё", "category"));
                categoryList.add(new Category(2, "Техника", "category"));
                categoryList.add(new Category(3, "Одежда", "category_on"));
                categoryList.add(new Category(4, "Продукты", "category"));
                categoryList.add(new Category(5, "Спорт", "category"));
                //  categoryList.add(new Category(5, "Разное", "category"));

            } else if (category == 4) {
                categoryList.clear();
                categoryList.add(new Category(1, "Всё", "category"));
                categoryList.add(new Category(2, "Техника", "category"));
                categoryList.add(new Category(3, "Одежда", "category"));
                categoryList.add(new Category(4, "Продукты", "category_on"));
                categoryList.add(new Category(5, "Спорт", "category"));
                // categoryList.add(new Category(5, "Разное", "category"));

            } else if (category == 5) {
                categoryList.clear();
                categoryList.add(new Category(1, "Всё", "category"));
                categoryList.add(new Category(2, "Техника", "category"));
                categoryList.add(new Category(3, "Одежда", "category"));
                categoryList.add(new Category(4, "Продукты", "category"));
                categoryList.add(new Category(5, "Спорт", "category_on"));
            }

            List<Shop> filter_shops = new ArrayList<>();

            shopList.clear();
            shopList.addAll(fullShopsList);
            filter_shops.clear();

            for (Shop sh : shopList) {
                if (sh.getCategory_s() == category) {
                    filter_shops.add(sh);
                }
            }

            shopList.clear();
            shopList.addAll(filter_shops);

            shopAdapter.notifyDataSetChanged();
            categoryAdapter.notifyDataSetChanged();
        }
    }
}
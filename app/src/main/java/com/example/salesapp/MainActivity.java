package com.example.salesapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Icon;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Pair;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesapp.adapter.ShopAdapter;
import com.example.salesapp.model.Category;
import com.example.salesapp.model.Shop;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView, shopRecycler;
    static ShopAdapter shopAdapter;
    static List<Category> categoryList = new ArrayList<>();
    static List<Shop> shopList = new ArrayList<>();
    static List<Shop> fullShopsList = new ArrayList<>();
    TextView about_us, coming_soon_scene, text_head_cat1, text_head_cat2;

    ImageView ico;

    FloatingActionButton fb0,fb1,fb2,fb3,fb4,fb5;
    boolean connected = false;
    private boolean clicked = false;

    boolean lang_selected = true;
    Context context;
    Resources resources;

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_head_cat1 = findViewById(R.id.text_head_cat);
        text_head_cat2 = findViewById(R.id.text_head_cat2);

        IntroPref introPref = new IntroPref(this);
        if (introPref.isFirstTimeLaunch()) {

            startActivity(new Intent(MainActivity.this, IntroActivity.class));
        } else {
            categoryList.clear();
            shopList.clear();
            fullShopsList.clear();
            fb0= findViewById(R.id.mainButn);
            fb1= findViewById(R.id.firstButn);
            fb2= findViewById(R.id.secondButn);
            fb3= findViewById(R.id.thirdButn);
            fb4= findViewById(R.id.fourthButn);
            fb5= findViewById(R.id.fifthButn);
            about_us = findViewById(R.id.about_us);
            fb0.setOnClickListener(view -> {
                onAddButtonClicked();
            });

            createShorcut();

            fb1.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    show_category_shops(1);
                    Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        v1.vibrate(VibrationEffect.createOneShot(100, 1));
                    }

                    Toast.makeText( MainActivity.this,R.string.all_f, Toast.LENGTH_SHORT).show();
                    text_head_cat2.setText(R.string.all_f3);
                    text_head_cat1.setText(R.string.main_text3);
                    fb1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(196, 188, 248)));
                    fb2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                    fb3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                    fb4.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                    fb5.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                }
            });
            fb2.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    show_category_shops(2);
                    Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        v1.vibrate(VibrationEffect.createOneShot(100, 1));
                    }

                    Toast.makeText( MainActivity.this,R.string.tech_f, Toast.LENGTH_SHORT).show();
//                   fb2.setColorFilter(Color.YELLOW);
                    text_head_cat2.setText(R.string.tech_f3);
                    text_head_cat1.setText(R.string.tech_f2);
                    fb1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                    fb2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(196,188,248)));
                    fb3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                    fb4.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                    fb5.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                }
            });
            fb3.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    show_category_shops(3);
                    Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        v1.vibrate(VibrationEffect.createOneShot(100, 1));
                    }

                    Toast.makeText( MainActivity.this,R.string.clothes_f, Toast.LENGTH_SHORT).show();
                    text_head_cat2.setText(R.string.clothes_f3);
                    text_head_cat1.setText(R.string.clothes_f2);
                    fb1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                    fb2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                    fb3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(196,188,248)));
                    fb4.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                    fb5.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));}
            });
            fb4.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    show_category_shops(4);
                    Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        v1.vibrate(VibrationEffect.createOneShot(100, 1));
                    }

                    Toast.makeText( MainActivity.this,R.string.products_f, Toast.LENGTH_SHORT).show();
                    text_head_cat2.setText(R.string.products_f3);
                    text_head_cat1.setText(R.string.products_f2);


                    fb1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                    fb2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                    fb3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                    fb4.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(196,188,248)));
                    fb5.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                }
            });
            fb5.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    show_category_shops(5);
                    Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        v1.vibrate(VibrationEffect.createOneShot(100, 1));
                    }

                    Toast.makeText( MainActivity.this,R.string.sport_f, Toast.LENGTH_SHORT).show();
                    text_head_cat2.setText(R.string.sport_f3);
                    text_head_cat1.setText(R.string.sport_f2);
                    fb1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                    fb2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                    fb3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(	232,	230,	245)));
                    fb4.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232,	230,	245)));
                    fb5.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(196,188,248)));
                }
            });

//        setCategotyRecycler(categoryList);
//        setShopRecycler(shopList);

            categoryList.add(new Category(1, 1));
            categoryList.add(new Category(2, 0));
            categoryList.add(new Category(3, 0));
            categoryList.add(new Category(4, 0));
            categoryList.add(new Category(5, 0));
            // categoryList.add(new Category(5, "Разное", "category"));

            shopList.add(new Shop(1, 3, "staff", "Staff", "Одежда", "10:00 - 20:00", "https://staff-clothes.com/"));
            //shopList.add(new Shop(2, 5,"roz","Rozetka", "Разное", "10:00 - 21:00", "https://rozetka.com.ua/" ));
            //shopList.add(new Shop(2, 2, "allo", "Allo", "Техника", "09:00 - 20:00", "https://allo.ua/"));
            shopList.add(new Shop(4, 3, "lc", "LC Waikiki", "Одежда", "10:00 - 21:00", "https://www.lcwaikiki.ua/"));//*
            shopList.add(new Shop(5, 2, "ciber", "Kibernetiki", "Техника", "10:00 - 21:00", "https://kibernetiki.com.ua"));//*
            shopList.add(new Shop(6, 4, "atb", "ATB", "Продукты", "00:00 - 24:00", "https://atbmarket.com/"));
            shopList.add(new Shop(14, 4, "ahn", "Ashan", "Продукты", "00:00 - 24:00", "https://auchan.zakaz.ua/ru/"));
            shopList.add(new Shop(7, 3, "urb", "Urban Planet", "Одежда", "10:00–20:00", "https://urbanplanet.com/"));//*
            shopList.add(new Shop(8, 2, "fox", "Foxtrot", "Техника", "09:00 - 21:00", "https://foxtrot.com.ua/"));
            shopList.add(new Shop(9, 5, "spr", "Sportmaster", "Спорт", "10:00 - 22:00", "https://sportmaster.ua/"));
            shopList.add(new Shop(9, 5, "jysk", "Jysk", "Мебель", "10:30 - 20:30", "https://jysk.ua/"));
            shopList.add(new Shop(10, 4, "metr", "Metro", "Продукты", "07:00 - 23:00", "https://www.metro.ua/"));//*
            shopList.add(new Shop(11, 3, "sin", "Sinsey", "Одежда", "10:00 - 21:00", "https://www.sinsay.com/ua"));//*
            shopList.add(new Shop(12, 4, "tavr", "Tavriya", "Продукты", "08:00 - 23:00", "https://www.tavriav.ua/"));//*
            shopList.add(new Shop(13, 3, "ah", "Aviatsiya", "Одежда", "10:00 - 21:00", "https://aviatsiyahalychyny.com"));//*
//            shopList.add(new Shop(3, 2, "citrus", "Citrus", "Техника", "10:00 - 20:00", "https://www.citrus.ua/")); //*
//f
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
            coming_soon_scene = findViewById(R.id.coming_soon_scene);
            coming_soon_scene.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        v1.vibrate(VibrationEffect.createOneShot(100, 1));
                    }

                    v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_item));
                    openMatchingActivity();

                }
            });
        }

    }



    private void onAddButtonClicked() {
        setVisibility(clicked);
        setAnimation(clicked);
        setClickable(clicked);
        clicked = !clicked;
    }
    private void setAnimation(boolean clicked) {
        if (!clicked){
            fb1.setVisibility(View.VISIBLE);
            fb2.setVisibility(View.VISIBLE);
            fb3.setVisibility(View.VISIBLE);
            fb4.setVisibility(View.VISIBLE);
            fb5.setVisibility(View.VISIBLE);
        }else{
            fb1.setVisibility(View.INVISIBLE);
            fb2.setVisibility(View.INVISIBLE);
            fb3.setVisibility(View.INVISIBLE);
            fb4.setVisibility(View.INVISIBLE);
            fb5.setVisibility(View.INVISIBLE);
        }
    }

    private void setClickable(boolean clicked){
        if (!clicked){
            fb1.setClickable(true);
            fb2.setClickable(true);
            fb3.setClickable(true);
            fb4.setClickable(true);
            fb5.setClickable(true);

        }else{
            fb1.setClickable(false);
            fb2.setClickable(false);
            fb3.setClickable(false);
            fb4.setClickable(false);
            fb5.setClickable(false);

        }

    }

    private void setVisibility(boolean clicked) {
        if (!clicked){
            Animation a= AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
            Animation b= AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);
            fb1.startAnimation(a);
            fb2.startAnimation(a);
            fb3.startAnimation(a);
            fb4.startAnimation(a);
            fb5.startAnimation(a);

            fb0.startAnimation(b);
        }else{
            Animation a= AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);
            Animation b= AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
            fb1.startAnimation(a);
            fb2.startAnimation(a);
            fb3.startAnimation(a);
            fb4.startAnimation(a);
            fb5.startAnimation(a);

            fb0.startAnimation(b);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    private void createShorcut() {
        ShortcutManager sM = getSystemService(ShortcutManager.class);

        Intent intent1 = new Intent(getApplicationContext(), AboutUsActivity.class);
        intent1.setAction(Intent.ACTION_VIEW);

        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
        intent2.setAction(Intent.ACTION_VIEW);

        Intent intent3 = new Intent(getApplicationContext(), Matching.class);
        intent3.setAction(Intent.ACTION_VIEW);

        ShortcutInfo shortcut1 = new ShortcutInfo.Builder(this, "shortcut1")
                .setIntent(intent1)
                .setShortLabel("About")
                .setRank(2)
                .setLongLabel("About us page")
                .setShortLabel("This is an about us page")
                .setIcon(Icon.createWithResource(this, R.drawable.in))
                .build();


        ShortcutInfo shortcut2 = new ShortcutInfo.Builder(this, "shortcut2")
                .setIntent(intent2)
                .setShortLabel("Main")
                .setRank(3)
                .setLongLabel("Main page")
                .setShortLabel("This is main page")
                .setIcon(Icon.createWithResource(this, R.drawable.home))
                .build();

        sM.setDynamicShortcuts(Arrays.asList(shortcut1,shortcut2));

    }
//    private void showCustomDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Please connect to the Internet ")
//                .setCancelable(false)
//                .setPositiveButton("Connect", (dialogInterface, i) -> startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)))
//                .setNegativeButton("Exit", (dialogInterface, i) -> {
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                    finish();
//                })
//                .show()
//        ;
//    }

    public void exitApp(View view) {
        finish();
    }
//    public void openCart(View view){
//        startActivity(new Intent(this, CartPage.class));
//    }

    private void openAboutActivity() {
        ico = findViewById(R.id.ico_image);
        Intent intent = new Intent(this, AboutUsActivity.class);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) this,
                new Pair<View, String>(ico, "icoImage")
        );

        startActivity(intent, options.toBundle());
    }
    private void openMatchingActivity() {
        startActivity(new Intent(this, Matching.class));
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

    public static void show_category_shops(int category) {

//            shopList.clear();
//            shopList.addAll(fullShopsList);

        if (category == 1) {

            categoryList.clear();
            shopList.clear();
            shopList.addAll(fullShopsList);

            categoryList.add(new Category(1, 1));
            categoryList.add(new Category(2, 0));
            categoryList.add(new Category(3, 0));
            categoryList.add(new Category(4, 0));
            categoryList.add(new Category(5, 0));
            // categoryList.add(new Category(5, "Разное", "category"));

            shopAdapter.notifyDataSetChanged();
        } else {
            if (category == 2) {
                categoryList.clear();
                categoryList.add(new Category(1, 0));
                categoryList.add(new Category(2, 1));
                categoryList.add(new Category(3, 0));
                categoryList.add(new Category(4, 0));
                categoryList.add(new Category(5, 0));
                //   categoryList.add(new Category(5, "Разное", "category"));
            } else if (category == 3) {
                categoryList.clear();
                categoryList.add(new Category(1, 0));
                categoryList.add(new Category(2, 0));
                categoryList.add(new Category(3, 1));
                categoryList.add(new Category(4, 0));
                categoryList.add(new Category(5, 0));
                //  categoryList.add(new Category(5, "Разное", "category"));

            } else if (category == 4) {
                categoryList.clear();
                categoryList.add(new Category(1, 0));
                categoryList.add(new Category(2, 0));
                categoryList.add(new Category(3, 0));
                categoryList.add(new Category(4, 1));
                categoryList.add(new Category(5, 0));
                // categoryList.add(new Category(5, "Разное", "category"));

            } else if (category == 5) {
                categoryList.clear();
                categoryList.add(new Category(1, 0));
                categoryList.add(new Category(2, 0));
                categoryList.add(new Category(3, 0));
                categoryList.add(new Category(4, 0));
                categoryList.add(new Category(5, 1));
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
        }
    }
}

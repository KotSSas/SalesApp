package com.example.salesapp.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesapp.IntroPref;
import com.example.salesapp.ItemPage;
import com.example.salesapp.R;
import com.example.salesapp.adapter.CategoryAdapter;
import com.example.salesapp.adapter.ShopAdapter;
import com.example.salesapp.model.Category;
import com.example.salesapp.model.Shop;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView, shopRecycler;
    static ShopAdapter shopAdapter;
    static CategoryAdapter categoryAdapter;
    static List<Category> categoryList = new ArrayList<>();
    static List<Shop> shopList = new ArrayList<>();
    static List<Shop> fullShopsList = new ArrayList<>();
    private static final String TAG = "MainActivity";

    //check new account

    static TextView filt;

    ImageView image, image_support;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    //тест

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        if (getIntent().getBooleanExtra("finish", false)) finish();


        IntroPref introPref = new IntroPref(this);
        if (introPref.isFirstTimeLaunch()) {
            startActivity(new Intent(MainActivity.this, IntroActivity.class));
            sendNotification(true);
        } else {
            sendNotification(false);

            filt = findViewById(R.id.filter_text);
            filt.setText(R.string.filter1);
            navigationView = findViewById(R.id.nav_view);
            drawerLayout = findViewById(R.id.drawer_layout);
            image = findViewById(R.id.menuImage);
            //animated background
            AnimationDrawable animationDrawable = (AnimationDrawable) drawerLayout.getBackground();
            animationDrawable.setEnterFadeDuration(2500);
            animationDrawable.setExitFadeDuration(5000);
            animationDrawable.start();

            navigationView.bringToFront();
            navigationView.setNavigationItemSelectedListener(item -> {
                itemListener(item);
                return true;
            });
            navigationView.setCheckedItem(R.id.nav_home);


            image.setOnClickListener(view -> {
                drawerLayout.openDrawer(Gravity.LEFT);
            });
            createShorcut();

            shopList.clear();
            categoryList.clear();

            categoryList.add(new Category(1, "gr1cl"));
           // categoryList.add(new Category(2, "gr2"));
            categoryList.add(new Category(3, "gr4"));
            categoryList.add(new Category(4, "gr3"));
            categoryList.add(new Category(5, "gr5"));

            setCategoryRecycler(categoryList);

            //shopList.add(new Shop(5, 2, "ciber", "ciber_l", "Kibernetiki"));                      //No available prices because of the war
            //shopList.add(new Shop(6, 4, "atb", "atb_l", "ATB"));  //No available prices because of the war
            //shopList.add(new Shop(14, 4, "ahn", "ahn_l", "Ashan"));  //No available prices because of the war
            shopList.add(new Shop(7, 3, "urb", "urb_l", "Urban Planet"));
            shopList.add(new Shop(1, 5, "prost", "prost_l", "Prostor"));
            //shopList.add(new Shop(8, 2, "fox", "fox_l", "Foxtrot")); //No available prices because of the war
            shopList.add(new Shop(9, 5, "spr", "spr_l", "Sportmaster"));
            shopList.add(new Shop(1, 3, "staff", "staff_l", "Staff"));
            //shopList.add(new Shop(9, 5, "jysk", "jysk_l", "Jysk")); //No available prices because of the war
            //shopList.add(new Shop(10, 4, "metr", "metr_l", "Metro")); //No available prices because of the war
            shopList.add(new Shop(12, 4, "tavr", "tavr_l", "Tavriya"));
            shopList.add(new Shop(13, 3, "ah", "ah_l", "Aviatsiya"));

            fullShopsList.addAll(shopList);

            setShopRecycler(shopList);
        }

        image_support = findViewById(R.id.image_support);
        image_support.setOnClickListener(v -> {
                Vibrator v1 = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }

                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_item));

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://stand-with-ukraine.pp.ua/"));
                startActivity(browserIntent);
            });
    }

    private void checkConnenction() {

    }

    private void setCategoryRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL
                , false);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        recyclerView.setAdapter(categoryAdapter);
    }

    private void itemListener(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.nav_gallery:
                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
                Toast.makeText(this, "About us page\uD83D\uDC4C\uD83D\uDE3C", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_git:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/KotSSas/SalesApp"));
                startActivity(browserIntent);
                Toast.makeText(this, "Git hub page👨‍💻", Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }


    private void sendNotification(boolean firstTime) {
        NotificationManagerCompat notificationManagerCompat;
        Notification notification;
        if (firstTime) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("myCh", "Notification", NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myCh")
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_foreground))
                    .setSmallIcon(R.mipmap.ic_launcher_foreground)
                    .setAutoCancel(true)
                    .setContentTitle(getString(R.string.welcome_note_message))
                    .setContentText(getString(R.string.hello_n_message));

            notification = builder.build();
            notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(1, notification);
            Log.i(TAG, "First time app launch Notification");

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("myCh", "Notification", NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myCh")
                    .setSmallIcon(R.mipmap.ic_launcher_foreground)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_foreground))
                    .setAutoCancel(true)
                    .setContentTitle(getString(R.string.welcome_note_message))
                    .setContentText(getString(R.string.hello_again_n_message));
            notification = builder.build();
            notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(1, notification);
            Log.i(TAG, "Launch Notification");


        }
    }

// ffff
    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    private void createShorcut() {
        ShortcutManager sM = getSystemService(ShortcutManager.class);

        Intent intent1 = new Intent(getApplicationContext(), AboutUsActivity.class);
        intent1.setAction(Intent.ACTION_VIEW);

        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
        intent2.setAction(Intent.ACTION_VIEW);

        ShortcutInfo shortcut1 = new ShortcutInfo.Builder(this, "shortcut1")
                .setIntent(intent1)
                .setShortLabel("About")
                .setRank(2)
                .setLongLabel("About us page")
                .setShortLabel("This is an about us page")
                .setIcon(Icon.createWithResource(this, R.drawable.info))
                .build();


        ShortcutInfo shortcut2 = new ShortcutInfo.Builder(this, "shortcut2")
                .setIntent(intent2)
                .setShortLabel("Home")
                .setRank(3)
                .setLongLabel("Home page")
                .setShortLabel("This is home page")
                .setIcon(Icon.createWithResource(this, R.drawable.home1))
                .build();

        sM.setDynamicShortcuts(Arrays.asList(shortcut1, shortcut2));

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

    public static void showCategory(int category) {

        shopList.clear();
        shopList.addAll(fullShopsList);

        List<Shop> filtShops = new ArrayList<>();

        if (category == 1) {

            categoryList.clear();
            categoryList.add(new Category(1, "gr1cl"));
           // categoryList.add(new Category(2, "gr2"));
            categoryList.add(new Category(3, "gr4"));
            categoryList.add(new Category(4, "gr3"));
            categoryList.add(new Category(5, "gr5"));

            filt.setText(R.string.filter1);

            shopList.clear();
            shopList.addAll(fullShopsList);

        } else {
            for (Shop s : shopList) {
                if (s.getCategory_s() == category) {
                    filtShops.add(s);
                }
            }

            if (category == 2) {
                categoryList.clear();
                categoryList.add(new Category(1, "gr1"));
              //  categoryList.add(new Category(2, "gr2cl"));
                categoryList.add(new Category(3, "gr4"));
                categoryList.add(new Category(4, "gr3"));
                categoryList.add(new Category(5, "gr5"));
                filt.setText(R.string.tech_f2);

            } else if (category == 3) {
                categoryList.clear();
                categoryList.add(new Category(1, "gr1"));
              //  categoryList.add(new Category(2, "gr2"));
                categoryList.add(new Category(3, "gr4cl"));
                categoryList.add(new Category(4, "gr3"));
                categoryList.add(new Category(5, "gr5"));
                filt.setText(R.string.clothes_f2);

            } else if (category == 4) {
                categoryList.clear();
                categoryList.add(new Category(1, "gr1"));
              //  categoryList.add(new Category(2, "gr2"));
                categoryList.add(new Category(3, "gr4"));
                categoryList.add(new Category(4, "gr3cl"));
                categoryList.add(new Category(5, "gr5"));
                filt.setText(R.string.products_f2);

            } else if (category == 5) {
                categoryList.clear();
                categoryList.add(new Category(1, "gr1"));
               // categoryList.add(new Category(2, "gr2"));
                categoryList.add(new Category(3, "gr4"));
                categoryList.add(new Category(4, "gr3"));
                categoryList.add(new Category(5, "gr5cl"));
                filt.setText(R.string.sport_f2);

            }
            shopList.clear();
            shopList.addAll(filtShops);
        }
        categoryAdapter.notifyDataSetChanged();
        shopAdapter.notifyDataSetChanged();
    }


}

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
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.salesapp.R;
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
    static List<Category> categoryList = new ArrayList<>();
    static List<Shop> shopList = new ArrayList<>();
    static List<Shop> fullShopsList = new ArrayList<>();
    private static final String TAG = "MainActivity";


    ImageView image;
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


        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        image = findViewById(R.id.menuImage);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(item -> {
            itemListener(item);
            return true;
        });
        navigationView.setCheckedItem(R.id.nav_home);


        image.setOnClickListener(view -> {
            drawerLayout.openDrawer(Gravity.LEFT);
        });


        categoryList.add(new Category(1, 1));
        categoryList.add(new Category(2, 0));
        categoryList.add(new Category(3, 0));
        categoryList.add(new Category(4, 0));
        categoryList.add(new Category(5, 0));

        shopList.add(new Shop(1, 3, "staff", "Staff"));
        shopList.add(new Shop(5, 2, "ciber", "Kibernetiki"));//*
        shopList.add(new Shop(6, 4, "atb", "ATB"));
        shopList.add(new Shop(14, 4, "ahn", "Ashan"));
        shopList.add(new Shop(7, 3, "urb", "Urban Planet"));//*
        shopList.add(new Shop(1, 5, "prost", "Prostor"));
        shopList.add(new Shop(8, 2, "fox", "Foxtrot"));
        shopList.add(new Shop(9, 5, "spr", "Sportmaster"));
        shopList.add(new Shop(9, 5, "jysk", "Jysk"));
        shopList.add(new Shop(10, 4, "metr", "Metro"));//*
//            shopList.add(new Shop(11, 3, "sin", "Sinsey", "Одежда", "10:00 - 21:00", "https://www.sinsay.com/ua"));//*
        shopList.add(new Shop(12, 4, "tavr", "Tavriya"));//*
        shopList.add(new Shop(13, 3, "ah", "Aviatsiya"));//*
//            shopList.add(new Shop(4, 3, "lc", "LC Waikiki", "Одежда", "10:00 - 21:00", "https://www.lcwaikiki.ua/"));//*
        fullShopsList.addAll(shopList);

        setShopRecycler(shopList);


    }

    private void itemListener(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.nav_gallery:
                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_slideshow:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
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
                .setIcon(Icon.createWithResource(this, R.drawable.home1))
                .build();

        sM.setDynamicShortcuts(Arrays.asList(shortcut1, shortcut2));

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


}

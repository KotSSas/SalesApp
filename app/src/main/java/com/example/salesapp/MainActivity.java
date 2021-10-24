package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.salesapp.adapter.CategoryAdapter;
import com.example.salesapp.adapter.ShopAdapter;
import com.example.salesapp.model.Category;
import com.example.salesapp.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView, shopRecycler;
    static CategoryAdapter categoryAdapter;
    static ShopAdapter shopAdapter;
    static   List<Category> categoryList = new ArrayList<>();
    static   List<Shop> shopList = new ArrayList<>();
    static   List<Shop> fullShopsList = new ArrayList<>();
    TextView about_us;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryList.clear();
        shopList.clear();


        categoryList.add(new Category(1, "Всё", "category_on"));
        categoryList.add(new Category(2, "Техника", "category"));
        categoryList.add(new Category(3, "Одежда", "category"));
        categoryList.add(new Category(4, "Продукты", "category"));
        categoryList.add(new Category(5, "Разное", "category"));

        setCategotyRecycler(categoryList);


        shopList.add(new Shop(1,2, "citrus","Citrus", "Техника", "10:00 - 20:00", "https://www.citrus.ua/" ));
        shopList.add(new Shop(2, 3,"lc","LC Waikiki", "Одежда", "10:00 - 21:00", "https://www.lcwaikiki.ua/" ));
        shopList.add(new Shop(3, 4,"atb","ATB", "Продукты", "00:00 - 24:00", "https://atbmarket.com/" ));
        shopList.add(new Shop(4, 3,"staff","Staff", "Одежда", "10:00 - 20:00", "https://staff-clothes.com/" ));
        shopList.add(new Shop(5,2, "comf","Comfy", "Техника", "09:00 - 21:00", "https://comfy.ua/" ));
        shopList.add(new Shop(6, 2,"allo","Allo", "Техника", "09:00 - 20:00", "https://allo.ua/" ));
        shopList.add(new Shop(7, 5,"roz","Rozetka", "Разное", "10:00 - 21:00", "https://rozetka.com.ua/" ));
        shopList.add(new Shop(8, 4,"metr","Metro", "Продукты", "07:00 - 23:00", "https://www.metro.ua/" ));
        shopList.add(new Shop(9, 3,"sin","Sinsey", "Одежда", "10:00 - 21:00", "https://www.sinsay.com/ua" ));
        shopList.add(new Shop(10, 4,"tavr","Tavriya", "Продукты", "08:00 - 23:00", "https://www.tavriav.ua/" ));

        fullShopsList.addAll(shopList);

        setShopRecycler(shopList);

        about_us = findViewById(R.id.about_us);
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutActivity();
            }
        });

    }

    private void openAboutActivity() {
        startActivity(new Intent(this, AboutUsActivity.class));
    }

    private void setShopRecycler(List<Shop> shopList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        shopRecycler = findViewById(R.id.shops_recycler);
        shopRecycler.setLayoutManager(layoutManager);

        shopAdapter = new ShopAdapter(this, shopList);
        shopRecycler.setAdapter(shopAdapter);
    }

    private void setCategotyRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        recyclerView = findViewById(R.id.category_recycle);
        recyclerView.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        recyclerView.setAdapter(categoryAdapter);
    }

    public static void show_category_shops(int category){

            shopList.clear();
            shopList.addAll(fullShopsList);

            if (category == 1){

                categoryList.clear();
                categoryList.add(new Category(1, "Всё", "category_on"));
                categoryList.add(new Category(2, "Техника", "category"));
                categoryList.add(new Category(3, "Одежда", "category"));
                categoryList.add(new Category(4, "Продукты", "category"));
                categoryList.add(new Category(5, "Разное", "category"));

                shopAdapter.notifyDataSetChanged();
                categoryAdapter.notifyDataSetChanged();
            }else {
                if (category == 2){
                    categoryList.clear();
                    categoryList.add(new Category(1, "Всё", "category"));
                    categoryList.add(new Category(2, "Техника", "category_on"));
                    categoryList.add(new Category(3, "Одежда", "category"));
                    categoryList.add(new Category(4, "Продукты", "category"));
                    categoryList.add(new Category(5, "Разное", "category"));
                }else if (category==3){
                    categoryList.clear();
                    categoryList.add(new Category(1, "Всё", "category"));
                    categoryList.add(new Category(2, "Техника", "category"));
                    categoryList.add(new Category(3, "Одежда", "category_on"));
                    categoryList.add(new Category(4, "Продукты", "category"));
                    categoryList.add(new Category(5, "Разное", "category"));
                }else if(category == 4){
                    categoryList.clear();
                    categoryList.add(new Category(1, "Всё", "category"));
                    categoryList.add(new Category(2, "Техника", "category"));
                    categoryList.add(new Category(3, "Одежда", "category"));
                    categoryList.add(new Category(4, "Продукты", "category_on"));
                    categoryList.add(new Category(5, "Разное", "category"));
                }else if(category == 5){
                    categoryList.clear();
                    categoryList.add(new Category(1, "Всё", "category"));
                    categoryList.add(new Category(2, "Техника", "category"));
                    categoryList.add(new Category(3, "Одежда", "category"));
                    categoryList.add(new Category(4, "Продукты", "category"));
                    categoryList.add(new Category(5, "Разное", "category_on"));
                }

                List<Shop> filter_shops = new ArrayList<>();

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
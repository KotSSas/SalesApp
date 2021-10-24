package com.example.salesapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesapp.adapter.ItemAdapter;
import com.example.salesapp.model.Item;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShopPage extends AppCompatActivity {
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    static List<Item> itemList = new ArrayList<>();
    List<String> item;
    TextView  title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_page);

        ImageView image = findViewById(R.id.shop_page_img);
        title = findViewById(R.id.shop_page_text);
        TextView category = findViewById(R.id.shop_page_cat);

        image.setImageResource(getIntent().getIntExtra("image", 0));
        title.setText(getIntent().getStringExtra("title"));
        category.setText(getIntent().getStringExtra("category"));

        itemList.clear();


//        itemList.add(new Item(1,"Продукт", "Text", "20грн", "12грн"));
//        itemList.add(new Item(2,"Продукт", "Text\nText", "20грн", "12грн"));
//        itemList.add(new Item(3,"Продукт", "Text\nText\nText", "20грн", "12грн"));
//        itemList.add(new Item(4,"Продукт", "Text\nText\nText", "20грн", "12грн"));
//        itemList.add(new Item(5,"Продукт", "Text\nText\nText", "20грн", "12грн"));
//        itemList.add(new Item(6,"Продукт", "Text\nText\nText", "20грн", "12грн"));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Document document = getDocument();
            Elements pricesU = document.getElementsByClass("recommended-products__container");
            item = new ArrayList<String>();
            for (Element e : pricesU) {
                Elements items = e.getElementsByClass("products__item");
                for (Element i : items) {
                    item.add(i.text());
                }
            }
            itemList.add(new Item(1,item.get(1),"Text", "2", "1"));
            itemList.add(new Item(2,item.get(2),"Text", "2", "1"));
            itemList.add(new Item(3,item.get(3),"Text", "2", "1"));
            itemList.add(new Item(4,item.get(4),"Text", "2", "1"));
            itemList.add(new Item(5,item.get(5),"Text", "2", "1"));
            setItemRecycler(itemList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //test

    private Document getDocument() throws IOException {
        String url = "https://www.tavriav.ua/";
        return Jsoup.connect(url).get();
    }

    private void setItemRecycler(List<Item> itemList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        recyclerView = findViewById(R.id.item_recycler);
        recyclerView.setLayoutManager(layoutManager);

        itemAdapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(itemAdapter);
    }
}
package com.example.salesapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        Toast.makeText(this, "Ожидайте, идет поист скидок! ;)", Toast.LENGTH_LONG).show();

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
            Elements pricesU = document.getElementsByClass("catalog-products__container");
            List<String> titlesList = new ArrayList<>();
            List<String> pricesList = new ArrayList<>();
            List<String> discountsList = new ArrayList<>();

            for (Element e : pricesU) {
                //All info abot the product
                Elements items = e.getElementsByClass("products__item");
                for (Element b : items) {
                    //Titles
                        titlesList.add(b.getElementsByClass("product__title").text());

                    //Prices
                    Elements product__price = b.getElementsByClass("product__price");
                    for (Element old : product__price) {
                        pricesList.add(old.getElementsByClass("price__old").text());
                    }
                    for (Element newP :product__price){
                        discountsList.add(newP.getElementsByClass("price__discount").text());

                    }
                }

            }
            itemList.add(new Item(1,titlesList.get(0),"Text",pricesList.get(0),discountsList.get(0)));
            itemList.add(new Item(2,titlesList.get(1),"Text",pricesList.get(1),discountsList.get(1)));
            itemList.add(new Item(3,titlesList.get(2),"Text",pricesList.get(2),discountsList.get(2)));
            itemList.add(new Item(4,titlesList.get(3),"Text",pricesList.get(3),discountsList.get(3)));
            itemList.add(new Item(5,titlesList.get(4),"Text",pricesList.get(4),discountsList.get(4)));
            setItemRecycler(itemList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Document getDocument() throws IOException {
        String url = "https://www.tavriav.ua/catalog/discount/";
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
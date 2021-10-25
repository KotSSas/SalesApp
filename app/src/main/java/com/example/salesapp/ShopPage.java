package com.example.salesapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
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
    static List<Item> fullItemList = new ArrayList<>();
    List<String> item;

    TextView title;
    TextView main_scene, about_us;

    List<String> name;
    List<String> old_price;
    List<String> new_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_page);

        Toast.makeText(this, "Ожидайте, идет поиск скидок! ;)", Toast.LENGTH_LONG).show();

        ImageView image = findViewById(R.id.shop_page_img);
        title = findViewById(R.id.shop_page_text);
        TextView category = findViewById(R.id.shop_page_cat);

        image.setImageResource(getIntent().getIntExtra("image", 0));
        title.setText(getIntent().getStringExtra("title"));
        category.setText(getIntent().getStringExtra("category"));

        itemList.clear();

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

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if (title.getText().equals("Tavriya")) {
            try {
                Document document = getDocument();
                Elements pricesU = document.getElementsByClass("catalog-products__container");

                name = new ArrayList<>();
                old_price = new ArrayList<>();
                new_price = new ArrayList<>();

                for (Element e : pricesU) {
                    //All info abot the product
                    Elements items = e.getElementsByClass("products__item");
                    for (Element b : items) {
                        //Titles
                        name.add(b.getElementsByClass("product__title").text());

                        //Prices
                        Elements product__price = b.getElementsByClass("product__price");
                        for (Element old : product__price) {
                            old_price.add(old.getElementsByClass("price__old").text());
                        }
                        for (Element newP : product__price) {
                            new_price.add(newP.getElementsByClass("price__discount").text());

                        }
                    }

                }
                itemList.add(new Item(1, name.get(12), old_price.get(12), new_price.get(12)));
                itemList.add(new Item(2, name.get(10), old_price.get(10), new_price.get(10)));
                itemList.add(new Item(3, name.get(9), old_price.get(9), new_price.get(9)));
                itemList.add(new Item(4, name.get(8), old_price.get(8), new_price.get(8)));
                itemList.add(new Item(5, name.get(7), old_price.get(7), new_price.get(7)));
                setItemRecycler(itemList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (title.getText().equals("Citrus")) {
            try {
                Document document = Jsoup.connect(
                        "https://www.citrus.ua/").get();
                /*
                 * slick-track
                 *product-slide slick-slide slick-current slick-active
                 *product-name product-price
                 * */
                name = new ArrayList<>();
                old_price = new ArrayList<>();
                new_price = new ArrayList<>();


                Elements a = document.getElementsByClass("main-container pt24");
                for (Element element : a) {
                    Elements elementsByClass = element.getElementsByClass("dg grid-0-2-140");
                    for (Element byClass : elementsByClass) {
                        Elements elemntsTitle = byClass.getElementsByClass("link link-0-2-145");
                        Elements elementsByClassPrice = byClass.getElementsByClass("prices-0-2-148");
                        for (Element element1 : elemntsTitle) {
                            Elements findTitle = element1.getElementsByClass("line-clamp-2 break-word");
                            name.add(findTitle.text());
                        }
                        for (Element element1 : elementsByClassPrice) {
                            old_price.add(element1.getElementsByClass("old-price mr8").text());
                        }


                        for (Element element1 : elementsByClassPrice) {
                            new_price.add(element1.getElementsByClass("medium price-0-2-151").text());

                        }
                    }

                }
                itemList.add(new Item(6, name.get(12), old_price.get(12) + "₴", new_price.get(12)));
                itemList.add(new Item(7, name.get(10), old_price.get(10) + "₴", new_price.get(10)));
                itemList.add(new Item(8, name.get(8), old_price.get(8) + "₴", new_price.get(8)));
                itemList.add(new Item(9, name.get(7), old_price.get(7) + "₴", new_price.get(7)));
                itemList.add(new Item(10, name.get(9), old_price.get(9) + "₴", new_price.get(9)));
                setItemRecycler(itemList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (title.getText().equals("Metro")) {
            try {
                Document document = Jsoup.connect(
                        "https://metro.zakaz.ua/ru/promotions/").get();
                name = new ArrayList<>();
                old_price = new ArrayList<>();
                new_price = new ArrayList<>();

                Elements elementsByClass =
                        document.getElementsByClass("jsx-33926795 ProductsBox__list");
                for (Element byClass : elementsByClass) {
                    Elements el_names = byClass.getElementsByClass("jsx-2958303393 ProductTile__titleWrapper");
                    for (Element el_name : el_names) {
                        name.add(el_name.getElementsByClass("jsx-2958303393 ProductTile__title").text());
                    }

                }
                for (Element byClass : elementsByClass) {
                    Elements el_prices = byClass.getElementsByClass("jsx-2958303393 ProductTile__prices");
                    for (Element el_price : el_prices) {

                        String s1= el_price.getElementsByClass("jsx-2958303393 ProductTile__oldPrice").text();
                        String s2= el_price.getElementsByClass("jsx-3642073353 Price__value_caption Price__value_discount").text();
                        if (s1.indexOf("г") == s1.indexOf("р") -1 && s1.indexOf("р") == s1.indexOf("н") -1){
                            old_price.add(s1.substring(0,s1.indexOf("г") -1) + "₴");
                            new_price.add(s2 + "₴");

                        }
                    }
                }
                itemList.add(new Item(11, name.get(0), old_price.get(0), new_price.get(0)));
                itemList.add(new Item(12, name.get(1), old_price.get(1), new_price.get(1)));
                itemList.add(new Item(13, name.get(2), old_price.get(2), new_price.get(2)));
                itemList.add(new Item(14, name.get(3), old_price.get(3), new_price.get(3)));
                itemList.add(new Item(15, name.get(4), old_price.get(4), new_price.get(4)));
                setItemRecycler(itemList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (title.getText().equals("LC Waikiki")) {
            try {
                Document document = Jsoup.connect("https://www.lcwaikiki.ua/ru-RU/UA/catalog/outlet").get();
                name = new ArrayList<>();
                old_price = new ArrayList<>();
                new_price = new ArrayList<>();

                Elements e = document.getElementsByClass("row c-items");
                for (Element element : e) {
                    Elements el = element.getElementsByClass("product-item-info");
                    for (Element element1 : el) {
                        name.add(element1.getElementsByClass("title").text());
                        String s1= element1.getElementsByClass("old-price").text();
                        String s2= element1.getElementsByClass("discount-price").text();
                            if (s1.indexOf("U") == s1.indexOf("A") -1 && s1.indexOf("A") == s1.indexOf("H") -1){
                                old_price.add(s1.substring(0,s1.indexOf("U") -1) + "₴");
                                new_price.add(s2.substring(0,s2.indexOf("U") -1) + "₴");
                            }
                    }
                }

                //Changed here
                itemList.add(new Item(16, name.get(0), old_price.get(0), new_price.get(0)));
                itemList.add(new Item(17, name.get(1), old_price.get(1), new_price.get(1)));
                itemList.add(new Item(18, name.get(2), old_price.get(2), new_price.get(2)));
                itemList.add(new Item(19, name.get(3), old_price.get(3), new_price.get(3)));
                itemList.add(new Item(20, name.get(4), old_price.get(4), new_price.get(4)));

                setItemRecycler(itemList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        fullItemList.addAll(itemList);

    }

    private Document getDocument() throws IOException {
        String url = "https://www.tavriav.ua/catalog/discount/";
        return Jsoup.connect(url).get();
    }

    private void openAboutActivity() {
        startActivity(new Intent(this, AboutUsActivity.class));
    }

    private void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void setItemRecycler(List<Item> itemList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        recyclerView = findViewById(R.id.item_recycler);
        recyclerView.setLayoutManager(layoutManager);

        itemAdapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(itemAdapter);
    }
}
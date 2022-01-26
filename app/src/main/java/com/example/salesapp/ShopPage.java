package com.example.salesapp;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesapp.activities.AboutUsActivity;
import com.example.salesapp.activities.MainActivity;
import com.example.salesapp.activities.Matching;
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
    private static final String TAG = "ShopPage";
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    static List<Item> itemList = new ArrayList<>();
    static List<Item> fullItemList = new ArrayList<>();

    TextView title;
    TextView main_scene, about_us, coming_soon_scene;
    List<String> name;
    List<String> old_price;
    List<String> new_price;
    List<String> photo;
    List<String> links;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_page);
        getSupportActionBar().hide();

        Toast.makeText(this, R.string.wait, Toast.LENGTH_LONG).show();

        ImageView image = findViewById(R.id.shop_page_img);
        title = findViewById(R.id.shop_page_text);
        TextView category = findViewById(R.id.shop_page_cat);

        image.setImageResource(getIntent().getIntExtra("image", 0));
        title.setText(getIntent().getStringExtra("title"));
        category.setText(getIntent().getStringExtra("category"));

        itemList.clear();

        main_scene = findViewById(R.id.main_scene);

        main_scene.setOnClickListener(v -> {
            Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

// Vibrate for 400 milliseconds

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                v1.vibrate(VibrationEffect.createOneShot(100, 1));
            }
            v.startAnimation(AnimationUtils.loadAnimation(ShopPage.this, R.anim.anim_item));
            openMainActivity();
        });

        about_us = findViewById(R.id.about_us);
        about_us.setOnClickListener(v -> {
            Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

// Vibrate for 400 milliseconds

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                v1.vibrate(VibrationEffect.createOneShot(100, 1));
            }
            v.startAnimation(AnimationUtils.loadAnimation(ShopPage.this, R.anim.anim_item));
            openAboutActivity();
        });
        coming_soon_scene = findViewById(R.id.coming_soon_scene);
        coming_soon_scene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }

                v.startAnimation(AnimationUtils.loadAnimation(ShopPage.this, R.anim.anim_item));
                openMatchingActivity();

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
                photo = new ArrayList<>();
                links = new ArrayList<>();

                for (Element e : pricesU) {

                    Elements items = e.getElementsByClass("products__item");
                    for (Element b : items) {

                        name.add(b.getElementsByClass("product__title").text());

                        Elements product__price = b.getElementsByClass("product__price");
                        for (Element old : product__price) {
                            old_price.add(old.getElementsByClass("price__old").text());
                        }
                        for (Element newP : product__price) {
                            new_price.add(newP.getElementsByClass("price__discount").text());
                        }
                        photo.add(b.getElementsByClass("product__image").select("img").attr("src"));
                        links.add(b.getElementsByClass("product__image").select("a").attr("href"));
                    }

                }
                for (int i = 0; i < name.size(); i++) {

                    itemList.add(new Item(25, name.get(i), old_price.get(i), new_price.get(i), photo.get(i), links.get(i)));
                }
                setItemRecycler(itemList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
//            if (title.getText().equals("Citrus")) {
//            try {
//                Document document = Jsoup.connect(
//                        "https://www.citrus.ua/shares/vremya-skidok-ot-philips-tehniku-dlya-kuhni/").get();
//                name = new ArrayList<>();
//                old_price = new ArrayList<>();
//                photo = new ArrayList<>();
//                links = new ArrayList<>();
//
//
//                Elements a = document.getElementsByClass("el-tabs");
//                for (Element element : a) {
//                    Elements elementsByClass = element.getElementsByClass("products__list");
//                    for (Element byClass : elementsByClass) {
//                        Elements elementsByClass1 = byClass.getElementsByClass("product-card product-card--mini product-card--mini--separate");
//                        for (Element element1 : elementsByClass1) {
//                            Elements a1 = elementsByClass1.select("a");
//                            for (Element element2 : a1) {
//                                links.add("https://www.citrus.ua" + element2.attr("href").replaceAll("reviews", "description"));
//                            }
//                            String name_el = element1.getElementsByClass("product-card__name").select("a").attr("title");
//                            name.add(name_el);
//                            old_price.add(element1.getElementsByClass("price").text());
//                            //это не работает
//
//                            String ph = element1.getElementsByClass("image-catalog").attr("src");
//
//                            photo.add(ph);
//
//                            links.add(element1.getElementsByClass("product-card__preview").select("a").attr("href"));
//                        }
//
//
//                    }
//
//                }
//
//
//
//                String s1;
//                String s2;
//                for (int i = 0; i < name.size(); i++) {
//                    if (old_price.get(i).lastIndexOf(" ") == old_price.get(i).indexOf(" ")) {
//                        s1 = old_price.get(i).substring(0, old_price.get(i).indexOf(" ")) + "₴";
//                        s2 = old_price.get(i).substring(old_price.get(i).indexOf(" ")) + "₴";
//                        itemList.add(new Item(i + 9, name.get(i), s1, s2, photo.get(i), links.get(i)));
//                    } else {
//                        s1 = old_price.get(i).substring(0, old_price.get(i).indexOf(" ", 3)) + "₴";
//                        s2 = old_price.get(i).substring(old_price.get(i).indexOf(" ", 3)) + "₴";
//                        itemList.add(new Item(i + 9, name.get(i), s1, s2, photo.get(i), links.get(i)));
//                    }
//                }
//
//
//                setItemRecycler(itemList);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else
//
            if (title.getText().equals("Metro")) {
                try {
                    Document document = Jsoup.connect(
                            "https://metro.zakaz.ua/ru/promotions/").get();
                    name = new ArrayList<>();
                    old_price = new ArrayList<>();
                    new_price = new ArrayList<>();
                    photo = new ArrayList<>();
                    links = new ArrayList<>();

                    Elements elementsByClass =
                            document.getElementsByClass("jsx-33926795 ProductsBox__list");
                    Elements a = elementsByClass.select("a");
                    for (Element k : a) {
                        links.add("https://metro.zakaz.ua" + k.attr("href"));
                    }
                    for (Element element : a) {
                        name.add(element.attr("title"));
                        links.add(element.attr("href"));
                        Elements img = a.select("img");
                        for (Element element1 : img) {
                            if (element1.attr("src").startsWith("https")) {
                                photo.add(element1.attr("src"));
                            }
                        }
                    }
                    for (Element byClass : elementsByClass) {
                        Elements el_prices = byClass.getElementsByClass("jsx-2958303393 ProductTile__prices");
                        for (Element el_price : el_prices) {

                            String s1 = el_price.getElementsByClass("jsx-2958303393 ProductTile__oldPrice").text();
                            String s2 = el_price.getElementsByClass("jsx-221669879 Price__value_caption Price__value_discount").text();
                            if (s1.indexOf("г") == s1.indexOf("р") - 1 && s1.indexOf("р") == s1.indexOf("н") - 1) {
                                old_price.add(s1.substring(0, s1.indexOf("г") - 1) + "₴");
                                new_price.add(s2 + "₴");

                            }

                        }
                    }
                    for (int i = 0; i <= name.size()-1; i++) {

                        itemList.add(new Item(25, name.get(i), old_price.get(i), new_price.get(i), photo.get(i), links.get(i)));
                    }
                    Log.d(TAG, "Metro launch");
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
                    photo = new ArrayList<>();
                    links = new ArrayList<>();
                    Elements e = document.getElementsByClass("row c-items");
                    for (Element element : e) {
                        Elements a = element.select("a");
                        for (Element u : a) {
                            links.add("https://www.lcwaikiki.ua" + u.attr("href"));
                        }
//                    System.out.println(element.select("a").attr("href"));
                        Elements el = element.getElementsByClass("product-item-info");
                        Elements el1 = element.getElementsByClass("picture-box");
                        for (Element element1 : el1) {
                            photo.add(element1.select("img").attr("data-src"));

                        }
                        for (Element element1 : el) {
                            name.add(element1.getElementsByClass("title").text());
                            String s1 = element1.getElementsByClass("old-price").text();
                            String s2 = element1.getElementsByClass("discount-price").text();
                            if (s1.indexOf("U") == s1.indexOf("A") - 1 && s1.indexOf("A") == s1.indexOf("H") - 1) {
                                old_price.add(s1.substring(0, s1.indexOf("U") - 1) + "₴");
                                new_price.add(s2.substring(0, s2.indexOf("U") - 1) + "₴");
                            }
                        }
                    }
                    for (int i = 1; i <= 5; i++) {
                        itemList.add(new Item(25, name.get(i), old_price.get(i), new_price.get(i), photo.get(i), links.get(i)));
                    }
                    printLogMessage("Lc Walkiki");

                    setItemRecycler(itemList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (title.getText().equals("Sinsey")) {
                try {
                    Document document = Jsoup.connect("https://www.sinsay.com/ua/ru/sale").get();
                    name = new ArrayList<>();
                    old_price = new ArrayList<>();
                    new_price = new ArrayList<>();
                    photo = new ArrayList<>();
                    links = new ArrayList<>();


                    Elements elementsByClass = document.getElementsByClass("section layout-with-controls layout-with-aside aside-on-left");
                    for (Element byClass : elementsByClass) {
                        Elements disc_pr = byClass.getElementsByClass("es-discount-price");
                        Elements regular_pr = byClass.getElementsByClass("es-regular-price");
                        for (Element element : disc_pr) {
                            new_price.add(element.text());
                        }
                        for (Element element : regular_pr) {

                            old_price.add(element.text());
                        }
                        Elements a = byClass.getElementsByClass("es-product").select("a");
                        Elements a1 = byClass.getElementsByClass("es-product-name").select("a");
                        for (Element element : a1) {
                            name.add(element.text());
                        }
                        for (Element element : a) {
                            photo.add(element.select("img").attr("data-src"));
                            links.add(element.select("a").attr("href"));
                        }
                        for (int i = 0; i < old_price.size(); i++) {

                            itemList.add(new Item(25, name.get(i), old_price.get(i), new_price.get(i), photo.get(i++), links.get(i)));
                        }




                        printLogMessage("Sinsay");

                        setItemRecycler(itemList);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else

//            if (title.getText().equals("Allo")) {
//            try {
//                Document document = Jsoup.connect(
//                        "https://allo.ua/").get();
//
//                List<String> name = new ArrayList<>();
//                List<String> old_price = new ArrayList<>();
//                List<String> new_price = new ArrayList<>();
//                List<String> photo = new ArrayList<>();
//
//                name = new ArrayList<>();
//                old_price = new ArrayList<>();
//                new_price = new ArrayList<>();
//                photo = new ArrayList<>();
//                links = new ArrayList<>();
//
//
//                Elements elementsByClass = document.getElementsByClass("h-products");
//                for (Element byClass : elementsByClass) {
//                    Elements elementsByClass1 = byClass.getElementsByClass("h-products__list h-pl h-products__list--before-mount");
//                    for (Element element : elementsByClass1) {
//                        Elements elementsByClass2 = element.getElementsByClass("h-pc no-rect-labels no-opts-2 no-opts-3 no-opts-4 no-opts-5 no-opts-6");
//                        for (Element element1 : elementsByClass2) {
//                            photo.add(element1.getElementsByClass("h-pc__img-block").select("img").attr("data-src"));
//                            Elements elementsByClass3 = element1.getElementsByClass("h-pc__content");
//                            for (Element element2 : elementsByClass3) {
//                                name.add(element2.getElementsByClass("product-card__title h-pc__title").text());
//                                old_price.add(element2.getElementsByClass("v-price-box__old").text());
//                                new_price.add(element2.getElementsByClass("v-price-box__cur v-price-box__cur--discount").text());
//                                links.add(element2.select("a").attr("href"));
//                            }
//                        }
//                    }
//                }
//                for (int i = 0; i < name.size(); i++) {
//
//                    itemList.add(new Item(41, name.get(i), old_price.get(i), new_price.get(i), photo.get(i), links.get(i)));
//                }
////                itemList.add(new Item(41, name.get(0), old_price.get(0), new_price.get(0), photo.get(0), links.get(0)));
////                itemList.add(new Item(42, name.get(1), old_price.get(1), new_price.get(1), photo.get(1), links.get(1)));
////                itemList.add(new Item(43, name.get(2), old_price.get(2), new_price.get(2), photo.get(2), links.get(2)));
////                itemList.add(new Item(44, name.get(3), old_price.get(3), new_price.get(3), photo.get(3), links.get(3)));
////                itemList.add(new Item(45, name.get(4), old_price.get(4), new_price.get(4), photo.get(4), links.get(4)));
////                itemList.add(new Item(46, name.get(5), old_price.get(5), new_price.get(5), photo.get(5), links.get(5)));
////                itemList.add(new Item(47, name.get(6), old_price.get(6), new_price.get(6), photo.get(6), links.get(6)));
////                itemList.add(new Item(48, name.get(7), old_price.get(7), new_price.get(7), photo.get(7), links.get(7)));
//                setItemRecycler(itemList);
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else

                if (title.getText().equals("Staff")) {
                    try {
                        Document document = Jsoup.connect(
                                "https://www.staff-clothes.com/m/discounts/").get();
                         name = new ArrayList<>();
                         old_price = new ArrayList<>();
                         new_price = new ArrayList<>();
                         photo = new ArrayList<>();
                        links = new ArrayList<>();


                        Elements elementsByClass = document.getElementsByClass("catalog__products-container");
                        for (Element byClass : elementsByClass) {
                            Elements elementsByClass1 = byClass.getElementsByClass("product-card__container");
                            Elements elementsByClass2 = document.getElementsByClass("catalog__product-catalog");
                            for (Element element : elementsByClass2) {
                                links.add("https://www.staff-clothes.com" + element.select("a").attr("href"));
                            }


                            for (Element element : elementsByClass1) {
                                photo.add(element.getElementsByClass("product-card__main-image").select("img").attr("src"));
                                name.add(element.getElementsByClass("product-card__info--title").text());


                                String s1 = element.getElementsByClass("product-card__info--price").text(); //with discount
                                String s2 = element.getElementsByClass("product-card__info--oldprice").text(); //without discount

                                if (s1.indexOf("г") == s1.indexOf("р") - 1 && s1.indexOf("р") == s1.indexOf("н") - 1) {
                                    new_price.add(s1.substring(0, s1.indexOf("г") - 1) + " ₴");
                                    old_price.add(s2.substring(0, s2.indexOf("г") - 1) + " ₴");
                                }

                            }
                        }
                        for (int i = 0; i < name.size(); i++) {
                            itemList.add(new Item(49, name.get(i), old_price.get(i), new_price.get(i), photo.get(i), links.get(i)));
                        }
                        printLogMessage("Staff");
                        setItemRecycler(itemList);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (title.getText().equals("Foxtrot")) {
                    try {
                        Document doc = Jsoup.connect("https://www.foxtrot.com.ua/uk/actions/12768").ignoreHttpErrors(true).timeout(5000).get();
                         name = new ArrayList<>();
                         old_price = new ArrayList<>();
                         new_price = new ArrayList<>();
                         photo = new ArrayList<>();
                        links = new ArrayList<>();

                        Elements elementsByClass1 = doc.getElementsByClass("action-product-container action-categoryid-58 slot");
                        for (Element e : elementsByClass1) {
                            Elements elementsByClass = e.getElementsByClass("listing__body-wrap");
                            for (Element byClass : elementsByClass) {
                                Elements card__body = byClass.getElementsByClass("card__body");
                                Elements card__head = byClass.getElementsByClass("card__image");
                                for (Element element : card__head) {
                                    photo.add(element.select("source").attr("srcset"));
                                    links.add("https://www.foxtrot.com.ua" + element.select("a").attr("href"));

                                }
                                for (Element element : card__body) {
                                    name.add(element.getElementsByClass("card__title").text());
                                    new_price.add(element.getElementsByClass("card-price").text());
                                    old_price.add(element.getElementsByClass("card__price-discount").select("p").text());

                                }
                            }
                        }
                        for (int i = 0; i < name.size(); i++) {

                            itemList.add(new Item(57, name.get(i), old_price.get(i) + " ₴", new_price.get(i), photo.get(i), links.get(i)));
                        }

                        printLogMessage("Foxtrot");


                        setItemRecycler(itemList);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (title.getText().equals("ATB")) {
                    try {
                        Document doc = Jsoup.connect("https://zakaz.atbmarket.com/catalog/1016/economy").get();
                         name = new ArrayList<>();
                         old_price = new ArrayList<>();
                         new_price = new ArrayList<>();
                         photo = new ArrayList<>();
                        links = new ArrayList<>();

                        Elements elementsByClass1 = doc.getElementsByClass("catalog-list");
                        for (Element e : elementsByClass1) {
                            Elements elementsByClass = e.getElementsByClass("  catalog-item js-product-container   ");
                            for (Element byClass : elementsByClass) {
                                Elements card__body = byClass.getElementsByClass("catalog-item__info");
                                Elements card__pr = byClass.getElementsByClass("catalog-item__bottom");
                                Elements card__head = byClass.getElementsByClass("catalog-item__photo");

                                for (Element element : card__head) {
                                    photo.add(element.getElementsByClass("catalog-item__img").attr("src"));
                                    links.add("https://zakaz.atbmarket.com" + element.getElementsByClass("catalog-item__photo-link").attr("href"));
                                }
                                for (Element element : card__body) {
                                    name.add(element.getElementsByClass("blue-link").text());
                                }
                                String s0;
                                String s1;
                                String s2;
                                String s3;
                                for (Element element : card__pr) {
                                    s0 = element.getElementsByClass("product-price__top").attr("value");
                                    s1 = element.getElementsByClass("product-price__bottom").attr("value");

                                    if (s1.length() != 0) {
                                        new_price.add(s0 + " ₴");
                                        old_price.add(s1 + " ₴");
                                    } else {
                                        new_price.add("Товар без скидки!");
                                        old_price.add(s0 + " ₴");
                                    }
                                }
                            }

                            for (int i = 0; i < name.size(); i++) {
                                itemList.add(new Item(64, name.get(i), old_price.get(i), new_price.get(i), photo.get(i), links.get(i)));
                            }
                        }
                        printLogMessage("ATB");

                        setItemRecycler(itemList);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (title.getText().equals("Urban Planet")) {
                    try {
                        name = new ArrayList<>();
                        old_price = new ArrayList<>();
                        new_price = new ArrayList<>();
                        photo = new ArrayList<>();
                        links = new ArrayList<>();
                        Document document = Jsoup.connect("https://urbanplanet-streetwear.com/sale").get();
                        Elements elementsByClass = document.getElementsByClass("products-page__content");
                        for (Element byClass : elementsByClass) {
                            Elements ti = byClass.getElementsByClass("product-card__media");
                            Elements im = byClass.getElementsByClass("product-card__media");
                            Elements link = byClass.getElementsByClass("product-card__title");
                            Elements pr = byClass.getElementsByClass("product-price product-price--has-old-price");
                            for (Element element : im) {

                                photo.add(element.select("img").attr("data-src"));
                            }
                            for (Element element : link) {
                                links.add("https://urbanplanet-streetwear.com" + element.select("a").attr("href"));
                            }
                            for (Element element : ti) {
                                name.add(element.select("img").attr("alt"));
                            }
                            for (Element element : pr) {
                                String s1 = element.getElementsByClass("product-price__price product-price__price--old").text();
                                String s2 = element.getElementsByClass("product-price__price product-price__price--current").text();
                                old_price.add(s1.substring(0, s1.indexOf("г")) + " ₴");
                                new_price.add(s2.substring(0, s2.indexOf("г")) + " ₴");
                            }

                        }
                        for (int i = 0; i < name.size(); i++) {

                            itemList.add(new Item(72, name.get(i), old_price.get(i), new_price.get(i), photo.get(i), links.get(i)));
                        }
//
                        printLogMessage("Urban Planet");

                        setItemRecycler(itemList);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else
            if (title.getText().equals("Aviatsiya")) {
            try {
                name = new ArrayList<>();
                old_price = new ArrayList<>();
                new_price = new ArrayList<>();
                photo = new ArrayList<>();
                links = new ArrayList<>();
                Document document = Jsoup.connect("https://www.aviatsiyahalychyny.com/shop/?orderby=sale").get();
                Elements pricesU = document.getElementsByClass("row");
                for (Element element : pricesU) {
                    Elements elementsByClass = element.getElementsByClass("col-lg-3 col-md-4 col-sm-6 shortcode-product");
                    for (Element byClass : elementsByClass) {

                        photo.add(byClass.select("img").attr("src"));
                        name.add(byClass.select("h2").attr("itemprop", "name").text());
                        links.add(byClass.select("h2").attr("itemprop", "url")
                                .select("a").attr("href"));
                        Elements buy_button_price = byClass.getElementsByClass("buy_button_price");
                        for (Element element1 : buy_button_price) {
                            Elements old = element1
                                    .getElementsByClass("product-price old-price");
                            for (Element g : old) {
                                old_price.add(g
                                        .select("span")
                                        .attr("itemprop", "price")
                                        .text());
                            }
                            Elements elementsByClass1 = element1
                                    .getElementsByClass("product-price color_red");
                            for (Element m : elementsByClass1) {
                                new_price.add(m
                                        .select("span")
                                        .attr("itemprop", "price")
                                        .text());
                            }

                        }
                    }
                }

                itemList.add(new Item(80, name.get(0), old_price.get(0), new_price.get(0), photo.get(0), links.get(0)));
                itemList.add(new Item(81, name.get(1), old_price.get(1), new_price.get(1), photo.get(1), links.get(1)));
                itemList.add(new Item(82, name.get(2), old_price.get(2), new_price.get(2), photo.get(2), links.get(2)));
                itemList.add(new Item(83, name.get(3), old_price.get(3), new_price.get(3), photo.get(3), links.get(3)));
                itemList.add(new Item(84, name.get(4), old_price.get(4), new_price.get(4), photo.get(4), links.get(4)));
                itemList.add(new Item(85, name.get(5), old_price.get(5), new_price.get(5), photo.get(5), links.get(5)));
                itemList.add(new Item(86, name.get(6), old_price.get(6), new_price.get(6), photo.get(6), links.get(6)));
                itemList.add(new Item(87, name.get(7), old_price.get(7), new_price.get(7), photo.get(7), links.get(7)));

                setItemRecycler(itemList);

            } catch (IOException e) {
                e.printStackTrace();
            }
            } else

                    if (title.getText().equals("Kibernetiki")) {
                        try {
                            name = new ArrayList<>();
                            old_price = new ArrayList<>();
                            new_price = new ArrayList<>();
                            photo = new ArrayList<>();
                            links = new ArrayList<>();


                            Document document = Jsoup.connect("https://kibernetiki.com.ua/#prod1_1").get();
                            Elements elementsByClass = document.getElementsByClass("product-section special");
                            for (Element byClass : elementsByClass) {
                                Elements img = byClass.select("img");
                                Elements pr = byClass.getElementsByClass("item__price-wrap");
                                Elements a = byClass.select("a");
                                for (Element b : a) {
                                    links.add(b.attr("href"));
                                }
                                String s1;
                                String s2;
                                for (Element element : pr) {
                                    s1 = element.getElementsByClass("item__price-old").text();
                                    s2 = element.getElementsByClass("item__price").text();
                                    old_price.add(s1.substring(0, s1.indexOf("г") - 1) + " ₴");
                                    new_price.add(s2.substring(0, s2.indexOf("г") - 1) + " ₴");
                                    //System.out.println(element.getElementsByClass("item__price").text());
                                }
                                for (Element element : img) {
                                    photo.add(element.attr("src"));
                                    name.add(element.attr("alt"));
                                }

                            }

                            links.remove(0);

                                itemList.add(new Item(33, name.get(1), old_price.get(1), new_price.get(1), photo.get(1), links.get(3)));
                                itemList.add(new Item(33, name.get(2), old_price.get(2), new_price.get(2), photo.get(2), links.get(5)));
                                itemList.add(new Item(33, name.get(3), old_price.get(3), new_price.get(3), photo.get(3), links.get(7)));
                                itemList.add(new Item(33, name.get(4), old_price.get(4), new_price.get(4), photo.get(4), links.get(9)));
                                itemList.add(new Item(33, name.get(5), old_price.get(5), new_price.get(5), photo.get(5), links.get(11)));
                                itemList.add(new Item(33, name.get(6), old_price.get(6), new_price.get(6), photo.get(6), links.get(13)));
                                itemList.add(new Item(33, name.get(7), old_price.get(7), new_price.get(7), photo.get(7), links.get(15)));
                                itemList.add(new Item(33, name.get(8), old_price.get(8), new_price.get(8), photo.get(8), links.get(17)));
                                itemList.add(new Item(33, name.get(9), old_price.get(9), new_price.get(9), photo.get(9), links.get(19)));
                                itemList.add(new Item(33, name.get(10), old_price.get(10), new_price.get(10), photo.get(10), links.get(21)));


                            printLogMessage("Kibernetiki");

                            setItemRecycler(itemList);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (title.getText().equals("Prostor")) {

                        try {
                            Document document = Jsoup.connect("https://prostor.ua/ru/supertseny/").get();

                            name = new ArrayList<>();
                            old_price = new ArrayList<>();
                            new_price = new ArrayList<>();
                            photo = new ArrayList<>();
                            links = new ArrayList<>();

                            Elements elementsByClass = document.getElementsByClass("catalogGrid catalog-grid catalog-grid--m catalog-grid--sidebar");

                            for (Element byClass : elementsByClass) {


                                Elements article = byClass.getElementsByClass("catalog-grid__item");

                                String s1;
                                String s2;

                                for (Element element : article) {
                                    name.add(element.getElementsByClass("catalogCard-img").attr("alt"));
                                    //System.out.println(element.getElementsByClass("product__link product__name").text());
                                    photo.add("https://prostor.ua" + element.getElementsByClass("catalogCard-img").attr("src"));
                                    links.add("https://prostor.ua" + element.getElementsByClass("catalogCard-image ").attr("href"));
                                    s1 = element.getElementsByClass("catalogCard-oldPrice").text();
                                    s2 = element.getElementsByClass("catalogCard-price").text();
                                    old_price.add(s1);
                                    new_price.add(s2);
                                }

                            }

                            for (int i = 0; i <= name.size() - 1; i++) {
                                itemList.add(new Item(33, name.get(i), old_price.get(i), new_price.get(i), photo.get(i), links.get(i)));
                            }
                            printLogMessage("Prostor");

                            setItemRecycler(itemList);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if (title.getText().equals("Sportmaster")) {
                        try {
                            name = new ArrayList<>();
                            old_price = new ArrayList<>();
                            new_price = new ArrayList<>();
                            photo = new ArrayList<>();
                            links = new ArrayList<>();


                            Document document = Jsoup.connect("https://www.sportmaster.ua/ru/catalog/odegda_i_obuv_kollektsiya_vesnaleto_2021/page-2/?sale=1").get();

                            Elements elementsByClass = document.getElementsByClass("products-list__box block_big l-goods  ");
                            for (Element byClass : elementsByClass) {
                                Elements img = byClass.getElementsByClass("products-list__box-image");
                                //Elements name_el = byClass.select("products-list__box-name");
                                Elements pr = byClass.getElementsByClass("products-list__box-info");

                                String s1;
                                String s2;
                                for (Element element : pr) {
                                    s1 = element.getElementsByClass("price-old").text();
                                    s2 = element.getElementsByClass("price-new bigger").text();
                                    old_price.add(s1.substring(0, s1.indexOf(",")) + " ₴");
                                    new_price.add(s2.substring(0, s2.indexOf(",")) + " ₴");
                                    name.add(element.getElementsByClass("products-list__box-name").attr("alt"));

                                    //System.out.println(element.getElementsByClass("item__price").text());
                                }
                                String s3;
                                for (Element element : img) {
                                    s3 = element.getElementsByClass("products-list__box-img").attr("style");
                                    photo.add(s3.substring(s3.indexOf("(") + 1, s3.length() - 2));

                                    links.add(element.getElementsByClass("products-list__box-img").attr("href"));
                                }

                            }
                            for (int i = 0; i < name.size(); i++) {
                                itemList.add(new Item(96, name.get(i), old_price.get(i), new_price.get(i), photo.get(i), "https://www.sportmaster.ua" + links.get(i)));

                            }
                            printLogMessage("Sportmaster");

                            setItemRecycler(itemList);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (title.getText().equals("Ashan")) {
                        try {
                            Document document = Jsoup.connect("https://auchan.zakaz.ua/ru/promotions/").get();
                            name = new ArrayList<>();
                            old_price = new ArrayList<>();
                            new_price = new ArrayList<>();
                            photo = new ArrayList<>();
                            links = new ArrayList<>();


                            Elements elementsByClass = document.getElementsByClass("jsx-1517360494 PageWrapBody");
                            for (Element byClass : elementsByClass) {
                                Elements elementsByClass1 = byClass.getElementsByClass("jsx-1311419895 ProductsBox");
                                for (Element e : elementsByClass1) {
                                    Elements e2 = e.getElementsByClass("jsx-1311419895 ProductsBox__list");
                                    for (Element g : e2) {
                                        Elements a = g.select("a");
                                        for (Element element : a) {
                                            name.add(element.attr("title"));
                                            links.add("https://auchan.zakaz.ua"+element.attr("href"));
                                        }

                                        Elements select = g.getElementsByClass("ProductTile__image").select("img");
                                        for (Element element1 : select) {
                                            photo.add(element1.attr("src"));
                                        }

                                        Elements pr = g.getElementsByClass("jsx-1278518368 ProductTile__details");
//                        System.out.println(pr);

                                        for (Element pr_l : pr) {
                                            old_price.add(pr_l.getElementsByClass("jsx-1278518368 ProductTile__oldPrice").text());
                                            new_price.add(pr_l.getElementsByClass("jsx-162399883 Price__value_caption Price__value_discount").text());
                                        }
                                    }

                                }
                            }
                            fillAshan();
                            setItemRecycler(itemList);
                            printLogMessage("Ashan");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (title.getText().equals("Jysk")) {
                        try {
                            Document document = Jsoup.connect("https://jysk.ua/campaigns#meta=solr&start=0&sort=fts_field_product_rating%2Bdesc").get();
                            name = new ArrayList<>();
                            old_price = new ArrayList<>();
                            new_price = new ArrayList<>();
                            photo = new ArrayList<>();
                            links = new ArrayList<>();


                            Elements elementsByClass = document.getElementsByClass("products");

                            for (Element byClass : elementsByClass) {
                                Elements article = byClass.select("article");
                                for (Element element : article) {
                                    name.add(element.select("img").attr("alt"));
                                    photo.add(element.select("img").attr("src"));
                                    links.add("https://jysk.ua" + element.select("a").attr("href"));
                                    old_price.add(element.getElementsByClass("product-price-support price-before beforeprice").text());
                                    new_price.add(element.getElementsByClass("product-price-value").text());
                                }

                            }

                            for (int i = 0; i <= name.size() - 1; i++) {
                                itemList.add(new Item(33, name.get(i), old_price.get(i), new_price.get(i), photo.get(i), links.get(i)));
                            }
                            setItemRecycler(itemList);
                            printLogMessage("Jysk");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


        fullItemList.clear();
        fullItemList.addAll(itemList);
    }


    private void fillAshan() {
        itemList.add(new Item(33, name.get(1), old_price.get(1) , new_price.get(1) + " грн", photo.get(3), "https://auchan.zakaz.ua" + links.get(1)));
        itemList.add(new Item(33, name.get(2), old_price.get(2) , new_price.get(2) + " грн", photo.get(5), "https://auchan.zakaz.ua" + links.get(2)));
        itemList.add(new Item(33, name.get(3), old_price.get(3) , new_price.get(3) + " грн", photo.get(7), "https://auchan.zakaz.ua" + links.get(3)));
        itemList.add(new Item(33, name.get(4), old_price.get(4) , new_price.get(4) + " грн", photo.get(9), "https://auchan.zakaz.ua" + links.get(4)));
        itemList.add(new Item(33, name.get(5), old_price.get(5) , new_price.get(5) + " грн", photo.get(11), "https://auchan.zakaz.ua" + links.get(5)));
        itemList.add(new Item(33, name.get(6), old_price.get(6) , new_price.get(6) + " грн", photo.get(13), "https://auchan.zakaz.ua" + links.get(6)));
        itemList.add(new Item(33, name.get(7), old_price.get(7) , new_price.get(7) + " грн", photo.get(15), "https://auchan.zakaz.ua" + links.get(7)));
        itemList.add(new Item(33, name.get(8), old_price.get(8) , new_price.get(8) + " грн", photo.get(17), "https://auchan.zakaz.ua" + links.get(8)));
        itemList.add(new Item(33, name.get(9), old_price.get(9) , new_price.get(9) + " грн", photo.get(19), "https://auchan.zakaz.ua" + links.get(9)));
        itemList.add(new Item(33, name.get(10), old_price.get(10) , new_price.get(10) + " грн", photo.get(21), "https://auchan.zakaz.ua" + links.get(10)));
        itemList.add(new Item(33, name.get(11), old_price.get(11) , new_price.get(11) + " грн", photo.get(23), "https://auchan.zakaz.ua" + links.get(11)));
        itemList.add(new Item(33, name.get(12), old_price.get(12) , new_price.get(12) + " грн", photo.get(25), "https://auchan.zakaz.ua" + links.get(12)));
        itemList.add(new Item(33, name.get(13), old_price.get(13) , new_price.get(13) + " грн", photo.get(27), "https://auchan.zakaz.ua" + links.get(13)));
        itemList.add(new Item(33, name.get(14), old_price.get(14) , new_price.get(14) + " грн", photo.get(29), "https://auchan.zakaz.ua" + links.get(14)));
        itemList.add(new Item(33, name.get(15), old_price.get(15) , new_price.get(15) + " грн", photo.get(31), "https://auchan.zakaz.ua" + links.get(15)));
    }

    private void printLogMessage(String s) {
        Log.d(TAG, s + " launch");
    }

    public void openCart(View view) {
        openCartPage();
    }

    private void openCartPage() {
//        Intent intent = new Intent(this, CartShopPage.class);
//        intent.putExtra("image",img_id);
//        intent.putExtra("title",.get(position).getTitle());
//        startActivity(intent);
    }

    private Document getDocument() throws IOException {
        String url = "https://www.tavriav.ua/catalog/discount/";
        return Jsoup.connect(url).get();
    }

    private void openAboutActivity() {
        ImageView ico = findViewById(R.id.imageView3);
        Intent intent = new Intent(this, AboutUsActivity.class);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) this,
                new Pair<View, String>(ico, "icoImage")
        );

        startActivity(intent, options.toBundle());
    }

    private void openMatchingActivity() {
        startActivity(new Intent(this, Matching.class));
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
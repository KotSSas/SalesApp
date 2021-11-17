package com.example.salesapp;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
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

import com.example.salesapp.adapter.ItemAdapter;
import com.example.salesapp.model.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

//                itemList.add(new Item(1, name.get(0), old_price.get(0), new_price.get(0), photo.get(0), links.get(0)));
//                itemList.add(new Item(2, name.get(1), old_price.get(1), new_price.get(1), photo.get(1), links.get(1)));
//                itemList.add(new Item(3, name.get(2), old_price.get(2), new_price.get(2), photo.get(2), links.get(2)));
//                itemList.add(new Item(4, name.get(3), old_price.get(3), new_price.get(3), photo.get(3), links.get(3)));
//                itemList.add(new Item(5, name.get(4), old_price.get(4), new_price.get(4), photo.get(4), links.get(4)));
//                itemList.add(new Item(6, name.get(5), old_price.get(5), new_price.get(5), photo.get(5), links.get(5)));
//                itemList.add(new Item(7, name.get(6), old_price.get(6), new_price.get(6), photo.get(6), links.get(6)));
//                itemList.add(new Item(8, name.get(7), old_price.get(7), new_price.get(7), photo.get(7), links.get(7)));
                setItemRecycler(itemList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (title.getText().equals("Citrus")) {
            try {
                Document document = Jsoup.connect(
                        "https://www.citrus.ua/shares/vremya-skidok-ot-philips-tehniku-dlya-kuhni/").get();
                name = new ArrayList<>();
                old_price = new ArrayList<>();
                photo = new ArrayList<>();
                links = new ArrayList<>();


                Elements a = document.getElementsByClass("el-tabs");
                for (Element element : a) {
                    Elements elementsByClass = element.getElementsByClass("products__list");
                    for (Element byClass : elementsByClass) {
                        Elements elementsByClass1 = byClass.getElementsByClass("product-card product-card--mini product-card--mini--separate");
                        for (Element element1 : elementsByClass1) {
                            Elements a1 = elementsByClass1.select("a");
                            for (Element element2 : a1) {
                                links.add("https://www.citrus.ua" + element2.attr("href").replaceAll("reviews", "description"));
                            }
                            String name_el = element1.getElementsByClass("product-card__name").select("a").attr("title");
                            name.add(name_el);
                            old_price.add(element1.getElementsByClass("price").text());
                            //это не работает

                            String ph = element1.getElementsByClass("image-catalog").attr("src");

                            photo.add(ph);

                            links.add(element1.getElementsByClass("product-card__preview").select("a").attr("href"));
                        }


                    }

                }



                String s1;
                String s2;
                for (int i = 0; i < name.size(); i++) {
                    if (old_price.get(i).lastIndexOf(" ") == old_price.get(i).indexOf(" ")) {
                        s1 = old_price.get(i).substring(0, old_price.get(i).indexOf(" ")) + "₴";
                        s2 = old_price.get(i).substring(old_price.get(i).indexOf(" ")) + "₴";
                        itemList.add(new Item(i + 9, name.get(i), s1, s2, photo.get(i), links.get(i)));
                    } else {
                        s1 = old_price.get(i).substring(0, old_price.get(i).indexOf(" ", 3)) + "₴";
                        s2 = old_price.get(i).substring(old_price.get(i).indexOf(" ", 3)) + "₴";
                        itemList.add(new Item(i + 9, name.get(i), s1, s2, photo.get(i), links.get(i)));
                    }
                }


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
                        String s2 = el_price.getElementsByClass("jsx-3642073353 Price__value_caption Price__value_discount").text();
                        if (s1.indexOf("г") == s1.indexOf("р") - 1 && s1.indexOf("р") == s1.indexOf("н") - 1) {
                            old_price.add(s1.substring(0, s1.indexOf("г") - 1) + "₴");
                            new_price.add(s2 + "₴");

                        }

                    }
                }
                for (int i = 0; i < name.size(); i++) {

                    itemList.add(new Item(25, name.get(i), old_price.get(i), new_price.get(i), photo.get(i), links.get(i)));
                }

//                itemList.add(new Item(17, name.get(0), old_price.get(0), new_price.get(0), photo.get(0), links.get(0)));
//                itemList.add(new Item(18, name.get(1), old_price.get(1), new_price.get(1), photo.get(1), links.get(1)));
//                itemList.add(new Item(19, name.get(2), old_price.get(2), new_price.get(2), photo.get(2), links.get(2)));
//                itemList.add(new Item(20, name.get(3), old_price.get(3), new_price.get(3), photo.get(3), links.get(3)));
//                itemList.add(new Item(21, name.get(4), old_price.get(4), new_price.get(4), photo.get(4), links.get(4)));
//                itemList.add(new Item(22, name.get(5), old_price.get(5), new_price.get(5), photo.get(5), links.get(5)));
//                itemList.add(new Item(23, name.get(6), old_price.get(6), new_price.get(6), photo.get(6), links.get(6)));
//                itemList.add(new Item(24, name.get(7), old_price.get(7), new_price.get(7), photo.get(7), links.get(7)));
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
                    System.out.println(element.select("a").attr("href"));
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
                for (int i = 0; i < 30; i++) {
                    itemList.add(new Item(25, name.get(i), old_price.get(i), new_price.get(i), photo.get(i), links.get(i)));
                }
//                itemList.add(new Item(26, name.get(1), old_price.get(1), new_price.get(1), photo.get(1), links.get(1)));
//                itemList.add(new Item(27, name.get(2), old_price.get(2), new_price.get(2), photo.get(2), links.get(2)));
//                itemList.add(new Item(28, name.get(3), old_price.get(3), new_price.get(3), photo.get(3), links.get(3)));
//                itemList.add(new Item(29, name.get(4), old_price.get(4), new_price.get(4), photo.get(4), links.get(4)));
//                itemList.add(new Item(30, name.get(5), old_price.get(5), new_price.get(5), photo.get(5), links.get(5)));
//                itemList.add(new Item(31, name.get(6), old_price.get(6), new_price.get(6), photo.get(6), links.get(6)));
//                itemList.add(new Item(25, name.get(0), old_price.get(0), new_price.get(0), photo.get(0), links.get(0)));
//                itemList.add(new Item(32, name.get(7), old_price.get(7), new_price.get(7), photo.get(7), links.get(7)));

                setItemRecycler(itemList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (title.getText().equals("Sinsey")) {
            try {
                Document document = Jsoup.connect("https://www.sinsay.com/ua/uk/").get();
                List<String> name = new ArrayList<>();
                List<String> old_price = new ArrayList<>();
                List<String> new_price = new ArrayList<>();
                List<String> photo
                        = new ArrayList<>();
                links = new ArrayList<>();


                Elements elementsByClass = document.getElementsByClass("home-ss20-recommended");
                for (Element byClass : elementsByClass) {
                    Elements elementsByClass1 = byClass.getElementsByClass("category-products active");
                    for (Element element : elementsByClass1) {
                        //https://www.sinsay.com/ua/uk
                        Elements elementsByClass2 = element.getElementsByClass("product-price");
                        for (Element element1 : elementsByClass2) {
                            Elements elementsByClass3 = element1.getElementsByClass("final-price");
                            String text1 = elementsByClass3.text();
                            System.out.println(text1);
                            if (text1.indexOf("U") == text1.indexOf("A") - 1 && text1.indexOf("A") == text1.indexOf("H") - 1) {
                                new_price.add(text1.substring(0, text1.indexOf("U") - 1) + " ₴");
                            }
                        }
                        Elements elements_names = element.getElementsByClass("product-name");
                        Elements a = elements_names.select("a");
                        for (Element element1 : a) {
                            name.add(element1.text());
                        }

                        Elements a1 = element.select("a");
                        for (Element element1 : a1) {
                            links.add(
                                    "https://www.sinsay.com/ua/uk/" + element1.attr("href"));
                        }
                        Elements elements_pic = element.getElementsByClass("product-photo-img");
                        for (Element element1 : elements_pic) {
                            photo.add(element1.attr("data-img"));
                        }
                    }
                }

                for (int i = 0; i < name.size(); i++) {
                    itemList.add(new Item(41, name.get(i), new_price.get(i) , "Товар без скидки!", photo.get(i), links.get(i)));
                }
//                itemList.add(new Item(33, name.get(0), new_price.get(0), "Товар без скидки!", photo.get(0), links.get(1)));
//                itemList.add(new Item(34, name.get(1), new_price.get(1), "Товар без скидки!", photo.get(1), links.get(2)));
//                itemList.add(new Item(35, name.get(2), new_price.get(2), "Товар без скидки!", photo.get(2), links.get(3)));
//                itemList.add(new Item(36, name.get(3), new_price.get(3), "Товар без скидки!", photo.get(3), links.get(4)));
//                itemList.add(new Item(37, name.get(4), new_price.get(4), "Товар без скидки!", photo.get(4), links.get(5)));
//                itemList.add(new Item(38, name.get(5), new_price.get(5), "Товар без скидки!", photo.get(5), links.get(6)));
//                itemList.add(new Item(39, name.get(6), new_price.get(6), "Товар без скидки!", photo.get(6), links.get(7)));
//                itemList.add(new Item(40, name.get(7), new_price.get(7), "Товар без скидки!", photo.get(7), links.get(8)));

                setItemRecycler(itemList);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (title.getText().equals("Allo")) {
            try {
                Document document = Jsoup.connect(
                        "https://allo.ua/").get();

                List<String> name = new ArrayList<>();
                List<String> old_price = new ArrayList<>();
                List<String> new_price = new ArrayList<>();
                List<String> photo = new ArrayList<>();

                name = new ArrayList<>();
                old_price = new ArrayList<>();
                new_price = new ArrayList<>();
                photo = new ArrayList<>();
                links = new ArrayList<>();


                Elements elementsByClass = document.getElementsByClass("h-products");
                for (Element byClass : elementsByClass) {
                    Elements elementsByClass1 = byClass.getElementsByClass("h-products__list h-pl h-products__list--before-mount");
                    for (Element element : elementsByClass1) {
                        Elements elementsByClass2 = element.getElementsByClass("h-pc no-rect-labels no-opts-2 no-opts-3 no-opts-4 no-opts-5 no-opts-6");
                        for (Element element1 : elementsByClass2) {
                            photo.add(element1.getElementsByClass("h-pc__img-block").select("img").attr("data-src"));
                            Elements elementsByClass3 = element1.getElementsByClass("h-pc__content");
                            for (Element element2 : elementsByClass3) {
                                name.add(element2.getElementsByClass("product-card__title h-pc__title").text());
                                old_price.add(element2.getElementsByClass("v-price-box__old").text());
                                new_price.add(element2.getElementsByClass("v-price-box__cur v-price-box__cur--discount").text());
                                links.add(element2.select("a").attr("href"));
                            }
                        }
                    }
                }
                for (int i = 0; i < name.size(); i++) {

                    itemList.add(new Item(41, name.get(i), old_price.get(i), new_price.get(i), photo.get(i), links.get(i)));
                }
//                itemList.add(new Item(41, name.get(0), old_price.get(0), new_price.get(0), photo.get(0), links.get(0)));
//                itemList.add(new Item(42, name.get(1), old_price.get(1), new_price.get(1), photo.get(1), links.get(1)));
//                itemList.add(new Item(43, name.get(2), old_price.get(2), new_price.get(2), photo.get(2), links.get(2)));
//                itemList.add(new Item(44, name.get(3), old_price.get(3), new_price.get(3), photo.get(3), links.get(3)));
//                itemList.add(new Item(45, name.get(4), old_price.get(4), new_price.get(4), photo.get(4), links.get(4)));
//                itemList.add(new Item(46, name.get(5), old_price.get(5), new_price.get(5), photo.get(5), links.get(5)));
//                itemList.add(new Item(47, name.get(6), old_price.get(6), new_price.get(6), photo.get(6), links.get(6)));
//                itemList.add(new Item(48, name.get(7), old_price.get(7), new_price.get(7), photo.get(7), links.get(7)));
                setItemRecycler(itemList);


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (title.getText().equals("Staff")) {
            try {
                Document document = Jsoup.connect(
                        "https://www.staff-clothes.com/m/discounts/").get();
                List<String> name = new ArrayList<>();
                List<String> old_price = new ArrayList<>();
                List<String> new_price = new ArrayList<>();
                List<String> photo = new ArrayList<>();
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

//                itemList.add(new Item(49, name.get(0), old_price.get(0), new_price.get(0), photo.get(0), links.get(0)));
//                itemList.add(new Item(50, name.get(1), old_price.get(1), new_price.get(1), photo.get(1), links.get(1)));
//                itemList.add(new Item(51, name.get(2), old_price.get(2), new_price.get(2), photo.get(2), links.get(2)));
//                itemList.add(new Item(52, name.get(3), old_price.get(3), new_price.get(3), photo.get(3), links.get(3)));
//                itemList.add(new Item(53, name.get(4), old_price.get(4), new_price.get(4), photo.get(4), links.get(4)));
//                itemList.add(new Item(54, name.get(5), old_price.get(5), new_price.get(5), photo.get(5), links.get(5)));
//                itemList.add(new Item(55, name.get(6), old_price.get(6), new_price.get(6), photo.get(6), links.get(6)));
//                itemList.add(new Item(56, name.get(7), old_price.get(7), new_price.get(7), photo.get(7), links.get(7)));
                setItemRecycler(itemList);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (title.getText().equals("Foxtrot")) {
            try {
                Document doc = Jsoup.connect("https://www.foxtrot.com.ua/uk/actions/12768").ignoreHttpErrors(true).timeout(5000).get();
                List<String> name = new ArrayList<>();
                List<String> old_price = new ArrayList<>();
                List<String> new_price = new ArrayList<>();
                List<String> photo = new ArrayList<>();
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
//                itemList.add(new Item(57, name.get(0), old_price.get(0) + " ₴", new_price.get(0), photo.get(0), links.get(0)));
//                itemList.add(new Item(58, name.get(1), old_price.get(1) + " ₴", new_price.get(1), photo.get(1), links.get(1)));
//                itemList.add(new Item(59, name.get(2), old_price.get(2) + " ₴", new_price.get(2), photo.get(2), links.get(2)));
//                itemList.add(new Item(60, name.get(3), old_price.get(3) + " ₴", new_price.get(3), photo.get(3), links.get(3)));
//                itemList.add(new Item(61, name.get(4), old_price.get(4) + " ₴", new_price.get(4), photo.get(4), links.get(4)));
//                itemList.add(new Item(62, name.get(5), old_price.get(5) + " ₴", new_price.get(5), photo.get(5), links.get(5)));
//                itemList.add(new Item(63, name.get(6), old_price.get(6) + " ₴", new_price.get(6), photo.get(6), links.get(6)));

                setItemRecycler(itemList);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (title.getText().equals("ATB")) {
            try {
                Document doc = Jsoup.connect("https://zakaz.atbmarket.com/catalog/1016/economy").ignoreHttpErrors(true).timeout(5000).get();
                List<String> name = new ArrayList<>();
                List<String> old_price = new ArrayList<>();
                List<String> new_price = new ArrayList<>();
                List<String> photo = new ArrayList<>();
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
                            links.add("https://zakaz.atbmarket.com" + element.select("a").attr("href"));
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

                            new_price.add(s0 + " ₴");
                            old_price.add(s1 + " ₴");
                        }
                }

               // itemList.add(new Item(64, "name.get(0)", "old_price.get(0)", "new_price.get(0)", "jj", "jj"));
                for (int i = 0; i < name.size(); i++) {
                    itemList.add(new Item(64, name.get(i), "old_price.get(i)", "new_price.get(i)", photo.get(i), links.get(i)));
                   }
                }
//                itemList.add(new Item(64, name.get(0), old_price.get(0), new_price.get(0), photo.get(0), links.get(0)));
//                itemList.add(new Item(65, name.get(7), old_price.get(7), new_price.get(7), photo.get(7), links.get(7)));
//                itemList.add(new Item(66, name.get(23), old_price.get(23), new_price.get(23), photo.get(23), links.get(23)));
//                itemList.add(new Item(67, name.get(8), old_price.get(8), new_price.get(8), photo.get(8), links.get(8)));
//                itemList.add(new Item(68, name.get(6), old_price.get(6), new_price.get(6), photo.get(6), links.get(6)));
//                itemList.add(new Item(69, name.get(15), old_price.get(15), new_price.get(15), photo.get(15), links.get(15)));
//                itemList.add(new Item(70, name.get(20), old_price.get(20), new_price.get(20), photo.get(20), links.get(20)));
//                itemList.add(new Item(71, name.get(9), new_price.get(9), "Товар без скидки!", photo.get(9), links.get(9)));

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
                itemList.add(new Item(72, name.get(0), old_price.get(0), new_price.get(0), photo.get(0), links.get(1)));
                itemList.add(new Item(73, name.get(1), old_price.get(1), new_price.get(1), photo.get(1), links.get(2)));
                itemList.add(new Item(74, name.get(2), old_price.get(2), new_price.get(2), photo.get(2), links.get(3)));
                itemList.add(new Item(75, name.get(3), old_price.get(3), new_price.get(3), photo.get(3), links.get(4)));
                itemList.add(new Item(76, name.get(4), old_price.get(4), new_price.get(4), photo.get(4), links.get(5)));
                itemList.add(new Item(77, name.get(5), old_price.get(5), new_price.get(5), photo.get(5), links.get(6)));
                itemList.add(new Item(78, name.get(6), old_price.get(6), new_price.get(6), photo.get(6), links.get(7)));
                itemList.add(new Item(79, name.get(7), old_price.get(7), new_price.get(7), photo.get(7), links.get(8)));

                setItemRecycler(itemList);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (title.getText().equals("Aviatsiya")) {
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
        } else if (title.getText().equals("Kibernetiki")) {
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
//                        old_price.add(s1.substring(0, s1.indexOf("г") - 1) + " ₴");
//                        new_price.add(s2.substring(0, s2.indexOf("г") - 1) + " ₴");
                        old_price.add(s1);
                        new_price.add(s2);
                        //System.out.println(element.getElementsByClass("item__price").text());
                    }
                    for (Element element : img) {
                        photo.add(element.attr("src"));
                        name.add(element.attr("alt"));
                    }

                }
                itemList.add(new Item(88, name.get(0), old_price.get(0), new_price.get(0), photo.get(0), links.get(1)));
                itemList.add(new Item(89, name.get(1), old_price.get(1), new_price.get(1), photo.get(1), links.get(2)));
                itemList.add(new Item(90, name.get(2), old_price.get(2), new_price.get(2), photo.get(2), links.get(3)));
                itemList.add(new Item(91, name.get(3), old_price.get(3), new_price.get(3), photo.get(3), links.get(4)));
                itemList.add(new Item(92, name.get(4), old_price.get(4), new_price.get(4), photo.get(4), links.get(5)));
                itemList.add(new Item(93, name.get(5), old_price.get(5), new_price.get(5), photo.get(5), links.get(6)));
                itemList.add(new Item(94, name.get(6), old_price.get(6), new_price.get(6), photo.get(6), links.get(7)));
                itemList.add(new Item(95, name.get(7), old_price.get(7), new_price.get(7), photo.get(7), links.get(8)));

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

//                itemList.add(new Item(97, name.get(1), old_price.get(1), new_price.get(1), photo.get(1), "https://www.sportmaster.ua" + links.get(1)));
//                itemList.add(new Item(98, name.get(2), old_price.get(2), new_price.get(2), photo.get(2), "https://www.sportmaster.ua" + links.get(2)));
//                itemList.add(new Item(99, name.get(3), old_price.get(3), new_price.get(3), photo.get(3), "https://www.sportmaster.ua" + links.get(3)));
//                itemList.add(new Item(100, name.get(4), old_price.get(4), new_price.get(4), photo.get(4), "https://www.sportmaster.ua" + links.get(4)));
//                itemList.add(new Item(101, name.get(5), old_price.get(5), new_price.get(5), photo.get(5), "https://www.sportmaster.ua" + links.get(5)));
//                itemList.add(new Item(102, name.get(6), old_price.get(6), new_price.get(6), photo.get(6), "https://www.sportmaster.ua" + links.get(6)));
//                itemList.add(new Item(103, name.get(7), old_price.get(7), new_price.get(7), photo.get(7), "https://www.sportmaster.ua" + links.get(7)));

                setItemRecycler(itemList);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (title.getText().equals("Ashan")) {
            try {
                Document document =  Jsoup.connect("https://auchan.zakaz.ua/ru/promotions/").get();
                name = new ArrayList<>();
                old_price = new ArrayList<>();
                new_price = new ArrayList<>();
                photo = new ArrayList<>();
                links = new ArrayList<>();



                Elements elementsByClass = document.getElementsByClass("jsx-33926795 ProductsBox");
                for (Element byClass : elementsByClass) {
                    Elements a = byClass.select("a");
                    for (Element element : a) {
                        links.add(element.attr("href"));
                        Elements elementsByClass1 = element.getElementsByClass("jsx-2958303393 ProductTile__prices");
                        for (Element element1 : elementsByClass1) {
                            old_price.add(element1.getElementsByClass("jsx-3642073353 Price__value_body Price__value_minor").text());
                            new_price.add(element1.getElementsByClass("jsx-3642073353 Price__value_caption Price__value_discount").text());
                        }
                    }

                    for (Element element : a) {
                        Elements elementsByClass1 = element.getElementsByClass("jsx-2958303393 ProductTile__imageContainer");
                        for (Element element1 : elementsByClass1) {
                            Elements img = element1.select("img");
                            for (Element element2 : img) {
                                photo.add(element2.attr("src"));
                                name.add(element2.attr("alt"));
                            }
                        }

                    }
                }


                itemList.add(new Item(34, name.get(1),old_price.get(1), new_price.get(1), photo.get(1), links.get(2)));
                itemList.add(new Item(35, name.get(2),old_price.get(2), new_price.get(2), photo.get(2), links.get(3)));
                itemList.add(new Item(36, name.get(3),old_price.get(3), new_price.get(3), photo.get(3), links.get(4)));
                itemList.add(new Item(37, name.get(4),old_price.get(4), new_price.get(4), photo.get(4), links.get(5)));
                itemList.add(new Item(38, name.get(5),old_price.get(5), new_price.get(5), photo.get(5), links.get(6)));
                itemList.add(new Item(39, name.get(6),old_price.get(6), new_price.get(6), photo.get(6), links.get(7)));
                itemList.add(new Item(40, name.get(7),old_price.get(7), new_price.get(7), photo.get(7), links.get(8)));

                setItemRecycler(itemList);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fullItemList.clear();
        fullItemList.addAll(itemList);
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
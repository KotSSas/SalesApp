package com.example.salesapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
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

    List<String> item;

    TextView title;
    TextView main_scene, about_us;

    List<String> name;
    List<String> old_price;
    List<String> new_price;
    List<String> photo;
    List<String> links;


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
        main_scene.setOnClickListener(v -> openMainActivity());

        about_us = findViewById(R.id.about_us);
        about_us.setOnClickListener(v -> openAboutActivity());

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
                        photo.add(b.getElementsByClass("product__image").select("img").attr("src"));
                        links.add(b.getElementsByClass("product__image").select("a").attr("href"));
                    }

                }
                itemList.add(new Item(1, name.get(0), old_price.get(0), new_price.get(0), photo.get(0)));
                itemList.add(new Item(2, name.get(1), old_price.get(1), new_price.get(1), photo.get(1)));
                itemList.add(new Item(3, name.get(2), old_price.get(2), new_price.get(2), photo.get(2)));
                itemList.add(new Item(4, name.get(3), old_price.get(3), new_price.get(3), photo.get(3)));
                itemList.add(new Item(5, name.get(4), old_price.get(4), new_price.get(4), photo.get(4)));
                itemList.add(new Item(6, name.get(5), old_price.get(5), new_price.get(5), photo.get(5)));
                itemList.add(new Item(7, name.get(6), old_price.get(6), new_price.get(6), photo.get(6)));
                itemList.add(new Item(8, name.get(7), old_price.get(7), new_price.get(7), photo.get(7)));
                setItemRecycler(itemList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (title.getText().equals("Citrus")) {
            try {
                Document document = Jsoup.connect(
                        "https://www.citrus.ua/shares/igrovu-periferiyu-razer/").get();
                name = new ArrayList<>();
                old_price = new ArrayList<>();


                Elements a = document.getElementsByClass("el-tabs");
                for (Element element : a) {
                    Elements elementsByClass = element.getElementsByClass("products__list");
//                System.out.println(elementsByClass);
                    for (Element byClass : elementsByClass) {
                        Elements elementsByClass1 = byClass.getElementsByClass("product-card product-card--mini product-card--mini--separate");
                        for (Element element1 : elementsByClass1) {
                            String name_el = element1.getElementsByClass("product-card__name").text();
                            name.add(name_el.substring(0, name_el.indexOf("(") - 1));
                            old_price.add(element1.getElementsByClass("price").text());
                        }
                    }

                }

                //ЕБАТЬ ЭТО РАБОТАЕТ, ПРОСТО НЕ ТРОГАЙ ЭТО, Я ЗАЕБАЛСЯ - ЭТО ПИЗДЕЦ!!!!

                String s1;
                String s2;
                for (int i = 0; i < 8; i++) {
                    if (old_price.get(i).lastIndexOf(" ") == old_price.get(i).indexOf(" ")) {
                        s1 = old_price.get(i).substring(0, old_price.get(i).indexOf(" ")) + "₴";
                        s2 = old_price.get(i).substring(old_price.get(i).indexOf(" ")) + "₴";
                        itemList.add(new Item(i + 9, name.get(i), s1, s2, "citrus"));
                    } else {
                        s1 = old_price.get(i).substring(0, old_price.get(i).indexOf(" ", 3)) + "₴";
                        s2 = old_price.get(i).substring(old_price.get(i).indexOf(" ", 3)) + "₴";
                        itemList.add(new Item(i + 9, name.get(i), s1, s2, "citrus"));
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

                Elements elementsByClass =
                        document.getElementsByClass("jsx-33926795 ProductsBox__list");
                for (Element byClass : elementsByClass) {
                    Elements el_names = byClass.getElementsByClass("jsx-2958303393 ProductTile__titleWrapper");
                    for (Element el_name : el_names) {
                        name.add(el_name.getElementsByClass("jsx-2958303393 ProductTile__title").text());
                    }

                }
                for (Element byClass : elementsByClass) {
                    Elements elementsByClass1 = byClass.getElementsByClass("jsx-2958303393 ProductTile__imageContainer");
                    Elements img = elementsByClass1.select("img");
                    for (Element a : img) {
                        //class="ProductTile__image" aria-hidden="true"
                        photo.add(a.select("img").attr("src"));


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
                itemList.add(new Item(17, name.get(0), old_price.get(0), new_price.get(0), photo.get(1)));
                itemList.add(new Item(18, name.get(1), old_price.get(1), new_price.get(1), photo.get(4)));
                itemList.add(new Item(19, name.get(2), old_price.get(2), new_price.get(2), photo.get(7)));
                itemList.add(new Item(20, name.get(3), old_price.get(3), new_price.get(3), photo.get(10)));
                itemList.add(new Item(21, name.get(4), old_price.get(4), new_price.get(4), photo.get(13)));
                itemList.add(new Item(22, name.get(5), old_price.get(5), new_price.get(5), photo.get(16)));
                itemList.add(new Item(23, name.get(6), old_price.get(6), new_price.get(6), photo.get(19)));
                itemList.add(new Item(24, name.get(7), old_price.get(7), new_price.get(7), photo.get(22)));
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
                Elements e = document.getElementsByClass("row c-items");
                for (Element element : e) {
                    Elements el = element.getElementsByClass("product-item-info");
                    Elements el1 = element.getElementsByClass("picture-box");
                    for (Element element1 : el1) {
                        photo.add(element1.select("img").attr("data-src"));
                        //System.out.println(element1.select("img").attr("data-index","0").attr("src"));
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

                //Changed here
                itemList.add(new Item(25, name.get(0), old_price.get(0), new_price.get(0), photo.get(0)));
                itemList.add(new Item(26, name.get(1), old_price.get(1), new_price.get(1), photo.get(1)));
                itemList.add(new Item(27, name.get(2), old_price.get(2), new_price.get(2), photo.get(2)));
                itemList.add(new Item(28, name.get(3), old_price.get(3), new_price.get(3), photo.get(3)));
                itemList.add(new Item(29, name.get(4), old_price.get(4), new_price.get(4), photo.get(4)));
                itemList.add(new Item(30, name.get(5), old_price.get(5), new_price.get(5), photo.get(5)));
                itemList.add(new Item(31, name.get(6), old_price.get(6), new_price.get(6), photo.get(6)));
                itemList.add(new Item(32, name.get(7), old_price.get(7), new_price.get(7), photo.get(7)));

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


                Elements elementsByClass = document.getElementsByClass("home-ss20-recommended");
                for (Element byClass : elementsByClass) {
                    Elements elementsByClass1 = byClass.getElementsByClass("category-products active");
                    for (Element element : elementsByClass1) {
                        //
                        Elements elementsByClass2 = element.getElementsByClass("product-price");
                        for (Element element1 : elementsByClass2) {
                            Elements elementsByClass3 = element1.getElementsByClass("final-price");
                            String text1 = elementsByClass3.text();
                            System.out.println(text1);
                            if (text1.indexOf("U") == text1.indexOf("A") - 1 && text1.indexOf("A") == text1.indexOf("H") - 1) {
                                new_price.add(text1.substring(0, text1.indexOf("U") - 1) + " ₴");
                            }
//                        Elements elementsByClass4 = element1.getElementsByClass("regular-price");
//                        // old_price.add(elementsByClass3.text());
//                        String text2 = elementsByClass4.text();
//
//                        if(text2.indexOf("U") == text2.indexOf("A") -1 && text2.indexOf("A") == text2.indexOf("H") -1){
//                            old_price.add(text2.substring(0,text2.indexOf("U") -1) + " ₴");
//                        }

                        }
                        Elements elements_names = element.getElementsByClass("product-name");
                        Elements a = elements_names.select("a");
                        for (Element element1 : a) {
                            name.add(element1.text());
                        }
                        //получение фотки
                        Elements elements_pic = element.getElementsByClass("product-photo-img");
                        for (Element element1 : elements_pic) {
                            photo.add(element1.attr("data-img"));
                        }
                    }
                }


                itemList.add(new Item(33, name.get(0), "Товар без скидки!", new_price.get(0), photo.get(0)));
                itemList.add(new Item(34, name.get(1), "Товар без скидки!", new_price.get(1), photo.get(1)));
                itemList.add(new Item(35, name.get(2), "Товар без скидки!", new_price.get(2), photo.get(2)));
                itemList.add(new Item(36, name.get(3), "Товар без скидки!", new_price.get(3), photo.get(3)));
                itemList.add(new Item(37, name.get(4), "Товар без скидки!", new_price.get(4), photo.get(4)));
                itemList.add(new Item(38, name.get(5), "Товар без скидки!", new_price.get(5), photo.get(5)));
                itemList.add(new Item(39, name.get(6), "Товар без скидки!", new_price.get(6), photo.get(6)));
                itemList.add(new Item(40, name.get(7), "Товар без скидки!", new_price.get(7), photo.get(7)));

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
                            }
                        }
                    }
                }

                itemList.add(new Item(41, name.get(0), old_price.get(0), new_price.get(0), photo.get(0)));
                itemList.add(new Item(42, name.get(1), old_price.get(1), new_price.get(1), photo.get(1)));
                itemList.add(new Item(43, name.get(2), old_price.get(2), new_price.get(2), photo.get(2)));
                itemList.add(new Item(44, name.get(3), old_price.get(3), new_price.get(3), photo.get(3)));
                itemList.add(new Item(45, name.get(4), old_price.get(4), new_price.get(4), photo.get(4)));
                itemList.add(new Item(46, name.get(5), old_price.get(5), new_price.get(5), photo.get(5)));
                itemList.add(new Item(47, name.get(6), old_price.get(6), new_price.get(6), photo.get(6)));
                itemList.add(new Item(48, name.get(7), old_price.get(7), new_price.get(7), photo.get(7)));
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


                Elements elementsByClass = document.getElementsByClass("catalog__products-container");
                for (Element byClass : elementsByClass) {
                    Elements elementsByClass1 = byClass.getElementsByClass("product-card__container");
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
                //System.out.println(elementsByClass);

                itemList.add(new Item(49, name.get(0), old_price.get(0), new_price.get(0), photo.get(0)));
                itemList.add(new Item(50, name.get(1), old_price.get(1), new_price.get(1), photo.get(1)));
                itemList.add(new Item(51, name.get(2), old_price.get(2), new_price.get(2), photo.get(2)));
                itemList.add(new Item(52, name.get(3), old_price.get(3), new_price.get(3), photo.get(3)));
                itemList.add(new Item(53, name.get(4), old_price.get(4), new_price.get(4), photo.get(4)));
                itemList.add(new Item(54, name.get(5), old_price.get(5), new_price.get(5), photo.get(5)));
                itemList.add(new Item(55, name.get(6), old_price.get(6), new_price.get(6), photo.get(6)));
                itemList.add(new Item(56, name.get(7), old_price.get(7), new_price.get(7), photo.get(7)));
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
                Elements elementsByClass1 = doc.getElementsByClass("action-product-container action-categoryid-58 slot");
                for (Element e : elementsByClass1) {
                    Elements elementsByClass = e.getElementsByClass("listing__body-wrap");
                    for (Element byClass : elementsByClass) {
                        Elements card__body = byClass.getElementsByClass("card__body");
                        Elements card__head = byClass.getElementsByClass("card__image");
                        for (Element element : card__head) {
                            photo.add(element.select("source").attr("srcset"));
                        }
                        for (Element element : card__body) {
                            name.add(element.getElementsByClass("card__title").text());
                            new_price.add(element.getElementsByClass("card-price").text());
                            old_price.add(element.getElementsByClass("card__price-discount").select("p").text());

                        }
                    }
                }


                itemList.add(new Item(57, name.get(0), old_price.get(0) + " ₴", new_price.get(0), photo.get(0)));
                itemList.add(new Item(58, name.get(1), old_price.get(1) + " ₴", new_price.get(1), photo.get(1)));
                itemList.add(new Item(59, name.get(2), old_price.get(2) + " ₴", new_price.get(2), photo.get(2)));
                itemList.add(new Item(60, name.get(3), old_price.get(3) + " ₴", new_price.get(3), photo.get(3)));
                itemList.add(new Item(61, name.get(4), old_price.get(4) + " ₴", new_price.get(4), photo.get(4)));
                itemList.add(new Item(62, name.get(5), old_price.get(5) + " ₴", new_price.get(5), photo.get(5)));
                itemList.add(new Item(63, name.get(6), old_price.get(6) + " ₴", new_price.get(6), photo.get(6)));

//                itemList.add(new Item(64, name.get(7), old_price.get(7), new_price.get(7)));
                setItemRecycler(itemList);

//                System.out.println();
                // System.out.println(elementsByClass);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // когда в список помещаешь новый елемент(как выше): itemList.add(new Item(****35****(я про это ниже говорю), name.get(4), old_price.get(4), new_price.get(4)));
            // то id пиши каждый раз по возрастающей если последний 56, то следущие 8 - 57, 58, 59....(это для корзины пока что нужно дальше не знаю)
        } else if (title.getText().equals("ATB")) {

            itemList.add(new Item(64, "Ікра Водный мир сайди солона", "36.40 ₴", "27.60 ₴", "atb"));
            itemList.add(new Item(65, "Алкогольний напій The Colonist Spiced Black", "229.90 ₴", "169.90 ₴", "atb"));
            itemList.add(new Item(66, "Балик Добров Дарницький, нарізка", "35.40 ₴", "29.10 ₴", "atb"));
            itemList.add(new Item(67, "Батончик вафельний Хіп Хоп в глазурі", "42.30 ₴", "32.90 ₴", "atb"));
            itemList.add(new Item(68, "Bареники Три Ведмеді Мішутка з картоплею", "35.90 ₴", "20.40 ₴", "atb"));
            itemList.add(new Item(69, "Вино Baron de Lusson сухе червоне, Франція", "99.90 ₴", "79.90 ₴", "atb"));
            itemList.add(new Item(70, "Горошок Своя лінія зелений", "24.40 ₴", "19.90 ₴", "atb"));
            itemList.add(new Item(71, "Готовий Сніданок 460г Nesquik", "64.70 ₴", "55.60 ₴", "atb"));
            setItemRecycler(itemList);
        } else if (title.getText().equals("Rozetka")) {
        }
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
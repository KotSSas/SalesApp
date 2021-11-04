package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesapp.model.Order;

import java.io.InputStream;
import java.net.URL;

public class ItemPage extends AppCompatActivity {

    TextView main_scene, about_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);

        ImageView image = findViewById(R.id.item_page_img);
        ImageView link_img = findViewById(R.id.imageView4);

        link_img.setOnClickListener(v -> {

            v.startAnimation(AnimationUtils.loadAnimation(ItemPage.this, R.anim.anim_item));
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("link")));
            startActivity(browserIntent);

        });

        TextView title = findViewById(R.id.item_page_text);
        TextView pr1 = findViewById(R.id.price_item1);
        TextView pr2 = findViewById(R.id.price_item2);

//        image.setImageResource(getIntent().getIntExtra("image", 0));

        new DownloadImageTask((ImageView) findViewById(R.id.item_page_img))
                .execute(getIntent().getStringExtra("image"));

        title.setText(getIntent().getStringExtra("title"));
        pr1.setText(getIntent().getStringExtra("pr1"));
        pr2.setText(getIntent().getStringExtra("pr2"));


        main_scene = findViewById(R.id.main_scene);
        main_scene.setOnClickListener(v -> {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
//                startActivity(browserIntent);
            openMainActivity();
        });

        about_us = findViewById(R.id.about_us);
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutActivity();
            }
        });

    }

    public void addToCart(View view){
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_item));
        int id = getIntent().getIntExtra("id", 0);
        //Order.items_id.add(id);
        if (id< 9){
            Order.tavr_id.add(id);
        }else if (id>8 && id <17){
            Order.citr_id.add(id);
        }else if (id>16 && id <25){
            Order.metr_id.add(id);
        }else if (id>24 && id <33){
            Order.waikiki_id.add(id);
        }else if (id>32 && id <41){
            Order.sin_id.add(id);
        }else if (id>40 && id <49){
            Order.allo_id.add(id);
        }else if (id>48 && id <57){
            Order.staff_id.add(id);
        }else if (id>56 && id <64){
            Order.fox_id.add(id);
        }else if (id>63 && id <72){
            Order.atb_id.add(id);
        }else if (id>71 && id <80){
            Order.urb_id.add(id);
        }else if (id>79 && id <88){
            Order.ac_id.add(id);
        }else if (id>87 && id <96){
            Order.ciber_id.add(id);
        }else if (id>95 && id <104){
            Order.spr_id.add(id);
        }
        Toast.makeText(this, "Добавлено в корзину! ;)", Toast.LENGTH_SHORT).show();
    }

    private void openAboutActivity() {
        startActivity(new Intent(this, AboutUsActivity.class));
    }

    private void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
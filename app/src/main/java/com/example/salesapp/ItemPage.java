package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesapp.model.Item;
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

    }

    public void addToCart(View view){
        int id = getIntent().getIntExtra("id", 0);
        if (id > 8 && id < 17) {
            Order.cit_list.add(id);
        }
        Toast.makeText(this, "Добавлено в корзину! ;)", Toast.LENGTH_LONG).show();
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
package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Pair;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.salesapp.activities.AboutUsActivity;
import com.example.salesapp.activities.MainActivity;
import com.example.salesapp.activities.Matching;

import java.io.InputStream;

public class ItemPage extends AppCompatActivity {

    ImageView main_scene;
    TextView title;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_item_page);

        image = findViewById(R.id.item_page_img);
        ImageView link_img = findViewById(R.id.imageView4);




        link_img.setOnClickListener(v -> {
            Vibrator v1 = (Vibrator) ItemPage.this.getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                v1.vibrate(VibrationEffect.createOneShot(100, 1));
            }

            v.startAnimation(AnimationUtils.loadAnimation(ItemPage.this, R.anim.anim_item));

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("link")));
            startActivity(browserIntent);

        });

        title = findViewById(R.id.item_title);
        TextView pr1 = findViewById(R.id.item_p1);
        TextView pr2 = findViewById(R.id.item_p2);

//        image.setImageResource(getIntent().getIntExtra("image", 0));

        new DownloadImageTask((ImageView) findViewById(R.id.item_page_img))
                .execute(getIntent().getStringExtra("image"));

        title.setText(getIntent().getStringExtra("title"));
        pr1.setText(getIntent().getStringExtra("pr1"));
        pr2.setText(getIntent().getStringExtra("pr2"));


        main_scene = findViewById(R.id.main_scene);
        main_scene.setOnClickListener(v -> {
            Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                v1.vibrate(VibrationEffect.createOneShot(100, 1));
            }
            v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_item));

//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
//                startActivity(browserIntent);
            openMainActivity();
        });

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
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
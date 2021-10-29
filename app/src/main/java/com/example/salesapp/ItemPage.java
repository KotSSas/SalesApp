package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesapp.model.Order;

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

        image.setImageResource(getIntent().getIntExtra("image", 0));

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
        Order.items_id.add(id);
        Toast.makeText(this, "Добавлено в корзину! ;)", Toast.LENGTH_LONG).show();
    }

    private void openAboutActivity() {
        startActivity(new Intent(this, AboutUsActivity.class));
    }

    private void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

}
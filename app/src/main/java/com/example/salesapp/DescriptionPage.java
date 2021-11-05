package com.example.salesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DescriptionPage extends AppCompatActivity {
    TextView title;
    TextView description;
    TextView main_scene, about_us;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        title = findViewById(R.id.title_desc);
        description = findViewById(R.id.desc);
        title.setText(getIntent().getStringExtra("title"));
        description.setText(getIntent().getStringExtra("description"));




        main_scene = findViewById(R.id.main_scene);
        main_scene.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(DescriptionPage.this, R.anim.anim_item));
            openMainActivity();
        });

        about_us = findViewById(R.id.about_us);
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(DescriptionPage.this, R.anim.anim_item));
                openAboutActivity();
            }
        });

    }
    private void openAboutActivity() {
        startActivity(new Intent(this, AboutUsActivity.class));
    }

    private void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

}

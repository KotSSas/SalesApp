package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Pair;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Matching extends AppCompatActivity {

    TextView main_scene, about_us;
    ImageView ico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);

        main_scene = findViewById(R.id.main_scene);

        main_scene.setOnClickListener(v -> {
            Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

// Vibrate for 400 milliseconds

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                v1.vibrate(VibrationEffect.createOneShot(100, 1));
            }
            v.startAnimation(AnimationUtils.loadAnimation(Matching.this, R.anim.anim_item));
            openMainActivity();
        });

        about_us = findViewById(R.id.about_us);
        about_us.setOnClickListener(v -> {
            Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

// Vibrate for 400 milliseconds

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                v1.vibrate(VibrationEffect.createOneShot(100, 1));
            }

            v.startAnimation(AnimationUtils.loadAnimation(Matching.this, R.anim.anim_item));
            openAboutActivity();
        });
    }

    private void openAboutActivity() {

        ico = findViewById(R.id.logo2);
        Intent intent = new Intent(this, AboutUsActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) this,
                new Pair<View, String>(ico, "icoImage")
        );

        startActivity(intent, options.toBundle());
    }

    private void openMainActivity() {
        FloatingActionButton fb0 = findViewById(R.id.mainButn);
        Intent intent = new Intent(this, MainActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) this,
                new Pair<View, String>(fb0, "filterIco")
        );

        startActivity(intent, options.toBundle());
    }

}
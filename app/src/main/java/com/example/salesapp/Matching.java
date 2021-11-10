package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Matching extends AppCompatActivity {

    TextView main_scene, about_us;

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
        startActivity(new Intent(this, AboutUsActivity.class));
    }

    private void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

}
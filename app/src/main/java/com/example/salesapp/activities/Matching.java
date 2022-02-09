package com.example.salesapp.activities;

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

import com.example.salesapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Matching extends AppCompatActivity {

    ImageView main_scene;
    ImageView ico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);
        getSupportActionBar().hide();

        main_scene = findViewById(R.id.main_scene);

        main_scene.setOnClickListener(v -> {
            Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                v1.vibrate(VibrationEffect.createOneShot(100, 1));
            }
            v.startAnimation(AnimationUtils.loadAnimation(Matching.this, R.anim.anim_item));
            openMainActivity();
        });
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
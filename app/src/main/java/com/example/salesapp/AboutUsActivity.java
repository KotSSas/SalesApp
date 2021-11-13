package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class AboutUsActivity extends AppCompatActivity {

    TextView main_scene, coming_soon_scene;
    ImageView ico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        main_scene = findViewById(R.id.main_scene);
        main_scene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }
                v.startAnimation(AnimationUtils.loadAnimation(AboutUsActivity.this, R.anim.anim_item));
                openMainActivity();
            }
        });
        coming_soon_scene = findViewById(R.id.coming_soon_scene);
        coming_soon_scene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }

                v.startAnimation(AnimationUtils.loadAnimation(AboutUsActivity.this, R.anim.anim_item));
                openMatchingActivity();

            }
        });

    }


    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        ico = findViewById(R.id.photo1);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) this,
                new Pair<View, String>(ico, "icoImage")
        );
        startActivity(intent, options.toBundle());
    }

    private void openMatchingActivity() {
        Intent intent = new Intent(this, Matching.class);
        ico = findViewById(R.id.photo1);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) this,
                new Pair<View, String>(ico, "icoImage")
        );
        startActivity(intent, options.toBundle());
    }
}
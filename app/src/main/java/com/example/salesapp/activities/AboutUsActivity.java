package com.example.salesapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class AboutUsActivity extends AppCompatActivity {

//    TextView main_scene, coming_soon_scene;
    ImageView ico;
    ImageView main_scene;
    FloatingActionButton commentsLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getSupportActionBar().hide();

        main_scene = findViewById(R.id.main_scene);
        main_scene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }
                v.startAnimation(AnimationUtils.loadAnimation(AboutUsActivity.this, R.anim.anim_item));
                finish();
            }
        });
//        coming_soon_scene = findViewById(R.id.coming_soon_scene);
//        coming_soon_scene.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
//                }
//
//                v.startAnimation(AnimationUtils.loadAnimation(AboutUsActivity.this, R.anim.anim_item));
//                openMatchingActivity();
//
//            }
//        });
//        commentsLink =findViewById(R.id.commentsFloatButton);

//        commentsLink.setOnClickListener(v -> {
//            //v.startAnimation(AnimationUtils.loadAnimation(ShopPage.this, R.anim.anim_item));
//            openCommentsActivity();
//
//        });

    }

    private void openCommentsActivity() {
        startActivity(new Intent(this, SignInAct.class));

    }
    public void openGit(View v){
        Vibrator v1 = (Vibrator) AboutUsActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            v1.vibrate(VibrationEffect.createOneShot(100, 1));
        }

        v.startAnimation(AnimationUtils.loadAnimation(AboutUsActivity.this, R.anim.anim_item));

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/KotSSas/SalesApp"));
        startActivity(browserIntent);
    }

    public void openTt(View v){
        Vibrator v1 = (Vibrator) AboutUsActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            v1.vibrate(VibrationEffect.createOneShot(100, 1));
        }

        v.startAnimation(AnimationUtils.loadAnimation(AboutUsActivity.this, R.anim.anim_item));
//
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(""));
//        startActivity(browserIntent);
    }
}
package com.example.salesapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesapp.R;

public class ErrorPage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        getSupportActionBar().hide();

        ImageView link_img = findViewById(R.id.imageView6);
        link_img.setOnClickListener(v -> {
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        });

        ImageView exit_img = findViewById(R.id.exit_b);
        exit_img.setOnClickListener(v -> exit());
    }

    private void exit() {
        Intent intent = new Intent(this, MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("finish", true);

        startActivity(intent);
    }

}

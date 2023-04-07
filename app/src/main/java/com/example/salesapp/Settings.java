package com.example.salesapp;

import static com.example.salesapp.R.id.sett_to_main;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.Locale;

public class Settings extends AppCompatActivity {

    Spinner spinner;
    String[] languages;
    ImageView sett_to_main;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_settings);

        sett_to_main = findViewById(R.id.sett_to_main);
        sett_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }
                v.startAnimation(AnimationUtils.loadAnimation(Settings.this, R.anim.anim_item));
                finish();
            }
        });

        languages = getResources().getStringArray(R.array.languages);
        spinner = findViewById(R.id.lan_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLanguage = parent.getItemAtPosition(position).toString();

                if(selectedLanguage.equals(languages[1])){
                    setLocation(Settings.this, "uk");
                    finish();
                    startActivity(getIntent());
                }else if (selectedLanguage.equals(languages[2])){
                        setLocation(Settings.this, "en");
                        finish();
                        startActivity(getIntent());
                }else if (selectedLanguage.equals(languages[3])){
                    setLocation(Settings.this, "");
                    finish();
                    startActivity(getIntent());
//                    case String.valueOf(R.string.spinner3) :
//                        setLocation(Settings.this, "");
//                        startActivity(getIntent());
//                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setLocation(Activity activity, String lanCode) {
        Locale locale = new Locale(lanCode);
        locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class AboutUsActivity extends AppCompatActivity {

    TextView main_scene;
    private ImageSwitcher mImageSwitcher;
    ImageView b_n, b_p;

    private final String[] imageNames={"mac", "mac3","ph1", "mac2"};
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        mImageSwitcher = (ImageSwitcher)findViewById(R.id.img_sw);
        Animation inAnimation = new AlphaAnimation(0, 1);
        inAnimation.setDuration(2000);
        Animation outAnimation = new AlphaAnimation(1, 0);
        outAnimation.setDuration(2000);

        main_scene = findViewById(R.id.main_scene);
        main_scene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }
                openMainActivity();
            }
        });

        b_n =  findViewById(R.id.button_pr);
        b_p =  findViewById(R.id.button_next);

        mImageSwitcher = (ImageSwitcher) findViewById(R.id.img_sw);

        // Animation when switching to another image.
        Animation out= AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        Animation in= AnimationUtils.loadAnimation(this, android.R.anim.fade_in);

        // Set animation when switching images.
        mImageSwitcher.setInAnimation(in);
        mImageSwitcher.setOutAnimation(out);

        //
        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            // Returns the view to show Image
            // (Usually should use ImageView)
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER);

                ImageSwitcher.LayoutParams params= new ImageSwitcher.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(params);
                return imageView;
            }
        });

        this.currentIndex=0;
        this.showImage(this.currentIndex);

        b_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousImage();
            }
        });

        b_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextImage();
            }
        });
    }


    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void previousImage()  {
        if(currentIndex > 0) {
            currentIndex--;
        }else  {
            currentIndex=imageNames.length-1;
        }
        this.showImage(currentIndex);
    }

    private void nextImage()  {
        if(currentIndex < this.imageNames.length-1) {
            currentIndex++;
        }else  {
            currentIndex=0;
        }
        this.showImage(currentIndex);
    }


    private void showImage(int imgIndex)  {
        String imageName= this.imageNames[imgIndex];

        int resId= getDrawableResIdByName(imageName);
        if(resId!=  0) {
            this.mImageSwitcher.setImageResource(resId);
        }
    }

    // Find Image ID corresponding to the name of the image (in the drawable folder).
    public int getDrawableResIdByName(String resName)  {
        String pkgName = this.getPackageName();
        // Return 0 if not found.
        int resID = this.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i("MyLog", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }

}
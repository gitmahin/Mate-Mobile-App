package com.example.mate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView mate_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mate_logo = findViewById(R.id.mate_logo);
//        setted visibility true so that aplha can work properly
        mate_logo.setVisibility(View.VISIBLE);
//        animating the splash screen imgview
        Animation mate_ani = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        mate_ani.setFillAfter(true);
        mate_logo.startAnimation(mate_ani);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_in);
                finish();
            }
        }, 1000);
    }
}
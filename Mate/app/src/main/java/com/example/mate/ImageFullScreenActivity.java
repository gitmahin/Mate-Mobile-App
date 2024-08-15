package com.example.mate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mate.ImageAdapter;

public class ImageFullScreenActivity extends AppCompatActivity {

    ImageView full_img_view;
    private ImageView back_to_adm_frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_full_screen);

        back_to_adm_frag = findViewById(R.id.back_to_adm_frag);
        full_img_view =(ImageView) findViewById(R.id.full_img_view);

        back_to_adm_frag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent i = getIntent();
        int position = i.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);
        full_img_view.setImageResource(imageAdapter.image_array[position]);
    }
}
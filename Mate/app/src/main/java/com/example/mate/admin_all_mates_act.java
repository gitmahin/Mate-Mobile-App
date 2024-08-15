package com.example.mate;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class admin_all_mates_act extends AppCompatActivity {

    private ImageView back_to_home_activity;
    private EditText search_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_all_mates);
        back_to_home_activity = findViewById(R.id.back_to_home_activity);
        search_text = findViewById(R.id.search_mate);


        //      Vibrate while search icon clicked
        search_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    int drawableEndX = search_text.getWidth() - search_text.getPaddingRight() - search_text.getCompoundDrawables()[2].getIntrinsicWidth();
                    if (event.getX() >= drawableEndX) {
                        // DrawableEnd (right drawable) was clicked
                        onSearchIconClick();
                        return true;
                    }
                }
                return false;
            }
        });

        back_to_home_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void onSearchIconClick() {
        Toast.makeText(this, "Search icon clicked", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void finish() {
        super.finish();
    }
}
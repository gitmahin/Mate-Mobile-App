package com.example.mate;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class search_main extends AppCompatActivity {

    private ImageView back_to_home, search_settings;
    private LinearLayout search_layout;
    boolean isExpanded = false;
    private EditText search_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);

        back_to_home = findViewById(R.id.back_to_home);
        search_settings = findViewById(R.id.search_settings);
        search_layout = findViewById(R.id.search_layout);
        search_text = findViewById(R.id.enter_search_text);

        back_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

        search_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup.LayoutParams params = search_layout.getLayoutParams();
                if (isExpanded) {
                    // Set the height back to the original height (70dp)
                    params.height = (int) (70 * getResources().getDisplayMetrics().density);
                } else {
                    // Expand the height to wrap
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }
                search_layout.setLayoutParams(params);
                isExpanded = !isExpanded; // Toggle the flag
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
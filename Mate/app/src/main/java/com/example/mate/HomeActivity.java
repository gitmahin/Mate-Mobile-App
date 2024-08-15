package com.example.mate;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {

    private ImageView search_icon;
    private LinearLayout avatar_layout, app_main_header, sec_header;
    private BottomNavigationView navigationView;
    private int int_layout = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewFragment(new home_fragment());

        avatar_layout = findViewById(R.id.avatar_layout);
        search_icon = findViewById(R.id.search_icon);
        app_main_header = findViewById(R.id.app_main_header);
        sec_header = findViewById(R.id.sec_header);

//      menu item -> open fragment
        navigationView = findViewById(R.id.navigation_menu);
        navigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home_icon) {
                    int_layout = 1;
                    viewFragment(new home_fragment());
                    header_placement();
                } else if (itemId == R.id.find_mate_icon) {
                    int_layout = 2;
                    viewFragment(new find_mates_fragment());
                    header_placement();
                }else if(itemId == R.id.watch_video_icon){
                    int_layout = 3;
                    viewFragment(new watch_video_fragment());
                    if(app_main_header.getTranslationY() == -100){
                        return true;
                    }else{
                        app_main_header.animate().translationYBy(-100).setDuration(300);
                        sec_header.animate().translationYBy(-100).setDuration(300);
                    }
                }else if(itemId == R.id.notification_icon){
                    int_layout = 4;
                    viewFragment(new notification_fragment());
                    header_placement();
                }else{
                    Toast.makeText(HomeActivity.this, "Menu not found", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });


        avatar_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int_layout = 5;
                viewFragment(new admin_fragment());
                header_placement();
                resetBottomNavigationView();
            }
        });

        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, search_main.class));
            }
        });


    }


    private void viewFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

//  animating header
    public void header_placement(){
        if(app_main_header.getTranslationY() == -100){
            app_main_header.animate().translationYBy(100).setDuration(300);
            sec_header.animate().translationYBy(100).setDuration(300);
        }
    }

    public Fragment getCurrentFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment != null && fragment.isVisible()) {
                return fragment;
            }
        }
        return null;
    }

    private void resetBottomNavigationView() {
        navigationView.getMenu().setGroupCheckable(0, true, false);
        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            navigationView.getMenu().getItem(i).setChecked(false);
        }
        navigationView.getMenu().setGroupCheckable(0, true, true);
    }

    @Override
    public void onBackPressed() {
        if(int_layout == 2 || int_layout == 3 || int_layout == 4 || int_layout == 5){
            int_layout = 1;
            viewFragment(new home_fragment());
            navigationView.setSelectedItemId(R.id.home_icon);
            header_placement();
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.zoom_out, R.anim.zoom_out);
    }
}
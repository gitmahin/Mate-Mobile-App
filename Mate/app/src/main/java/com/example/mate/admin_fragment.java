package com.example.mate;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class admin_fragment extends Fragment {
    View view;
    LinearLayout mates_no_btn_admin;
    BottomNavigationView admin_navigation_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewFragment(new admin_details());
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.admin_fragment, container, false);

        mates_no_btn_admin = view.findViewById(R.id.mates_no_btn_admin);

        mates_no_btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), admin_all_mates_act.class));
            }
        });

        admin_navigation_view = view.findViewById(R.id.admin_menu);
        admin_navigation_view.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.admin_details_item) {
                    viewFragment(new admin_details());

                } else if (itemId == R.id.admin_photos_item) {
                    viewFragment(new admin_gallery());

                }else if(itemId == R.id.admin_videos_item){
                    viewFragment(new admin_videos_frag());
                }else if(itemId == R.id.admin_stories){
                    viewFragment(new admin_stories_frag());

                }else{
                    Toast.makeText(getActivity(), "Menu not found", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        return view;
    }

    private void viewFragment(Fragment fragment){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.admin_menu_frag_lay, fragment);
        fragmentTransaction.commit();
    }

}
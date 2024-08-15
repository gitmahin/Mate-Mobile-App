package com.example.mate;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class admin_gallery extends Fragment {
    View view;
    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_admin_gallery, container, false);


        gridView = (GridView) view.findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageAdapter(getActivity()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ImageFullScreenActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });

        return view;



    }

}
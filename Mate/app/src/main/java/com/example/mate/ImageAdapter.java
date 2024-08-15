package com.example.mate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.mate.RoundedImageView;

public class ImageAdapter extends BaseAdapter {

    private Context my_context;

    public int[] image_array = {
            R.drawable.wallpaper_1,
            R.drawable.wallpaper_2,
            R.drawable.wallpaper_3,
            R.drawable.wallpaper_4,
            R.drawable.wallpaper_5
    };

    public ImageAdapter(Context my_context) {
        this.my_context = my_context;
    }

    @Override
    public int getCount() {
        return image_array.length;
    }

    @Override
    public Object getItem(int position) {
        return image_array[position];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        RoundedImageView roundedImageView = new RoundedImageView(my_context);
        roundedImageView.setImageResource(image_array[position]);
        roundedImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        roundedImageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250));

        return roundedImageView;
    }
}

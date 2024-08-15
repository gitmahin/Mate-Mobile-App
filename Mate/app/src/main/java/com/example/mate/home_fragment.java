package com.example.mate;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Vibrator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.widget.Toast;

public class home_fragment extends Fragment {
    ImageView img_h_post, more_btn_post, rm_post, love_react_btn, comment_btn, share_btn;
    TextView write_post_button_h, post_text, select_text_view_mode;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.home_fragment, container, false);

        img_h_post = view.findViewById(R.id.img_h_post);
        write_post_button_h = view.findViewById(R.id.write_post_button_h);
        more_btn_post = view.findViewById(R.id.more_btn_post);
        rm_post = view.findViewById(R.id.rm_post);
        post_text = view.findViewById(R.id.post_text);
        select_text_view_mode = view.findViewById(R.id.select_text_view_mode);
        love_react_btn = view.findViewById(R.id.love_react_btn);
        comment_btn = view.findViewById(R.id.comment_btn);
        share_btn = view.findViewById(R.id.share_btn);

//      TODO: temporary implemented. remove this later
        if (isTextTruncated(post_text) == true){
            select_text_view_mode.setVisibility(view.GONE);
        }else{
            select_text_view_mode.setVisibility(view.VISIBLE);
        }

        post_text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // Get the Vibrator service
                Vibrator vibrator = (Vibrator) requireContext().getSystemService(Context.VIBRATOR_SERVICE);

                String textToCopy = post_text.getText().toString();

                // Get the Clipboard Manager
                ClipboardManager clipboard = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);

                // Create a ClipData object with the text to copy
                ClipData clip = ClipData.newPlainText("Copied Text", textToCopy);

                // Set the ClipData to the clipboard
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getActivity(), "Text copied to clipboard", Toast.LENGTH_SHORT).show();

                // Check if the device has a vibrator
                if (vibrator != null && vibrator.hasVibrator()) {
                    // Vibrate for 500 milliseconds
                    vibrator.vibrate(200);
                }
                return true;
            }
        });

        select_text_view_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seemore();
            }
        });

        img_h_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        write_post_button_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        more_btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rm_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        love_react_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.reaction_animation);
                Animation reaction_rm_animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.reaction_rm_animation);
                Drawable heartDrawable = ContextCompat.getDrawable(view.getContext(), R.drawable.heart_solid);
                Drawable currentDrawable = love_react_btn.getDrawable();
                Bitmap heartBitmap = ((BitmapDrawable) heartDrawable).getBitmap();
                Bitmap currentBitmap = ((BitmapDrawable) currentDrawable).getBitmap();
                if(heartBitmap.sameAs(currentBitmap)){
                    love_react_btn.setImageResource(R.drawable.heart_stroke);
                    love_react_btn.startAnimation(reaction_rm_animation);
                }else{
                    love_react_btn.setImageResource(R.drawable.heart_solid);
                    love_react_btn.startAnimation(animation);
                }
            }
        });

        comment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.react_more_btns_animation);
                comment_btn.startAnimation(animation);
            }
        });

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.react_more_btns_animation);
                share_btn.startAnimation(animation);
            }
        });



        return view;
    }

    public void seemore() {
        // Check if the text is truncated
        if (post_text.getMaxLines() == 3) {
            post_text.setMaxLines(Integer.MAX_VALUE);
            select_text_view_mode.setText("See less");
        } else {
            post_text.setMaxLines(3);
            select_text_view_mode.setText("See more");
        }
    }

    private boolean isTextTruncated(TextView textView) {
        Layout layout = textView.getLayout();
        if (layout == null) {
            return false;
        }
        int lineCount = layout.getLineCount();
        if (lineCount == 0) {
            return false;
        }
        // Get the text width of the last visible line
        int ellipsisCount = layout.getEllipsisCount(lineCount - 1);
        return ellipsisCount > 0;
    }

}
package com.example.mate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

public class RoundedImageView extends AppCompatImageView {

    private float cornerRadius = 15.0f;
    private Path path;
    private RectF rect;

    public RoundedImageView(Context context) {
        super(context);
        init();
    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rect = new RectF(0, 0, w, h);
        path.addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CW);
        path.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int saveCount = canvas.save();
        canvas.clipPath(path);
        super.onDraw(canvas);
        canvas.restoreToCount(saveCount);
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
        invalidate();
    }
}

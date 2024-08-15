package com.example.mate;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import androidx.appcompat.widget.AppCompatImageView;

public class ZoomableImageView extends AppCompatImageView {

    private Matrix matrix;
    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;

    private float scale = 1f;
    private float minScale = 1f;
    private float maxScale = 4f;

    public ZoomableImageView(Context context) {
        super(context);
        init(context);
    }

    public ZoomableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ZoomableImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        matrix = new Matrix();
        setScaleType(ScaleType.MATRIX);

        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            fitImageToScreen();
        }
    }

    private void fitImageToScreen() {
        if (getDrawable() == null) return;

        matrix.reset();
        RectF imageRect = new RectF(0, 0, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
        RectF viewRect = new RectF(0, 0, getWidth(), getHeight());

        matrix.setRectToRect(imageRect, viewRect, Matrix.ScaleToFit.CENTER);
        setImageMatrix(matrix);
        scale = 1f;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            float newScale = scale * scaleFactor;

            if (newScale <= maxScale && newScale >= minScale) {
                scale = newScale;
                matrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());
                checkMatrixBounds();
                setImageMatrix(matrix);
            }

            return true;
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (scale > minScale) { // Only allow scrolling when zoomed in
                matrix.postTranslate(-distanceX, -distanceY);
                checkMatrixBounds();
                setImageMatrix(matrix);
            }
            return true;
        }
    }

    private void checkMatrixBounds() {
        RectF viewRect = new RectF(0, 0, getWidth(), getHeight());
        RectF imageRect = new RectF();
        matrix.mapRect(imageRect, new RectF(0, 0, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight()));

        float deltaX = viewRect.width() - imageRect.width();
        float deltaY = viewRect.height() - imageRect.height();

        if (deltaX > 0) {
            matrix.postTranslate(deltaX / 2 - imageRect.left, 0);
        } else if (imageRect.left > 0) {
            matrix.postTranslate(-imageRect.left, 0);
        } else if (imageRect.right < viewRect.width()) {
            matrix.postTranslate(viewRect.width() - imageRect.right, 0);
        }

        if (deltaY > 0) {
            matrix.postTranslate(0, deltaY / 2 - imageRect.top);
        } else if (imageRect.top > 0) {
            matrix.postTranslate(0, -imageRect.top);
        } else if (imageRect.bottom < viewRect.height()) {
            matrix.postTranslate(0, viewRect.height() - imageRect.bottom);
        }

        setImageMatrix(matrix);
    }
}

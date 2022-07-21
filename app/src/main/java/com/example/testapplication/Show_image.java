package com.example.testapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Show_image extends AppCompatActivity {
    ImageView imageshow1;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        imageshow1 = findViewById(R.id.imageshow1);
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        Bundle extras = getIntent().getExtras();
        byte[] bytes = extras.getByteArray("picture");
        try {
            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            imageshow1.setImageBitmap(bmp);
        } catch (Exception e) {
            Toast.makeText(this, "Error please try again  " + e, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        mScaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(1.0f,
                    Math.min(mScaleFactor, 10.0f));
            imageshow1.setScaleX(mScaleFactor);
            imageshow1.setScaleY(mScaleFactor);
            return true;
        }
    }


}

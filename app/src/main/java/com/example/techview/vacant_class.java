package com.example.techview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;


public class vacant_class extends AppCompatActivity {


    public ScaleGestureDetector scaleGestureDetector;
  

//    public float FACTOR = 1.0f;


    private Matrix matrix = new Matrix();
    private float scale = 1f;
    private float previousX, previousY;
    private float previousDistance = -1f;
    private ImageView img;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacant_class);


        PhotoView photoView = findViewById(R.id.vacant_timetable);
//        img = (ImageView) findViewById(R.id.vacant_timetable);
//        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListner());
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        photoView.setImageBitmap(bitmap);



        photoView.setMaximumScale(5f);
        photoView.setMinimumScale(0.5f);
    }

//        img.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction() & MotionEvent.ACTION_MASK) {
//                    case MotionEvent.ACTION_DOWN:
//                        previousX = event.getX();
//                        previousY = event.getY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        float dx = event.getX() - previousX;
//                        float dy = event.getY() - previousY;
//                        matrix.postTranslate(dx, dy);
//                        previousX = event.getX();
//                        previousY = event.getY();
//                        break;
//
//                    case MotionEvent.ACTION_POINTER_DOWN:
//                        previousDistance = getDistance(event);
//                        break;
//                    case MotionEvent.ACTION_POINTER_UP:
//                        previousDistance = -1f;
//                        break;
//                    case MotionEvent.ACTION_MOVE | MotionEvent.ACTION_POINTER_DOWN:
//                        float newDistance = getDistance(event);
//                        if (previousDistance != -1f) {
//                            float deltaScale = (newDistance - previousDistance) / img.getWidth();
//                            scale += deltaScale;
//                            scale = Math.max(0.1f, Math.min(scale, 10.0f));
//                            matrix.postScale(scale, scale, event.getX(), event.getY());
//                        }
//                        previousDistance = newDistance;
//                        break;
//                }
//
//                img.setImageMatrix(matrix);
//                return true;
//            }
//        });
//    }
//
//    private float getDistance(MotionEvent event) {
//        float dx = event.getX(0) - event.getX(1);
//        float dy = event.getY(0) - event.getY(1);
//        return (float) Math.sqrt(dx * dx + dy * dy);
//    }


}
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        scaleGestureDetector.onTouchEvent(event);
//        return super.onTouchEvent(event);
//    }

//    class ScaleListner extends ScaleGestureDetector.SimpleOnScaleGestureListener {
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
//
//            FACTOR *= detector.getScaleFactor();
//            FACTOR = Math.max(0.1f, Math.min(FACTOR, 20.f));
//            img.setScaleX(FACTOR);
//            img.setScaleY(FACTOR);
//            return true;
//        }
//    }


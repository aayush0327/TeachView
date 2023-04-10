package com.example.techview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

public class class_timetable extends AppCompatActivity {

    public ScaleGestureDetector scaleGestureDetector;

    private Matrix matrix = new Matrix();
    private float scale = 1f;
    private float previousX, previousY;
    private float previousDistance = -1f;
    private ImageView img;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_timetable);

        PhotoView photoView = findViewById(R.id.class_timetable);
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        photoView.setImageBitmap(bitmap);



        photoView.setMaximumScale(5f);
        photoView.setMinimumScale(0.5f);
    }
}
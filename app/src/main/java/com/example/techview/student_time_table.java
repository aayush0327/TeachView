package com.example.techview;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class student_time_table extends AppCompatActivity {

    ActivityStudentTimeTableBinding binding;
    ImageView Timetable;
    TextView fname;
    TextView facultyName;
    TextView subject;
    TextView branch;
    TextView roomno;
    PhotoView photoView;

    public ScaleGestureDetector scaleGestureDetector;
    private Matrix matrix = new Matrix();
    private float scale = 1f;
    private float previousX, previousY;
    private float previousDistance = -1f;
    private ImageView img;

    Bitmap bmp;
    private DatabaseReference mDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_time_table);
        photoView = findViewById(R.id.timetable);
        fname=(TextView) findViewById(R.id.facultyName);
        subject = findViewById(R.id.subject);
        branch = findViewById(R.id.branch);
        roomno = findViewById(R.id.room_number);


        byte[] byteArray = getIntent().getByteArrayExtra("image");
        String name=getIntent().getStringExtra("name");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        fname.setText(name);

        photoView.setImageBitmap(bmp);

        photoView.setMaximumScale(5f);
        photoView.setMinimumScale(0.5f);
        // ...
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("faculty").child(name).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if(task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        String sub = String.valueOf(dataSnapshot.child("subject").getValue());
                        String txt_branch =  String.valueOf(dataSnapshot.child("branch").getValue());
                        String txt_room_no = String.valueOf(dataSnapshot.child("room_no").getValue());

                        subject.setText(sub);
                        branch.setText(txt_branch);
                        roomno.setText(txt_room_no);
                    }
                }
                else {
                    Toast.makeText(student_time_table.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private class ActivityStudentTimeTableBinding {
    }
}
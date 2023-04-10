package com.example.techview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

public class MainActivity extends AppCompatActivity {




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView student;
        ImageView faculty;

        student = findViewById(R.id.student);
        faculty = findViewById(R.id.faculty);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null)
        {
            String email = mAuth.getCurrentUser().getEmail().toString();
            if(email.contains(".ac.")){
                Toast.makeText(this, "Faculty", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,faculty_homepage.class));
                finish();
            }
            else
            {
                Toast.makeText(this, "Student", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(MainActivity.this,HomePage.class));
                finish();
            }
        }


        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,student_login.class));
            }
        });

        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Teacher_login.class));
            }
        });
    }
}

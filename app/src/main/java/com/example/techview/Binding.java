package com.example.techview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.techview.databinding.ActivityHomePageBinding;
import com.example.techview.databinding.ActivityMainBinding;
import com.google.firebase.storage.StorageReference;

public class Binding extends AppCompatActivity {



    ActivityHomePageBinding binding;
    StorageReference storageReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);


//        binding = ActivityMainBinding.inflate(getLayoutInflater().inflate());
        setContentView(binding.getRoot());
//        binding.textViewDate.setOnClickListener();


    }
}
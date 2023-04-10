package com.example.techview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.techview.databinding.ActivityHomePageBinding;
import com.example.techview.databinding.ActivityOptionClassBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class option_class extends AppCompatActivity {

    String[] item = {"4CE-1","4CE-2","4CS-1","4CS-2","4IT-1","4IT-2"};

    AutoCompleteTextView autoCompletetextview1;
    DrawerLayout drawerLayout;
    ArrayAdapter<String> adapterItems;
    ActivityOptionClassBinding binding;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    String selecteditem;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_class);

        binding = ActivityOptionClassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        autoCompletetextview1 = findViewById(R.id.auto_Complete_textview1);
        adapterItems = new ArrayAdapter<>(this,R.layout.list_item,item);
        autoCompletetextview1.setAdapter(adapterItems);
        autoCompletetextview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item =  parent.getItemAtPosition(position).toString();
            }
        });


        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(option_class.this);
                progressDialog.setMessage("Fetching image..");
                progressDialog.setCancelable(false);
                progressDialog.show();

                String imageID = binding.autoCompleteTextview1.getText().toString();


//                Toast.makeText(HomePage.this, "imageID", Toast.LENGTH_SHORT).show();
                storageReference = FirebaseStorage.getInstance().getReference("class_timetable/"+imageID+".jpg");

                try {
                    File localfile = File.createTempFile("tempfile",".jpg");
                    storageReference.getFile(localfile)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                    if(progressDialog.isShowing())
                                        progressDialog.dismiss();

                                    bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                    binding.classTimetable.setImageBitmap(bitmap);


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    if (progressDialog.isShowing())
                                        progressDialog.dismiss();
                                    Toast.makeText(option_class.this, "Fail", Toast.LENGTH_SHORT).show();
                                }
                            });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

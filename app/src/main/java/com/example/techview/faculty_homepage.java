package com.example.techview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.techview.databinding.ActivityFacultyHomepageBinding;
import com.example.techview.databinding.ActivityHomePageBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.zip.Inflater;

public class faculty_homepage extends AppCompatActivity {
    TextView day;
    String[] item = {"Dr.Dweepna Garg (DG)","Bhavika Patel (BNP)","Binal Kaka (BSK)","Dr.Chirag Patel (CIP)","Dhruvi Gosai (DDG)","Janardan Bharvad(JB)","Khushi Patel (KAP)","Kashyap Patel (KSP)","Kajal Parmar (KPP)","Dr.Minal Patel (MP)","Nisha Panchal (NAP)","Prassan Barot (PDB)","Sanjay Patel (SRP)","sachi Joshi (SVJ)","Shraddha Vyas (SPV)","Urvashi Chaudhari (UMC)"};

    AutoCompleteTextView autoCompleteTextView;
    DrawerLayout drawerLayout;

    ArrayAdapter<String> adapterItems;

    ActivityFacultyHomepageBinding binding;

    StorageReference storageReference;
    ProgressDialog progressDialog;
    Button btn2;
    Button logoutbtn;
    FirebaseAuth auth;
    String selecteditem;
    Bitmap bitmap;
    MaterialToolbar appBar;
    TextView drawIcon;
    TextView drawText;

    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_homepage);

        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.auto_Complete_textview);
//        btn2=(Button)findViewById(R.id.btn2);
        logoutbtn = findViewById(R.id.logout);

         appBar = findViewById(R.id.appBar);
         navigationView = findViewById(R.id.nav_view);
         drawerLayout = findViewById(R.id.drawerLayout);
//         drawIcon = findViewById(R.id.drawicon);
//         drawText = findViewById(R.id.drawText);

//         appBar.setTitle("TechView");

//        setupView();

//        View header = navigationView.getHeaderView(0);
//        drawIcon = header.findViewById(R.id.drawicon);
//        drawText = header.findViewById(R.id.drawText);


        binding = ActivityFacultyHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        String[] splitDate = currentDate.split(",");
        String day = splitDate[0];


        auth = FirebaseAuth.getInstance();
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(faculty_homepage.this,MainActivity.class));
                finish();
            }
        });

        binding.textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(faculty_homepage.this);
                progressDialog.setMessage("Fetching image..");
                progressDialog.setCancelable(false);
                progressDialog.show();

                String imageID = binding.autoCompleteTextview.getText().toString();


//                Toast.makeText(HomePage.this, "imageID", Toast.LENGTH_SHORT).show();
                storageReference = FirebaseStorage.getInstance().getReference("images/"+imageID+".jpg");

                try {
                    File localfile = File.createTempFile("tempfile",".jpg");
                    storageReference.getFile(localfile)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                    if(progressDialog.isShowing())
                                        progressDialog.dismiss();

                                    bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                    //binding.imageView.setImageBitmap(bitmap);

                                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bStream);
                                    byte[] byteArray = bStream.toByteArray();

                                    Intent intent1=new Intent(faculty_homepage.this,student_time_table.class);
                                    intent1.putExtra("image", byteArray);
                                    intent1.putExtra("name",selecteditem);
                                    startActivity(intent1);


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    if (progressDialog.isShowing())
                                        progressDialog.dismiss();
                                    Toast.makeText(faculty_homepage.this, "Fail", Toast.LENGTH_SHORT).show();
                                }
                            });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.Profile:
//                        return true;
//                    case R.id.Contactus:
//                        return true;
//                    case R.id.About:
//                        return true;
//                }
//                return true;
//            }
//        });
        binding.textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(faculty_homepage.this);
                progressDialog.setMessage("Fetching image..");
                progressDialog.setCancelable(false);
                progressDialog.show();

                String imageID = binding.autoCompleteTextview.getText().toString();


//                Toast.makeText(HomePage.this, "imageID", Toast.LENGTH_SHORT).show();
                storageReference = FirebaseStorage.getInstance().getReference("images/"+imageID+"-"+day+".jpg");

                try {
                    File localfile = File.createTempFile("tempfile",".jpg");
                    storageReference.getFile(localfile)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                    if(progressDialog.isShowing())
                                        progressDialog.dismiss();

                                    bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                    //binding.imageView.setImageBitmap(bitmap);

                                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bStream);
                                    byte[] byteArray = bStream.toByteArray();

                                    Intent intent1=new Intent(faculty_homepage.this,student_time_table.class);
                                    intent1.putExtra("image", byteArray);
                                    intent1.putExtra("name",selecteditem);
                                    startActivity(intent1);


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    if (progressDialog.isShowing())
                                        progressDialog.dismiss();
                                    Toast.makeText(faculty_homepage.this, "Fail", Toast.LENGTH_SHORT).show();
                                }
                            });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(faculty_homepage.this);
                progressDialog.setMessage("Fetching image..");
                progressDialog.setCancelable(false);
                progressDialog.show();
                StorageReference   storageReference1 = FirebaseStorage.getInstance().getReference("vacant_class/"+"vacant_class"+".jpg");

                try {
                    File localfile = File.createTempFile("tempfile",".jpg");
                    storageReference1.getFile(localfile)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                    if(progressDialog.isShowing())
                                        progressDialog.dismiss();

                                    bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                    //binding.imageView.setImageBitmap(bitmap);

                                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bStream);
                                    byte[] byteArray = bStream.toByteArray();

                                    Intent intent1=new Intent(faculty_homepage.this,vacant_class.class);
                                    intent1.putExtra("image", byteArray);
                                    startActivity(intent1);


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    if (progressDialog.isShowing())
                                        progressDialog.dismiss();
                                    Toast.makeText(faculty_homepage.this, "Fail", Toast.LENGTH_SHORT).show();
                                }
                            });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

         binding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(faculty_homepage.this);
                progressDialog.setMessage("Fetching image..");
                progressDialog.setCancelable(false);
                progressDialog.show();
                StorageReference   storageReference1 = FirebaseStorage.getInstance().getReference("vacant_class/"+"vacant_lab"+".jpg");

                try {
                    File localfile = File.createTempFile("tempfile",".jpg");
                    storageReference1.getFile(localfile)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                    if(progressDialog.isShowing())
                                        progressDialog.dismiss();

                                    bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                    //binding.imageView.setImageBitmap(bitmap);

                                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bStream);
                                    byte[] byteArray = bStream.toByteArray();

                                    Intent intent1=new Intent(faculty_homepage.this,vacant_Lab.class);
                                    intent1.putExtra("image", byteArray);
                                    startActivity(intent1);


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    if (progressDialog.isShowing())
                                        progressDialog.dismiss();
                                    Toast.makeText(faculty_homepage.this,"Fail", Toast.LENGTH_SHORT).show();
                                }
                            });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


//        Toast.makeText(this, day, Toast.LENGTH_SHORT).show();
        Button textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);


        autoCompleteTextView = findViewById(R.id.auto_Complete_textview);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item, item);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selecteditem = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(faculty_homepage.this, "faculty"+item, Toast.LENGTH_SHORT).show();
            }
        });



//        textViewDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    private void setupView(){
        setupDrawerLayout();
    }

    private void setupDrawerLayout(){
        setSupportActionBar(appBar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_open, R.string.menu_close);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}




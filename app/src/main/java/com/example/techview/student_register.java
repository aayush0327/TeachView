package com.example.techview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class student_register extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private EditText student_email;
    private EditText student_pass;
    private Button register_con;

    private String txt_email;
    private String txt_pass;


    private FirebaseAuth mauth;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);


        student_email = findViewById(R.id.student_email);
        student_pass = findViewById(R.id.student_pass);
        register_con = findViewById(R.id.register_con);


        mauth = FirebaseAuth.getInstance();

        register_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_email = student_email.getText().toString();
                txt_pass = student_pass.getText().toString();

                createUser(txt_email,txt_pass);

            }
        });

    }

    private void createUser(String email, String password) {
        mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mauth.getCurrentUser();
                    updateUI(user);
                    Toast.makeText(student_register.this, "Authentication Successfull.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(student_register.this,student_login.class));
                    finish();
                }
                else{
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(student_register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }
    private void updateUI(FirebaseUser user) {

    }
}
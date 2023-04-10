package com.example.techview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.checkerframework.checker.nullness.qual.NonNull;

public class ForgotPass extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button forgot;
    private EditText loginemail;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);


        forgot = findViewById(R.id.continuebtn);
        loginemail = findViewById(R.id.loginemail);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPasswordReset();
            }
        });

    }

    public void sendPasswordReset() {
        // [START send_password_reset]
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = loginemail.getText().toString();
        if (emailAddress.isEmpty()){
            Toast.makeText(this, "Enter Your Email", Toast.LENGTH_SHORT).show();
        }
        else{
            auth.sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Email sent.");
                                Toast.makeText(ForgotPass.this, "Please check your mail", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgotPass.this,student_login.class));
                                finish();
                            }
                            else{
                                Toast.makeText(ForgotPass.this, "Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@androidx.annotation.NonNull Exception e) {
                            Toast.makeText(ForgotPass.this, "Please Enter Email-id", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }
}
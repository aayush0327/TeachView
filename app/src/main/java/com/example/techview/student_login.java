package com.example.techview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;

public class student_login extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private Button student_register;
    private  Button student_login;
    private String txt_email;
    private String txt_pass;

    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;

    private TextView forgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);


        email = findViewById(R.id.loginemail);
        password = findViewById(R.id.loginpassword);
        forgotPass = findViewById(R.id.forgotPass);
        mAuth = FirebaseAuth.getInstance();
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(student_login.this,ForgotPass.class));
            }
        });


        student_register = findViewById(R.id.student_register);
        student_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(student_login.this,student_register.class));
                finish();
            }
        });


        student_login = findViewById(R.id.student_login);
        student_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_email = email.getText().toString();
                txt_pass = password.getText().toString();

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_pass)){
                    Toast.makeText(student_login.this, "Please Enter valid credentials", Toast.LENGTH_SHORT).show();
                }
                else{
//                    if(txt_email.contains(".edu.")){
                        signIn(txt_email,txt_pass);
//                    }
                }
            }
        });
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    startActivity(new Intent(student_login.this,HomePage.class));
                    finish();
                }
                else{
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(student_login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    private void updateUI(FirebaseUser user) {

    }


}
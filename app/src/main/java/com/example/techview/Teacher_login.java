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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Teacher_login extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private Button teacher_register;
    private  Button teacher_login;
    private String txt_email;
    private String txt_pass;

    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;
    private TextView forgotPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);


        email = findViewById(R.id.loginemail);
        password = findViewById(R.id.loginpassword);
        mAuth = FirebaseAuth.getInstance();
        forgotPass = findViewById(R.id.forgotPass);

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Teacher_login.this,ForgotPass.class));
//                Toast.makeText(Teacher_login.this, "Please make first Page", Toast.LENGTH_SHORT).show();
            }
        });


        teacher_register = findViewById(R.id.teacher_register);
        teacher_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Teacher_login.this,teacher_register.class));
                finish();
            }
        });


        teacher_login = findViewById(R.id.teacher_login);
        teacher_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_email = email.getText().toString();
                txt_pass = password.getText().toString();

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_pass)){
                    Toast.makeText(Teacher_login.this, "Please Enter valid credentials", Toast.LENGTH_SHORT).show();
                }
                else{
//
                    if(txt_email.contains(".ac.")){
                        signIn(txt_email,txt_pass);
                    }
                    else
                    {
                        Toast.makeText(Teacher_login.this, "login faild", Toast.LENGTH_SHORT).show();
                    }

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
                    startActivity(new Intent(Teacher_login.this,faculty_homepage.class));
                    finish();
                }
                else{
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(Teacher_login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    private void updateUI(FirebaseUser user) {

    }
}
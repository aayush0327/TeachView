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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class teacher_register extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private EditText femail;
    private EditText fpass;
    private Button register_con1;

    private String txt_email;
    private String txt_pass;
    private FirebaseAuth mauth;
    EditText fname;
//    EditText femail;
    EditText fsub;
    EditText fbranch;
    EditText froom_no;
//    EditText fpass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);


        femail = findViewById(R.id.femail);
        fpass = findViewById(R.id.fpass);
        register_con1 = findViewById(R.id.register_con1);
//        fname = findViewById(R.id.faultyid);
//        femail = findViewById(R.id.femail);
//        fsub = findViewById(R.id.subject);
//        fbranch = findViewById(R.id.branch);
//        froom_no = findViewById(R.id.room_number);


        mauth = FirebaseAuth.getInstance();

        register_con1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_email = femail.getText().toString();
                txt_pass = fpass.getText().toString();

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
                    Toast.makeText(teacher_register.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(teacher_register.this,Teacher_login.class));
                    finish();
                }
                else{
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(teacher_register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }
    private void updateUI(FirebaseUser user) {

    }
}

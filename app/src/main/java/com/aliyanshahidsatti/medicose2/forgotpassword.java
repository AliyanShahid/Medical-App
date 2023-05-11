package com.aliyanshahidsatti.medicose2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {

    private Button forgetpass;
    private EditText forgetemail;
    private String email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        auth =FirebaseAuth.getInstance();
            forgetemail = (EditText) findViewById(R.id.reset_email);
            forgetpass =(Button) findViewById(R.id.reset_password);
            forgetpass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validatedata();
                }
            });
    }
    private void validatedata()
    {
        email = forgetemail.getText().toString().trim();
        if(email.isEmpty())
        {
            forgetemail.setError("Email is required");
        }else {
            forgetpass();
        }

    }
    private void forgetpass() {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(forgotpassword.this, "Kindly check your email and Spam Folder as well", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(forgotpassword.this, patientlogin.class);
                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(forgotpassword.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
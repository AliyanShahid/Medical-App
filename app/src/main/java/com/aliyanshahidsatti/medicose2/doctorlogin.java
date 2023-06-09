package com.aliyanshahidsatti.medicose2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class doctorlogin extends AppCompatActivity {

     private TextView signup;
      private TextView forgotpass;
      private EditText emailTextView, passwordTextView;
      private Button Btn;
      private ProgressBar progressbar;
      private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorlogin);

        signup = findViewById(R.id.signupText);
        emailTextView = findViewById(R.id.usernamedoctor);
        passwordTextView=findViewById(R.id.passworddoctor);
        Btn =  findViewById(R.id.loginButtondoctor);
        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(doctorlogin.this,doctorsignup.class);
                startActivity(intent);
            }
        });
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount1();
            }
        });
    }
    private void loginUserAccount1()
    {

        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                                    "Login successful!!",
                                                    Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar


                                    // if sign-in is successful
                                    // intent to home activity
                                    Intent intent
                                            = new Intent(doctorlogin.this,
                                            doctorsProfile.class);
                                    startActivity(intent);
                                } else {

                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                                    "Login failed!!",
                                                    Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar

                                }
                            }
                        });
    }
    }

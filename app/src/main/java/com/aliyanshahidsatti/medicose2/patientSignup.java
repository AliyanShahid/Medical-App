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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.FirestoreGrpc;

import java.util.HashMap;
import java.util.Map;

public class patientSignup extends AppCompatActivity {
    private TextView loginscrn;
    private EditText emailTextView, passwordTextView,fullname;
    private Button Btn;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        // initialising all views through id defined above
        emailTextView = findViewById(R.id.signup_email);
        fullname = findViewById(R.id.signup_name);
        passwordTextView = findViewById(R.id.signup_password);
        Btn = findViewById(R.id.signup_button);
        progressbar = findViewById(R.id.progressbar);

        loginscrn = (TextView) findViewById(R.id.loginRedirectText);
        loginscrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(patientSignup.this, patientlogin.class);
                startActivity(intent);
            }
        });

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }

    private void registerNewUser() {

        progressbar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        // Validations for input email and password
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

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),
                                            "Registration successful!",
                                            Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar
                            progressbar.setVisibility(View.GONE);
                            DocumentReference df = fstore.collection("Patient").document(user.getUid());
                            Map<String,Object> userinfo = new HashMap<>();
                             userinfo.put("FullName",fullname.getText().toString());
                            userinfo.put("Email",emailTextView.getText().toString());
                            userinfo.put("Password",passwordTextView.getText().toString());
                            df.set(userinfo);
                            // if the user created intent to login activity
                            Intent intent
                                    = new Intent(patientSignup.this,
                                    patientlogin.class);
                            startActivity(intent);
                        }
                        else {

                            // Registration failed
                            Toast.makeText(
                                            getApplicationContext(),
                                            "Registration failed!!"
                                                    + " Please try again later",
                                            Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
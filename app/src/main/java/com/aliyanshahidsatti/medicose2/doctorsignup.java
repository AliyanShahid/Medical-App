package com.aliyanshahidsatti.medicose2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class doctorsignup extends AppCompatActivity {
    String[] items =  {"Islamabad","Karachi","Lahore","Peshawar","Rawalpindi"};
    String[] itemsp =  {"Dentist","Surgeon","General Medicine","Physiotherapist","Neurologist"};
    String[] itemsa =  {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    AutoCompleteTextView autoCompleteTxt;
    AutoCompleteTextView autoCompleteTxtp;
    AutoCompleteTextView autoCompleteTxta;
    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterItemsp;
    ArrayAdapter<String> adapterItemsa;
    private EditText emailTextView, passwordTextView,fullname;
    private Button Btn;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorsignup);

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        autoCompleteTxtp = findViewById(R.id.auto_complete_txt1);
        autoCompleteTxta = findViewById(R.id.auto_complete_txt2);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        adapterItemsp = new ArrayAdapter<String>(this,R.layout.list_item,itemsp);
        adapterItemsa = new ArrayAdapter<String>(this,R.layout.list_item,itemsa);

        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxtp.setAdapter(adapterItemsp);
        autoCompleteTxta.setAdapter(adapterItemsa);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        // initialising all views through id defined above
        emailTextView = findViewById(R.id.email_doctor);
        fullname = findViewById(R.id.username_doctor);
        passwordTextView = findViewById(R.id.password_doctor);
        Btn = findViewById(R.id.registerButton_doctor);
        progressbar = findViewById(R.id.progressbar);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });
        autoCompleteTxtp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });
        autoCompleteTxta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewdoctor();
            }
        });
    }
    private void registerNewdoctor()
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
                    DocumentReference df = fstore.collection("Doctor").document(user.getUid());
                    Map<String,Object> userinfo = new HashMap<>();
                    userinfo.put("FullName",fullname.getText().toString());
                    userinfo.put("Email",emailTextView.getText().toString());
                    userinfo.put("Password",passwordTextView.getText().toString());
                    userinfo.put("City",autoCompleteTxt.getText().toString());
                    userinfo.put("Profession",autoCompleteTxtp.getText().toString());
                    userinfo.put("Day",autoCompleteTxta.getText().toString());
                    df.set(userinfo);
                    // if the user created intent to login activity
                    Intent intent
                            = new Intent(doctorsignup.this,
                            doctorlogin.class);
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

                }
            }
        });

    }
}
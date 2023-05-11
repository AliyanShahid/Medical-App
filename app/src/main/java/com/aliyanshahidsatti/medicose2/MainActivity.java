package com.aliyanshahidsatti.medicose2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button loginbutton;
    private Button ptnyloginbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginbutton = (Button) findViewById(R.id.doctorlogin);
        ptnyloginbutton = (Button) findViewById(R.id.patientlogin);
        ptnyloginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,patientlogin.class);
                startActivity(intent);
            }
        });
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,doctorlogin.class);
                startActivity(intent);
            }
        });
    }
}
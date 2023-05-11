package com.aliyanshahidsatti.medicose2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DoctorDetailsPatient extends AppCompatActivity {
    TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details_patient);
        t1 = (TextView) findViewById(R.id.desc_header);
        t2 = (TextView) findViewById(R.id.desc_desc);
        t1.setText(getIntent().getStringExtra("header"));
        t2.setText(getIntent().getStringExtra("desc"));
    }
}
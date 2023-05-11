package com.aliyanshahidsatti.medicose2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class patienthomescreen extends AppCompatActivity {
    private ImageView imageView;
    private ImageView imageView1;
    private ImageView imageView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patienthomescreen);
        imageView =  findViewById(R.id.appointment);
        imageView1 =  findViewById(R.id.selfdgbtn);
        imageView3 =  findViewById(R.id.apppp);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(patienthomescreen.this,DoctorsListAppointment.class);
                startActivity(intent);
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(patienthomescreen.this,Selfdiagnose.class);
                startActivity(intent);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(patienthomescreen.this,BookingAppointmenDoctor.class);
                startActivity(intent);
            }
        });
    }
}
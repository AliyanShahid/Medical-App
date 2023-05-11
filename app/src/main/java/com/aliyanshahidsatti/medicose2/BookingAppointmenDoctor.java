package com.aliyanshahidsatti.medicose2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class BookingAppointmenDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_appointmen_doctor);
        Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
        Spinner mySpinner1=(Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(BookingAppointmenDoctor.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        ArrayAdapter<String> myAdapter1=new ArrayAdapter<String>(BookingAppointmenDoctor.this, android.R.layout.simple_list_item_2,getResources().getStringArray(R.array.doc_names));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter1);
    }
    }

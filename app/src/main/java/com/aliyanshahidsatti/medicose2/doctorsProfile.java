package com.aliyanshahidsatti.medicose2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class doctorsProfile extends AppCompatActivity {
     Button addprofilebtn;
     ImageView imageView;
     EditText username,email,pass,phone;
     DBHelper dbHelper;
     private static  final int PICK_IMAGE_REQUEST =99;
     private Uri imagepath;
     private Bitmap imagetostore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_profile);
        username = findViewById(R.id.userNameprofiledoctor);
        email = findViewById(R.id.emailaddprofile);
        pass = findViewById(R.id.passwordaddprofile);
        phone = findViewById(R.id.Phoneaddprofile);
        addprofilebtn = findViewById(R.id.signUpBtn);
        imageView = findViewById(R.id.Profile_image);
        dbHelper = new DBHelper(this);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseImage();
            }
        });

        addprofilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeImage();
            }
        });
    }
    private void choseImage(){
        try {
                     Intent intent = new Intent();
                     intent.setType("image/*");
                     intent.setAction(Intent.ACTION_GET_CONTENT);
                     startActivityForResult(intent,PICK_IMAGE_REQUEST);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
              if(requestCode==PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null ){
                  imagepath = data.getData();
                  imagetostore = MediaStore.Images.Media.getBitmap(getContentResolver(),imagepath);
                  imageView.setImageBitmap(imagetostore);
              }

        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }
    private void storeImage(){
       if(!username.getText().toString().isEmpty() && !email.getText().toString().isEmpty()
           && !phone.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()
            && imageView.getDrawable() != null && imagetostore != null){
             dbHelper.storeData(new ModelClass(username.getText().toString(), email.getText().toString(),pass.getText().toString(),phone.getText().toString(),imagetostore));
             Intent intent = new Intent(doctorsProfile.this,DoctorHomeScreen.class);
             startActivity(intent);
       }
       else
       {
           Toast.makeText(getApplicationContext(),"Please Fill all th fields",Toast.LENGTH_SHORT).show();
       }
    }
}
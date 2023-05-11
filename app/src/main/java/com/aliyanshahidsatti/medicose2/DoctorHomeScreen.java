package com.aliyanshahidsatti.medicose2;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class DoctorHomeScreen extends AppCompatActivity {
   MaterialToolbar toolbar;
   DrawerLayout drawerLayout;
   NavigationView navigationView;
   FrameLayout frameLayout;
   Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_screen);
     toolbar = findViewById(R.id.toolbar);
     setSupportActionBar(toolbar);
     frameLayout = findViewById(R.id.main_frameLayout);
     drawerLayout =  findViewById(R.id.drawerlayout);
     navigationView = findViewById(R.id.naviation);

      /* View header =navigationView.getHeaderView(0);
        ImageView nav_image = (ImageView) findViewById(R.id.nav_image);
        TextView nav_text1 =  findViewById(R.id.nav_userName);
        TextView nav_email = (TextView) findViewById(R.id.nav_email);
        DBHelper dbHelper = new DBHelper(this);
        Cursor cursor =dbHelper.getUser();
        if(cursor.getCount()==0)
        {
            Toast.makeText(getApplicationContext(), "No entries", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext()){
                nav_text1.setText(""+cursor.getString(0));
                nav_email.setText(""+cursor.getString(1));
                byte[] imageByte =cursor.getBlob(4);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte,0, imageByte.length);
                nav_image.setImageBitmap(bitmap);
            }
        }*/



        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }
}
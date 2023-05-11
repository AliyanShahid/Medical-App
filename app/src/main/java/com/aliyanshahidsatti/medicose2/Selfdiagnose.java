package com.aliyanshahidsatti.medicose2;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Selfdiagnose extends AppCompatActivity {

    EditText t1,t2;
    Button browse,submit;
    ImageView img;
    Bitmap bitmap;
    String encodeimage;
    private static final String url= "http://172.17.51.117/medicose/fileupload.php";

    Button notify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfdiagnose);

         img = findViewById(R.id.img);
         submit = findViewById(R.id.upload);
         browse= findViewById(R.id.browse);
         browse.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Dexter.withActivity(Selfdiagnose.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                         .withListener(new PermissionListener() {
                             @Override
                             public void onPermissionGranted(PermissionGrantedResponse response) {
                                      Intent intent = new Intent(Intent.ACTION_PICK);
                                      intent.setType("image/*");
                                 startActivityForResult(Intent.createChooser(intent,"Browse image"),1);
                             }

                             @Override
                             public void onPermissionDenied(PermissionDeniedResponse response) {

                             }

                             @Override
                             public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                  token.continuePermissionRequest();
                             }
                         }).check();
             }
         });
       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               uploaddatatodb();
           }
       });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel =  new NotificationChannel("New Notification","New Notification",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notify = findViewById(R.id.btn);
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Message = "We have Observed your Symptoms You should Have an Appointment with Dentist";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        Selfdiagnose.this,"New Notification"
                ).setSmallIcon(R.drawable.ic_baseline_person_24).setContentTitle("New Notification")
                        .setContentText(Message).setAutoCancel(true);
                Intent intent = new Intent(Selfdiagnose.this,DoctorsListAppointment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message",Message);
                PendingIntent pendingIntent = PendingIntent.getActivity(Selfdiagnose.this,0
                ,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager) getSystemService(
                Context.NOTIFICATION_SERVICE );

                    notificationManager.notify(0,builder.build());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       if(requestCode==1 && resultCode==RESULT_OK)
       {
           Uri filepath = data.getData();
           try {
               InputStream inputStream = getContentResolver().openInputStream(filepath);
               bitmap = BitmapFactory.decodeStream(inputStream);
               img.setImageBitmap(bitmap);
               encodeBitmapImage(bitmap);

           }catch (Exception e){

           }
       }


        super.onActivityResult(requestCode, resultCode, data);
    }
    private void encodeBitmapImage(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytesofimage = byteArrayOutputStream.toByteArray();
        encodeimage=android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);

    }
    private void uploaddatatodb(){
          t1= findViewById(R.id.t1);
          t2=findViewById(R.id.t2);
          final String name= t1.getText().toString().trim();
          final String dsg =t2.getText().toString().trim();

          StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                       t1.setText("");
                       t2.setText("");
                       img.setImageResource(R.drawable.ic_launcher_foreground);
                  Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_SHORT).show();
              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                  Toast.makeText(getApplicationContext(),"Image Added Successfully to DB",Toast.LENGTH_SHORT).show();

              }
          }){
              @Nullable
              @Override
              protected Map<String, String> getParams() throws AuthFailureError {
                  Map<String,String> map= new HashMap<String,String>();
                  map.put("submit",encodeimage);

                  return map;
              }
          };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }
}
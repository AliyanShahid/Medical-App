package com.aliyanshahidsatti.medicose2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class DBHelper extends SQLiteOpenHelper {

    Context context;
    private static  String DB_NAME = "user.db";
    private static int DB_VERSION = 1;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;
    private static String createTableQuery = "Create table LoginUser(userName TEXT " +
            ",email TEXT " +
            ",phone TEXT " +
            ",password TEXT " +
            ",image BLOB)";


    public DBHelper( Context context) {
        super(context,DB_NAME,null,DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
              db.execSQL(createTableQuery);
        Toast.makeText(context.getApplicationContext(),"table created successfully",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  void storeData(ModelClass modelClass){
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imagetoStoreBitmap = modelClass.getProfileimage();
         byteArrayOutputStream= new
                 ByteArrayOutputStream();
         imagetoStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
          imageInBytes = byteArrayOutputStream.toByteArray();
          ContentValues contentValues= new ContentValues();
          contentValues.put("userName",modelClass.getUserName());
          contentValues.put("email",modelClass.getEmail());
          contentValues.put("phone",modelClass.getPhone());
          contentValues.put("password",modelClass.getPassword());
          contentValues.put("image",imageInBytes);

          long checkIfQueryRun = db.insert("LoginUser",null,contentValues);
          if(checkIfQueryRun != -1){
              Toast.makeText(context.getApplicationContext(),"Table Added Successfully",Toast.LENGTH_SHORT).show();
              db.close();
          }
          else {
              Toast.makeText(context.getApplicationContext(), "Failed to Add", Toast.LENGTH_SHORT).show();
          }
    }
    public Cursor getUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from LoginUser",null);
        return cursor;
    }
}

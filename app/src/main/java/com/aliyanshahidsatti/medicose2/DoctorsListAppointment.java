package com.aliyanshahidsatti.medicose2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DoctorsListAppointment extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseFirestore db;
    MyAdapter myAdapter;
    ArrayList<doctors> list;
    SearchView searchView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list_appointment);
        searchView = findViewById(R.id.searchView);
        progressDialog  = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data ........");
        progressDialog.show();
        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.DoctorsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapter = new MyAdapter(list,getApplicationContext());
        recyclerView.setAdapter(myAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return true;
            }
        });
        db.collection("Doctor").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
           List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();
           for (DocumentSnapshot d : list1){
               doctors obj = d.toObject(doctors.class);
               list.add(obj);
           }
          myAdapter.notifyDataSetChanged();
           if(progressDialog.isShowing()){
               progressDialog.dismiss();
           }

            }
        });
    }

    }




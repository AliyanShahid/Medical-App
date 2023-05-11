package com.aliyanshahidsatti.medicose2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {


    ArrayList<doctors> list;
    ArrayList<doctors>backup;
    Context context;

    public MyAdapter(ArrayList<doctors> list, Context context)
    {
        this.list = list;
        this.context=context;
        this.backup = new ArrayList<>(list);

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctoritems,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                 final doctors temp = list.get(position);
                 holder.name.setText(list.get(position).getFullName());
                 holder.profession.setText(list.get(position).getProfession());
                 holder.location.setText(list.get(position).getCity());
                 holder.day.setText(list.get(position).getDay());

                 holder.name.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent intent = new Intent(context,DoctorDetailsPatient.class);
                         intent.putExtra("header",temp.getFullName());
                         intent.putExtra("desc",temp.getEmail());
                         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         context.startActivity(intent);
                     }
                 });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<doctors> filtereddata= new ArrayList<>();
            if(keyword.toString().isEmpty()){
                filtereddata.addAll(backup);
            }
            else
                for(doctors obj : backup){
                    if(obj.getProfession().toString().toLowerCase().contains(keyword.toString().toLowerCase())){
                        filtereddata.add(obj);
                    }
                }
                FilterResults results = new FilterResults();
                results.values=filtereddata;
                return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
                 list.clear();
                 list.addAll((ArrayList<doctors>)results.values);
                 notifyDataSetChanged();
        }
    };

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
       private final TextView name,profession,location,day;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            profession=itemView.findViewById(R.id.tvProfession);
            location = itemView.findViewById(R.id.tvLocation);
            day = itemView.findViewById(R.id.tvDay);
        }
    }
}

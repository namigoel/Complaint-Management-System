package com.example.complaintregister;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Status_fragment extends Fragment {
    DBhelper myDB;
    RecyclerView rv;
    String email;
    String department,category,description,status;
    ArrayList<GetterSetter> a1=new ArrayList<>();
    public static Status_fragment getInstance(String email){
        Status_fragment myFragment = new Status_fragment();
        Bundle bundle=new Bundle();
        bundle.putString("data", email); //key and value
        myFragment.setArguments(bundle);
        return myFragment;
    }


    public Status_fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_status_fragment, container, false);
        rv=RootView.findViewById(R.id.rev);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(RootView.getContext());
        rv.setLayoutManager(layoutManager);
        myDB=new DBhelper(RootView.getContext());
        email=getArguments().getString("data");
        Cursor data=myDB.getListContents(email);
        if(data.getCount()>0){
            if(data.moveToFirst()){
                do{

                    department=data.getString(1);
                    category=data.getString(2);
                    description=data.getString(3);
                    status="Yet to open";


                    GetterSetter g1=new GetterSetter(department,category,description,email);
                    a1.add(g1);
                }while (data.moveToNext());
            }
        }
        MyAdapter myAdapter=new MyAdapter(RootView.getContext(),a1);
        rv.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent in=new Intent(getActivity(),CardActivity_user.class);
                in.putExtra("object",a1.get(position));
                startActivity(in);
            }
        });


        // Inflate the layout for this fragment
        return RootView;

    }

}
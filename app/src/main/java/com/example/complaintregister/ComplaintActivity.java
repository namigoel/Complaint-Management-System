package com.example.complaintregister;

import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class ComplaintActivity extends AppCompatActivity {
    DBhelper myDB;
    RecyclerView rv;
    String department,category,description,email,status;
    ArrayList<GetterSetter> a1=new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        rv=findViewById(R.id.rev1);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);
        myDB=new DBhelper(this);
        Cursor data=myDB.getListContents1();
        if(data.getCount()>0){
            if(data.moveToFirst()){
                do{
                    department=data.getString(1);
                    category=data.getString(2);
                    description=data.getString(3);
                    email=data.getString(4);
                    status="Yet to open";

                    GetterSetter g1=new GetterSetter(department,category,description,email);
                    a1.add(g1);
                }while (data.moveToNext());
            }
        }
        MyAdapter myAdapter=new MyAdapter(this,a1);
        rv.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent in=new Intent(ComplaintActivity.this,CardActivity.class);
                in.putExtra("object",a1.get(position));
                startActivity(in);
            }
        });


    }



}

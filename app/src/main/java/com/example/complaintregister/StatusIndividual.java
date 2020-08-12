package com.example.complaintregister;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StatusIndividual extends AppCompatActivity {
    String cat,status1,dept,des,useremail;
    DBhelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_individual);
        cat=getIntent().getStringExtra("cat");
        dept=getIntent().getStringExtra("dept");
        des=getIntent().getStringExtra("des");
        useremail=getIntent().getStringExtra("email");
        myDB=new DBhelper(this);

        Cursor data=myDB.getListContents2(useremail,dept,cat,des);
        if(data.getCount()>0){
            if(data.moveToFirst()){
                status1=data.getString(5);
            }
        }

        TextView category=(TextView)findViewById(R.id.individual_category);
        TextView status=(TextView)findViewById(R.id.individual_status);
        category.setText(cat);
        status.setText(status1);


    }
}

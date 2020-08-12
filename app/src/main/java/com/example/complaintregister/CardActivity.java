package com.example.complaintregister;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class CardActivity extends AppCompatActivity {
    Button forward_button, reject_button, resolved_button;
    final Context context = this;
    EditText email_forward;
    DBhelper myDB;
    String dept,cate,des,useremail;
    int status;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        Intent in = getIntent();
        final GetterSetter getterSetter = in.getParcelableExtra("object");
         dept = getterSetter.getDepartment();
         cate = getterSetter.getCategory();
        des = getterSetter.getDescription();
        useremail=getterSetter.getEmail();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_card);
        myDB=new DBhelper(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("       " + dept);
        forward_button = findViewById(R.id.card_forward);
        reject_button = findViewById(R.id.card_reject);
        resolved_button = findViewById(R.id.card_resolved);
        TextView cat_card = (TextView) findViewById(R.id.card_category);
        TextView des_card = (TextView) findViewById(R.id.card_description);
        final TextView user_email=(TextView)findViewById(R.id.user_email);
        cat_card.setText("Category--"+cate);
        des_card.setText("Description--"+des);
        user_email.setText("Email--"+useremail);
        reject_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String status1="Rejected.. For more details--- contact 1234567890";
               int insertdata=myDB.addstatus(status1,useremail,dept,des,cate);
                String sub="Complaint regarding dept "+dept;
                String msg="Hello! This email is with consideration to complaint regarding "+cate+" .Sorry! But your complaint has been rejected after verifying the actual scenario regarding the description of complaint"+des+" \n For more details\nContact--";

                //Creating SendMail object
                SendMail sm = new SendMail(CardActivity.this, useremail, sub, msg);

                //Executing sendmail to send email
                sm.execute();

            }
        });
        resolved_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status1="Resolved.. Any Query ----contact 1234567890";
                int insertdata=myDB.addstatus(status1,useremail,dept,des,cate);

            }
        });
        forward_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(CardActivity.this,ForwardActivity.class);
                in.putExtra("dept",dept);
                in.putExtra("cat",cate);
                in.putExtra("des",des);
                in.putExtra("email",useremail);
                String status1="Pending. --In Process--";
                int insertdata=myDB.addstatus(status1,useremail,dept,des,cate);

                startActivity(in);

            }
        });


    }
}

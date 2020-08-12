package com.example.complaintregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CardActivity_user extends AppCompatActivity {
    String dept,cate,des,useremail;
    Button checkStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_user);
        Intent in = getIntent();
        GetterSetter getterSetter = in.getParcelableExtra("object");
        dept = getterSetter.getDepartment();
        cate = getterSetter.getCategory();
        des = getterSetter.getDescription();
        useremail=getterSetter.getEmail();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_card_user);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("       Department--" + dept);
        TextView cat_card = (TextView) findViewById(R.id.card_category_user);
        TextView des_card = (TextView) findViewById(R.id.card_description_user);
        TextView user_email=(TextView)findViewById(R.id.card_email_user);
        cat_card.setText("Category--"+cate);
        des_card.setText("Description--"+des);
        user_email.setText("Email--"+useremail);

         checkStatus=findViewById(R.id.card_status);
         checkStatus.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent in=new Intent(CardActivity_user.this,StatusIndividual.class);
                 in.putExtra("cat",cate);
                 in.putExtra("dept",dept);
                 in.putExtra("des",des);
                 in.putExtra("email",useremail);
                 startActivity(in);
             }
         });


    }
}

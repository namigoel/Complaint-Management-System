package com.example.complaintregister;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button_user;
    Button button_admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_user=(Button)findViewById(R.id.button_user);
        Button button_admin=(Button)findViewById(R.id.button_admin);
        button_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(v.getContext() , UserActivity.class);
                startActivity(in);

            }
        });
        button_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(v.getContext(),AdminActivity.class);
                startActivity(in);
            }
        });

    }
}

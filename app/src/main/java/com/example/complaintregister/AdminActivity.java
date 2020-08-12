package com.example.complaintregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {
    EditText email_admin,password_admin;
    Button login_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        email_admin=(EditText)findViewById(R.id.input_email_login_admin);
        password_admin=(EditText)findViewById(R.id.input_password_login_admin);
        login_admin=(Button)findViewById(R.id.btn_login_admin);


    }
    public void onAdmin(View v){
        final String password_admin_str=password_admin.getText().toString();
        final String email_admin_str=email_admin.getText().toString();
        if(email_admin_str.equals("admin")&&password_admin_str.equals("password")){
            Intent i=new Intent(AdminActivity.this,ComplaintActivity.class);
            Toast toast=Toast.makeText(AdminActivity.this,"Login Successful",Toast.LENGTH_SHORT);
            toast.show();
            startActivity(i);
        }


    }
}

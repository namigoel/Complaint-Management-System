package com.example.complaintregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForwardActivity extends AppCompatActivity {
    String email,dept,cat,des,email_user;
    EditText email_forward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forward);
        Button forward_button=(Button)findViewById(R.id.button_forward);
        email_forward=(EditText)findViewById(R.id.email_forward);
        Intent in=getIntent();
        dept=in.getStringExtra("dept");
        cat=in.getStringExtra("cat");
        des=in.getStringExtra("des");
        email_user=in.getStringExtra("email");
        forward_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=email_forward.getText().toString();
                String sub="Complaint regarding dept "+dept;
                String msg="Hello! This email is with consideration to complaint regarding "+cat+" . Look upon this complaint and solve it as soon as possible and start giving updates to "+email_user+" regarding the status of complaint.Description of complaint is ----->"+des;

                //Creating SendMail object
                SendMail sm = new SendMail(ForwardActivity.this, email, sub, msg);

                //Executing sendmail to send email
                sm.execute();


            }
        });

    }
}

package com.example.complaintregister;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class Register_fragment extends Fragment {
    String dept,cat,des,email,status;
    EditText department,category,description;
    DBhelper db;
    Button submit_button;
    public Register_fragment() {
        // Required empty public constructor
    }
    public static Register_fragment getInstance(String email){
        Register_fragment myFragment = new Register_fragment();
        Bundle bundle=new Bundle();
        bundle.putString("data", email); //key and value
        myFragment.setArguments(bundle);
        return myFragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_register_fragment, container, false);
        email=getArguments().getString("data");
         department=(EditText)RootView.findViewById(R.id.id_department);
         category=(EditText)RootView.findViewById(R.id.id_category);
         description=(EditText)RootView.findViewById(R.id.id_description);
         submit_button=(Button)RootView.findViewById(R.id.id_button);
        db=new DBhelper(RootView.getContext());
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dept=department.getText().toString();
                cat=category.getText().toString();
                des=description.getText().toString();
                AddData(dept,cat,des,email);
                sendEmail();

            }
        });


        // Inflate the layout for this fragment
        return RootView;
    }
    public void AddData(String dept,String cat,String des,String email){
        int insertdata=db.adddata(dept,cat,des,email);
        if(insertdata==1)
        {
            Toast toast=Toast.makeText(getActivity(), "Successfully added data", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    private void sendEmail() {
        String email=getArguments().getString("data");
        //Getting content for email
        String sub="Complaint regarding dept "+dept;
        String msg="Hello! This email is with consideration to your complaint regarding "+cat+" . This would be solved as soon as possible and a feedback mail will be provided soon.";
        String subject = sub.trim();
        String message = msg.trim();

        //Creating SendMail object
        SendMail sm = new SendMail(getActivity(), email, sub, msg);

        //Executing sendmail to send email
        sm.execute();
    }

    // TODO: Rename method, update argument and hook method into UI event


}

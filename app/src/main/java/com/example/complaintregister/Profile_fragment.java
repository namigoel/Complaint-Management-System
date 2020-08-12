package com.example.complaintregister;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.Executor;



public class Profile_fragment extends Fragment {
    private static boolean t2;
    private TextView name;
    private TextView email;
    User user;
    String name_user,email_user,t1,t3,personName;
    Button logOut;
    public DatabaseHelper peopleDB;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient mGoogleApiClient;



    public static Profile_fragment getInstance(String email,Boolean t2){
        Profile_fragment myFragment = new Profile_fragment();
        Bundle bundle=new Bundle();
        bundle.putString("data", email);//key and value
        bundle.putBoolean("flag",t2);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.fragment_profile_fragment, container, false);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(RootView.getContext());
        if (acct != null) {
             personName = acct.getDisplayName();
        }
        peopleDB = new DatabaseHelper(RootView.getContext());
        TextView name=(TextView)RootView.findViewById(R.id.text_name);
        TextView email=(TextView)RootView.findViewById(R.id.text_email);
        logOut=RootView.findViewById(R.id.logout);
         String t1=getArguments().getString("data");
         t2=getArguments().getBoolean("flag",false);
         //t3 = getArguments().getString("name");
        if(t2==true){
            email.setText(t1);
            name.setText("Hello! "+personName+" Welcome to Complaint Service Management");
        }
        else {

            email.setText(t1);
            user = new User();
            user = peopleDB.getUser(t1);

            name.setText("Hello! " + user.getName() + " Welcome to Complaint Service Management");
        }

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                // ...
                                Toast.makeText(RootView.getContext(),"Logged Out",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(RootView.getContext(),MainActivity.class);
                                startActivity(i);
                            }
                        });
            }
        });





        // Inflate the layout for this fragment
        return RootView;
    }



    @Override
    public void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }

}

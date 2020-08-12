package com.example.complaintregister;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;



import com.google.android.gms.auth.api.Auth;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Arrays;


public class SignUpActivity extends AppCompatActivity {
    private EditText email_signup;
    private EditText password_signup;
    private EditText name_signup;
    private Button sign_up;

    TextView FacebookDataTextView;
    private static final int RC_SIGN_IN = 0;
    public DatabaseHelper peopleDB;
   Boolean flag=false;

    private FirebaseAuth mAuth;


    GoogleSignInClient mGoogleSignInClient;
    TextView info,link_login;
    //EditText name_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_acticvity);
        peopleDB = new DatabaseHelper(this);//used to resolve null pointer exception

        email_signup = findViewById(R.id.input_email);
        password_signup = findViewById(R.id.input_password);
        sign_up = findViewById(R.id.btn_signup);
        link_login=findViewById(R.id.link_login);
        name_signup = findViewById(R.id.input_name);
        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(SignUpActivity.this,UserActivity.class);
                startActivity(in);
            }
        });




        if(flag==false)
        AddData();

    }


    public void AddData() {
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = name_signup.getText().toString();
                String email = email_signup.getText().toString();
                String password = password_signup.getText().toString();

                int insertData = peopleDB.addData(name, email, password);

                if (insertData == 1) {
                    Toast.makeText(SignUpActivity.this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SignUpActivity.this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}

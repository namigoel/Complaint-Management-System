package com.example.complaintregister;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class UserActivity extends AppCompatActivity {
    TextView linkas_signup;
    private EditText inputEmail, inputPassword;
    public DatabaseHelper peopleDB;
    private ProgressBar progressBar;
    private Button btnLogin;
    String email;
    private static final int RC_SIGN_IN = 007;
    Boolean flag=false;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user);
        inputEmail = (EditText) findViewById(R.id.input_email_login);
        inputPassword = (EditText) findViewById(R.id.input_password_login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnLogin = (Button) findViewById(R.id.btn_login);
        findViewById(R.id.btn_google).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        TextView linkas_signup = (TextView) findViewById(R.id.link_signup);
        peopleDB = new DatabaseHelper(this);

        linkas_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



    }

        public void checkLogin(View view){
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email=inputEmail.getText().toString();
                    int checkData=peopleDB.checkAlreadyExist(email);
                    if(checkData==1){
                        if(email.matches("")){
                            Toast toast=Toast.makeText(UserActivity.this,"Enter details",Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else {

                            Intent i = new Intent(UserActivity.this, Got_a_Complaint.class);
                            i.putExtra("data", email);
                            startActivity(i);
                        }
                        /*FragmentManager manager=getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction t=manager.beginTransaction();
                        Profile_fragment p4=  new Profile_fragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("email",inputEmail.getText().toString());
                        p4.setArguments(bundle);*/

                    }
                    else
                    {
                        Toast toast = Toast.makeText(UserActivity.this,"Email not valid",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
        }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 007:
                    try {
                        // The Task returned from this call is always completed, no need to attach
                        // a listener.
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        updateUI(account);
                    } catch (ApiException e) {
                        // The ApiException status code indicates the detailed failure reason.
                        Log.w("status", "signInResult:failed code=" + e.getStatusCode());
                    }
                    break;
            }
    }
    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyloggedAccount != null) {
            Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show();
            updateUI(alreadyloggedAccount);
        } else {
            Log.d("status", "Not logged in");
        }
    }



    private void updateUI(GoogleSignInAccount googleSignInAccount) {
        Intent in=new Intent(UserActivity.this, Got_a_Complaint.class);
        email=inputEmail.getText().toString();
        in.putExtra("data",email);
        flag=true;
        in.putExtra("flag",flag);
        startActivity(in);
        finish();
    }



}















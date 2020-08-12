package com.example.complaintregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Got_a_Complaint extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Boolean flag;
    String personName,personEmail;
    private GoogleSignInClient mGoogleSignInClient;
    DBhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Got A Complaint ?");
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        flag=getIntent().getBooleanExtra("flag",false);
        if (acct != null) {

             personName = acct.getDisplayName();
             personEmail = acct.getEmail();
        }

         if(flag==false) {
             String data = getIntent().getStringExtra("data");

             NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
             View hView = navigationView.getHeaderView(0);
             TextView nav_user = (TextView) hView.findViewById(R.id.textView_email);
             nav_user.setText(data);
         }

           else {
             NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
             View hView = navigationView.getHeaderView(0);
             TextView nav_user = (TextView) hView.findViewById(R.id.textView_email);
             nav_user.setText(personEmail);
         }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigation_View = (NavigationView) findViewById(R.id.nav_view);
        navigation_View.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment selectedFragment=null;
        flag=getIntent().getBooleanExtra("flag",false);
        if (id == R.id.nav_profile) {
            selectedFragment = new Profile_fragment();
            if(flag==false) {
                String email = getIntent().getStringExtra("data");
                Bundle b = new Bundle();
                b.putString("data", email);
                Profile_fragment obj = new Profile_fragment();
                obj.setArguments(b);
                Bundle b1=new Bundle();
                b1.putBoolean("flag",flag);
                Profile_fragment obj1=new Profile_fragment();
                obj1.setArguments(b1);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame,Profile_fragment.getInstance(email,flag));
                fragmentTransaction.commit();
            }
            else{
                Bundle b = new Bundle();
                b.putString("data", personEmail);
                Profile_fragment obj = new Profile_fragment();
                obj.setArguments(b);
                Bundle b1=new Bundle();
                b1.putBoolean("flag",flag);
                Profile_fragment obj1=new Profile_fragment();
                obj1.setArguments(b1);
                /*Bundle b2 = new Bundle();
                b2.putString("name", personName);
                Profile_fragment obj2 = new Profile_fragment();
                obj2.setArguments(b2);*/
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame,Profile_fragment.getInstance(personEmail,flag));
                fragmentTransaction.commit();
            }


        }


            // Handle the camera action
         else if (id == R.id.nav_register) {
            selectedFragment=new Register_fragment();
            if (flag == false) {
                String email = getIntent().getStringExtra("data");
                Bundle b = new Bundle();
                b.putString("data", email);
                Register_fragment obj = new Register_fragment();
                obj.setArguments(b);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, Register_fragment.getInstance(email));
                fragmentTransaction.commit();
            }
            else{
                Bundle b = new Bundle();
                b.putString("data", personEmail);
                Register_fragment obj = new Register_fragment();
                obj.setArguments(b);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, Register_fragment.getInstance(personEmail));
                fragmentTransaction.commit();
            }

        } else if (id == R.id.nav_status) {
            selectedFragment=new Status_fragment();
            if (flag == false) {
                String email = getIntent().getStringExtra("data");
                Bundle b = new Bundle();
                b.putString("data", email);
                Status_fragment obj = new Status_fragment();
                obj.setArguments(b);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, Status_fragment.getInstance(email));
                fragmentTransaction.commit();
            }
            else{
                Bundle b = new Bundle();
                b.putString("data", personEmail);
                Status_fragment obj = new Status_fragment();
                obj.setArguments(b);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, Status_fragment.getInstance(personEmail));
                fragmentTransaction.commit();
            }

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

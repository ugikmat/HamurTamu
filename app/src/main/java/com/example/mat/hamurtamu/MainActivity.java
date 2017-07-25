package com.example.mat.hamurtamu;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    TextView textView;
    NavigationView navigationView;
    SharedPreferences sharedPref;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getPreferences(Context.MODE_PRIVATE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        HomeFragment fragment = new HomeFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, fragment,"home_fragment").commit();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                } else {
                    // User is signed out

                }
                // ...
            }
        };

    }

    public void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void loggedIn(){

        navigationView = (NavigationView)findViewById(R.id.nav_view);
        textView = (TextView)findViewById(R.id.text_username);
        if(sharedPref.getString("login",null)==null){
            textView.setText("Hello Guest");
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_main_drawer);
        }else{
            textView.setText("Hello "+sharedPref.getString("login",null));
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_main_drawer_user);
        }
    }

    public void init(){}

    @Override
    public void onBackPressed() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(getSupportFragmentManager().findFragmentByTag("register_fragment")!=null){
            LoginFragment fragmentLogin = new LoginFragment();
            transaction.replace(R.id.fragment_container, fragmentLogin,"login_fragment").commit();
            setTitle("Login");
        }else if(getSupportFragmentManager().findFragmentByTag("home_fragment")==null){
            HomeFragment fragment = new HomeFragment();
            transaction.replace(R.id.fragment_container, fragment,"home_fragment").commit();
        }else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

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

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (id){
            case R.id.nav_login:
                LoginFragment fragmentLogin = new LoginFragment();
                transaction.replace(R.id.fragment_container, fragmentLogin,"login_fragment").commit();
                setTitle("Login");
                break;
            case R.id.nav_home:
                HomeFragment fragment = new HomeFragment();
                transaction.replace(R.id.fragment_container, fragment,"home_fragment").commit();
                setTitle("Hamur-On");
                break;
            case R.id.nav_cari:
                Cari fragmentCari = new Cari();
                transaction.replace(R.id.fragment_container, fragmentCari,"cari_fragment").commit();
                setTitle("Cari");
                break;
            case R.id.nav_chat:
                break;
            case R.id.nav_add:
                break;
            case R.id.nav_setting:
                break;
            case R.id.nav_edit:
                break;
            case R.id.nav_notif:
                break;
            case R.id.nav_fav:
                break;
            case R.id.nav_logout:
                sharedPref.edit().remove("login").commit();
                loggedIn();
                break;
            case R.id.nav_order:
                break;
            case R.id.nav_feed:
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

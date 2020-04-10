package com.example.lifelinev2.Doctor;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lifelinev2.Patient.AppointmentsFragment;
import com.example.lifelinev2.Patient.ForumFragment;
import com.example.lifelinev2.Patient.HospitalFragment;
import com.example.lifelinev2.Patient.ProfileEdit;
import com.example.lifelinev2.Patient.ProfileFragment;
import com.example.lifelinev2.Patient.ProfileInfo;
import com.example.lifelinev2.R;
import com.google.firebase.auth.FirebaseAuth;


public class d_ProfileActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        d_ProfileInfo.OnFragmentInteractionListener,
        d_ProfileEdit.OnFragmentInteractionListener{//,
      //  SearchDonor.OnFragmentInteractionListener,
     //   AllDonor.OnFragmentInteractionListener{

    private DrawerLayout drawer;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_activity_profile);

        Toolbar toolbar =  findViewById(R.id.d_toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.d_drawer_layout);
        mAuth = FirebaseAuth.getInstance();

        NavigationView navigationView =  findViewById(R.id.d_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.d_fragment_container, new d_ProfileFragment()).commit();
            navigationView.setCheckedItem(R.id.d_nav_profile);
        }
    }

    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, d_MainActivity.class));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {

            case R.id.d_nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.d_fragment_container, new d_ProfileFragment()).commit();
                break;
            case R.id.d_nav_MyPatients:
         //       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FinddonorFragment()).commit();
                break;
            case R.id.d_nav_hospital:
                getSupportFragmentManager().beginTransaction().replace(R.id.d_fragment_container, new d_HospitalFragment()).commit();
                break;
            case R.id.d_nav_myAppointments:
                getSupportFragmentManager().beginTransaction().replace(R.id.d_fragment_container, new d_AppointmentsFragment()).commit();
                break;
            case R.id.d_nav_forum:
                getSupportFragmentManager().beginTransaction().replace(R.id.d_fragment_container, new d_ForumFragment()).commit();
                break;
            case R.id.nav_share :
                Toast.makeText(this, "Share this App", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_feedback :
                Toast.makeText(this, "Give Feedback", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_aboutus :
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

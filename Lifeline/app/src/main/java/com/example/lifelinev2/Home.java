package com.example.lifelinev2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lifelinev2.Doctor.d_MainActivity;
import com.example.lifelinev2.Patient.MainActivity;

public class Home extends AppCompatActivity  {

    Button pat,doc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

      pat=  findViewById(R.id.Patientbtn);
       doc = findViewById(R.id.Doctorbtn);

       pat.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(Home.this, "Patient", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(Home.this, MainActivity.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
              // finish();
               startActivity(intent);

           }
       });

       doc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(Home.this, "Doctor", Toast.LENGTH_SHORT).show();
               Intent intent2 = new Intent(Home.this, d_MainActivity.class);
               intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //   finish();
               startActivity(intent2);

           }
       });


    }
/*
    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.Patientbtn:
                finish();
                startActivity(new Intent(this, MainActivity.class));

                break;

            case  R.id.Doctorbtn:
                finish();
                startActivity(new Intent(this, d_MainActivity.class));

                break;
        }
    } */
}

package com.example.lifelinev2.Patient;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lifelinev2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //    private DatabaseReference MyRef;
    EditText Email, Password;
    ProgressBar progressBar;
    private  FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_main);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.TextViewSignUp).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        Email = (EditText) findViewById(R.id.EmailEditText);
        Password = (EditText) findViewById(R.id.PasswordEditText);
        progressBar = findViewById(R.id.progressbar);
//        MyRef = FirebaseDatabase.getInstance().getReference();

    }

    private  void LoginUser () {

        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if(email.isEmpty()){
            Email.setError("Email is required.");
            Email.requestFocus();
            return ;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Email.setError("Please enter a valid email");
            Email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            Password.setError("Password is required.");
            Password.requestFocus();
            return ;
        }

        if(password.length() < 6){
            Password.setError("Password minimum length should be 6 characters.");
            Password.requestFocus();
            return ;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);

                if (task.isSuccessful()) {

                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                    startActivity(intent);

                }
                else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(),Toast.LENGTH_LONG).show();

                }

            }
        });



    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if(mAuth.getCurrentUser() != null) {
//            finish();
//            startActivity(new Intent(this, d_ProfileActivity.class));
//        }
//    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.TextViewSignUp:
                finish();
                startActivity(new Intent(this, SignUpActivity.class));

                break;

            case  R.id.btn1:
                LoginUser ();

                break;
        }
    }
}

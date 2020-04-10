package com.example.lifelinev2.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lifelinev2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    EditText Email, Password, Name;
    ProgressBar progressBar;
    RadioGroup radioGroup;
    RadioButton radioButton;
    private FirebaseAuth mAuth;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_sign_up);

        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.TextViewSignIn).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressbar);
        Email = findViewById(R.id.EmailEditText);
        Password = findViewById(R.id.PasswordEditText);
        Name = findViewById(R.id.NameEditText);
        radioGroup = findViewById(R.id.radio_gender);

    }

    private void RegisterUser() {
        final String email = Email.getText().toString().trim();
        final String password = Password.getText().toString().trim();
        final String name = Name.getText().toString().trim();

        radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        final String gender = (String) radioButton.getText();

        if(email.isEmpty()){
            Email.setError("Email is required.");
            Email.requestFocus();
            return ;
        }

        if(name.isEmpty()){
            Name.setError("Username is required.");
            Name.requestFocus();
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

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.GONE);

                if (task.isSuccessful()) {

                    Toast.makeText(getApplicationContext(), "Patient account created successfully.", Toast.LENGTH_LONG).show();
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    String userId = firebaseUser.getUid();


                    ref = FirebaseDatabase.getInstance().getReference("Patient:").child(userId);


                   User user = new User(userId,name,email,password,"Default", gender,"Default","Default","Default");

//                    HashMap <String,String> hashMap = new HashMap<>();
//
//                    hashMap.put("ID", userId);
//                    hashMap.put("Name",name);
//                    hashMap.put("Email", email);
//                    hashMap.put("Password", password);
//                    hashMap.put("PhotoURL", "Default");
//                    hashMap.put("Gender", gender);
//                    hashMap.put("Status", "Default");
//                    hashMap.put("Blood Group", "Default");
//                    hashMap.put("Last Donated", "Default");
//                    hashMap.put("Phone", "Default");


                    ref.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()) {
                                Intent intent = new Intent(SignUpActivity.this, ProfileActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                finish();
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });





                }else {

                        if(task.getException() instanceof FirebaseAuthUserCollisionException) {

                            Toast.makeText(getApplicationContext(), "Patient Account Already Registered.", Toast.LENGTH_LONG).show();

                        }else{

                            Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }

                }
            }
        });
    }
    @Override
    public void onClick(View view) {
     switch (view.getId()) {

         case R.id.btn2:

             RegisterUser ();
             break;

         case R.id.TextViewSignIn:

             finish();
             startActivity(new Intent(this, MainActivity.class));
             break;

     }
    }
}

package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    Button buttonSignUp;
    EditText emailAddress, password;
    TextView textViewSignUp;
    RadioGroup radioGroup;
    RadioButton radioButton;
    private FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        buttonSignUp = findViewById(R.id.buttonSignUp);
        emailAddress = findViewById(R.id.emailAddress);
        password = findViewById(R.id.password);
        textViewSignUp = findViewById(R.id.textViewSignUp);
        radioGroup = findViewById(R.id.radioGroup);

        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton)findViewById(radioId);
                auth.createUserWithEmailAndPassword(emailAddress.getText().toString(), password.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (radioButton.getText().toString().equals("Student")) {
                                    Users user = new Users(emailAddress.getText().toString(), password.getText().toString());
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(user);
                                }else{
                                    Teachers guruji = new Teachers(emailAddress.getText().toString(), password.getText().toString());
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Teachers").child(id).setValue(guruji);
                                }
                                if(task.isSuccessful()){

                                    Toast.makeText(SignUpActivity.this, "Sign Up Complete. Please, Sign in to enter", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d("my_tag","galat");
                                }

                            }
                        });
            }
        });
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
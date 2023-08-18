package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {
    Button buttonSignIn;
    EditText emailAddressSignIn, passwordSignIn;
    TextView textViewSignIn;
    RadioGroup radioGroupSignIn;
    RadioButton radioButtonSignIn;
    private FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        buttonSignIn = findViewById(R.id.buttonSignIn);
        emailAddressSignIn = findViewById(R.id.emailAddressSignIn);
        passwordSignIn = findViewById(R.id.passwordSignIn);
        textViewSignIn = findViewById(R.id.textViewSignIn);
        radioGroupSignIn = findViewById(R.id.radioGroupSignIn);


        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();



        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            boolean bool = false;
            @Override
            public void onClick(View view) {
                auth.signInWithEmailAndPassword(emailAddressSignIn.getText().toString(), passwordSignIn.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    int radioId = radioGroupSignIn.getCheckedRadioButtonId();
                                    radioButtonSignIn = (RadioButton)findViewById(radioId);
                                    String id = task.getResult().getUser().getUid();
                                    if (radioButtonSignIn.getText().toString().equals("Student")) {
                                        database.getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.hasChild(id)) {
                                                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }else{
                                                    Toast.makeText(SignInActivity.this, "Not a Student", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                                    }else{
                                        database.getReference().child("Teachers").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.hasChild(id)) {
                                                    Intent intent = new Intent(SignInActivity.this, Teacher.class);
                                                    startActivity(intent);
                                                    finish();
                                                }else{
                                                    Toast.makeText(SignInActivity.this, "Not a Teacher", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                                    }
                                }else {
                                    Toast.makeText(SignInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent2);
                finish();
            }
        });


    }
}
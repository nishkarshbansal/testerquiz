package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView textView, textView2;
    Button next, submit, clear;
    RadioGroup radioGroup1;
    RadioButton option1, option2, option3, option4, radioButton;

    FirebaseDatabase database2;

    private int score = 0;
    private int idx = 1;
    private int saare;
    private String[] ans = new String[1];
    public void setProb(FirebaseDatabase database2, int idx){
        textView2.setText("Q."+idx);
        database2.getReference().child("quizContent").child("question"+idx).child("question").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                textView.setText(task.getResult().getValue().toString( ));
            }
        });
        database2.getReference().child("quizContent").child("question"+idx).child("option1").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                option1.setText(task.getResult().getValue().toString( ));
            }
        });
        database2.getReference().child("quizContent").child("question"+idx).child("option2").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                option2.setText(task.getResult().getValue().toString( ));
            }
        });
        database2.getReference().child("quizContent").child("question"+idx).child("option3").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                option3.setText(task.getResult().getValue().toString( ));
            }
        });
        database2.getReference().child("quizContent").child("question"+idx).child("option4").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                option4.setText(task.getResult().getValue().toString( ));
            }
        });
        database2.getReference().child("quizContent").child("question"+idx).child("answer").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String str = task.getResult().getValue().toString( );
                ans[0] = str;
            }
        });
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        next = findViewById(R.id.button2);
        submit = findViewById(R.id.submit);
        clear = findViewById(R.id.clear);
        radioGroup1 = findViewById(R.id.radioGroup1);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        database2 = FirebaseDatabase.getInstance();
        database2.getReference().child("quizContent").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               int n = (int) snapshot.getChildrenCount();
               saare = n;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        setProb(database2, idx);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioGroup1.getCheckedRadioButtonId() == -1){
                    idx++;
                }else{
                    int radioId = radioGroup1.getCheckedRadioButtonId();
                    radioButton = (RadioButton)findViewById(radioId);
                    if(radioButton.getText().toString().equals(ans[0])){
                        score++;
                    }
                    idx++;
                    radioGroup1.clearCheck();
                }
                if(idx<saare) {

                    setProb(database2, idx);
                }else{
                    Intent intent2 = new Intent(MainActivity.this, LogoutUser.class);
                    intent2.putExtra("scoreDeclaration", score);
                    startActivity(intent2);
                    finish();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioGroup1.getCheckedRadioButtonId() != -1){
                    radioGroup1.clearCheck();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LogoutUser.class);
                intent.putExtra("scoreDeclaration", score);
                startActivity(intent);
                finish();
            }
        });
//          yes.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View view) {
//                  if(idx<questions.length){
//                    if(answers[idx]){
//                        score++;
//                    }}
//                  if(idx<questions.length-1){
//                    idx++;
//                    textView.setText(questions[idx]);
//                  }
//
//             }
//         });
//            no.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(idx<questions.length){
//                    if(answers[idx]==false){
//                        score++;
//                    }}
//                    if(idx<questions.length-1){
//                    idx++;
//                    textView.setText(questions[idx]);
//                    }
//                }
//            });
//
//            submit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    scoreText.setText("Score is "+score);
//                }
//            });


        }



}
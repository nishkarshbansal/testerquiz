package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaTimestamp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class Teacher extends AppCompatActivity {
    private int num = 0;
    EditText editques_t, editop1_t, editop2_t, editop3_t, editop4_t, editans_t;
    TextView question_t, op1_t, op2_t, op3_t, op4_t, answer_t;
    Button save_t, add_t, finish_t;

    FirebaseDatabase database3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        editques_t = findViewById(R.id.editques_t);
        editop1_t = findViewById(R.id.editop1_t);
        editop2_t = findViewById(R.id.editop2_t);
        editop3_t = findViewById(R.id.editop3_t);
        editop4_t = findViewById(R.id.editop4_t);
        editans_t = findViewById(R.id.editans_t);
        question_t = findViewById(R.id.question_t);
        op1_t = findViewById(R.id.op1_t);
        op2_t = findViewById(R.id.op2_t);
        op3_t = findViewById(R.id.op3_t);
        op4_t = findViewById(R.id.op4_t);
        answer_t = findViewById(R.id.answer_t);
        save_t = findViewById(R.id.save_t);
        add_t = findViewById(R.id.add_t);
        finish_t = findViewById(R.id.finish_t);

        database3  = FirebaseDatabase.getInstance();
        quizContent problem = new quizContent("", "", "", "", "", "");
        database3.getReference().child("quizContent").child("question0").setValue(problem );

        save_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = num + 1;
                problem.question = editques_t.getText().toString();
                problem.option1 = editop1_t.getText().toString();
                problem.option2 = editop2_t.getText().toString();
                problem.option3 = editop3_t.getText().toString();
                problem.option4 = editop4_t.getText().toString();
                problem.answer = editans_t.getText().toString();
                database3.getReference().child("quizContent").child("question"+num).setValue(problem );
                Toast.makeText(Teacher.this, "Problem Saved Successfully!", Toast.LENGTH_SHORT).show();
            }
        });
        add_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editques_t.getText().clear();
                editop1_t.getText().clear();
                editop2_t.getText().clear();
                editop3_t.getText().clear();
                editop4_t.getText().clear();
            }
        });
        finish_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Teacher.this, LogoutTeacher.class);
                startActivity(intent3);
                finish();
            }
        });


    }
}
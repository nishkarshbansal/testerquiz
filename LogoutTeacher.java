package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LogoutTeacher extends AppCompatActivity {
    TextView textView;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout_teacher);
        textView = findViewById(R.id.textView4);
        logout = findViewById(R.id.logoutTeacher);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogoutTeacher.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
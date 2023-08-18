package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LogoutUser extends AppCompatActivity {
    TextView textView;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout_user);
        textView = findViewById(R.id.textView3);
        logout = findViewById(R.id.logoutUser);

        int report = getIntent().getExtras().getInt("scoreDeclaration");
        textView.setText("Your Score is "+ report);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogoutUser.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
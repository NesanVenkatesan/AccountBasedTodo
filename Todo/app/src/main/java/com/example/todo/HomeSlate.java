package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeSlate extends AppCompatActivity {

    TextView textviewText;
    Button btnLogOut;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_slate);

        textviewText = findViewById(R.id.text);
        btnLogOut = findViewById(R.id.logout);
        auth = FirebaseAuth.getInstance();

        textviewText.setText(getIntent().getStringExtra("mail"));

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(HomeSlate.this, MainActivity.class));
                finish();
            }
        });
    }
}
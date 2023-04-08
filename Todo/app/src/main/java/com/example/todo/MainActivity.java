package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Button btnLogin;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(MainActivity.this, "Already Logged in", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, HomeSlate.class);
            startActivity(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView signup = findViewById(R.id.signup);
        TextView forgotPw = findViewById(R.id.forgot_pw);

        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(MainActivity.this, signup.class)));
            }
        });

        forgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, forgot_password.class));
            }
        });

       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String username = String.valueOf(editTextUsername.getText());
               String password = String.valueOf(editTextPassword.getText());

               if(TextUtils.isEmpty(username)){
                   Toast.makeText(MainActivity.this,"Enter Username", Toast.LENGTH_SHORT).show();
                   return;
               }

               if(TextUtils.isEmpty(password)){
                   Toast.makeText(MainActivity.this,"Enter password", Toast.LENGTH_SHORT).show();
                   return;
               }

               mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                           // Sign in success, update UI with the signed-in user's information
                           Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                           Intent i = new Intent(MainActivity.this, HomeSlate.class);
                           startActivity(i);
                       }
                       else {
                           // If sign in fails, display a message to the user.
                           Toast.makeText(MainActivity.this, "Authentication failed.",
                                   Toast.LENGTH_SHORT).show();
                       }
                   }
               });
           }
       });


    }
}
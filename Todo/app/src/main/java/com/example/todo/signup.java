package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {

    EditText editTextNewUsername, editTextE_mail, editTextPassword, editTextRetype_password;
    Button editTextSignUp;

    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(signup.this, "Already Logged in",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextNewUsername = findViewById(R.id.new_username);
        editTextE_mail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.new_password);
        editTextRetype_password = findViewById(R.id.re_type_new_password);
        editTextSignUp = findViewById(R.id.signin_button);
        mAuth = FirebaseAuth.getInstance();
        editTextSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newUsername = String.valueOf(editTextNewUsername.getText());
                String E_mail = String.valueOf(editTextE_mail.getText());
                String Password = String.valueOf(editTextPassword.getText());
                String Retype_password = String.valueOf(editTextRetype_password.getText());

                if(TextUtils.isEmpty(newUsername)){
                    Toast.makeText(signup.this,"Enter Username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!Password.equals(Retype_password)){
                    Toast.makeText(signup.this,"Passwords doesn't match, Try Again", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(newUsername, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(signup.this, "Account Created, Welcome!",
                                    Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(signup.this, HomeSlate.class);
                            i.putExtra("mail",E_mail);
                            startActivity(i);
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void goLogin(View view){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
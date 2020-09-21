package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;


public class Login_Form extends AppCompatActivity {

    EditText txtEmail,txtPwd;
    Button btnLogIn;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        getSupportActionBar().setTitle("LOGIN");

        txtEmail = (EditText)findViewById(R.id.email);
        txtPwd = (EditText)findViewById(R.id.pwd);
        btnLogIn = (Button)findViewById(R.id.btnLogIn);

        firebaseAuth = FirebaseAuth.getInstance();



        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email = txtEmail.getText().toString().trim();
                String pwd = txtPwd.getText().toString().trim();


                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(Login_Form.this,"Please Enter Email ID...",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pwd))
                {
                    Toast.makeText(Login_Form.this,"Please Enter Correct Password...",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pwd.length()<6)
                {
                    Toast.makeText(Login_Form.this,"Password Too Short",Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    Toast.makeText(Login_Form.this,"Registration Successful...",Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(Login_Form.this,"LOGIN Failed or User not Available",Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });

            }
        });



    }

    public void btn_signup_Form(View view) {
        startActivity(new Intent(getApplicationContext(),SignUp_Form.class));
    }
}
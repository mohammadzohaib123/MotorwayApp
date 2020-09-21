package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp_Form extends AppCompatActivity {

    EditText txtFirstName, txtLastName, txtEmail, txtPassword, txtConfirmPassword;
    Button btnRegister;
    private FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up__form);
        getSupportActionBar().setTitle("SIGNUP");
        txtFirstName = (EditText) findViewById(R.id.firstName);
        txtLastName = (EditText) findViewById(R.id.lastName);
        txtEmail = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.pwd);
        txtConfirmPassword = (EditText) findViewById(R.id.confirmpwd);
        btnRegister = (Button) findViewById(R.id.btnRegister) ;
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(SignUp_Form.this,"WELCOME",Toast.LENGTH_SHORT);

                //==============================================Intializing Variables==================================
                String firstName = txtFirstName.getText().toString().trim();
                String lastName = txtLastName.getText().toString().trim();
                String email = txtEmail.getText().toString().trim();
                String pwd = txtPassword.getText().toString().trim();
                String confirmPwd = txtConfirmPassword.getText().toString().trim();

                //=====================================================VALIDATIONS=====================================

                if(TextUtils.isEmpty(firstName))
                {
                    Toast.makeText(SignUp_Form.this,"Please Enter First Name...",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(SignUp_Form.this,"Please Enter Email ID...",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pwd))
                {
                    Toast.makeText(SignUp_Form.this,"Please Enter Correct Password...",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmPwd))
                {
                    Toast.makeText(SignUp_Form.this,"Please Enter Confirm Password...",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pwd.length()<6)
                {
                    Toast.makeText(SignUp_Form.this,"Password Too Short",Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                if(pwd.equals(confirmPwd))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(SignUp_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(),Login_Form.class));
                                        Toast.makeText(SignUp_Form.this,"Registration Successful   Now LOGIN...",Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(SignUp_Form.this,"Authentication Failed...",Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });




                }

            }
        });

    }
}
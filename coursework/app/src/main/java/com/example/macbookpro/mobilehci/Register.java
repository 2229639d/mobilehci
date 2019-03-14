package com.example.macbookpro.mobilehci;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");


    private FirebaseAuth firebaseAuth;


    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPassword2;
    private String email = "";
    private String password = "";
    private String password2 = "";
    private Button singupBTN;
    private ImageButton homeBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);


        firebaseAuth = FirebaseAuth.getInstance();
        editTextEmail = (EditText) findViewById(R.id.registerEmail);
        editTextPassword = (EditText) findViewById(R.id.registerPw);
        editTextPassword2 = (EditText) findViewById(R.id.pw2);

        singupBTN= (Button) findViewById(R.id.registerBTN);
        singupBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                singUp(v);
            }
        });

        homeBTN = (ImageButton) findViewById(R.id.homeBTN);
        homeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
    }


    private boolean isValidEmail() {
        if (email.isEmpty()) {
            Toast.makeText(Register.this, "Please check Email ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(Register.this, "Please check Email ", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }


    private boolean isValidPasswd() {
        if (password.isEmpty()) {

            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {

            return false;
        }else if(!password.equals(password2)){

            Toast.makeText(Register.this, "Please check your confirm passwords ", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
    public void singUp(View view) {
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();
        password2 = editTextPassword2.getText().toString();
        if(isValidEmail() && isValidPasswd()) {
            createUser(email, password);
        }
    }

    // 회원가입
    private void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, R.string.success_signup, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this,Login.class);
                            startActivity(intent);
                        } else {

                            Toast.makeText(Register.this, R.string.failed_signup, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }}


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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");


    private FirebaseAuth firebaseAuth;


    private EditText editTextEmail;
    private EditText editTextPassword;

    private String email = "";
    private String password = "";

    private Button singinBTN;

    private Button singupBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);


        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.emailedittxt);
        editTextPassword = findViewById(R.id.pwedittxt);
        singupBTN =  (Button) findViewById(R.id.signupbtn);
        singupBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);

            }
        });
        singinBTN = (Button) findViewById(R.id.signinbtn);
        singinBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn(v);
            }
        });

    }


    public void signIn(View view) {
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();

        if(isValidEmail() && isValidPasswd()) {
            loginUser(email, password);
        }
    }


    private boolean isValidEmail() {
        if (email.isEmpty()) {

            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

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
        } else {
            return true;
        }
    }


    private void loginUser(String email, String password)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(Login.this, R.string.success_login, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this,Profile.class);
                            startActivity(intent);

                        } else {

                            Toast.makeText(Login.this, R.string.failed_login, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
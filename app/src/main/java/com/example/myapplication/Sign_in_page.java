package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Sign_in_page extends AppCompatActivity {

    private EditText user;
    private EditText pass;
    private Button sign_in_login;
    private TextView f_pass;
    FirebaseAuth mAuth;
    ProgressDialog loadingbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        mAuth = FirebaseAuth.getInstance();
        user = (EditText) findViewById(R.id.sign_in_username);
        pass = (EditText) findViewById(R.id.sign_in_password);
        sign_in_login = (Button) findViewById(R.id.sign_in_login_btn);
        f_pass = (TextView) findViewById(R.id.forgot_pass);
        loadingbar = new ProgressDialog(this);



      sign_in_login.setOnClickListener(v -> {


          final String username = user.getText().toString().trim();
          final String password = pass.getText().toString().trim();

          if (username.isEmpty() || password.isEmpty())
          {
              Toast.makeText(Sign_in_page.this, "Data cannot be empty", Toast.LENGTH_SHORT).show();
          }
          else if (password.length() < 8)
          {
              Toast.makeText(Sign_in_page.this, "Password must contain 8 characters", Toast.LENGTH_SHORT).show();
          }
          else
          {
              loadingbar.setTitle("Creating Account");
              loadingbar.setMessage("Creating Account");
              loadingbar.setCanceledOnTouchOutside(false);
              loadingbar.show();
              mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()){
                          loadingbar.dismiss();

                          Toast.makeText(Sign_in_page.this, "Login succesfully", Toast.LENGTH_SHORT).show();
                          Intent intent = new Intent(Sign_in_page.this,Dashboard.class);
                          startActivity(intent);
                      }
                      else{
                          loadingbar.dismiss();

                          Toast.makeText(Sign_in_page.this, "Login failed", Toast.LENGTH_SHORT).show();
                          String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                          if (errorCode.equals("ERROR_INVALID_EMAIL")){
                              Toast.makeText(Sign_in_page.this, "Email format is not correct", Toast.LENGTH_SHORT).show();
                          }
                          else if (errorCode.equals("ERROR_WRONG_PASSWORD")){
                              Toast.makeText(Sign_in_page.this, "Incoorect password", Toast.LENGTH_SHORT).show();
                          }
                          else if (errorCode.equals("ERROR_USER_NOT_FOUND")){
                              Toast.makeText(Sign_in_page.this, "This email does not exist", Toast.LENGTH_SHORT).show();
                          }
                          else {
                              Toast.makeText(Sign_in_page.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                          }


                      }
                  }
              });


          }

      });

//        sign_in_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        f_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), forgot_password.class);
                startActivity(intent);
            }
        });
    }

//    public void login() {
//

//    }
}




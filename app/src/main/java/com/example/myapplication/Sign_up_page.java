package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Sign_up_page extends AppCompatActivity {

    private EditText names, mail, contact, dob, username, password, c_password;
    private TextView createnew;
    FirebaseAuth mAuth;
    ProgressDialog loadingbar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    CustomerInfo customerInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Customer info");
        customerInfo = customerInfo;
        mAuth = FirebaseAuth.getInstance();
        names = findViewById(R.id.name);
        mail = findViewById(R.id.email);
        contact = findViewById(R.id.phone_no);
        dob = findViewById(R.id.DOB);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.confirm_password);
        createnew = findViewById(R.id.create);
        loadingbar = new ProgressDialog(this);

        createnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name_up = names.getText().toString().trim();
                final String mail_up = mail.getText().toString().trim();
                final String contact_up = contact.getText().toString().trim();
                final String dob_up = dob.getText().toString().trim();
                final String username_up = username.getText().toString().trim();
                final String password_up = password.getText().toString().trim();
                final String c_password_up = c_password.getText().toString().trim();

                if (name_up.isEmpty()||mail_up.isEmpty()||contact_up.isEmpty()||dob_up.isEmpty()||username_up.isEmpty()||password_up.isEmpty()||c_password_up.isEmpty()){
                    Toast.makeText(Sign_up_page.this, "Data cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if (!mail_up.endsWith("@gmail.com")) {
                    Toast.makeText(Sign_up_page.this, "Email address is invalid", Toast.LENGTH_SHORT).show();
                }
                else if (contact_up.length()<10) {
                    Toast.makeText(Sign_up_page.this, "Contact must contain 10 digit", Toast.LENGTH_SHORT).show();
                }
                else if (password_up.length()>8) {
                    Toast.makeText(Sign_up_page.this, "Password must contain 8", Toast.LENGTH_SHORT).show();
                }
                else if (!password_up.equals(c_password_up)) {
                    Toast.makeText(Sign_up_page.this, "Passwords must be same", Toast.LENGTH_SHORT).show();
                }
                else {
                    loadingbar.setTitle("Creating Account");
                    loadingbar.setMessage("Creating Account");
                    loadingbar.setCanceledOnTouchOutside(false);
                    loadingbar.show();
                    mAuth.createUserWithEmailAndPassword(mail_up,password_up).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            loadingbar.dismiss();
                            if (task.isSuccessful());{
                                Toast.makeText(Sign_up_page.this, "Regristration succesful", Toast.LENGTH_SHORT).show();
                                databaseReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        customerInfo.setName(name_up);
                                        customerInfo.setMail(mail_up);
                                        customerInfo.setContact(contact_up);
                                        customerInfo.setDob(dob_up);
                                        customerInfo.setUsername(username_up);
                                        databaseReference.setValue(customerInfo);
                                        Toast.makeText(Sign_up_page.this, "Data added", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Sign_up_page.this, Dashboard.class);
                                        startActivity(intent);
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(Sign_up_page.this, "Failed to add data", Toast.LENGTH_SHORT).show();
                                    }

                                });
                            }

                        }
                    });

                }
            }
        });
    }
}
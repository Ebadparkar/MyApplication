package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    TextView username;
    TextView name;
    TextView mail;
    TextView phone;
    TextView dob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        username = findViewById(R.id.user);
        name = findViewById(R.id.name_d);
        mail = findViewById(R.id.mail);
        phone = findViewById(R.id.contact);
        dob = findViewById(R.id.dob_dashborad);

        String user_d = getIntent().getStringExtra("username");
        String name_d = getIntent().getStringExtra("name");
        String mail_d = getIntent().getStringExtra("mail");
        String contact_d = getIntent().getStringExtra("contact");
        String dob_d = getIntent().getStringExtra("dob");

        username.setText(user_d);
        name.setText(name_d);
        mail.setText(mail_d);
        phone.setText(contact_d);
        dob.setText(dob_d);


    }
}
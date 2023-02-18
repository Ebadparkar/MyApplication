package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class forgot_password extends AppCompatActivity {

    EditText mail_phone;
    TextView get_otp;
    EditText enter_OTP;
    TextView n_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mail_phone = findViewById(R.id.enter_email_phone);
        get_otp = findViewById(R.id.send_otp);
        enter_OTP = findViewById(R.id.enter_otp);
        n_pass  = findViewById(R.id.new_password);

        get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(forgot_password.this, "OTP send succesfully", Toast.LENGTH_SHORT).show();
            }
        });
        enter_OTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Reset_password.class);
                startActivity(intent);
            }
        });
        n_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Reset_password.class);
                startActivity(intent);
            }
        });
    }
}
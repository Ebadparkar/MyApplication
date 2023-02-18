package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Reset_password extends AppCompatActivity {

    EditText new_password;
    EditText c_new_password;
    TextView reset_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        new_password = findViewById(R.id.new_pass);
        c_new_password = findViewById(R.id.c_new_pass);
        reset_password = findViewById(R.id.pass_reset);

        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Reset_password.this, "Password resetted succesfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
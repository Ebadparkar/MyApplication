package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button sign_in;
    Button sign_up;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign_in = findViewById(R.id.main_sign_in);
        sign_up = findViewById(R.id.main_sign_up);

        sign_in.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Sign in Button Clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(v.getContext(),Sign_in_page.class);
            startActivity(intent);
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Sign Up Button Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(),Sign_up_page.class);
                startActivity(intent);
            }
        });


    }
}
package com.example.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Paymenttype extends AppCompatActivity {

    Button button;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymenttype);

        button =findViewById(R.id.btnCon);
        btn = findViewById(R.id.btnCC);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), Card_details_MainActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in  = new Intent(getApplicationContext(), PayOnDeliveryMainActivity.class);
                startActivity(in);
            }
        });

    }
}
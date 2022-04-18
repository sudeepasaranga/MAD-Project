package com.example.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

public class activity_main_checkout extends AppCompatActivity {

    private TextView name, code, phone, province, city, zone, address;
    private Button button;
    private  Button btn;

    private DBhandler dbHandler;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_checkout2);


        button = findViewById(R.id.btnChange);
        btn = findViewById(R.id.btnProceed);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i  = new Intent(getApplicationContext(), ListViewActivity.class);

                startActivity(i);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent  = new Intent(getApplicationContext(), Paymenttype.class);

                startActivity(intent);
            }
        });



        context = this;
        dbHandler = new DBhandler(context);



        name = findViewById(R.id.editform8);
        code = findViewById(R.id.editform9);
        phone = findViewById(R.id.editform10);
        province = findViewById(R.id.editform11);
        city = findViewById(R.id.editform12);
        zone = findViewById(R.id.editform13);
        address = findViewById(R.id.editform14);


        final String id = getIntent().getStringExtra("id");
        CheckoutModel checkoutmodel = dbHandler.getSingleCheckoutModel(Integer.parseInt(id));

        name.setText(checkoutmodel.getName());
        code.setText(checkoutmodel.getCode());
        phone.setText(checkoutmodel.getPhone());
        province.setText(checkoutmodel.getProvince());
        city.setText(checkoutmodel.getCity());
        zone.setText(checkoutmodel.getZone());
        address.setText(checkoutmodel.getAddress());




    }
}
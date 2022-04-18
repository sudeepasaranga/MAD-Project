package com.example.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePageActivity extends AppCompatActivity {

    private EditText name, code, phone, province, city, zone, address;
    private Button edit;
    private DBhandler dbHandler;
    private Context context;
    private Long updateDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);


        context = this;
        dbHandler = new DBhandler(context);

        name = findViewById(R.id.editform1);
        code = findViewById(R.id.editform2);
        phone = findViewById(R.id.editform3);
        province = findViewById(R.id.editform4);
        city = findViewById(R.id.editform5);
        zone = findViewById(R.id.editform6);
        address = findViewById(R.id.editform7);

        edit = findViewById(R.id.btnCon);

        final String id = getIntent().getStringExtra("id");
        CheckoutModel checkoutmodel = dbHandler.getSingleCheckoutModel(Integer.parseInt(id));

        name.setText(checkoutmodel.getName());
        code.setText(checkoutmodel.getCode());
        phone.setText(checkoutmodel.getPhone());
        province.setText(checkoutmodel.getProvince());
        city.setText(checkoutmodel.getCity());
        zone.setText(checkoutmodel.getZone());
        address.setText(checkoutmodel.getAddress());


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameText = name.getText().toString();
                String codeText = code.getText().toString();
                String phoneText = phone.getText().toString();
                String provinceText = province.getText().toString();
                String cityText = city.getText().toString();
                String zoneText = zone.getText().toString();
                String addressText = address.getText().toString();
                updateDate = System.currentTimeMillis();

                CheckoutModel checkoutModel = new CheckoutModel(Integer.parseInt(id),nameText,codeText,phoneText,provinceText,cityText,zoneText,addressText, updateDate,0);
                int state = dbHandler.updateSingleCheckoutModel(checkoutModel);
                System.out.println(state);

                Toast.makeText(context, "Update Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context,ListViewActivity.class));
            }
        });

    }
}
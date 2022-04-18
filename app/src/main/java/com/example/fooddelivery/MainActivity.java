 package com.example.fooddelivery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

 public class MainActivity extends AppCompatActivity {

     //Initialize Variable

     private EditText name, code, phone, province, city, zone, address;
     private Button add;
     private  DBhandler dbHandler;
     private Context context;

     AwesomeValidation awesomeValidation;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);




         //Assign variables

         name = findViewById(R.id.editText1);
         code = findViewById(R.id.editform2);
         phone = findViewById(R.id.editform3);
         province = findViewById(R.id.editform4);
         city = findViewById(R.id.editform5);
         zone = findViewById(R.id.editform6);
         address = findViewById(R.id.editform7);

         //intialize Validation Style

          awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


         //add validation for name
         awesomeValidation.addValidation(this,R.id.editText1,
                 RegexTemplate.NOT_EMPTY,R.string.invalid_name);

         //add validation for Code

         awesomeValidation.addValidation(this,R.id.editform2,
                 "[1-9]{2}$",R.string.invalid_code);

             //add validation for phone
         awesomeValidation.addValidation(this,R.id.editform3,
               "[0-9]{3}[0-9]{7}$",R.string.invalid_mobile);

         awesomeValidation.addValidation(this,R.id.editform4,
                 RegexTemplate.NOT_EMPTY,R.string.invalid_province);

         awesomeValidation.addValidation(this,R.id.editform5,
                 RegexTemplate.NOT_EMPTY,R.string.invalid_city);

         awesomeValidation.addValidation(this,R.id.editform6,
                 RegexTemplate.NOT_EMPTY,R.string.invalid_zone);


         awesomeValidation.addValidation(this,R.id.editform7,
                 RegexTemplate.NOT_EMPTY,R.string.invalid_address);






         add = findViewById(R.id.btnAdd);
         context = this;
         dbHandler = new DBhandler(context);


         add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {


                 String userName = name.getText().toString();
                 String userCode = code.getText().toString();
                 String userPhone = phone.getText().toString();
                 String userProvince = province.getText().toString();
                 String userCity = city.getText().toString();
                 String userZone = zone.getText().toString();
                 String userAddress = address.getText().toString();
                 long started = System.currentTimeMillis();

                 //catch saved data
                 CheckoutModel checkoutModel = new  CheckoutModel(userName,userCode,userPhone,userProvince,userCity,userZone,userAddress,started,0);
                 dbHandler.addCheckout(checkoutModel );


                 if(awesomeValidation.validate()){
                     //On Success
                     Toast.makeText(getApplicationContext()
                             ,"Form Validate Successfully..",Toast.LENGTH_SHORT).show();

                     Toast.makeText(context, "Details Added Successfully", Toast.LENGTH_SHORT).show();

                     startActivity(new Intent(context,ListViewActivity.class));


                 }else {
                     Toast.makeText(getApplicationContext()
                             ,"Validation Faild", Toast.LENGTH_SHORT).show();
                 }




             }
         });
     }


 }





  /* @Override
  protected void onStart() {
     super.onStart();
       Log.i(TAG,"onStart");
    }*/

   // @Override
  //  protected void onRestart() {
     //   super.onRestart();
     //   Log.i(TAG,"onRestart");
  //  }

  //  @Override
  //  protected void onPause() {
  //      super.onPause();
  //      Log.i(TAG,"onPause");
 //   }

  //  @Override
 //   protected void onResume() {
   //     super.onResume();
    //    Log.i(TAG,"onResume");
   // }

   // @Override
  //  protected void onStop() {
   //     super.onStop();
  //      Log.i(TAG,"onStop");
  //  }

  //  @Override
  //  protected void onDestroy() {
  //      super.onDestroy();
 //      Log.i(TAG,"onDestroy");
  //  }

  //  @Override
  //  protected void onRestoreInstanceState(Bundle savedInstanceState) {
  //      super.onRestoreInstanceState(savedInstanceState);
  //      Log.i(TAG,"onRestoreInstanceState");
  //  }

  //  @Override
 //   protected void onSaveInstanceState(Bundle outState) {
      //  super.onSaveInstanceState(outState);

   //     Log.i(TAG,"onSaveInstanceState");
   // }


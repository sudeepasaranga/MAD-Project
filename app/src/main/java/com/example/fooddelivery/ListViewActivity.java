package com.example.fooddelivery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private Button add;
    private  ListView listView;
    private TextView count;
    Context context;
    private  DBhandler dbHandler;
    private List<CheckoutModel> checks;


   /*  String name[] ={"Sahan Perara", "Kamal Perera", "Sunil Perera", "Sahan Silva","Savindi Silva", "Kamal Nimal"};
    String code[] ={"+94", " +94", " +94", " +94", "+94"," +94 "};
    String phone[] ={" 0778989789", "0778989878 ", "0778989878 ", "0778989878","0778989878 ", "0778989878 "};
    String province[] ={" South", "South ", " South", " South", " South"," South"};
    String city[] ={"Matara", " Colombo", "Homagama", "Matara ", " Colombo","Matara"};
    String zone[] ={" South", " South", " South", " South", "South ", "West"};
    String address[] ={" MAtaraa", "Nupe Matara", " Perera MAwatha", "Sahan Mawatha", "Dasa awraa","Kamal Matraa"};


    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        context = this;

        dbHandler = new DBhandler(context);
        add = findViewById(R.id.add);
        count = findViewById(R.id.count);
        listView = findViewById(R.id.listview);
        checks = new ArrayList<>();


        checks = dbHandler.getAll();
        checkAdapter adapter = new checkAdapter(context,R.layout.single,checks);
        listView.setAdapter(adapter);

        //get row counts from the table
        int countD = dbHandler.countD();
        count.setText(+countD+" addresses");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(context,MainActivity.class));
            }
        });

     /*   go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = D.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Contact :"+res.getString(1)+"\n");
                    buffer.append("Date of Birth :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }}


      */

//    CustomeAdapter adapter = new CustomeAdapter(this,name, code,phone,province,city,zone,address);
     //   listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final CheckoutModel checkoutmodel = checks.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(checkoutmodel.getName());
                builder.setMessage(checkoutmodel.getAddress());
                builder.setPositiveButton("SELECT", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkoutmodel.setFinished(System.currentTimeMillis());
                        dbHandler.updateSingleCheckoutModel(checkoutmodel);
                        Intent intent = new Intent(context,activity_main_checkout.class);
                        intent.putExtra("id",String.valueOf(checkoutmodel.getId()));
                        Toast.makeText(context, "Selected", Toast.LENGTH_SHORT).show();
                        startActivity(intent);


                    }
                });


                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deleteCheckoutModel(checkoutmodel.getId());
                        Toast.makeText(context, "Delete Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context,ListViewActivity.class));
                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context,UpdatePageActivity.class);
                        intent.putExtra("id",String.valueOf(checkoutmodel.getId()));
                        startActivity(intent);
                    }
                });


                builder.show();
            }
        });


    }
}
/*class CustomeAdapter extends  ArrayAdapter<String>{


    Context context;
    String[] name;
    String[] code;
    String[] phone;
    String[] province;
    String[] city;
    String[] zone;
    String[] address;



    CustomeAdapter(Context context, String[] name, String[] code, String[] phone, String[] province, String[] city, String[] zone, String[] address){

        super(context,R.layout.single, R.id.name,name);
        this.context = context;
        this.name = name;
        this.code = code;
        this.phone = phone;
        this.province = province;
        this.city = city;
        this.zone = zone;
        this.address = address;




    }

 */

   /* @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

         View row = inflater.inflate(R.layout.single,parent,false);


        TextView nameView = row.findViewById(R.id.name);
        TextView codeView= row.findViewById(R.id.code);
        TextView phoneView = row.findViewById(R.id.phone);
        TextView provinceView = row.findViewById(R.id.province);
        TextView cityView = row.findViewById(R.id.city);
        TextView zoneView = row.findViewById(R.id.zone);
        TextView addressView = row.findViewById(R.id.address);

        nameView.setText(name[position]);
        codeView.setText(code[position]);
        phoneView.setText(phone[position]);
        provinceView.setText(province[position]);
        cityView.setText(city[position]);
        zoneView.setText(zone[position]);
        addressView.setText(address[position]);

        return row;


    }

    */

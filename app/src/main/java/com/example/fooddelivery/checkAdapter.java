package com.example.fooddelivery;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class checkAdapter extends ArrayAdapter<CheckoutModel>{

    private Context context;
    private int resource;
    List<CheckoutModel> checks;

    checkAdapter(Context context, int resource, List<CheckoutModel> checks){
        super(context,resource,checks);
        this.context = context;
        this.resource = resource;
        this.checks = checks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView name = row.findViewById(R.id.name);
        TextView code = row.findViewById(R.id.code);
        TextView phone = row.findViewById(R.id.phone);
        TextView province = row.findViewById(R.id.province);
        TextView city = row.findViewById(R.id.city);
        TextView zone = row.findViewById(R.id.zone);
        TextView address = row.findViewById(R.id.address);
      //  ImageView imageView = row.findViewById(R.id.imageView2);

        // checks [obj1,obj2,obj3]
        CheckoutModel checkoutModel = checks.get(position);

        name.setText(checkoutModel.getName());
        code.setText(checkoutModel.getCode());
        phone.setText(checkoutModel.getPhone());
        province.setText(checkoutModel.getProvince());
        city.setText(checkoutModel.getCity());
        zone.setText(checkoutModel.getZone());
        address.setText(checkoutModel.getAddress());
      //  imageView.setVisibility(View.VISIBLE);


        return row;
    }
}


package com.example.fooddelivery;

import android.content.ContentValues;
import  android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBhandler extends SQLiteOpenHelper {

    private static final int VERSION = 3;
    private static final String DB_NAME = "food";
    private static final String TABLE_NAME = "checkout";

    //column names
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CODE = "code";
    private static final String PHONE = "phone";
    private static final String PROVINCE = "province";
    private static final String CITY = "city";
    private static final String  ZONE = "zone";
    private static final String ADDRESS = "address";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";

    public DBhandler(@Nullable Context context) {
        super(context,DB_NAME , null, VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY = " CREATE TABLE "+TABLE_NAME+" "+
                "("
                +ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NAME+ " TEXT,"
                +CODE+ " TEXT,"
                +PHONE+ " TEXT,"
                +PROVINCE+ " TEXT,"
                +CITY+ " TEXT,"
                +ZONE+ " TEXT,"
                +ADDRESS+ " TEXT,"
                +STARTED+ " TEXT,"
                +FINISHED+ " TEXT" +
                ");";

          //MAKE A TABLE

       /* CREATE TABLE checkout(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, code TEXT, phone TEXT, province TEXT, city TEXT, zone TEXT, address TEXT, started TEXT, finished TEXT);*/

        db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion , int newVersion) {

        String DROP_TABLE_QUERY = " DROP TABLE IF EXISTS " + TABLE_NAME;
        //drop older table if existed
        db.execSQL(DROP_TABLE_QUERY);
        //create table again
        onCreate(db);

    }

    //insert row
    public void addCheckout( CheckoutModel checkoutModel){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME,checkoutModel.getName());
        contentValues.put(CODE,checkoutModel.getCode());
        contentValues.put(PHONE,checkoutModel.getPhone());
        contentValues.put(PROVINCE,checkoutModel.getProvince());
        contentValues.put(CITY,checkoutModel.getCity());
        contentValues.put(ZONE,checkoutModel.getZone());
        contentValues.put(ADDRESS,checkoutModel.getAddress());
        contentValues.put(STARTED,checkoutModel.getStarted());
        contentValues.put(FINISHED,checkoutModel.getFinished());

        //save to table
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        //close database
        sqLiteDatabase.close();

    }

    // Count checkout table records
    public int countD(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    // Get all addresses into a list
    public List<CheckoutModel> getAll(){

        List<CheckoutModel> checks = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                // Create new  object
                CheckoutModel checkoutModel = new CheckoutModel();
                // mmgby6hh
                checkoutModel.setId(cursor.getInt(0));
                checkoutModel.setName(cursor.getString(1));
                checkoutModel.setCode(cursor.getString(2));
                checkoutModel.setPhone(cursor.getString(3));
                checkoutModel.setProvince(cursor.getString(4));
                checkoutModel.setCity(cursor.getString(5));
                checkoutModel.setZone(cursor.getString(6));
                checkoutModel.setAddress(cursor.getString(7));
                checkoutModel.setStarted(cursor.getLong(8));
                checkoutModel.setFinished(cursor.getLong(9));

                // [obj,objs,asas,asa]
                checks.add(checkoutModel);
            }while (cursor.moveToNext());
        }
        return checks;
    }

    // Delete item
    public void deleteCheckoutModel(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,"id =?",new String[]{String.valueOf(id)});
        db.close();
    }

    // Get a single one
    public CheckoutModel getSingleCheckoutModel(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{ID,  NAME, CODE ,PHONE, PROVINCE, CITY, ZONE, ADDRESS, STARTED, FINISHED},
                ID + "= ?",new String[]{String.valueOf(id)}
                ,null,null,null);

        CheckoutModel checkoutModel;
        if(cursor != null){
            cursor.moveToFirst();
            checkoutModel = new CheckoutModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getLong(8),
                    cursor.getLong(9)
            );
            return checkoutModel;
        }
        return null;
    }

    // Update
    public int updateSingleCheckoutModel(CheckoutModel checkoutModel){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME,checkoutModel.getName());
        contentValues.put(CODE,checkoutModel.getCode());
        contentValues.put(PHONE,checkoutModel.getPhone());
        contentValues.put(PROVINCE,checkoutModel.getProvince());
        contentValues.put(CITY,checkoutModel.getCity());
        contentValues.put(ZONE,checkoutModel.getZone());
        contentValues.put(ADDRESS,checkoutModel.getAddress());

        contentValues.put(STARTED,checkoutModel.getStarted());
        contentValues.put(FINISHED,checkoutModel.getFinished());

        int status = db.update(TABLE_NAME,contentValues,ID +" =?",
                new String[]{String.valueOf(checkoutModel.getId())});

        db.close();
        return status;
    }

}

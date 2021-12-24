package com.example.database.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.database.model.Contact;
import com.example.database.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = " CREATE TABLE " + Params.TABLE_NAME + " ("
                + Params.KEY_ID + " INTEGER PRIMARY KEY, " + Params.KEY_NAME
                + " TEXT, " + Params.KEY_PHONE + " TEXT " + ") ";
        Log.d("myDB", "created" + create);
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + Params.TABLE_NAME);
        onCreate(db);

    }

    // to add contact
    public void addContact(String name, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(Params.KEY_NAME, name);
        value.put(Params.KEY_PHONE, phoneNumber);

        db.insert(Params.TABLE_NAME, null, value);
        Log.d("myAddContact", "contact added");
    }

    //to retrive contact from database
//    public List<Contact> getAllContact(){
//        List<Contact> contactList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String storeData = "SELECT * FROM " + Params.TABLE_NAME;
//        Cursor cursor = db.rawQuery(storeData,null);
//
//        if (cursor.moveToFirst()){
//            do{
//                Contact contact = new Contact();
//                contact.setId(Integer.parseInt(cursor.getString(0)));
//                contact.setName(cursor.getString(1));
//                contact.setPhoneNumber(cursor.getString(2));
//                contactList.add(contact);
//            }while (cursor.moveToNext());
//        }
//        return contactList;
//    }

    //to retrive contact from database
    public Cursor readAllData() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + Params.TABLE_NAME;

        Cursor cursor = null;

        if (db != null) {
            cursor = db.rawQuery(query, null);

        }
        return cursor;
    }

}

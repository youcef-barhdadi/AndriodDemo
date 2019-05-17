package com.thor.table;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class DBConnection extends SQLiteOpenHelper {

    // Database Info
    private static final String DATABASE_NAME = "postsDatabase";
    private static final int DATABASE_VERSION = 1;



    // For Singleton Pattren
    private static DBConnection sInstance;


    public  static  synchronized DBConnection getDBConnection(Context context){

        if(sInstance == null){
            return  new DBConnection(context);
        }

        return  sInstance;

    }




    private  DBConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists admin (id integer primary key,name text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop  table if  exists admin");
        onCreate(db);

    }
    public  void  InsertRowAdmin(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);


        db.insert("admin",null,values);

    }



    public  void  Delete(long ID){
        String query ="delete from admin where id = "+ID;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);


    }


    public ArrayList<Admin> GetAllAdmin(){
        String  qurey = "Select * from admin";
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(qurey,null);
        ArrayList<Admin> array = new ArrayList<Admin>();
        if(cursor.moveToNext()){

            do{

                Admin admin = new Admin();
                admin.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                admin.setName(cursor.getString(cursor.getColumnIndex("name")));
                array.add(admin);



            }while (cursor.moveToNext());


        }




        return  array;
    }
}

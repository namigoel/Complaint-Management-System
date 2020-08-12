package com.example.complaintregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;


public  class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "people.db";
    public static final String TABLE_NAME = "people_table";
    public static final String COL0 = "id";
    public static final String COL1 = "name";
    public static final String COL2 = "email";
    public static final String COL3 = "password";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COL0 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL1 + " TEXT,"
                + COL2 + " VARCHAR," + COL3 + " TEXT" + ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public int addData(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, email);
        contentValues.put(COL3, password);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return 0;
        } else {
            return 1;
        }
    }

    public int checkAlreadyExist(String email) {
        String[] columns = {
                COL0
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL2 + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return 1;
        }

        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)

    public User getUser(String emailId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String name= null;
        String query = "SELECT*FROM " + TABLE_NAME + " where " + COL2 + " = "+"'"+emailId+"'" ;
        Cursor cursor=db.rawQuery(query,null);
        User user=new User();
        if(cursor.moveToFirst()){

            user.setName(cursor.getString(1));
        }
        cursor.close();
        db.close();
        return user;




    }
}



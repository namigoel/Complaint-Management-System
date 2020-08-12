package com.example.complaintregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DATABASE_1 = "status.db";
    public static final String TABLE_1 = "status_table";
    public static final String col0 = "department";
    public static final String col1 = "category";
    public static final String col3 = "description";
    public static final String col5 = "emailId";
    public static final String col4 = "id";
    public static final String col6 = "status";

    public DBhelper(Context context) {
        super(context, DATABASE_1, null, 3);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_1 + "("
                + col4 + " INTEGER PRIMARY KEY AUTOINCREMENT," + col0 + " TEXT,"
                + col1 + " VARCHAR," + col3 + " TEXT," + col5 + " VARCHAR," + col6 + " VARCHAR" + ")";
        db.execSQL(createTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_1);
        onCreate(db);

    }

    public int adddata(String Department, String category, String description, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col5, email);
        contentValues.put(col0, Department);
        contentValues.put(col1, category);
        contentValues.put(col3, description);
        contentValues.put(col6, "Yet to open");

        long result = db.insert(TABLE_1, null, contentValues);
        if (result == -1)
            return 0;
        else
            return 1;
    }

    public Cursor getListContents(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM status_table WHERE emailId = '" + email + "'", null);
        return data;
    }

    public Cursor getListContents1() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_1, null);
        return data;
    }

    public Cursor getListContents2(String email, String dept, String cat, String des) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT *FROM " +
                        TABLE_1 + " WHERE " + col5 + " = ? AND " + col0 +
                        " = ? AND  " + col1 + " = ? AND " + col3 + " = ?",
                new String[]{email, dept, cat, des});
        return data;
    }

    public int addstatus( String value,String email,String dept,String des,String cate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col6, value);
        db.update(TABLE_1, cv, col5 + "= ? AND "+col1 + "= ? AND "+col3+ "= ? AND "+col0+"= ?", new String[] {email,cate,des,dept});
        return 1;

    }


}

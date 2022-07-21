package com.example.testapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;
import java.sql.SQLException;


public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "Student.db";
    public final static String TABLE_NAME = "student_table";
    public final static String TABLE_NAME1 = "student_table1";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";
    public static final String COL_5 = "IMAGE";
    public static final String COL_6 = "TAG";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME1+"(ID INTEGER PRIMARY KEY ,IMAGE BLOB NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY ,NAME TEXT,SURNAME TEXT,MARKS INTEGER,IMAGE BLOB NOT NULL, TAG INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public DatabaseHelper open() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public boolean insertData(String id, String name, String surname, String marks, byte[] imageBytes, String tag) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        ContentValues cv2 = new ContentValues();
        cv.put(COL_1, id);
        cv.put(COL_2, name);
        cv.put(COL_3, surname);
        cv.put(COL_4, marks);
        cv.put(COL_5, imageBytes);
        cv.put(COL_6, tag);

        long result = db.insert(TABLE_NAME, null, cv);
        //cv2.put(COL_1, id);
        //cv2.put(COL_5, imageBytes);
        //long result1 = db.insert(TABLE_NAME1, null, cv2);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT *FROM " + TABLE_NAME + " WHERE TAG='" + id + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Cursor getData1(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT ID,NAME,SURNAME,MARKS FROM " + TABLE_NAME + " WHERE TAG='" + id + "'";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public boolean updateData(String id, String name, String surname, String marks, byte[] imageBytes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, marks);
        contentValues.put(COL_5, imageBytes);
        db.update(TABLE_NAME, contentValues, "ID=?", new String[]{id});
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    public byte[] retreiveImageFromDB(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT *FROM " + TABLE_NAME + " WHERE TAG='" + id + "'";
        Cursor cur = db.rawQuery(query, null);
        if (cur.moveToFirst()) {
            @SuppressLint("Range") byte[] blob = cur.getBlob(cur.getColumnIndex(COL_5));
            cur.close();
            return blob;
        }
        cur.close();
        return null;
    }
}

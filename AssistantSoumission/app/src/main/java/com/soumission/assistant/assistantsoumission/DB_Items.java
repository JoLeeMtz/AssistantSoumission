package com.soumission.assistant.assistantsoumission;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 201327549 on 2016-10-21.
 */

public class DB_Items extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // DataBase name
    private static final String DB_NAME = "SoumissionsItems";

    // Table name
    private static final String TABLE = "Materiaux";

    // Columns in TABLE
    private static final String ID = "ID";
    private static final String ITEM = "Item";
    private static final String PRICE = "Prix";
    private static final String DATE_UPDATE = "MAJ";


    // CONSTRUCTORS
    public DB_Items(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }
    public DB_Items(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DB_Items(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    // Drop the table if it already exists
    public void dropTableMateriaux(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
    }
    // Create the table
    public void createTableMateriaux(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE
                           + "("
                           + ID + " INTEGER PRIMARY KEY, "
                           + ITEM + " VARCHAR(40), "
                           + PRICE + " NUMERIC, "
                           + DATE_UPDATE + " VARCHAR(40) "
                           + ")";

        db.execSQL(createTable);
    }


    // Get one item
    public Items getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE,
                new String[] { ID, ITEM, PRICE, DATE_UPDATE }, ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null) cursor.moveToFirst();

        Items item = new Items(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));

        return item;
    }
    // List the table Materiaux
    public List<Items> getListItems() {
        List<Items> listItem = new ArrayList<Items>();

        String select = "SELECT " + ID + ", " + ITEM + ", " + PRICE + ", " + DATE_UPDATE
                      + " FROM " + TABLE
                      + " ORDER BY " + ITEM;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);

        // Move to first row
        if (cursor.moveToFirst()) {
            do {
                Items item = new Items();
                item.set_id(Integer.parseInt(cursor.getString(0)));
                item.set_nameItem(cursor.getString(1));
                item.set_price(cursor.getString(2));
                item.set_MAJ(cursor.getString(3));

                listItem.add(item);
            } while (cursor.moveToNext());
        }

        // return list of items
        return listItem;
    }
    // Get count of items (number of items)
    public int getItemsCount() {
        String totalItems = "SELECT * FROM " + TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(totalItems, null);
        cursor.close();

        return cursor.getCount();
    }
    // Add an item
    public void addItem(Items item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();

        val.put(ITEM, item.get_nameItem());
        val.put(PRICE, item.get_price());
        val.put(DATE_UPDATE, item.get_MAJ());

        // Insert new item
        db.insert(TABLE, null, val);
        db.close();
    }
    // Update an item
    public int updateItem(Items item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();

        val.put(ITEM, item.get_nameItem());
        val.put(PRICE, item.get_price());
        val.put(DATE_UPDATE, item.get_MAJ());

        return db.update(TABLE,
                         val,
                         ID + " = ?",
                         new String[] { String.valueOf(item.get_id()) });
    }
    // Delete an item
    public void deleteItem(Items item) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE, ID + " = ?", new String[] { String.valueOf(item.get_id()) });
        db.close();
    }


    // Override section
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableMateriaux(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table
        dropTableMateriaux(db);
        // Create new one
        onCreate(db);
    }
}

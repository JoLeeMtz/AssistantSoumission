package com.soumission.assistant.assistantsoumission;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joaquin on 2016-11-02.
 */

public class DB_Clients extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // DataBase name
    private static final String DB_NAME = "SoumissionsClients";

    // Table name
    private static final String TABLE = "Clients";

    // Columns in TABLE
    private static final String ID = "ID";
    private static final String NOM = "Nom";
    private static final String PRENOM = "Prenom";
    private static final String VILLE = "Ville";
    private static final String CODE_POSTAL = "Code_Postal";
    private static final String ADRESSE = "Adresse";
    private static final String TELEPHONE = "Telephone";
    private static final String CELL = "Cell";
    private static final String COURRIEL = "Courriel";


    // CONSTRUCTORS
    public DB_Clients(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }
    public DB_Clients(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DB_Clients(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
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
                + NOM + " VARCHAR(40), "
                + PRENOM + " VARCHAR(40), "
                + VILLE + " VARCHAR(40), "
                + CODE_POSTAL + " VARCHAR(7), "
                + ADRESSE + " VARCHAR(40), "
                + TELEPHONE + " VARCHAR(11), "
                + CELL + " VARCHAR(11), "
                + COURRIEL + " VARCHAR(40) "
                + ")";

        db.execSQL(createTable);
    }


    // Get one client
    public Clients getClient(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE,
                new String[] { ID, NOM, PRENOM, VILLE, CODE_POSTAL, ADRESSE, TELEPHONE, CELL, COURRIEL}, ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null) cursor.moveToFirst();

        Clients client = new Clients(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8));

        return client;
    }
    // List the table Clients
    public List<Clients> getListClients() {
        List<Clients> listClients = new ArrayList<Clients>();

        String select = "SELECT * FROM " + TABLE + " ORDER BY " + NOM;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);

        // Move to first row
        if (cursor.moveToFirst()) {
            do {
                Clients client = new Clients();
                client.set_id(Integer.parseInt(cursor.getString(0)));
                client.set_nom(cursor.getString(1));
                client.set_prenom(cursor.getString(2));
                client.set_ville(cursor.getString(3));
                client.set_codePostal(cursor.getString(4));
                client.set_adresse(cursor.getString(5));
                client.set_telephone(cursor.getString(6));
                client.set_cell(cursor.getString(7));
                client.set_courriel(cursor.getString(8));

                listClients.add(client);
            } while (cursor.moveToNext());
        }

        // return list of items
        return listClients;
    }
    // Get count of clients (number of clients)
    public int getClientsCount() {
        String totalClients = "SELECT * FROM " + TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(totalClients, null);
        cursor.close();

        return cursor.getCount();
    }


    // CRUD
    // Add a client
    public void addClient(Clients client) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();

        val.put(NOM, client.get_nom());
        val.put(PRENOM, client.get_prenom());
        val.put(VILLE, client.get_ville());
        val.put(CODE_POSTAL, client.get_codePostal());
        val.put(ADRESSE, client.get_adresse());
        val.put(TELEPHONE, client.get_telephone());
        val.put(CELL, client.get_cell());
        val.put(COURRIEL, client.get_courriel());

        // Insert new item
        db.insert(TABLE, null, val);
        db.close();
    }
    // Update a client
    public int updateClient(Clients client) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();

        val.put(ID, client.get_id());
        val.put(NOM, client.get_nom());
        val.put(PRENOM, client.get_prenom());
        val.put(VILLE, client.get_ville());
        val.put(CODE_POSTAL, client.get_codePostal());
        val.put(ADRESSE, client.get_adresse());
        val.put(TELEPHONE, client.get_telephone());
        val.put(CELL, client.get_cell());
        val.put(COURRIEL, client.get_courriel());

        return db.update(TABLE,
                val,
                ID + " = ?",
                new String[] { String.valueOf(client.get_id()) });
    }
    // Delete a client
    public void deleteClient(Clients client) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE, ID + " = ?", new String[] { String.valueOf(client.get_id()) });
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
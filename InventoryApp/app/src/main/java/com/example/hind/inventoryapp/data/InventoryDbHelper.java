package com.example.hind.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hind.inventoryapp.data.InventoryContract.inventoryEntry;

public class InventoryDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "stock.db";
    private static final int DATABASE_VERSION = 4;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_INVENTORY_TABLE  = "CREATE TABLE " + inventoryEntry.TABLE_NAME + " ("
                + inventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + inventoryEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + inventoryEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL, "
                + inventoryEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL DEFAULT 0, "
                + inventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME + " TEXT NOT NULL, "
                + inventoryEntry.COLUMN_PRODUCT_SUPPLIER_NUMBER + " INTEGER NOT NULL);";
        //execute Sql statement
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + inventoryEntry.TABLE_NAME);
        onCreate(db);
    }
}

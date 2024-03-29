package com.example.hind.inventoryapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.hind.inventoryapp.data.InventoryContract.inventoryEntry;


public class InventoryProvider extends ContentProvider {
    /** Tag for the log messages */
    public static final String LOG_TAG = InventoryProvider.class.getSimpleName();

    private static final int INVENTORY = 100;

    private static final int INVENTORY_ID = 101;

    /** Database helper that will provide us access to the database */
    private InventoryDbHelper mDbHelper;


    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY, INVENTORY);
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY + "/#", INVENTORY_ID);

    }

    /**
     * Initialize the provider and the database helper object.
     */
    @Override
    public boolean onCreate() {
        mDbHelper = new InventoryDbHelper(getContext());
        return true;
    }

    /**
     * Perform the query for the given URI. Use the given projection, selection, selection arguments, and sort order.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // Get readable database
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                // For the INVENTORY code, query the inventory table directly with the given
                // projection, selection, selection arguments, and sort order. The cursor
                // could contain multiple rows of the inventory table.
                cursor = database.query(inventoryEntry.TABLE_NAME, projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case INVENTORY_ID:
                // For the INVENTORY_ID code, extract out the ID from the URI.
                //
                // For every "?" in the selection, we need to have an element in the selection
                // arguments that will fill in the "?". Since we have 1 question mark in the
                // selection, we have 1 String in the selection arguments' String array.
                selection = inventoryEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                // Cursor containing that row of the table.
                cursor = database.query(inventoryEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        // Set notification URI on the Cursor,
        // so we know what content URI the Cursor was created for.
        // If the data at this URI changes, then we know we need to update the Cursor.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    /**
     * Insert new data into the provider with the given ContentValues.
     */
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return insertInventory(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    /**
     * Insert a inventory into the database with the given content values. Return the new content URI
     * for that specific row in the database.
     */
    private Uri insertInventory(Uri uri, ContentValues values) {
        // Check that the name is not null
        String name = values.getAsString(inventoryEntry.COLUMN_PRODUCT_NAME);
        if (name == null) {
            throw new IllegalArgumentException("inventory requires a name");
        }

        // If the price is provided, check that it's greater than or equal to 0 kg
        Integer price = values.getAsInteger(inventoryEntry.COLUMN_PRODUCT_PRICE);
        if (price != null && price < 0) {
            throw new IllegalArgumentException("inventory requires valid price");
        }

        // If the quantity is provided, check that it's greater than or equal to 0 kg
        Integer quantity = values.getAsInteger(inventoryEntry.COLUMN_PRODUCT_QUANTITY);
        if (quantity != null && quantity < 0) {
            throw new IllegalArgumentException("inventory requires valid quantity");
        }

        // Check that the Supplier name is not null
        String supplierName = values.getAsString(inventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
        if (supplierName == null) {
            throw new IllegalArgumentException("inventory requires a Supplier name");
        }

        // Check that the Supplier name is not null
        Integer supplierNumber = values.getAsInteger(inventoryEntry.COLUMN_PRODUCT_SUPPLIER_NUMBER);
        if (supplierNumber == null && supplierNumber < 0) {
            throw new IllegalArgumentException("inventory requires a Supplier name");
        }


        // Get writeable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Insert the new inventory with the given values
        long id = database.insert(inventoryEntry.TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Notify all listeners that the data has changed for the product content URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);
    }

    /**
     * Updates the data at the given selection and selection arguments, with the new ContentValues.
     */
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return updateInventory(uri, contentValues, selection, selectionArgs);
            case INVENTORY_ID:
                // For the INVENTORY_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = inventoryEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateInventory(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    /**
     * Update inventory in the database with the given content values. Apply the changes to the rows
     * specified in the selection and selection arguments (which could be 0 or 1 or more product).
     * Return the number of rows that were successfully updated.
     */
    private int updateInventory(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // If the {@link inventoryEntry#COLUMN_PRODUCT_NAME} key is present,
        // check that the name value is not null.
        if (values.containsKey(inventoryEntry.COLUMN_PRODUCT_NAME)) {
            String name = values.getAsString(inventoryEntry.COLUMN_PRODUCT_NAME);
            if (name == null) {
                throw new IllegalArgumentException("Product requires a name");
            }
        }

        // If the {@link inventoryEntry#COLUMN_PRODUCT_PRICE} key is present,
        // check that the price value is valid.
        if (values.containsKey(inventoryEntry.COLUMN_PRODUCT_PRICE)) {
            // Check that the price is greater than or equal to 0 kg
            Integer price = values.getAsInteger(inventoryEntry.COLUMN_PRODUCT_PRICE);
            if (price != null && price < 0) {
                throw new IllegalArgumentException("Product requires valid price");
            }
        }

        // If the {@link inventoryEntry#COLUMN_PRODUCT_QUANTITY} key is present,
        // check that the quantity value is valid.
        if (values.containsKey(inventoryEntry.COLUMN_PRODUCT_QUANTITY)) {
            // Check that the quantity is greater than or equal to 0 kg
            Integer quantity = values.getAsInteger(inventoryEntry.COLUMN_PRODUCT_QUANTITY);
            if (quantity != null && quantity < 0) {
                throw new IllegalArgumentException("Product requires valid Quantity");
            }
        }

        // If the {@link inventoryEntry#COLUMN_PRODUCT_SUPPLIER_NAME} key is present,
        // check that the supplierName value is not null.
        if (values.containsKey(inventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME)) {
            String supplierName = values.getAsString(inventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
            if (supplierName == null) {
                throw new IllegalArgumentException("Product requires a Supplier Name");
            }
        }

        // If the {@link inventoryEntry#COLUMN_PRODUCT_SUPPLIER_NUMBER} key is present,
        // check that the supplierNumber value is valid.
        if (values.containsKey(inventoryEntry.COLUMN_PRODUCT_SUPPLIER_NUMBER)) {
            // Check that the supplierNumber is greater than or equal to 0 kg
            Integer supplierNumber = values.getAsInteger(inventoryEntry.COLUMN_PRODUCT_SUPPLIER_NUMBER);
            if (supplierNumber != null && supplierNumber < 0) {
                throw new IllegalArgumentException("Product requires valid Supplier Number");
            }
        }

        // If there are no values to update, then don't try to update the database
        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }
        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Perform the update on the database and get the number of rows affected
        int rowsUpdated = database.update(inventoryEntry.TABLE_NAME, values, selection, selectionArgs);

        // If 1 or more rows were updated, then notify all listeners that the data at the
        // given URI has changed
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows updated
        return rowsUpdated;
    }

    /**
     * Delete the data at the given selection and selection arguments.
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writeable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Track the number of rows that were deleted
        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                // Delete all rows that match the selection and selection args
                rowsDeleted = database.delete(inventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case INVENTORY_ID:
                // Delete a single row given by the ID in the URI
                selection = inventoryEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = database.delete(inventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        // If 1 or more rows were deleted, then notify all listeners that the data at the
        // given URI has changed
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    /**
     * Returns the MIME type of data for the content URI.
     */
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return inventoryEntry.CONTENT_LIST_TYPE;
            case INVENTORY_ID:
                return inventoryEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}

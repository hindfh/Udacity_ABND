package com.example.hind.inventoryapp;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hind.inventoryapp.data.InventoryContract;
import com.example.hind.inventoryapp.data.InventoryContract.inventoryEntry;

public class InventoryCursorAdapter extends CursorAdapter{
    /**
     * Constructs a new {@link InventoryCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the inventory data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current product can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(final View view, final Context context, Cursor cursor) {

        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        Button saleButton = view.findViewById(R.id.sale_button);
        final TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);

        // Find the columns of pet attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(inventoryEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(inventoryEntry.COLUMN_PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(inventoryEntry.COLUMN_PRODUCT_QUANTITY);

        // Read the pet attributes from the Cursor for the current product
        String productName = cursor.getString(nameColumnIndex);
        int productPrice = cursor.getInt(priceColumnIndex);
        final int quantity = cursor.getInt(quantityColumnIndex);

        final long  id = cursor.getLong(cursor.getColumnIndex(InventoryContract.inventoryEntry._ID));

        saleButton=view.findViewById(R.id.sale_button);
        saleButton.setFocusable(false);
        saleButton.setFocusableInTouchMode(false);
        saleButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                ContentResolver resolver = context.getContentResolver();
                ContentValues values = new ContentValues();

                if(quantity>0){
                    Uri currentUri = ContentUris.withAppendedId(inventoryEntry.CONTENT_URI,id);
                    int currentQuantity = quantity;
                    currentQuantity--;
                    values.put(inventoryEntry.COLUMN_PRODUCT_QUANTITY,currentQuantity);
                    resolver.update(currentUri,values,null,null);
                    context.getContentResolver().notifyChange(currentUri,null);
                } else {
                    Toast.makeText(view.getContext(), "Sold out!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Update the TextViews with the attributes for the current product
        nameTextView.setText(productName);
        priceTextView.setText("Price: " + productPrice + "$");
        quantityTextView.setText("Quantity " + quantity);
    }
}

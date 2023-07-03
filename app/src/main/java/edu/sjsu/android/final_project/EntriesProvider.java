package edu.sjsu.android.final_project;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class EntriesProvider extends ContentProvider {
    private EntriesDB db;
    private final static String AUTHORITY = "edu.sjsu.android.final_project";
    public final static Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY); //Used in the main function to insert into the database.

    // getContentResolver().insert(LocationsProvider.CONTENT_URI,values); - in main activity to enter values to database.
    /*public LocationProvider() {
        db = new LocationDB(getContext());
    }

     */

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        return db.deleteAll();

        // TODO - Implement Delete by calling delete in LocationsDb class - Refer exercise
        //throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        long inserted = db.insert(values);
        if (inserted > 0){
            Uri _uri = ContentUris.withAppendedId(uri, inserted);
            getContext().getContentResolver().notifyChange(_uri,null);
            return _uri;
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        db = new EntriesDB(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        return db.getAllLocations();

        //throw new UnsupportedOperationException("Not yet implemented");
    }
    // no nedd to implement -update
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
    // no need to implement - gettype
    @Override
    public String getType(Uri uri) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
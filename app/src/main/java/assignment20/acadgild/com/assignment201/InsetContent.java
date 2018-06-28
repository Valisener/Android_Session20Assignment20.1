/*package assignment20.acadgild.com.assignment201;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class InsetContent extends ContentProvider {
    private SQLiteDatabase db;
    String name = "Dustin";
    String phoneNumber = "555-555-5555";

    @Override
    public boolean onCreate() {
//        ArrayList <ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
//
//        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
//                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
//                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
//                .build());

        // Inser an empty contact.
//        ContentValues contentValues = new ContentValues();
//        Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, contentValues);
//// Get the newly created contact raw id.
//        long ret = ContentUris.parseId(rawContactUri);




        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
*/
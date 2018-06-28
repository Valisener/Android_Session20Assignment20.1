package assignment20.acadgild.com.assignment201;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MatrixCursor matrixCursor;
    private SimpleCursorAdapter simpleCursorAdapter;
    private ListView listView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        matrixCursor = new MatrixCursor(new String[]{"_id", "name", "phone"});
        textView = findViewById(R.id.textview);
        //create new class insertcontacttask
        InsertContactTask insertContactTask = new InsertContactTask();
        //execute the asynk task that inserts the new contact
        insertContactTask.execute();

    }
    //class that handles inserting the contact information
    class InsertContactTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
//            //create a uri that will have the data for content
//            Uri uri = ContactsContract.Contacts.CONTENT_URI;
//            //create new content resolver and get current resolver
//            ContentResolver contentResolver = getContentResolver();
//            //create new cursor that loads all the context
//            Cursor cursor = contentResolver.query(uri, null, null, null, null, null);
//            ContactsContract.Contacts.
//            if (cursor != null) {
                ArrayList<ContentProviderOperation> contentProviderOperations = new ArrayList<>();
                int rawContactInsertIndex = contentProviderOperations.size();

                contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_NAME,null )
                        .build());
                contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, "93X-XXXXXXXXX")
                        .build());
                contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "Dustin")
                        .build());
                try {
                    getContentResolver().applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
                    textView.setText("Contact Successfully Added");
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (OperationApplicationException e) {
                    e.printStackTrace();
                }
//            }
            return null;
        }
    }
}




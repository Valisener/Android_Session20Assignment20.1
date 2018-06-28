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
    //initialize the textview variable
    private TextView textView;

    private String success = "Adding Contract Successful";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set text view
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
            //create arraylist of content provider operation
                ArrayList<ContentProviderOperation> contentProviderOperations = new ArrayList<>();
                //get the contentprovideroperations size
                int rawContactInsertIndex = contentProviderOperations.size();
            //perform beginning operations
                contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_NAME,null )
                        .build());
                //Add the phone number operation
                contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, "540-555-55555")
                        .build());
                //Add the display name operation
                contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "Dustin44")
                        .build());
                try {
                    //Do the batch operations if possible
                    getContentResolver().applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
                    //set the text to success if it was a success
                    textView.setText(success);
                    //catch in case it fails
                } catch (RemoteException e) {
                    e.printStackTrace();
                    //catch in case it fails
                } catch (OperationApplicationException e) {
                    e.printStackTrace();
                }
            //return null it is required
            return null;
        }
    }
}
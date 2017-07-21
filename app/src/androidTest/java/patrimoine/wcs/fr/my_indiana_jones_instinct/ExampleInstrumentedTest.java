package patrimoine.wcs.fr.my_indiana_jones_instinct;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.icu.text.MessagePattern.ArgType.SELECT;
import static android.support.test.espresso.core.deps.guava.net.HttpHeaders.FROM;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("patrimoine.wcs.fr.my_indiana_jones_instinct", appContext.getPackageName());
    }

    @Test
    public void databaseTestUser() throws Exception {

        // Gets the data repository in write mode
        DbHelper dbHelper = new DbHelper(InstrumentationRegistry.getTargetContext());
        SQLiteDatabase dbS = dbHelper.getWritableDatabase();
        dbHelper.resetDatabase(dbS);

        //User database test
        ContentValues user = new ContentValues();
        user.put(DatabaseContract.UserEntry.COLUMN_NAME_NAME, "Indiana Jones");
        user.put(DatabaseContract.UserEntry.COLUMN_NAME_EMAIL, "Indiana@gmail.com");


        long newUserId = dbS.insert(DatabaseContract.UserEntry.TABLE_NAME, null, user);
        assertNotEquals(-1, newUserId);

        for (int i = 0; i < 10; i++) {
            ContentValues tweet = new ContentValues();
            tweet.put(DatabaseContract.TweetEntry.COLUMN_NAME_CONTENT, "content" + i);
            tweet.put(DatabaseContract.TweetEntry.COLUMN_NAME_USER_ID, (int) newUserId);
            long newTweetId = dbS.insert(DatabaseContract.TweetEntry.TABLE_NAME, null, tweet);
            assertNotEquals(-1, newTweetId);

        }

    }

    @Test
    public void databaseTestOrganization() throws Exception {

        // Gets the data repository in write mode
        DbHelper dbHelper = new DbHelper(InstrumentationRegistry.getTargetContext());
        SQLiteDatabase dbS = dbHelper.getWritableDatabase();
        //Org database test
        ContentValues organization = new ContentValues();
        organization.put(DatabaseContract.OrganizationEntry.COLUMN_NAME_NAME, "redhead has no soul");
        organization.put(DatabaseContract.OrganizationEntry.COLUMN_NAME_WEBSITE_URL, "Forcedumal666.com");
        long newOrganizationId = dbS.insert(DatabaseContract.OrganizationEntry.TABLE_NAME, null, organization);
        assertNotEquals(-1, newOrganizationId);

        for (int i = 0; i < 10; i++) {
            ContentValues user = new ContentValues();
            organization.put(DatabaseContract.UserEntry.COLUMN_NAME_NAME, "Satanas" + i);
            organization.put(DatabaseContract.UserEntry.COLUMN_NAME_ID, (int) newOrganizationId);
            long newUserId = dbS.insert(DatabaseContract.UserEntry.TABLE_NAME, null, user);
            assertNotEquals(-1, newUserId);

        }
    }

   @Test
    public void databaseReadable() throws Exception {

        DbHelper dbHelper = new DbHelper(InstrumentationRegistry.getTargetContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DatabaseContract.TweetEntry._ID,
                DatabaseContract.TweetEntry.COLUMN_NAME_ID,
                DatabaseContract.TweetEntry.COLUMN_NAME_CONTENT
        };

        // Filter results WHERE "title" = 'My Title'

        // How you want the results sorted in the resulting Cursor

        Cursor cursor = db.query(
                DatabaseContract.SQL_CREATE_TWEET,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );




       cursor.toString().trim();
   }
   public void selectDatabase(){

        SELECT* FROM newUserId;

    }




}




package around.expire.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * LocalDBHelper
 *
 * Create local phone database via sqlite
 *
 * @author Davide
 * @version 0.0.1
 */

public class LocalDBHelper extends SQLiteOpenHelper {

    public static final String ITEM_TABLE_NAME = "items";
    public static final String ITEM_COLUMN_ID = "id";
    public static final String ITEM_COLUMN_NAME = "name";
    public static final String ITEM_COLUMN_INSERTION_DATE = "insertionDate";
    public static final String ITEM_COLUMN_EXPIRE_DATE = "expireDate";

    private static final String DATABASE_NAME = "AroundExpireLocal.db";
    private static final int DATABASE_VERSION = 1;

    //database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + ITEM_TABLE_NAME + "( " + ITEM_COLUMN_ID
            + " integer primary key autoincrement, " + ITEM_COLUMN_NAME
            + " text not null, " + ITEM_COLUMN_INSERTION_DATE
            + " date, " + ITEM_COLUMN_EXPIRE_DATE
            + " date);";

    /**
     * Class Constructor
     *
     * @param context context
     */
    public LocalDBHelper (Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * onCreate Override
     *
     * @param db db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE);

    }

    /**
     * onUpgrade Override
     *
     * @param database      database
     * @param oldVersion    oldversion
     * @param newVersion    newversion
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

        Log.w(LocalDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", wich will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
        onCreate(database);

    }

}

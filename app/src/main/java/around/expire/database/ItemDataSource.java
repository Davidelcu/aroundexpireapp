package around.expire.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;



/**
 * @author Davide
 * @version 0.0.1
 */

public class ItemDataSource {

    private SQLiteDatabase database;
    private LocalDBHelper dbHelper;
    private String[] allColumns = { LocalDBHelper.ITEM_COLUMN_ID, LocalDBHelper.ITEM_COLUMN_NAME,
            LocalDBHelper.ITEM_COLUMN_INSERTION_DATE, LocalDBHelper.ITEM_COLUMN_EXPIRE_DATE };

    public ItemDataSource(Context context) {
        dbHelper = new LocalDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /**
     * createItem
     *
     * create new item in database
     *
     * @param name name
     * @param expirationDate expirationDate
     * @return newItem
     */
    public Item createItem(String name, Integer insertionDate, Integer expirationDate) {
        ContentValues values = new ContentValues();
        values.put(LocalDBHelper.ITEM_COLUMN_NAME, name);
        values.put(LocalDBHelper.ITEM_COLUMN_INSERTION_DATE, insertionDate);
        values.put(LocalDBHelper.ITEM_COLUMN_EXPIRE_DATE, expirationDate);
        long insterId = database.insert(LocalDBHelper.ITEM_TABLE_NAME, null, values);
        Cursor cursor = database.query(LocalDBHelper.ITEM_TABLE_NAME, allColumns, LocalDBHelper.ITEM_COLUMN_ID + " = " + insterId, null, null, null, null);
        cursor.moveToFirst();
        Item newItem = cursorToItem(cursor);
        cursor.close();
        return newItem;
    }

    /**
     * deleteItem
     *
     * delete item by id
     *
     * @param item item
     */
    public void deleteItem(Item item) {
        long id = item.getId();
        System.out.println("Item with id: " + id + " deleted");
        database.delete(LocalDBHelper.ITEM_TABLE_NAME, LocalDBHelper.ITEM_COLUMN_ID + " = " + id, null);
    }

    /**
     * updateItem
     *
     * update database item and return true
     *
     * @param id id
     * @param name name
     * @param expirationDate expiration date
     * @return true
     */
    public boolean updateItem(Long id, String name, Integer expirationDate) {
        ContentValues values = new ContentValues();
        values.put(LocalDBHelper.ITEM_COLUMN_NAME, name);
        values.put(LocalDBHelper.ITEM_COLUMN_EXPIRE_DATE, expirationDate);
        database.update(LocalDBHelper.ITEM_TABLE_NAME, values, "id = ?", new String[] {Long.toString(id)});
        return true;
    }

    /**
     * getAllItems
     *
     * get all the item created and put theme in a List
     *
     * @return items
     */
    public List<Item> getAllItem() {
        List<Item> items = new ArrayList<>();

        Cursor cursor = database.query(LocalDBHelper.ITEM_TABLE_NAME, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Item item = cursorToItem(cursor);
            items.add(item);
            cursor.moveToNext();
        }

        cursor.close();
        return items;

    }

    /**
     * cursorToItem
     *
     * point data and set it
     * return item setted
     * @param cursor cursor
     * @return item
     */
    private Item cursorToItem(Cursor cursor) {
        Item item = new Item();
        item.setId(cursor.getLong(0));
        item.setName(cursor.getString(1));
        item.setInsertionDate(cursor.getInt(2));
        item.setExpireDate((cursor.getInt(3)));
        return item;
    }
}

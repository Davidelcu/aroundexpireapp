package around.expire;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.List;

import around.expire.database.Item;
import around.expire.database.ItemDataSource;

/**
 * MainActiviry Class
 * @author Davide
 * @version 0.0.1
 */
public class MainActivity extends AppCompatActivity {

    SwipeMenuListView listView;
    FloatingActionButton fab;
    private ItemDataSource itemData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get second activity
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAddActivity();
            }
        });

        //get data from database and store in listview
        listView = findViewById(R.id.swipeListViewItem);
        slideList();

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Item item;
                switch (index) {
                    case 0:

                        break;
                    case 1:
                        if(position >= 0) {
                            item = (Item) listView.getItemAtPosition(position);
                            itemData.deleteItem(item);
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "Item delete at: " + position, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        break;
                }
                return false;
            }
        });

        itemData = new ItemDataSource(this);
        itemData.open();

        List<Item> values = itemData.getAllItem();
        ArrayAdapter<Item> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
    }


    private void callAddActivity() {
        Intent intent = new Intent(this, addActivity.class);
        startActivity(intent);
    }

    public void slideList() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem editItem = new SwipeMenuItem(getApplicationContext());
                editItem.setBackground(new ColorDrawable(Color.rgb(255,255,153)));
                editItem.setWidth(250);
                editItem.setTitle("Edit");
                editItem.setTitleSize(18);
                editItem.setTitleColor(Color.BLACK);
                menu.addMenuItem(editItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(204,0,0)));
                deleteItem.setWidth(250);
                deleteItem.setTitle("Delete");
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.BLACK);
                menu.addMenuItem(deleteItem);
            }
        };

        listView.setMenuCreator(creator);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onResume() {
        itemData.open();
        super.onResume();
    }

    protected void onPause() {
        itemData.close();
        super.onPause();
    }
}

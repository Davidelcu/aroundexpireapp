package around.expire;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import around.expire.database.Item;
import around.expire.database.ItemDataSource;

/**
 * MainActiviry Class
 * @author Davide
 * @version 0.0.1
 */
public class MainActivity extends AppCompatActivity {

    ListView listView;
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
        listView = findViewById(R.id.listViewItem);

        itemData = new ItemDataSource(this);
        itemData.open();

        List<Item> values = itemData.getAllItem();
        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);

    }


    private void callAddActivity() {
        Intent intent = new Intent(this, addActivity.class);
        startActivity(intent);
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

package around.expire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import around.expire.database.ItemDataSource;

/**
 * AddActivity Class
 * @author Davide
 * @version 0.0.1
 */
public class addActivity extends AppCompatActivity {

    Button saveButton;
    EditText addTextItem, addDateItem;
    private ItemDataSource itemData;
    /**
     * onCreate
     *
     * @param savedInstanceState saveInstamceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        saveButton = findViewById(R.id.addSaveButton);
        addTextItem = findViewById(R.id.editItemText);
        addDateItem = findViewById(R.id.editDateText);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToMain();
            }
        });

    }

    /**
     * SaveToMain
     *
     * get the data and save it into the database
     */
    private void saveToMain() {
        String itemName = addTextItem.getText().toString();
        Date today = new Date();
        Integer insertionDate = today.getDate();
        if(itemName != null) {
            itemData = new ItemDataSource(this);
            itemData.open();
            itemData.createItem(itemName, insertionDate, insertionDate);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Non hai inserito correttamente i campi", Toast.LENGTH_SHORT).show();
        }


    }
}

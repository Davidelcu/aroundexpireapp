package around.expire;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import around.expire.database.ItemDataSource;

/**
 * AddActivity Class
 * @author Davide
 * @version 0.0.1
 */
public class addActivity extends AppCompatActivity {

    Button saveButton, datePickerButton;
    EditText addTextItem, addDateItem;
    private ItemDataSource itemData;
    protected int pickYear;
    protected int pickMonth;
    protected int pickDay;

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
        datePickerButton = findViewById(R.id.getDatePicker);
        addDateItem = findViewById(R.id.editDateText);


        addDateItem.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override @SuppressWarnings("deprecation")
            public void onClick(View view) {
                showDialog(999);
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToMain();
            }
        });

    }


    protected DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            pickDay = dayOfMonth;
            pickMonth = month;
            pickYear = year;
            updateAddDateItemView();
        }
    };

    protected void updateAddDateItemView() {
        addDateItem.setText(
                new StringBuilder()
                        .append(pickDay).append("-").append(pickMonth + 1).append("-").append(pickYear));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            final Calendar calendar = Calendar.getInstance();
            pickDay = calendar.get(Calendar.DAY_OF_MONTH);
            pickMonth = calendar.get(Calendar.MONTH);
            pickYear = calendar.get(Calendar.YEAR);
            return new DatePickerDialog(this, datePickerListener, pickYear, pickMonth, pickDay);
        }
        return null;
    }

    /**
     * SaveToMain
     *
     * get the data and save it into the database
     */
    private void saveToMain() {
        String itemName = addTextItem.getText().toString();

        String expirationDate = addDateItem.getText().toString();

        if(!itemName.isEmpty() && !expirationDate.isEmpty()) {
            itemData = new ItemDataSource(this);
            itemData.open();
            itemData.createItem(itemName, expirationDate);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Non hai inserito correttamente i campi", Toast.LENGTH_SHORT).show();
        }


    }
}

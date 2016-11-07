package com.soumission.assistant.assistantsoumission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Page_ModifyItem extends AppCompatActivity {
    // Item selected
    private Items _item = new Items();

    // EditText for items
    private EditText name;
    private EditText price;
    // Modify
    private Button modify;

    // Constructor
    public Page_ModifyItem() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_modify_item);

        initVar();
        // Show <- (backButton) on the top left corner (equivalent back button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = (EditText)findViewById(R.id.editText);
        price = (EditText)findViewById(R.id.editText2);
        modify = (Button)findViewById(R.id.modify);

        // Limit the price to 10 digits maximum including 2 digits after the point
        price.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(10,2)});

        init_EditText();
    }
    // Make the <- go back
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // Initialise variables
    public void initVar() {
        _item.set_id(getIntent().getExtras().getInt("id"));
        _item.set_nameItem(getIntent().getExtras().getString("item"));
        _item.set_price(getIntent().getExtras().getString("price"));
        _item.set_MAJ(getIntent().getExtras().getString("maj"));
    }
    // Initialise EditText
    public void init_EditText() {
        name.setText(_item.get_nameItem());
        price.setText(_item.get_price());
    }
    // Get modification on edit text
    public void updateChanges(){
        SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");
        String newDateStr = postFormater.format(new Date());

        _item.set_nameItem(String.valueOf(name.getText()));
        _item.set_price(String.valueOf(price.getText()));
        _item.set_MAJ(newDateStr);
    }


    // Modify item in DB SQLite
    public void modifyItem(View view) {
        updateChanges();

        // Update the item
        new DB_Items(getApplicationContext()).updateItem(_item);

        // Return in list
        finish();
    }
    // Delete item in DB SQLite
    public void deleteItem(View view) {
        // Delete the item
        new DB_Items(getApplicationContext()).deleteItem(_item);

        // Return in list
        finish();
    }
}

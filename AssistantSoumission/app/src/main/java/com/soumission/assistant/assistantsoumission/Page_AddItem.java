package com.soumission.assistant.assistantsoumission;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Page_AddItem extends AppCompatActivity {
    // Item selected
    private Items _item = new Items();

    // EditText for items
    private EditText name;
    private EditText price;
    // Add
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_add_item);


        // Show <- (backButton) on the top left corner (equivalent back button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = (EditText)findViewById(R.id.editText);
        price = (EditText)findViewById(R.id.editText2);

        // Limit the price to 10 digits maximum including 2 digits after the point
        price.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(10,2)});
    }


    // Give values to _item
    public void init_Vars() {
        SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");
        String newDateStr = postFormater.format(new Date());


        _item.set_nameItem(name.getText().toString());
        _item.set_price(price.getText().toString());
        _item.set_MAJ(newDateStr);
    }


    // Add item to DB SQLite
    public void addItem(View view) {
        init_Vars();
        new DB_Items(getApplicationContext()).addItem(_item);

        finish();
    }
}

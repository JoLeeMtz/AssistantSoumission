package com.soumission.assistant.assistantsoumission;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Page_AddClient extends AppCompatActivity {
    // Client selected
    private Clients _client = new Clients();

    // EditText for clients
    private EditText name;
    private EditText firstname;
    private EditText city;
    private EditText zipcode;
    private EditText adress;
    private EditText phone;
    private EditText cell;
    private EditText email;

    // Add
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_add_client);


        // Show <- (backButton) on the top left corner (equivalent back button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = (EditText)findViewById(R.id.editText);
        firstname = (EditText)findViewById(R.id.editText2);
        city = (EditText)findViewById(R.id.editText3);
        zipcode = (EditText)findViewById(R.id.editText4);
        adress = (EditText)findViewById(R.id.editText5);
        phone = (EditText)findViewById(R.id.editText6);
        //PhoneNumberUtils.formatNumber(phone.getText().toString());
        cell = (EditText)findViewById(R.id.editText7);
        //PhoneNumberUtils.formatNumber(cell.getText().toString());
        email = (EditText)findViewById(R.id.editText8);
        add = (Button)findViewById(R.id.add);

        zipcode.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
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


    // Give values to _client
    public void init_Vars() {
        _client.set_nom(name.getText().toString());
        _client.set_prenom(firstname.getText().toString());
        _client.set_ville(city.getText().toString());
        _client.set_codePostal(zipcode.getText().toString());
        _client.set_adresse(adress.getText().toString());
        _client.set_telephone(phone.getText().toString());
        _client.set_cell(cell.getText().toString());
        _client.set_courriel(email.getText().toString());
    }


    // Add item to DB SQLite
    public void addClient(View view) {
        init_Vars();
        new DB_Clients(getApplicationContext()).addClient(_client);

        finish();
    }
}

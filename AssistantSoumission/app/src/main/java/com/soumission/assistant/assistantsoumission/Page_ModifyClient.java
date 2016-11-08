package com.soumission.assistant.assistantsoumission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Page_ModifyClient extends AppCompatActivity {
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

    // Modify
    private Button modify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_modify_client);

        initVar();
        // Show <- (backButton) on the top left corner (equivalent back button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = (EditText)findViewById(R.id.editText);
        firstname = (EditText)findViewById(R.id.editText2);
        city = (EditText)findViewById(R.id.editText3);
        zipcode = (EditText)findViewById(R.id.editText4);
        adress = (EditText)findViewById(R.id.editText5);
        phone = (EditText)findViewById(R.id.editText6);
        cell = (EditText)findViewById(R.id.editText7);
        email = (EditText)findViewById(R.id.editText8);
        modify = (Button)findViewById(R.id.modify);

        InputFilter[] filters = {new InputFilter.LengthFilter(6), new InputFilter.AllCaps()};
        zipcode.setFilters(filters);

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
        _client.set_id(getIntent().getExtras().getInt("id"));
        _client.set_nom(getIntent().getExtras().getString("nom"));
        _client.set_prenom(getIntent().getExtras().getString("prenom"));
        _client.set_ville(getIntent().getExtras().getString("ville"));
        _client.set_codePostal(getIntent().getExtras().getString("code_postal"));
        _client.set_adresse(getIntent().getExtras().getString("adresse"));
        _client.set_telephone(getIntent().getExtras().getString("telephone"));
        _client.set_cell(getIntent().getExtras().getString("cell"));
        _client.set_courriel(getIntent().getExtras().getString("courriel"));
    }
    // Initialise EditText
    public void init_EditText() {
        name.setText(_client.get_nom());
        firstname.setText(_client.get_prenom());
        city.setText(_client.get_ville());
        zipcode.setText(_client.get_codePostal());
        adress.setText(_client.get_adresse());
        phone.setText(_client.get_telephone());
        cell.setText(_client.get_cell());
        email.setText(_client.get_courriel());
    }
    // Get modification on edit text
    public void updateChanges(){
        _client.set_nom(String.valueOf(name.getText()));
        _client.set_prenom(String.valueOf(firstname.getText()));
        _client.set_ville(String.valueOf(city.getText()));
        _client.set_codePostal(String.valueOf(zipcode.getText()));
        _client.set_adresse(String.valueOf(adress.getText()));
        _client.set_telephone(String.valueOf(phone.getText()));
        _client.set_cell(String.valueOf(cell.getText()));
        _client.set_courriel(String.valueOf(email.getText()));
    }


    // Modify item in DB SQLite
    public void modifyItem(View view) {
        updateChanges();

        // Update the item
        new DB_Clients(getApplicationContext()).updateClient(_client);

        setResult(GererClientsFragment.REQUEST_ADD, new Intent().putExtra("refresh", true));

        // Return in list
        finish();
    }
    // Delete item in DB SQLite
    public void deleteItem(View view) {
        // Delete the item
        new DB_Clients(getApplicationContext()).deleteClient(_client);

        setResult(GererClientsFragment.REQUEST_ADD, new Intent().putExtra("refresh", true));

        // Return in list
        finish();
    }
}

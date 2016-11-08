package com.soumission.assistant.assistantsoumission;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class GererClientsFragment extends Fragment {
    private RecyclerView mListClients;
    private MyClientsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static final int REQUEST_ADD = 1;


    public GererClientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vue = inflater.inflate(R.layout.fragment_gerer_clients, container, false);

        mListClients = (RecyclerView)vue.findViewById(R.id.list_items);

        // Icon +
        FloatingActionButton fab = (FloatingActionButton)vue.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent add_Activity = new Intent(getContext(), Page_AddClient.class);

                getContext().startActivity(add_Activity);
                //startActivityForResult(add_Activity, REQUEST_ADD);
            }
        });


        //new DB_Clients(getContext()).addClient(new Clients(1, "Bob", "Sexy", "PedoStyle", "j7e8r9", "Rue Pedo", "11111111111", "", "callMe_69@hotmail.com"));
        //new DB_Clients(getContext()).addClient(new Clients(2, "Robert", "LaVille", "PedoStyle", "j7f8r1", "Rue Sexy Girls", "11122233344", "12345678910", "CallMeRobert_ThaChamp@gmail.com"));
        //new DB_Clients(getContext()).addClient(new Clients(3, "Jeff", "My Name", "SomeWhere", "h1r2a2", "Rue Pas De Tes Affaires", "69696969690", "911", "MyName_isJeff@yahoo.com"));

        init_RecyclerView();

        // Inflate the layout for this fragment
        return vue;
    }
    @Override
    public void onResume() {
        super.onResume();
        init_RecyclerView();
        mAdapter.notifyDataSetChanged();
    }


    private void init_RecyclerView() {
        // Improve performance if changes in content do
        // not change the layout size of RecyclerView
        mListClients.setHasFixedSize(true);

        // Initialise linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mListClients.setLayoutManager(mLayoutManager);

        // Initialise adapter
        mAdapter = new MyClientsAdapter(new DB_Clients(getContext()).getListClients(), getActivity());
        //mAdapter = new MyItemsAdapter(db_items.getListItems());
        mListClients.setAdapter(mAdapter);
    }
}

package com.soumission.assistant.assistantsoumission;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class GererItemsFragment extends Fragment {
    private RecyclerView mListItems;
    private MyItemsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static final int REQUEST_ADD = 1;

    public GererItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vue = inflater.inflate(R.layout.fragment_gerer_items, container, false);


        mListItems = (RecyclerView)vue.findViewById(R.id.list_items);

        // Icon +
        FloatingActionButton fab = (FloatingActionButton)vue.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent add_Activity = new Intent(getContext(), Page_AddItem.class);

                getContext().startActivity(add_Activity);
                //startActivityForResult(add_Activity, REQUEST_ADD);
            }
        });

        init_RecyclerView();

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
        mListItems.setHasFixedSize(true);

        // Initialise linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mListItems.setLayoutManager(mLayoutManager);

        // Initialise adapter
        mAdapter = new MyItemsAdapter(new DB_Items(getContext()).getListItems(), getActivity());
        mListItems.setAdapter(mAdapter);
    }
}
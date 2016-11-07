package com.soumission.assistant.assistantsoumission;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainFragment extends Fragment {
    private Button mBTN_GererItems;
    private Button mBTN_GererClients;
    private Button mBTN_GererCompagnie;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vue = inflater.inflate(R.layout.fragment_main, container, false);

        mBTN_GererItems = (Button)vue.findViewById(R.id.btn_gerer_item);
        mBTN_GererItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).goToItems();
            }
        });


        mBTN_GererClients = (Button)vue.findViewById(R.id.btn_gerer_client);
        mBTN_GererClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).goToClients();
            }
        });


        mBTN_GererCompagnie = (Button)vue.findViewById(R.id.btn_gerer_compagnie);
        mBTN_GererCompagnie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).goToCompagnie();
            }
        });

        return vue;
    }
}
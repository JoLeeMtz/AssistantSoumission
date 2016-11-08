package com.soumission.assistant.assistantsoumission;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Joaquin on 2016-11-02.
 */

public class MyClientsAdapter extends RecyclerView.Adapter<MyClientsAdapter.ViewHolder> {
    private List<Clients> mDataSet;
    private Activity activity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;
        public TextView mTextView5;
        public TextView mTextView6;
        public TextView mTextView7;
        public TextView mTextView8;
        public LinearLayout linear;

        public ViewHolder(View v) {
            super(v);
            mTextView1 = (TextView)v.findViewById(R.id.client_name);
            mTextView2 = (TextView)v.findViewById(R.id.client_first_name);
            mTextView3 = (TextView)v.findViewById(R.id.client_city);
            mTextView4 = (TextView)v.findViewById(R.id.client_zip_code);
            mTextView5 = (TextView)v.findViewById(R.id.client_adress);
            mTextView6 = (TextView)v.findViewById(R.id.client_phone);
            mTextView7 = (TextView)v.findViewById(R.id.client_cell);
            mTextView8 = (TextView)v.findViewById(R.id.client_email);

            linear = (LinearLayout)v.findViewById(R.id.linear_clients);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyClientsAdapter(List<Clients> myDataSet, Activity activity) {
        mDataSet = myDataSet;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyClientsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_soumission_clients, parent, false);
        // set the view's size, margins, paddings and layout parameters

        MyClientsAdapter.ViewHolder vh = new MyClientsAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyClientsAdapter.ViewHolder holder, final int pos) {
        // - get element from your dataset at this pos
        // - replace the contents of the view with that element
        holder.mTextView1.setText("Nom: " + mDataSet.get(pos).get_nom());
        holder.mTextView2.setText("Prénom: " + mDataSet.get(pos).get_prenom());
        holder.mTextView3.setText("Ville: " + mDataSet.get(pos).get_ville());
        holder.mTextView4.setText("Code Postal: " + mDataSet.get(pos).get_codePostal());
        holder.mTextView5.setText("Adresse: " + mDataSet.get(pos).get_adresse());
        holder.mTextView6.setText("Numéro de téléphone: " + mDataSet.get(pos).get_telephone());
        holder.mTextView7.setText("Numéro de cellulaire: " + mDataSet.get(pos).get_cell());
        holder.mTextView8.setText("Adresse courriel: " + mDataSet.get(pos).get_courriel());


        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), mDataset.get(pos).get_nameItem(), Toast.LENGTH_SHORT).show();
                Intent activityModify = new Intent(v.getContext(), Page_ModifyClient.class);

                activityModify.putExtra("id", mDataSet.get(pos).get_id());
                activityModify.putExtra("nom", mDataSet.get(pos).get_nom());
                activityModify.putExtra("prenom", mDataSet.get(pos).get_prenom());
                activityModify.putExtra("ville", mDataSet.get(pos).get_ville());
                activityModify.putExtra("code_postal", mDataSet.get(pos).get_codePostal());
                activityModify.putExtra("adresse", mDataSet.get(pos).get_adresse());
                activityModify.putExtra("telephone", mDataSet.get(pos).get_telephone());
                activityModify.putExtra("cell", mDataSet.get(pos).get_cell());
                activityModify.putExtra("courriel", mDataSet.get(pos).get_courriel());

                v.getContext().startActivity(activityModify);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}

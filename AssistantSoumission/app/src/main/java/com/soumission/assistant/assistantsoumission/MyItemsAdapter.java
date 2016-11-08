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
 * Created by Joaquin on 2016-10-20.
 */

public class MyItemsAdapter extends RecyclerView.Adapter<MyItemsAdapter.ViewHolder> {
    private List<Items> mDataSet;
    private Activity activity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public LinearLayout linear;

        public ViewHolder(View v) {
            super(v);
            mTextView1 = (TextView)v.findViewById(R.id.name_item);
            mTextView2 = (TextView)v.findViewById(R.id.price_item);
            mTextView3 = (TextView)v.findViewById(R.id.MAJ_item);
            linear = (LinearLayout)v.findViewById(R.id.linear_items);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyItemsAdapter(List<Items> myDataset, Activity activity) {
        mDataSet = myDataset;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_soumission_items, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int pos) {
        // - get element from your dataset at this pos
        // - replace the contents of the view with that element
        holder.mTextView1.setText("Item : " + mDataSet.get(pos).get_nameItem());
        holder.mTextView2.setText("Prix : " + mDataSet.get(pos).get_price() + "$");
        holder.mTextView3.setText("Dernière mise à jour : " + mDataSet.get(pos).get_MAJ());


        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), mDataSet.get(pos).get_nameItem(), Toast.LENGTH_SHORT).show();
                Intent activityModify = new Intent(v.getContext(), Page_ModifyItem.class);

                activityModify.putExtra("id", mDataSet.get(pos).get_id());
                activityModify.putExtra("item", mDataSet.get(pos).get_nameItem());
                activityModify.putExtra("price", mDataSet.get(pos).get_price());
                activityModify.putExtra("maj", mDataSet.get(pos).get_MAJ());

                activity.startActivityForResult(activityModify, GererItemsFragment.REQUEST_ADD);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
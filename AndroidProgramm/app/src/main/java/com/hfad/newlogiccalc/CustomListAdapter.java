package com.hfad.newlogiccalc;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private int id;
    private List<String> items ;

    public CustomListAdapter(Context context, int textViewResourceId , List<String> list )
    {
        super(context, textViewResourceId, list);
        mContext = context;
        id = textViewResourceId;
        items = list ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get the Item from ListView
        View view = super.getView(position, convertView, parent);
        // Initialize a TextView for ListView each Item
        TextView tv = (TextView) view.findViewById(android.R.id.text1);
        // Set the text color of TextView (ListView Item)
        tv.setTypeface(Typeface.MONOSPACE);
        // Generate ListView Item using TextView
        return view;
    }



}

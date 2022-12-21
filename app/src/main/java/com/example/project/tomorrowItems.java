package com.example.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tomorrowItems#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tomorrowItems extends Fragment {



    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tomorrow_items, container, false);
        listView=(ListView) view.findViewById(R.id.listVItemsLayout);
        listView.setAdapter(arrayAdapter);

        return view;
    }
}
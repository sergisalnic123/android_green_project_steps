package com.example.sergisalnic.green_way_01;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;


public class b2_03_Fragment_tab_food extends Fragment {

    List<String> mAllValues;
    private ArrayAdapter<String> mAdapter;
    private Context mContext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.b2_03_fragment_tab_food, container, false);
    }

}


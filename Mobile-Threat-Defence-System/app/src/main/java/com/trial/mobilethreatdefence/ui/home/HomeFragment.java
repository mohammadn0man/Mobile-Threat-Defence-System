package com.trial.mobilethreatdefence.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.trial.mobilethreatdefence.Adapters.HomeAdapter;
import com.trial.mobilethreatdefence.Models.HomeModel;
import com.trial.mobilethreatdefence.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String TAG = "MyApp";
    private ArrayList<HomeModel> homeModelArrayList = new ArrayList<>();
    private HomeModel homeModel;
    private HomeAdapter homeAdapter;
    ListView listView;
    TextView textView1;
    TextView textView2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        Log.d(TAG, "Hello World");

        listView = (ListView) root.findViewById(R.id.list_view);
        textView1 = (TextView) root.findViewById(R.id.textView1);
        textView2 = (TextView) root.findViewById(R.id.textView2);

        homeListPopulate();

        Log.d(TAG, "populated;");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        return root;
    }

    private void homeListPopulate() {
        homeModel = new HomeModel("CPU Usage", "Current CPU useage in Percentage = ");
        homeModelArrayList.add(homeModel);
        homeModel = new HomeModel("RAM Usage", "Current RAM useage in Percentage = ");
        homeModelArrayList.add(homeModel);
        homeModel = new HomeModel("Disk Usage", "Current Storage useage in Percentage = ");
        homeModelArrayList.add(homeModel);
        homeModel = new HomeModel("Applications", "View Installed Apps");
        homeModelArrayList.add(homeModel);

        homeAdapter = new HomeAdapter(getContext(),R.layout.home_list_layout,homeModelArrayList);
        listView.setAdapter(homeAdapter);

    }
}
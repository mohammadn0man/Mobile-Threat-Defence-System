package com.trial.mobilethreatdefence.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.trial.mobilethreatdefence.Models.HomeModel;
import com.trial.mobilethreatdefence.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class HomeAdapter extends ArrayAdapter<HomeModel> {
    Context context;
    int resource;
    ArrayList<HomeModel> homeModelArrayList;

    public HomeAdapter(@NonNull Context context, int resource, ArrayList<HomeModel> homeModelArrayList) {
        super(context, resource, homeModelArrayList);
        this.context = context;
        this.resource = resource;
        this.homeModelArrayList = homeModelArrayList;
    }

    public int getCount() {
        return super.getCount();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.home_list_layout, null);
        HomeModel homeModel = getItem(position);
        TextView head = (TextView) v.findViewById(R.id.textView1);
        TextView description = (TextView) v.findViewById(R.id.textView2);
        head.setText(homeModel.getHead());
        description.setText(homeModel.getDescription());

        return v;
    }

}

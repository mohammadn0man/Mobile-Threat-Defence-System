package com.trial.mobilethreatdefence.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.TextView;

import com.trial.mobilethreatdefence.Models.AppListModel;
import com.trial.mobilethreatdefence.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class AppListAdapter extends ArrayAdapter<AppListModel> {
    Context context;
    int resource;
    ArrayList<AppListModel> appListModelArrayList;

    public AppListAdapter(@NonNull Context context, int resource, ArrayList<AppListModel> appListModelArrayList) {
        super(context, resource, appListModelArrayList);
        this.context = context;
        this.resource = resource;
        this.appListModelArrayList = appListModelArrayList;
    }

    public int getCount(){ return super.getCount();}

    public View getView(int position, View convertView, ViewGroup parent){
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.app_list_layout, null);
        AppListModel appListModel = getItem(position);
        TextView appName = (TextView) v.findViewById(R.id.textView1);
        appName.setText(appListModel.getAppName());

        return v;
    }

}

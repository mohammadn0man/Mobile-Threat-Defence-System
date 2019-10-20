package com.trial.mobilethreatdefence.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.trial.mobilethreatdefence.Models.AccessPermissionModel;
import com.trial.mobilethreatdefence.Models.HomeModel;
import com.trial.mobilethreatdefence.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class AccessPermissionAdapter extends ArrayAdapter<AccessPermissionModel> {
    Context context;
    int resource;
    ArrayList<AccessPermissionModel> accessPermissionModelArrayList;

    public AccessPermissionAdapter(@NonNull Context context, int resource, ArrayList<AccessPermissionModel> accessPermissionModelArrayList) {
        super(context, resource, accessPermissionModelArrayList);
        this.context = context;
        this.resource = resource;
        this.accessPermissionModelArrayList = accessPermissionModelArrayList;
    }

    public int getCount() {
        return super.getCount();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.gallery_list_layout, null);
        AccessPermissionModel accessPermissionModel = getItem(position);
        TextView name = (TextView) v.findViewById(R.id.textView1);
        TextView number = (TextView) v.findViewById(R.id.textView2);
        name.setText(accessPermissionModel.getName());
        number.setText(accessPermissionModel.getNumber());

        return v;
    }

}

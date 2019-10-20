package com.trial.mobilethreatdefence.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CpuUsageInfo;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ActivityManager;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ApplicationInfo;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.app.ActivityManager.MemoryInfo;
import com.trial.mobilethreatdefence.Adapters.HomeAdapter;
import com.trial.mobilethreatdefence.DeviceUsage.HardwareUsageDetails;
import com.trial.mobilethreatdefence.Models.HomeModel;
import com.trial.mobilethreatdefence.R;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;

public class HomeFragment extends Fragment {

    private static final String TAG = "MyAppHome";
    private ArrayList<HomeModel> homeModelArrayList = new ArrayList<>();
    private HomeModel homeModel;
    private HomeAdapter homeAdapter;
    ListView listView;
    TextView textView1;
    TextView textView2;
    Context context = this.getActivity();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
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

        try {
            homeListPopulate();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Log.d(TAG, "populated;");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0: break;
                    case 1: break;
                    case 2: break;
                    case 3:
                        final PackageManager pm = getActivity().getPackageManager();
                        PackageInfo packageInfo = new PackageInfo();
                        PermissionInfo permissionInfo;
                        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
                        for (ApplicationInfo applicationInfo : packages) {
                            try {
                                packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
                                permissionInfo = pm.getPermissionInfo(applicationInfo.packageName, PackageManager.GET_META_DATA);
//                                Log.d(TAG, "Permission Info :" + permissionInfo.group);
                            } catch (PackageManager.NameNotFoundException e) {
                                e.printStackTrace();
                            }
//                            Log.d(TAG, "Installed package :" + applicationInfo.packageName);
//                            Log.d(TAG, "Package Info :" + packageInfo.permissions);
//                            Log.d(TAG, "Source dir : " + applicationInfo.sourceDir);
//                            Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(applicationInfo.packageName));

                            ApplicationInfo ai;
                            try {
                                ai = pm.getApplicationInfo(applicationInfo.packageName, 0);
                            } catch (PackageManager.NameNotFoundException e) {
                                ai = null;
                                e.printStackTrace();
                            }
                            String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
                            Log.d(TAG, "Package Name :" + applicationName);

                        }
                        break;
                }


            }
        });

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void homeListPopulate() throws IOException, IOException {

        MemoryInfo mi = new MemoryInfo();
        ActivityManager activityManager = (ActivityManager) this.getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        double availableMegs = mi.availMem / 0x100000L;
        //Percentage can be calculated for API 16+
        double percentAvail = mi.availMem / (double)mi.totalMem * 100.0;
        double totalMen =  mi.totalMem / 0x100000L;


        homeModel = new HomeModel("CPU Usage", "Current CPU useage in Percentage = " + HardwareUsageDetails.readCpuUsage());
        homeModelArrayList.add(homeModel);
        homeModel = new HomeModel("RAM Usage", "Current RAM available is " + availableMegs + " Out of " + totalMen + "\nPercentage = " + Math.round(percentAvail * 100) /100);
        homeModelArrayList.add(homeModel);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            homeModel = new HomeModel("Disk Usage", "Free Storage is " + HardwareUsageDetails.getAvailableInternalMemorySize() + " out of " + HardwareUsageDetails.getTotalInternalMemorySize());
        }
        homeModelArrayList.add(homeModel);
        homeModel = new HomeModel("Applications", "View Installed Apps");
        homeModelArrayList.add(homeModel);

        homeAdapter = new HomeAdapter(getContext(),R.layout.home_list_layout,homeModelArrayList);
        listView.setAdapter(homeAdapter);

    }



}
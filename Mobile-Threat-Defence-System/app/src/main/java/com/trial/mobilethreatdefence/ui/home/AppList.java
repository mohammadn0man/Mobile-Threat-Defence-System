package com.trial.mobilethreatdefence.ui.home;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.trial.mobilethreatdefence.Adapters.AppListAdapter;
import com.trial.mobilethreatdefence.Models.AppListModel;
import com.trial.mobilethreatdefence.R;
import com.trial.mobilethreatdefence.Sorters.AppSorter;

import java.util.List;
import java.util.ArrayList;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class AppList extends AppCompatActivity {

    private final String TAG = "MyAppAppList";
    ListView listView;
    TextView textView;
    ArrayList<AppListModel> appListModelArrayList = new ArrayList<>();
    AppListModel appListModel;
    AppListAdapter appListAdapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_list);
        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> applicationInfoList = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        listView = (ListView) findViewById(R.id.list_view_app_list);

        Log.d(TAG, "Populating applist");

        for(ApplicationInfo applicationInfo : applicationInfoList){
            ApplicationInfo ai;
            try {
                ai = pm.getApplicationInfo(applicationInfo.packageName, 0);
            } catch (PackageManager.NameNotFoundException e) {
                ai = null;
                e.printStackTrace();
            }
            String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");

            appListModel = new AppListModel(applicationName);
            appListModelArrayList.add(appListModel);

        }

        appListModelArrayList.sort(new AppSorter());

        appListAdapter = new AppListAdapter(getApplicationContext(), R.layout.app_list_layout, appListModelArrayList);
        listView.setAdapter(appListAdapter);

    }

}





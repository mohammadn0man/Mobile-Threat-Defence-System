package com.trial.mobilethreatdefence.ui.gallery;

import android.Manifest;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.Manifest.permission;
import com.trial.mobilethreatdefence.Adapters.AppListAdapter;
import com.trial.mobilethreatdefence.Models.AppListModel;
import com.trial.mobilethreatdefence.R;
import com.trial.mobilethreatdefence.Sorters.AppSorter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class PermittedAppList extends AppCompatActivity {


    private final String TAG = "MyAppPermittedAppList";
    ListView listView;
    TextView textView;
    ArrayList<AppListModel> appListModelArrayList = new ArrayList<>();
    AppListModel appListModel;
    AppListAdapter appListAdapter;
    String PERMISSION_FLAG;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_list);
        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> applicationInfoList = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        listView = (ListView) findViewById(R.id.list_view_app_list);

        Log.d(TAG, "Populating applist");

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        PERMISSION_FLAG = bundle.getString("GET_PERMISSION_FLAG");

        for (ApplicationInfo applicationInfo : applicationInfoList) {
            int flag = pm.checkPermission(PERMISSION_FLAG , applicationInfo.packageName);
            ApplicationInfo ai;
            try {
                ai = pm.getApplicationInfo(applicationInfo.packageName, 0);
            } catch (PackageManager.NameNotFoundException e) {
                ai = null;
                e.printStackTrace();
            }
            String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
            if (flag == PackageManager.PERMISSION_GRANTED) {
                appListModel = new AppListModel(applicationName);
                appListModelArrayList.add(appListModel);
            }
        }

        appListModelArrayList.sort(new AppSorter());

        appListAdapter = new AppListAdapter(getApplicationContext(), R.layout.app_list_layout, appListModelArrayList);
        listView.setAdapter(appListAdapter);

    }


}

package com.trial.mobilethreatdefence.ui.gallery;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.Manifest.permission;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import java.security.PermissionCollection;
import com.trial.mobilethreatdefence.Adapters.AccessPermissionAdapter;
import com.trial.mobilethreatdefence.Models.AccessPermissionModel;
import com.trial.mobilethreatdefence.Models.HomeModel;
import com.trial.mobilethreatdefence.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private static final String TAG = "MyAppAccess";
//    private GalleryViewModel galleryViewModel;
    private ArrayList<AccessPermissionModel> accessPermissionModelArrayList = new ArrayList<>();
    private AccessPermissionModel accessPermissionModel;
    private AccessPermissionAdapter accessPermissionAdapter;
    ListView listView;
    TextView textView1;
    TextView textView2;
    Context context = this.getActivity();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
//        final TextView textView = root.findViewById(R.id.text_gallery);
//        galleryViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        Log.d(TAG, "Hello World");

        listView = (ListView) root.findViewById(R.id.list_view_gallery);
        textView1 = (TextView) root.findViewById(R.id.textView1);
        textView2 = (TextView) root.findViewById(R.id.textView2);

        galleryListPopulate();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final PackageManager pm = getActivity().getPackageManager();
                List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

                switch (i){
                    case 0:
                        for (ApplicationInfo applicationInfo : packages) {

                            int flag = pm.checkPermission( permission.READ_CALL_LOG , applicationInfo.packageName);

                            ApplicationInfo ai;
                            try {
                                ai = pm.getApplicationInfo(applicationInfo.packageName, 0);
                            } catch (PackageManager.NameNotFoundException e) {
                                ai = null;
                                e.printStackTrace();
                            }
                            String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
                            if (flag == PackageManager.PERMISSION_GRANTED) {
                                Log.d(TAG, "Package Name with permission :" + applicationName + " Has Read call log Permission.");
                            }
                            else {
                                Log.d(TAG, "Package Name don't have permission:" + applicationName );

                            }
                        }
                        break;
                    case 1: break;
                    case 2: break;
                    case 3: break;
                    case 4: break;
                    case 5: break;
                }

            }
        });

        return root;
    }

    private void galleryListPopulate() {
        accessPermissionModel = new AccessPermissionModel("Call Logs","");
        accessPermissionModelArrayList.add(accessPermissionModel);
        accessPermissionModel = new AccessPermissionModel("Camera","");
        accessPermissionModelArrayList.add(accessPermissionModel);
        accessPermissionModel = new AccessPermissionModel("Contacts","");
        accessPermissionModelArrayList.add(accessPermissionModel);
        accessPermissionModel = new AccessPermissionModel("Storage","");
        accessPermissionModelArrayList.add(accessPermissionModel);
        accessPermissionModel = new AccessPermissionModel("SMS","");
        accessPermissionModelArrayList.add(accessPermissionModel);
        accessPermissionModel = new AccessPermissionModel("Location","");
        accessPermissionModelArrayList.add(accessPermissionModel);

        accessPermissionAdapter = new AccessPermissionAdapter(getContext(), R.layout.gallery_list_layout,accessPermissionModelArrayList);
        listView.setAdapter(accessPermissionAdapter);

    }
}
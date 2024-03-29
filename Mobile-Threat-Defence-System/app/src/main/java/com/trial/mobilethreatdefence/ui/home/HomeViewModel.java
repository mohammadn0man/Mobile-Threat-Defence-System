package com.trial.mobilethreatdefence.ui.home;

import android.os.Build;
import android.util.Log;
import com.trial.mobilethreatdefence.DeviceUsage.HardwareUsageDetails;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class HomeViewModel extends ViewModel {

    private static final String TAG = "MyApp";

    private MutableLiveData<String> mText;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        Log.d(TAG, "Hello World from fragment_home class HomeViewModel");
//        HardwareUsageDetails hardwareUsageDetails = new HardwareUsageDetails();
//        mText.setValue("The CPU Usage = " + hardwareUsageDetails.readCpuUsage() + ", " + hardwareUsageDetails.getCpuPer()
//                        + "\nThe Ram Usage = " + hardwareUsageDetails.getCpuPer());
    }
    public LiveData<String> getText() {
        return mText;
    }
}
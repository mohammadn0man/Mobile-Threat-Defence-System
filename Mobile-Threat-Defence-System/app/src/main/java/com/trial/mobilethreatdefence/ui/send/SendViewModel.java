package com.trial.mobilethreatdefence.ui.send;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SendViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SendViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Mobile security refers to efforts to secure" +
                " data on mobile devices such as smartphones and tablets." +
                " Typically, mobile security is something that we are " +
                "working on to control sensitive information that could be " +
                "jeopardized because of its use on various mobile devices.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
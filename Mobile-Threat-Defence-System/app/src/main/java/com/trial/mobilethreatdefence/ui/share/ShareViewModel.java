package com.trial.mobilethreatdefence.ui.share;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShareViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("\n\n\n\n\nYou can send us mail on \nalphabeta@gamma.com \nor just press the button below.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
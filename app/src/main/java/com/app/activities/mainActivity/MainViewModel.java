package com.app.activities.mainActivity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.app.utils.AlertUtils;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<String> buttonText;
    private int counter = 0;

    public MainViewModel(@NonNull Application application) {
        super(application);
        buttonText = new MutableLiveData<>();
        buttonText.setValue("Click 0");
    }

    public void onButtonClicked(View view) {
        counter++;
        setButtonText("Click " + counter);
        AlertUtils.showToast("Button Clicked " + counter, Toast.LENGTH_SHORT, view.getContext());
    }

    public MutableLiveData<String> getButtonText() {
        return buttonText;
    }

    private void setButtonText(String buttonText) {
        this.buttonText.setValue(buttonText);
    }
}

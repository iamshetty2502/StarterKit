package com.app.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.app.App;
import com.app.R;

import java.util.Calendar;

public class PasswordCheck {

    private String current = "";
    private String ddmmyyyy = "DDMMYYYY";
    private Calendar cal = Calendar.getInstance();
    private EditText password, confirmpassword;

    public PasswordCheck(EditText password, EditText confirmpassword, final int passwordLength) {
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.confirmpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!password.getText().toString().equals(confirmpassword.getText().toString())) {
                    confirmpassword.setTextColor(App.getAppContext().getColor(R.color.colorAccent));
                } else {
                    confirmpassword.setTextColor(App.getAppContext().getColor(R.color.white));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!password.getText().toString().equals(s.toString())) {
                    confirmpassword.setError("Password is not matching");
                }
            }
        });
        this.password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() < passwordLength) {
                    password.setError("Password length should be greater then 8");
                }
            }
        });
    }

}
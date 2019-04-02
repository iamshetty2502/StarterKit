package com.app.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.R;


public class SimpleDialog extends Dialog {

    public ConstraintLayout container;
    public TextView titleTv, headerTv;
    public Button submitBtn;
    public EditText nameEt;

    public SimpleDialog(@NonNull Context context) {
        super(context);
        setLayout();
    }

    private void setLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        setWindowLayout();
        container = findViewById(R.id.container);
        titleTv = findViewById(R.id.titleTv);
        headerTv = findViewById(R.id.headerTv);
        nameEt = findViewById(R.id.nameEt);
        submitBtn = findViewById(R.id.submitBtn);
        Button cancelButton = findViewById(R.id.cancelBtn);
        cancelButton.setOnClickListener(v -> dismiss());
    }

    private void setWindowLayout() {
        this.setCancelable(false);
        Window window = this.getWindow();
        if (window != null) {
            window.setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.getAttributes().windowAnimations = R.style.DialogTransitionSlide;
        }
    }

}

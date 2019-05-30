package com.app.utils;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.App;
import com.app.R;
import com.bumptech.glide.Glide;

import java.util.Objects;

public class BindingUtil {

    @BindingAdapter({"setUpWithViewpager", "setUpIcon", "setAddOnTabSelectedListener"})
    public static void setUpWithViewpager(final TabLayout tabLayout, ViewPager viewPager, int[] drawable, TabLayout.BaseOnTabSelectedListener tabSelectedListener) {
        viewPager.addOnAdapterChangeListener((viewPager1, oldAdapter, newAdapter) -> {
            if (oldAdapter == null && newAdapter == null) {
                return;
            }
            tabLayout.setupWithViewPager(viewPager1);
            for (int i = 0; i < drawable.length; i++) {
                if (drawable[i] != Constants.IGNORE) {
                    if (tabLayout.getTabAt(i) != null) {
                        (Objects.requireNonNull(tabLayout.getTabAt(i))).setIcon(drawable[i]);
                    }
                }
            }
            tabLayout.addOnTabSelectedListener(tabSelectedListener);
        });
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(App.getAppContext()).load(imageUrl).placeholder(R.drawable.background).error(R.drawable.profimg).into(view);
    }

    @BindingAdapter({"setUpIcon", "setUpText", "setAddOnTabSelectedListener"})
    public static void setUpIcon(final TabLayout tabLayout, int[] drawable, String[] tabName, TabLayout.BaseOnTabSelectedListener tabSelectedListener) {

        for (int i = 0; i < drawable.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setIcon(drawable[i]);
            tab.setText(tabName[i]);
            tabLayout.addTab(tab);
        }
        tabLayout.addOnTabSelectedListener(tabSelectedListener);
    }

    @BindingAdapter({"imageUrlFit"})
    public static void loadImageFit(ImageView view, String imageUrl) {
        Glide.with(App.getAppContext()).load(imageUrl).placeholder(R.drawable.background).centerCrop().error(R.drawable.profimg).into(view);
    }

    @BindingAdapter({"setBold"})
    public static void setBold(TextView view, Boolean isNormal) {
        if (isNormal) {
            view.setTypeface(null, Typeface.NORMAL);
        } else {
            view.setTypeface(null, Typeface.BOLD);
            int value = (int) App.getAppContext().getResources().getDimension(R.dimen.app_spacing_5dp);
            view.setPadding(value, value, value, value);
        }
    }

    @BindingAdapter({"setErrorText"})
    public static void setErrorText(EditText editText, String errorMessage) {
        editText.setError(errorMessage);
    }
}

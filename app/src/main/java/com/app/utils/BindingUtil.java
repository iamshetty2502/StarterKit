package com.app.utils;

import android.databinding.BindingAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

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
}

package com.app.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.widget.TextView;

import com.app.App;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class FacebookKeyGenerator {

    TextView textView;

    public FacebookKeyGenerator() {
        // Add code to print out the key hash
        try {
            PackageInfo info = App.getAppContext().getPackageManager().getPackageInfo("com.app.sample", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                LogUtils.LogError("KeyHash: "+ Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }
    }
}
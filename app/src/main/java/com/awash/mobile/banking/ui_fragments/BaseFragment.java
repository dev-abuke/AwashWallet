package com.awash.mobile.banking.ui_fragments;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.awash.mobile.banking.Classes.Constants;

public class BaseFragment extends Fragment {

    private Activity context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
    }

    protected String getStringResource(@StringRes int id){

        return getResources().getString(id);
    }

    protected boolean isPhonePermissionsGranted() {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED;
    }

    protected void askPhonePermission() {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CALL_PHONE}, Constants.CALL_REQUEST_CODE);
    }
}

package com.awash.mobile.banking.ui_fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.awash.mobile.banking.Classes.Constants;
import com.awash.mobile.banking.Classes.TinyDB;
import com.awash.mobile.banking.R;
import com.suke.widget.SwitchButton;

public class BaseFragment extends Fragment {

    private Context context;
    private Activity activity;
    TinyDB tinyDB;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tinyDB = new TinyDB(context);
        activity = getActivity();
    }

    protected String getStringResource(@StringRes int id){

        return getResources().getString(id);
    }

    protected boolean isPhonePermissionsGranted() {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED;
    }

    protected void askPhonePermission() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, Constants.CALL_REQUEST_CODE);
    }

    protected void handleSwitch(boolean isChecked, SwitchButton switchButton, View viewVisibility) {

        switchButton.setChecked(isChecked);

        if (isChecked){
            viewVisibility.setVisibility(View.VISIBLE);
        }else {
            viewVisibility.setVisibility(View.GONE);
        }
    }

    protected int getCurrentAcc(){

        return tinyDB.getInt(getStringResource(R.string.current_account)) + 1;
    }

    protected AlertDialog.Builder showConfirmation(String type, String amount,
                                                   String info, final Intent intent){

        String message = "Are you sure you want to %s %s Birr for %s ";

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm Transaction");

        switch (type){

            case Constants.TYPE_SCHOOL_PAYMENT:
                builder.setMessage(String.format(message,"Pay",amount,Constants.TYPE_SCHOOL_PAYMENT));
                break;
            case Constants.TYPE_AIRLINE_TICKET:
                builder.setMessage(String.format(message,"Pay",amount,Constants.TYPE_AIRLINE_TICKET));
                break;
            case Constants.TYPE_DSTV_PAYMENT:
                builder.setMessage(String.format(message,"Pay",amount,Constants.TYPE_DSTV_PAYMENT));
                break;
            case Constants.TYPE_OTHER_PAYMENT:
                builder.setMessage(String.format(message,"Transfer Payment",amount,"account No " + info));
                break;
            case Constants.TYPE_RECHARGE:
                builder.setMessage(String.format(message,"Recharge",amount,info));
                break;
        }

        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        return builder;
    }
}

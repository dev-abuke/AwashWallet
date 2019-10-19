package com.awash.mobile.banking.ui_fragments.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

import com.awash.mobile.banking.Classes.TinyDB;
import com.awash.mobile.banking.R;
import com.awash.mobile.banking.activities.BaseActivity;

import java.util.ArrayList;


public class SettingsFragment extends PreferenceFragmentCompat{

    Context context;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferece_ui,rootKey);


    }

}
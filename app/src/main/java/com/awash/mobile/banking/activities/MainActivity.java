package com.awash.mobile.banking.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.awash.mobile.banking.Classes.Constants;
import com.awash.mobile.banking.Classes.TinyDB;
import com.awash.mobile.banking.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends BaseActivity implements
        BottomNavigationView.OnNavigationItemReselectedListener,
        Spinner.OnItemSelectedListener{

    TinyDB tinyDB;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tinyDB = new TinyDB(this);
        context = this;

        BottomNavigationView navView = findViewById(R.id.nav_view);
        Spinner spinner = findViewById(R.id.accSpinner);

        navView.setOnNavigationItemReselectedListener(this);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);


       int selectedAccount = tinyDB.getInt(getStringResource(R.string.current_account));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item,getResources().getStringArray(R.array.entries));

        spinner.setAdapter(adapter);
        spinner.setSelection(selectedAccount,false);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!isPhonePermissionsGranted())
            askPhonePermission();
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int selection, long l) {

        tinyDB.putInt(getStringResource(R.string.current_account),selection);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    void askPhonePermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, Constants.CALL_REQUEST_CODE);
    }

    boolean isPhonePermissionsGranted() {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED;
    }
}

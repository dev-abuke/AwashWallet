package com.awash.mobile.banking.activities;

import android.content.Context;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.awash.mobile.banking.Classes.TinyDB;


public class BaseActivity extends AppCompatActivity{


    public String getStringResource(@StringRes int id){

        return getResources().getString(id);
    }
}

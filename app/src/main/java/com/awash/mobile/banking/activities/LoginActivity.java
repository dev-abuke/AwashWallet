package com.awash.mobile.banking.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import com.awash.mobile.banking.Classes.TinyDB;
import com.awash.mobile.banking.R;
import com.google.android.material.snackbar.Snackbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    EditText accountF,phoneF,pinF;
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountF = findViewById(R.id.accountF);
        phoneF = findViewById(R.id.phoneF);
        pinF = findViewById(R.id.pinF);

        tinyDB = new TinyDB(this);

        findViewById(R.id.btnLogin).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnLogin){
            //Continue btn Clicked

                boolean number = checkDataForIntegrity(

                        accountF.getText().toString(),
                        phoneF.getText().toString(),
                        pinF.getText().toString(),
                        view
                );

                if (number){
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
            }
        }
    }

    private boolean checkDataForIntegrity(String account,String phone,String pin,View view) {

        if (account.isEmpty()){
            accountF.setError("Account can not be empty");
            return false;
        }else if (phone.isEmpty()){
            phoneF.setError("Phone can not be empty");
            return false;
        }else if (pin.isEmpty()){
            pinF.setError("Pin can not be empty");
            return false;
        }

        return checkForNumberError(account,phone,pin,view);
    }

    boolean checkForNumberError(String account,String phone,String pin,View view){
        //Check for invalid Data
        long acc,phoneN,pinN;

        try {
            acc = Long.parseLong(account);
            phoneN = Long.parseLong(phone);
            pinN = Long.parseLong(pin);
        }catch (NumberFormatException nfe){

            Snackbar snackbar = Snackbar.make(view,"Provide only Number!",Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.deepBlue));
            return false;
        }

        String number = account.replaceFirst("(\\d{4})(\\d{4})(\\d{3})(\\d+)", "$1-$2-$3-$4");
        tinyDB.putLong("default_acc_num",acc);
        tinyDB.putString("default_acc_string",number);
        tinyDB.putLong("default_phone",phoneN);

        return true;
    }
}

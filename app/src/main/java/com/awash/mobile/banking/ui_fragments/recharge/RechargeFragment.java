package com.awash.mobile.banking.ui_fragments.recharge;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.awash.mobile.banking.Classes.Constants;
import com.awash.mobile.banking.Classes.TinyDB;
import com.awash.mobile.banking.activities.LoginActivity;
import com.awash.mobile.banking.activities.MainActivity;
import com.awash.mobile.banking.R;
import com.awash.mobile.banking.ui_fragments.BaseFragment;
import com.suke.widget.SwitchButton;

public class RechargeFragment extends BaseFragment
        implements View.OnClickListener,
        SwitchButton.OnCheckedChangeListener,
        AlertDialog.OnClickListener{

    private View root;
    private LinearLayout phone;
    private EditText phoneField,amountF,pinF;
    private ImageView contacts;
    private SwitchButton switchBtn;
    private Button recharge;
    private Context context;
    TinyDB tinyDB;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        context = getContext();
        root = inflater.inflate(R.layout.fragment_recharge, container, false);

        phoneField = root.findViewById(R.id.phoneField);
        amountF = root.findViewById(R.id.amountRechaF);
        pinF = root.findViewById(R.id.pinRechaF);
        switchBtn = root.findViewById(R.id.switchRecharge);
        contacts = root.findViewById(R.id.contactPicker);
        recharge = root.findViewById(R.id.btnRecharge);
        phone = root.findViewById(R.id.phoneFieldContainer);

        contacts.setOnClickListener(this);
        switchBtn.setOnCheckedChangeListener(this);
        recharge.setOnClickListener(this);

        tinyDB = new TinyDB(context);

        boolean isChecked = tinyDB.getBoolean(getStringResource(R.string.recharge_other));

        handleSwitch(isChecked,switchBtn,phone);

        return root;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.contactPicker){

            startContactPicker();
            Toast.makeText(context, getStringResource(R.string.pick_contact),Toast.LENGTH_LONG).show();

        }else if (id == R.id.btnRecharge){
            //Recharge button implimentation

            Intent dialIntent = new Intent(Intent.ACTION_DIAL);

            int account = getCurrentAcc();

            if (isPhonePermissionsGranted()){

                String phoneNo = phoneField.getText().toString();
                String amount = amountF.getText().toString();
                String pin = pinF.getText().toString();

             boolean isDataCorrect = checkData(phoneNo,amount,pin);

             startAction(isDataCorrect,dialIntent,account,pin,amount,phoneNo);

            }else
                askPhonePermission();
        }
    }

    private void startAction(boolean isDataCorrect,final Intent dialIntent,
                             int account, String pin, String amount, String phoneNo) {

        if (isDataCorrect){

            if (switchBtn.isChecked()){
                String send = String.format(Constants.RECHARGE_OTHER,pin,account,phoneNo,amount);
                dialIntent.setData(Uri.parse("tel:" + Uri.encode(send)));
            }else {
                String send = String.format(Constants.RECHARGE_OWN,pin,account,amount);
                dialIntent.setData(Uri.parse("tel:" + Uri.encode(send)));
                phoneNo = "yourself";
            }

            showConfirmation(Constants.TYPE_RECHARGE,amount,phoneNo,dialIntent).show();
        }
    }

    private boolean checkData(String phoneNo, String amount, String pin) {

        if (switchBtn.isChecked()){

            if (phoneNo.isEmpty()){
                phoneField.setError("Phone can not be empty");
                return false;
            }
        }
        if (amount.isEmpty()) {
                amountF.setError("Amount can not be empty");
                return false;
            }
        if (pin.isEmpty()) {
                pinF.setError("Pin can not be empty");
                return false;
         }

        return true;
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {

        tinyDB.putBoolean(getStringResource(R.string.recharge_other),isChecked);

        handleSwitch(isChecked, view, phone);

    }

    private void startContactPicker() {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(i, Constants.SELECT_PHONE_NUMBER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       String trimmedPhoneNumber = trimNum(getPhoneNumber(requestCode,resultCode,data));
       setPhoneNum(trimmedPhoneNumber);

    }

    private void setPhoneNum(String trimmedPhoneNumber) {
        phoneField.setText(trimmedPhoneNumber);
    }

    private String getPhoneNumber(int requestCode, int resultCode, Intent data) {

        String PhoneNumber = "";

        if (requestCode == Constants.SELECT_PHONE_NUMBER && resultCode == MainActivity.RESULT_OK) {

            if (data != null) {

                Uri contactUri = data.getData();

                String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
                assert contactUri != null;
                Cursor cursor = context.getContentResolver().query(contactUri, projection,
                        null, null, null);

                if (cursor != null && cursor.moveToFirst()) {
                    int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    PhoneNumber = cursor.getString(numberIndex);
                }

                assert cursor != null;
                cursor.close();
            }
        }

        return PhoneNumber;
    }

    //removes unnecessary numbers from phone number
    private String trimNum(String phoneNumber) {

        String newString = phoneNumber.replaceAll("\\s", "");

        boolean b = phoneNumber.contains("+251") || phoneNumber.contains("251") ;

        if (b) {

            newString = phoneNumber.replace("+251", "0").trim().replaceAll("\\s", "");
        }

        return newString;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
}
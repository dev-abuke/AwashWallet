package com.awash.mobile.banking.ui_fragments.paymentOptions;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.awash.mobile.banking.Classes.Constants;
import com.awash.mobile.banking.R;
import com.awash.mobile.banking.ui_fragments.BaseFragment;
import com.suke.widget.SwitchButton;


public class DsTvPayFragment extends BaseFragment implements
        SwitchButton.OnCheckedChangeListener,
        View.OnClickListener{

    private View root;
    private SwitchButton reasonSw;
    private EditText addReasonF,dsCardNoF,amountF,pinF;
    private Button btnDsPay;

    public DsTvPayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_ds_tv_pay, container, false);

        reasonSw  = root.findViewById(R.id.switchAddReason);
        addReasonF = root.findViewById(R.id.reasonF);
        dsCardNoF = root.findViewById(R.id.cardNo);
        amountF   = root.findViewById(R.id.amountF);
        pinF      = root.findViewById(R.id.pinF);
        btnDsPay  = root.findViewById(R.id.btnPayDs);

        reasonSw.setOnCheckedChangeListener(this);
        btnDsPay.setOnClickListener(this);

        return root;
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {

      handleSwitch(isChecked,view,addReasonF);

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.btnPayDs){

            Intent dialIntent = new Intent(Intent.ACTION_DIAL);

            int account = getCurrentAcc();

            String cardNo = dsCardNoF.getText().toString();
            String amount = amountF.getText().toString();
            String pin    = pinF.getText().toString();
            String reason = addReasonF.getText().toString();

            if (isPhonePermissionsGranted()){

                boolean isCorrect = checkData(cardNo,amount,pin,reason);

                if (isCorrect){

                    if (reasonSw.isChecked()){
                        //to add reason for payment
                        //to be implemented
                    }else {

                    }
                    showConfirmation(Constants.TYPE_DSTV_PAYMENT,amount,cardNo,dialIntent).show();
                }

            }else
                askPhonePermission();
        }
    }

    private boolean checkData(String cardNo, String amount,
                              String pin, String reason) {

        if (cardNo.isEmpty()) {
            dsCardNoF.setError("Card Number can not be empty");
            return false;
        }
        if (amount.isEmpty()) {
            amountF.setError("Amount can not be empty");
            return false;
        }
        if (reasonSw.isChecked()) {

            if (reason.isEmpty()) {
                addReasonF.setError("Please state reason for payment");
                return false;
            }
        }
        if (pin.isEmpty()) {
            pinF.setError("Pin can not be empty");
            return false;
        }

        return true;
    }
}

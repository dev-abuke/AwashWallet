package com.awash.mobile.banking.ui_fragments.paymentOptions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.awash.mobile.banking.R;
import com.awash.mobile.banking.ui_fragments.BaseFragment;
import com.suke.widget.SwitchButton;


public class AirPayFragment extends BaseFragment implements
        SwitchButton.OnCheckedChangeListener,
        View.OnClickListener{

    private View root;
    private SwitchButton reasonSw;
    private EditText addReasonF,bookingNoF,amountF,pinF;

    public AirPayFragment() {
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
        root = inflater.inflate(R.layout.fragment_air_pay, container, false);

        reasonSw   = root.findViewById(R.id.switchAddReason);
        addReasonF = root.findViewById(R.id.reasonF);
        amountF    = root.findViewById(R.id.amountF);
        pinF       = root.findViewById(R.id.pinF);
        bookingNoF = root.findViewById(R.id.bookingNo);

        reasonSw.setOnCheckedChangeListener(this);
        return root;
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {

        if (isChecked){
            addReasonF.setVisibility(View.VISIBLE);
        }else
            addReasonF.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.btnBuy) {

            if (isPhonePermissionsGranted()){

            }else
                askPhonePermission();
        }
    }
}

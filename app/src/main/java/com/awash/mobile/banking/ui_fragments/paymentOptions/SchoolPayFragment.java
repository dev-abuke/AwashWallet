package com.awash.mobile.banking.ui_fragments.paymentOptions;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;

import com.awash.mobile.banking.R;
import com.suke.widget.SwitchButton;


public class SchoolPayFragment extends Fragment implements SwitchButton.OnCheckedChangeListener{

    private View root;
    private SwitchButton reasonSw;
    EditText addReason;
    NestedScrollView scrollView;

    public SchoolPayFragment() {
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
        root = inflater.inflate(R.layout.fragment_school, container, false);

        addReason = root.findViewById(R.id.reasonF);
        scrollView = root.findViewById(R.id.scrollView);
        reasonSw = root.findViewById(R.id.switchAddReason);

        reasonSw.setOnCheckedChangeListener(this);

        return root;
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {

        if (isChecked){
            addReason.setVisibility(View.VISIBLE);
        }else
            addReason.setVisibility(View.GONE);
    }
}

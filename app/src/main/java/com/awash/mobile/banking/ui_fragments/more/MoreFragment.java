package com.awash.mobile.banking.ui_fragments.more;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.awash.mobile.banking.Classes.Constants;
import com.awash.mobile.banking.R;
import com.awash.mobile.banking.adapters.PaymentMethodsAdapter;
import com.awash.mobile.banking.ui_fragments.payment.PayViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment implements View.OnClickListener{

    private LinearLayout linearLayout;
    Context context;

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_more, container, false);

        ArrayList<PayViewModel> list = new ArrayList<PayViewModel>();

        list.add(new PayViewModel("Check Balance",R.drawable.check_balance));
        list.add(new PayViewModel("Buying Rate",R.drawable.ic_call_blue_24dp));
        list.add(new PayViewModel("Selling Rate",R.drawable.ic_call_blue_24dp));
        list.add(new PayViewModel("Plane Ticket",R.drawable.ic_call_blue_24dp));

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_more);
        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        recyclerView.setAdapter(new PaymentMethodsAdapter(recyclerView,list,context));

        return root;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.check_bal_layout){
            int acct_no = 2;
            int pin = 1212;
            String phone = String.format(Constants.AWASH_CHECK_BALANCE,pin,acct_no);
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + Uri.encode(phone)));
            startActivity(intent);
        }
    }
}

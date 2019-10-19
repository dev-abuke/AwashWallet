package com.awash.mobile.banking.ui_fragments.payment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.awash.mobile.banking.Classes.Constants;
import com.awash.mobile.banking.R;
import com.awash.mobile.banking.adapters.PagerAdapter;
import com.awash.mobile.banking.ui_fragments.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;

public class PayFragment extends BaseFragment {

    private Context context;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pay, container, false);
        context = getContext();

        /*
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new PaymentMethodsAdapter(recyclerView,getData(),context));
        */

        viewPager = root.findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagerAdapter(getChildFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));

        tabLayout = root.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        setTabIcons(tabLayout);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private ArrayList<PayViewModel> getData(){

        ArrayList<PayViewModel> payData = new ArrayList<PayViewModel>();

        payData.add(new PayViewModel
                (getStringResource(R.string.school_payment),R.drawable.ic_school));
        payData.add(new PayViewModel(
                getStringResource(R.string.airline_ticket),R.drawable.ic_plane));
        payData.add(new PayViewModel
                (getStringResource(R.string.dstv_ayment),R.drawable.ic_dstv));
        payData.add(new PayViewModel
                (getStringResource(R.string.other_payment),R.drawable.ic_others));

        return payData;
    }

    private void setTabIcons(TabLayout tab){

        Objects.requireNonNull(tab.getTabAt(0)).setIcon(Constants.ICONS[0]);
        Objects.requireNonNull(tab.getTabAt(1)).setIcon(Constants.ICONS[1]);
        Objects.requireNonNull(tab.getTabAt(2)).setIcon(Constants.ICONS[2]);
        Objects.requireNonNull(tab.getTabAt(3)).setIcon(Constants.ICONS[3]);
    }
}
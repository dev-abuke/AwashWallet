package com.awash.mobile.banking.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.awash.mobile.banking.Classes.Constants;
import com.awash.mobile.banking.R;
import com.awash.mobile.banking.ui_fragments.payment.PayViewModel;
import com.suke.widget.SwitchButton;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

    public class PaymentMethodsAdapter extends RecyclerView.Adapter<PaymentMethodsAdapter.ViewHolder> {

        private static final int UNSELECTED = -1;

        private RecyclerView recyclerView;
        private int selectedItem = UNSELECTED;
        ArrayList<PayViewModel> payTypes;
        Context context;

        public PaymentMethodsAdapter(RecyclerView recyclerView, ArrayList<PayViewModel> payTypes,Context context) {
            this.recyclerView = recyclerView;
            this.payTypes = payTypes;
            this.context = context;
        }

        @NonNull
        @Override
        public PaymentMethodsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item, parent, false);

            return new PaymentMethodsAdapter.ViewHolder(itemView, context, payTypes);
        }

        @Override
        public void onBindViewHolder(PaymentMethodsAdapter.ViewHolder holder, int position) {
            holder.bind();
        }

        @Override
        public int getItemCount() {
            return payTypes.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private ImageView icon;
            LinearLayout container;
            TextView footer;
            View recycler;
            Context context;
            ArrayList<PayViewModel> payTypes;

            public ViewHolder(View itemView, Context context, ArrayList<PayViewModel> payTypes) {
                super(itemView);

                this.context = context;
                this.payTypes = payTypes;
                recycler = itemView;

                container = itemView.findViewById(R.id.check_bal_layout);
                icon = itemView.findViewById(R.id.image_desc);
                footer = itemView.findViewById(R.id.text_desc);

               //container.setOnClickListener(this);

                /*
                expandableLayout.setInterpolator(new DecelerateInterpolator());
                expandableLayout.setOnExpansionUpdateListener(this);
                swReason.setOnCheckedChangeListener(this);
                anyButton.setOnClickListener(this);
                expandButton.setOnClickListener(this);
                */
            }

            public void bind() {
                int position = getAdapterPosition();
                //boolean isSelected = position == selectedItem;

                icon.setImageResource(payTypes.get(position).getIcon());
                footer.setText(payTypes.get(position).getName());

                /*
                expandButton.setSelected(isSelected);
                expandableLayout.setExpanded(isSelected, false);
                */
            }


        }

    }
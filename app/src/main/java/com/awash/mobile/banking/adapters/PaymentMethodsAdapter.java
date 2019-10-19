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

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
                ExpandableLayout.OnExpansionUpdateListener,
                SwitchButton.OnCheckedChangeListener{
            private ExpandableLayout expandableLayout;
            private ImageView expandButton,icon;
            private Button anyButton;
            SwitchButton swReason;
            TextView header;
            View recycler;
            EditText schoolId,anyET,amountF,pinF,studentNameF,reasonF;
            Context context;
            ArrayList<PayViewModel> payTypes;

            public ViewHolder(View itemView, Context context, ArrayList<PayViewModel> payTypes) {
                super(itemView);

                this.context = context;
                this.payTypes = payTypes;
                recycler = itemView;

                expandableLayout = itemView.findViewById(R.id.expandable_layout);
                icon = itemView.findViewById(R.id.icon);
                anyButton = itemView.findViewById(R.id.btnAny);
                header = itemView.findViewById(R.id.textHeader);
                schoolId = itemView.findViewById(R.id.schoolId);
                anyET = itemView.findViewById(R.id.editText2);
                amountF = itemView.findViewById(R.id.amountF);
                pinF = itemView.findViewById(R.id.pinF);
                studentNameF = itemView.findViewById(R.id.studentName);
                reasonF = itemView.findViewById(R.id.reasonF);
                swReason = itemView.findViewById(R.id.switchAddReason);
                expandButton = itemView.findViewById(R.id.ic_arrowDown);


                expandableLayout.setInterpolator(new DecelerateInterpolator());
                expandableLayout.setOnExpansionUpdateListener(this);
                swReason.setOnCheckedChangeListener(this);
                anyButton.setOnClickListener(this);
                expandButton.setOnClickListener(this);
            }

            public void bind() {
                int position = getAdapterPosition();
                boolean isSelected = position == selectedItem;

                icon.setImageResource(payTypes.get(position).getIcon());
                header.setText(payTypes.get(position).getName());

                expandButton.setSelected(isSelected);
                expandableLayout.setExpanded(isSelected, false);
            }

            @Override
            public void onClick(View view) {

                int id = view.getId();

                int position = getAdapterPosition();

                if (id == R.id.ic_arrowDown){

                    PaymentMethodsAdapter.ViewHolder holder = (PaymentMethodsAdapter.ViewHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
                    if (holder != null) {
                        holder.expandButton.setSelected(false);
                        holder.expandableLayout.collapse();
                    }

                    if (position == selectedItem) {
                        selectedItem = UNSELECTED;
                    } else {
                        handle(position);
                        expandButton.setSelected(true);
                        expandableLayout.expand();
                        selectedItem = position;
                    }
                }else if (id == R.id.btnAny){

                    if (position == Constants.SCHOOL){
                        Toast.makeText(context,"SCHOOL btn",Toast.LENGTH_SHORT).show();
                    }
                    else if (position == Constants.AIRLINE){
                        Toast.makeText(context,"AIR btn",Toast.LENGTH_SHORT).show();
                    }
                    else if (position == Constants.DSTV){
                        Toast.makeText(context,"DSTV btn",Toast.LENGTH_SHORT).show();
                    }
                    else if (position == Constants.OTHER){
                        Toast.makeText(context,"OTHER btn",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {

                Log.w("ExpandableLayout", "State: " + state);

                if (state == ExpandableLayout.State.EXPANDED) {
                    recyclerView.smoothScrollToPosition(getAdapterPosition());
                }
                if (Constants.AIRLINE == getAdapterPosition()){
                    icon.setRotation(expansionFraction * 90);
                }
                if (Constants.OTHER == getAdapterPosition()){
                    icon.setRotation(expansionFraction * 360);
                }

                Log.w("ExpandableLayout", "State: " + state);
                expandButton.setRotation(expansionFraction * 180);

            }

            private void handle(int position){

                if (position == Constants.SCHOOL){
                    anyButton.setText(context.getResources().getString(R.string.pay));
                    Toast.makeText(context,"SCHOOL Expanded",Toast.LENGTH_SHORT).show();
                }
                else if (position == Constants.AIRLINE){
                    anyButton.setText(context.getResources().getString(R.string.buy));
                    schoolId.setVisibility(View.GONE);
                    studentNameF.setVisibility(View.GONE);
                    anyET.setHint(context.getResources().getString(R.string.booking_number));
                    Toast.makeText(context,"AIR Expanded",Toast.LENGTH_SHORT).show();
                }
                else if (position == Constants.DSTV){
                    anyButton.setText(context.getResources().getString(R.string.pay));
                    schoolId.setVisibility(View.GONE);
                    studentNameF.setVisibility(View.GONE);
                    anyET.setHint(context.getResources().getString(R.string.card_number));
                    Toast.makeText(context,"DSTV Expanded",Toast.LENGTH_SHORT).show();
                }
                else if (position == Constants.OTHER){
                    anyButton.setText(context.getResources().getString(R.string.transfer));
                    schoolId.setVisibility(View.GONE);
                    studentNameF.setVisibility(View.GONE);
                    anyET.setHint(context.getResources().getString(R.string.account_number));
                    Toast.makeText(context,"OTHER Expanded",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked){
                    reasonF.setVisibility(View.VISIBLE);
                }else {
                    reasonF.setVisibility(View.GONE);
                }

                recyclerView.smoothScrollToPosition(getAdapterPosition());
            }
        }

    }
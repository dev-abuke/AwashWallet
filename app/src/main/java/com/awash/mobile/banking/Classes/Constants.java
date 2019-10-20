package com.awash.mobile.banking.Classes;

import com.awash.mobile.banking.R;

public class Constants {

    public static final int SELECT_PHONE_NUMBER = 0;
    public static final int CALL_REQUEST_CODE = 11;
    public static final int SCHOOL  = 0;
    public static final int AIRLINE = 1;
    public static final int DSTV    = 2;
    public static final int OTHER   = 3;

    public static String RECHARGE_OTHER     = "*901*%s*2*%s*1*2*%s*%s*1*2#";
    public static String RECHARGE_OWN       = "*901*%s*2*%s*1*1*%s*1*2#";
    public static String SCHOOL_PAYMENT_LEN = "*901*%s*6*1*%s*%s*%s*%s*%s*%s*1*2#";
    public static String AIR_TICKET_LEN     = "*901*%s*6*2*%s*1*%s*%s*%s*2#";
    public static String DSTV_PAYMENT_LEN   = "*901*%s*6*3*%s*%s*%s*%s*1*2#";
    public static String OTHER_PAYMENT_LEN  = "*901*%s*6*4*%s*%s*%s*%s*1*2#";

    public static final String[] TAB_TITLES = {
            "School Bill",
            "Air Ticket",
            "DsTv Bill",
            "Others Bill"
    };

    public static final int[] ICONS = {
            R.drawable.ic_school,
            R.drawable.ic_plane,
            R.drawable.ic_dstv,
            R.drawable.ic_others
    };

    public static final String TYPE_SCHOOL_PAYMENT  = "School";
    public static final String TYPE_AIRLINE_TICKET  = "Airline Ticket";
    public static final String TYPE_DSTV_PAYMENT    = "Dstv";
    public static final String TYPE_OTHER_PAYMENT   = "Others";
    public static final String TYPE_RECHARGE        = "Recharge";
}

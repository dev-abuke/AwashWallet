package com.awash.mobile.banking.Classes;

import com.awash.mobile.banking.R;

public class Constants {

    //REQUEST CODES
    public static final int SELECT_PHONE_NUMBER_REQUEST_CODE = 0;
    public static final int CALL_REQUEST_CODE = 11;

    public static final int SCHOOL  = 0;
    public static final int AIRLINE = 1;
    public static final int DSTV    = 2;
    public static final int OTHER   = 3;

    //AWASH BANK USSD CALLING STRINGS
    public static String AWASH_RECHARGE_OTHER = "*901*%s*2*%s*1*2*%s*%s*1*2#";
    public static String AWASH_RECHARGE_OWN = "*901*%s*2*%s*1*1*%s*1*2#";
    public static String AWASH_CHECK_BALANCE = "*901*%s*1*%s#";
    public static String AWASH_SCHOOL_PAYMENT_LEN = "*901*%s*6*1*%s*%s*%s*%s*%s*%s*1*2#";
    public static String AWASH_AIR_TICKET_LEN     = "*901*%s*6*2*%s*1*%s*%s*%s*2#";
    public static String AWASH_DSTV_PAYMENT_LEN = "*901*%s*6*3*%s*%s*%s*%s*1*2#";
    public static String AWASH_OTHER_PAYMENT_LEN  = "*901*%s*6*4*%s*%s*%s*%s*1*2#";

    //COMMERCIAL BANK USSD CALLING STRINGS
    public static String CBE_TRANSFER_MONEY = "*886*1*%s*2*%s*%s*%s*%s*%s*%s*1*2#";

    //TABS TO BE SHOWN ON UI
    public static final String[] TAB_TITLES = {
            "School Bill",
            "Air Ticket",
            "DsTv Bill",
            "Transfer",
            "Others Bill"
    };

    //USE CASE FOR ALERT DIALOGUE
    public static final String TYPE_SCHOOL_PAYMENT  = "School";
    public static final String TYPE_AIRLINE_TICKET  = "Airline Ticket";
    public static final String TYPE_DSTV_PAYMENT    = "Dstv";
    public static final String TYPE_OTHER_PAYMENT   = "Others";
    public static final String TYPE_RECHARGE        = "Recharge";
}

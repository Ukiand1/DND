package com.example.uros.dnd.broadcast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.example.uros.dnd.broadcast.PhoneCallReceiver;

import java.util.Date;

/**
 * Created by Mihailo on 9/6/2015.
 */
public class SMSAnswer extends PhoneCallReceiver{


    public SMSAnswer() {
        sms = null;
    }

    String sms;


    @Override
    protected void onIncomingCallStarted(Context ctx, String number, Date start) {
        super.onIncomingCallStarted(ctx, number, start);
    }

    public void sendSMS(Context ctx, String number) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, sms, null, null);
    }
}


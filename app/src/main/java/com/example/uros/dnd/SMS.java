package com.example.uros.dnd;

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
public class SMS extends PhoneCallReceiver{


    String textPhoneNo; // ne treba
    String sms;


    @Override
    protected void onIncomingCallStarted(Context ctx, String number, Date start) {
        super.onIncomingCallStarted(ctx, number, start);
        this.textPhoneNo = number;
        sendSMS(ctx,textPhoneNo);
    }

    public void sendSMS(Context ctx, String number) {

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(textPhoneNo, null, sms, null, null);
            Toast.makeText(ctx, "SMS Sent!",
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(ctx,
                    "SMS faild, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}


package com.example.uros.dnd.broadcast;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

import java.util.Date;

/**
 * Created by Uros on 0022 22 Aug.
 */
public class CallReceiver extends PhoneCallReceiver {
    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        super.onIncomingCallEnded(ctx, number, start, end);
    }

    @Override
    protected void onIncomingCallStarted(Context ctx, String number, Date start) {

        AudioManager audioManager = (AudioManager)ctx.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {
        super.onMissedCall(ctx, number, start);
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
        super.onOutgoingCallEnded(ctx, number, start, end);
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
        super.onOutgoingCallStarted(ctx, number, start);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}


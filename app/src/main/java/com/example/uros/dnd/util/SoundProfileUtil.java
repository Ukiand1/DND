package com.example.uros.dnd.util;

import android.content.Context;
import android.media.AudioManager;
import android.widget.Toast;

/**
 * Created by Mihailo on 9/19/2015.
 */
public class SoundProfileUtil {

    private static Boolean isVibration = null;
    private static Integer volume = null;

    public static void setPreviousState(Context context) {
        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL) {
            isVibration = true;
            volume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
            return;
        }
        if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE) {
            isVibration = true;
            volume = 0;
            return;
        }
        if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
            isVibration = false;
            volume = 0;
            return;
        }



    }

    public static boolean isPreviousStateSetted() {
        return isVibration != null && volume != null;
    }


    private static void setVibrateMode(Context context) {
        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }

    private static void setNormalMode(Context context) {
        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        audioManager.setStreamVolume(AudioManager.STREAM_RING, maxVolume, 0);
    }

    private static void setSilentMode(Context context) {
        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }

    public static void setMode(Context context, int soundLevel, boolean vibration) {
        if (soundLevel == 0 && vibration == false) {
            setSilentMode(context);
            return;
        }
        if (soundLevel == 100 && vibration == true) {
            setNormalMode(context);
            return;
        }
        if (soundLevel == 0 && vibration == true) {
            setVibrateMode(context);
            return;
        }
        if (soundLevel > 0 && vibration == true) {
            setNormalMode(context);
            return;
        }


    }

    public static void resetMode(Context context) {
        if (isPreviousStateSetted())
            setMode(context,volume,isVibration);
        volume = null;
        isVibration = null;

    }




}

package com.example.uros.dnd.domen;

/**
 * Created by Uros on 0023 23 Aug.
 */
public class Action {

    private long actionId;
    private String name;
    private String call;
    private boolean vibrate;
    private int sound;


    public Action(){
    }

    public Action(long actionId, String name, String call, boolean vibrate, int sound) {
        this.actionId = actionId;
        this.name = name;
        this.call = call;
        this.vibrate = vibrate;
        this.sound = sound;
    }

    public long getActionId() {
        return actionId;
    }

    public void setActionId(long actionId) {
        this.actionId = actionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }


}

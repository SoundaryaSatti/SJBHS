package com.integro.sibhs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NotificationList {
    @SerializedName("sjbhs_notification")
    private ArrayList<Notification> notificationArrayList;

    private String success;

    private String message;
    public ArrayList<Notification>getNotificationArrayList()
    {
        return notificationArrayList;
    }

    public NotificationList(ArrayList<Notification> notificationArrayList) {
        this.notificationArrayList = notificationArrayList;
    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

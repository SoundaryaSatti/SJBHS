package com.integro.sibhs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CalenderList {

    private String success;

    //private Sjbhs_events[] sjbhs_events;
    @SerializedName("sjbhs_events")
    ArrayList<Calender> calenderArrayList;
    private String message;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<Calender> getCalenderArrayList() {
        return calenderArrayList;
    }

    public CalenderList(ArrayList<Calender> calenderArrayList) {
        this.calenderArrayList = calenderArrayList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

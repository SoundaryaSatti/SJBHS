package com.integro.sibhs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GoverningBodyList {
    private String success;

    private String message;

    //private Sjbhs_leadership[] sjbhs_leadership;
    @SerializedName("sjbhs_leadership")
    ArrayList<GoverningBody> governingBodyArrayList;

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

    //public Sjbhs_leadership[] getSjbhs_leadership ()
    public ArrayList<GoverningBody> getGoverningBodyArrayList() {
        return governingBodyArrayList;
    }

    public void setGoverningBodyArrayList(ArrayList<GoverningBody> governingBodyArrayList) {
        this.governingBodyArrayList = governingBodyArrayList;
    }
}

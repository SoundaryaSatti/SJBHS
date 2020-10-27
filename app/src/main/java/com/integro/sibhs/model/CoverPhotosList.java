package com.integro.sibhs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CoverPhotosList {
    //private Sjbhs_coverphoto[] sjbhs_coverphoto;

    @SerializedName("sjbhs_coverphoto")
    ArrayList<CoverPhotos> coverPhotosArrayList;
    private String success;

    private String message;

    //public Sjbhs_coverphoto[] getSjbhs_coverphoto ()

    public ArrayList<CoverPhotos> getCoverPhotosArrayList() {
        return coverPhotosArrayList;
    }

    public void setCoverPhotosArrayList(ArrayList<CoverPhotos> coverPhotosArrayList) {
        this.coverPhotosArrayList = coverPhotosArrayList;
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

package com.integro.sibhs.apis;

import com.integro.sibhs.model.CalenderList;
import com.integro.sibhs.model.CoverPhotosList;
import com.integro.sibhs.model.GoverningBodyList;
import com.integro.sibhs.model.News;
import com.integro.sibhs.model.NewsImagesList;
import com.integro.sibhs.model.NewsLetter;
import com.integro.sibhs.model.NewsLetterList;
import com.integro.sibhs.model.NewsList;
import com.integro.sibhs.model.NotificationList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("sjbhs_news.php")
    Call<NewsList> getNewsList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("sjbhs_notification.php")
    Call<NotificationList> getNotificationList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("sjbhs_newsletter.php")
    Call<NewsLetterList> getNewsLetterList(@Field("updated_at")String updated_at);

   @FormUrlEncoded
    @POST("sjbhs_newsimage.php")
    Call<NewsImagesList> getNewsImagesList(@Field("news_id")String id);

    @FormUrlEncoded
    @POST("sjbhs_leadership.php")
    Call<GoverningBodyList> getGoverningBodyList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("sjbhs_coverphoto.php")
    Call<CoverPhotosList> getCoverPhotoList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("sjbhs_events.php")
    Call<CalenderList> getCalenderList(@Field("updated_at")String updated_at);

}

package com.integro.sibhs.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.github.demono.AutoScrollViewPager;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.integro.sibhs.activities.CalenderActivity;
import com.integro.sibhs.activities.CourseDetailsActivity;
import com.integro.sibhs.activities.MainActivity;
import com.integro.sibhs.activities.NewsLetterActivity;
import com.integro.sibhs.activities.WebActivity;
import com.integro.sibhs.adapters.CoverPhotosViewPagerAdapter;
import com.integro.sibhs.adapters.NewsViewPagerAdapter;
import com.integro.sibhs.apis.ApiClients;
import com.integro.sibhs.apis.ApiServices;
import com.integro.sibhs.model.CoverPhotos;
import com.integro.sibhs.model.CoverPhotosList;
import com.integro.sibhs.model.News;
import com.integro.sibhs.model.NewsList;
import com.integro.sjbhs.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.MediaStore.Video.Thumbnails.VIDEO_ID;

public class HomeFragment extends Fragment implements View.OnClickListener {
    Context context;
    TextView tvLogin, tvAdmission, tvNewsletter, tvCalender, tvNews, tvClubs;
    ImageView imageViewlogo;
    YouTubePlayerView youTubePlayerView;
    TextView tvFacultyLogin, tvParentLogin;


    //public static final String API_KEY = "AIzaSyAe2T6TA18CbiH-_C7SOZMjTl2MIRL25f4";
    //public static final String vedioId = "YxehHRJk3_U";
    //bsk key
    //public static final String API_KEY="AIzaSyAFX8nBvkaczNYIBK7aRORsPUoPG_wOBn8";
    public YouTubePlayer youTubePlayer;
    private ApiServices apiServices;
    private AutoScrollViewPager vpNews;
    private ArrayList<News> newsArrayList;
    Call<NewsList> newsListCall;
    private NewsViewPagerAdapter newsViewPagerAdapter;

    RecyclerView rvcoverphotos;
    private AutoScrollViewPager vpCoverPhotos;
    private ArrayList<CoverPhotos> coverPhotosArrayList;
    Call<CoverPhotosList> coverPhotosListCall;
    private CoverPhotosViewPagerAdapter coverPhotosViewPagerAdapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        apiServices = ApiClients.getClients().create(ApiServices.class);
        newsArrayList = new ArrayList<>();
        vpNews = view.findViewById(R.id.vpNews);

        coverPhotosArrayList = new ArrayList<>();
        rvcoverphotos = view.findViewById(R.id.rvcoverphotos);
        //vpCoverPhotos = view.findViewById(R.id.vpCoverphoto);


        tvLogin = view.findViewById(R.id.tvLogin);
        tvAdmission = view.findViewById(R.id.tvAdmission);
        tvNewsletter = view.findViewById(R.id.tvNewsletter);
        tvCalender = view.findViewById(R.id.tvCalender);
        imageViewlogo = view.findViewById(R.id.cvImagelogo);
        tvNews = view.findViewById(R.id.tvNews);
        tvClubs = view.findViewById(R.id.tvClubs);
        youTubePlayerView = view.findViewById(R.id.youtubeplayerview);

        //vpCoverPhotos.setOnClickListener(this);
        // vpText.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvAdmission.setOnClickListener(this);
        tvNewsletter.setOnClickListener(this);
        tvCalender.setOnClickListener(this);
        imageViewlogo.setOnClickListener(this);
        tvNews.setOnClickListener(this);
        tvClubs.setOnClickListener(this);
        //youTubePlayerView.setOnClickListener(this);
        getNewsList();
        //getCoverPhotos();
        return view;

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.vpCoverphoto:
                Toast.makeText(getContext(), "item clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tvLogin:

                getLogin();
                // Toast.makeText(getContext(), "item clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tvAdmission:
                Intent intent = new Intent(getContext(), CourseDetailsActivity.class);
                startActivity(intent);
                Toast.makeText(getContext(), "item clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tvNewsletter:
                Intent i = new Intent(getContext(), NewsLetterActivity.class);
                startActivity(i);
                //Toast.makeText(getContext(), "item clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tvCalender:
                Intent intentcal = new Intent(getContext(), CalenderActivity.class);
                startActivity(intentcal);
                // getCalender();
                break;

            case R.id.cvImagelogo:
                Toast.makeText(getContext(), "item clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tvNews:
                Toast.makeText(getContext(), "item clicked", Toast.LENGTH_SHORT).show();
                break;


            case R.id.tvClubs:
                Toast.makeText(getContext(), "item clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.youtubeplayerview:

                getLifecycle().addObserver(youTubePlayerView);
                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(YouTubePlayer youTubePlayer) {
                        super.onReady(youTubePlayer);
                        String videoId = "E15ccb7UZMU";
                        youTubePlayer.loadVideo(videoId, 0f);
                       /* if (!b) {
                            if (VIDEO_ID != null) {
                                YPlayer = youTubePlayer;
                                YPlayer.cueVideo("Fd5E55ItlnI");
                                YPlayer.setFullscreen(false);
                            } else {
                                YPlayer.cueVideo("Fd5E55ItlnI");
                                YPlayer.pause();
                                YPlayer.setFullscreen(false);
                            }
                        }*/

                    }
                });
        }
    }


    private void getNewsList() {
        String date = "2019-10-26 22:00:51";
        newsListCall = apiServices.getNewsList(date);
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                int size = response.body().getNewsArrayList().size();
                for (int i = 0; i < size; i++) {
                    newsArrayList.add(response.body().getNewsArrayList().get(i));
                }
                newsViewPagerAdapter = new NewsViewPagerAdapter(getContext(), newsArrayList);
                vpNews.setAdapter(newsViewPagerAdapter);
                vpNews.startAutoScroll(3000);
                vpNews.setCycle(true);

            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCoverPhotos() {
        String date = "";
        coverPhotosListCall = apiServices.getCoverPhotoList(date);
        coverPhotosListCall.enqueue(new Callback<CoverPhotosList>() {
            @Override
            public void onResponse(Call<CoverPhotosList> call, Response<CoverPhotosList> response) {
                if (response.isSuccessful()) {
                    int size = response.body().getCoverPhotosArrayList().size();
                    for (int i = 0; i < size; i++) {
                        coverPhotosArrayList.add(response.body().getCoverPhotosArrayList().get(i));
                    }
                    if (coverPhotosArrayList.size() > 0) {
                        coverPhotosViewPagerAdapter = new CoverPhotosViewPagerAdapter(getContext(), coverPhotosArrayList);
                        vpCoverPhotos.startAutoScroll(3000);
                        vpCoverPhotos.setCycle(true);
                        vpCoverPhotos.setAdapter(coverPhotosViewPagerAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<CoverPhotosList> call, Throwable t) {
            }
        });
    }

    public void getLogin() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.login_alret, null);
        dialogBuilder.setView(view);
        tvFacultyLogin = view.findViewById(R.id.tv_faculty_login);
        tvParentLogin = view.findViewById(R.id.tv_parent_login);

        tvParentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentParent = new Intent(getContext(), WebActivity.class);
                String UrlParent = "https://sjbhs.pupilpod.in/home?destination=home";
                intentParent.putExtra("TAG", UrlParent);
                startActivity(intentParent);
            }
        });
        tvFacultyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFaculty = new Intent(getContext(), WebActivity.class);
                String UrlFacuty = "http://sjbhs.schoolphins.com/";
                intentFaculty.putExtra("TAG", UrlFacuty);
                startActivity(intentFaculty);
            }
        });
        dialogBuilder.show();
    }

}


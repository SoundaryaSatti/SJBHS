package com.integro.sibhs.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.demono.AutoScrollViewPager;
import com.integro.sibhs.adapters.NewsImagesAdapterVp;
import com.integro.sibhs.apis.ApiClients;
import com.integro.sibhs.apis.ApiServices;
import com.integro.sibhs.model.News;
import com.integro.sibhs.model.NewsImages;
import com.integro.sibhs.model.NewsImagesList;
import com.integro.sjbhs.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailsActivity extends AppCompatActivity {

    private static final String TAG = "NewsDetailsActivity";

    String itemId;

    ArrayList<NewsImages> newsImagesArrayList;
    AutoScrollViewPager vpNewsImages;
    NewsImagesAdapterVp newsImagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        newsImagesArrayList = new ArrayList<>();

        News news = (News) getIntent().getSerializableExtra("NEWS");

        TextView tvShare = findViewById(R.id.tvShare);
        vpNewsImages = findViewById(R.id.vpNewsImage);

        TextView tvDate = findViewById(R.id.tvDate);
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvDiscription = findViewById(R.id.tvDescription);


        tvDate.setText(news.getDate());
        tvTitle.setText(news.getTitle());
        tvDiscription.setText(news.getDescription());

        itemId = news.getId();
        // String newsId = getIntent().getStringExtra("NEWS_ID");

        Log.i(TAG, "onCreate: " + itemId);

        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "http://sjbhs.edu.in/sjbhs_app/newsshare.php?id=" + news.getId());
                startActivity(intent);
            }
        });
        getNewsImagesList();
    }

    public void getNewsImagesList() {

        Call<NewsImagesList> newsImagesCall;
        newsImagesCall = ApiClients.getClients().create(ApiServices.class).getNewsImagesList(itemId);
        newsImagesCall.enqueue(new Callback<NewsImagesList>() {
            @Override
            public void onResponse(Call<NewsImagesList> call, Response<NewsImagesList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getNewsImagesArrayList() != null) {
                        int size = response.body().getNewsImagesArrayList().size();
                        Log.d("response", "NewsImages" + size);
                        newsImagesArrayList.addAll(response.body().getNewsImagesArrayList());
                        newsImagesAdapter = new NewsImagesAdapterVp(getApplicationContext(), newsImagesArrayList);
                        vpNewsImages.startAutoScroll(3000);
                        vpNewsImages.setCycle(true);
                        vpNewsImages.setAdapter(newsImagesAdapter);
                        Log.i(TAG, "onResponse: " + newsImagesArrayList.size());
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsImagesList> call, Throwable t) {

            }
        });


    }
}



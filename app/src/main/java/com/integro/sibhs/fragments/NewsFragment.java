
package com.integro.sibhs.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.integro.sibhs.adapters.NewsAdapters;
import com.integro.sibhs.apis.ApiClients;
import com.integro.sibhs.apis.ApiServices;
import com.integro.sibhs.model.News;
import com.integro.sibhs.model.NewsList;
import com.integro.sjbhs.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class NewsFragment extends Fragment {

    ApiServices apiServices;
    RecyclerView rvNews;
    StaggeredGridLayoutManager manager;
    NewsAdapters adapter;
    ArrayList<News> newsArrayList;
    Call<NewsList> newsListCall;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvNews = view.findViewById(R.id.rvNews);
        newsArrayList = new ArrayList<>();
        manager = new StaggeredGridLayoutManager(2,RecyclerView.VERTICAL);
        rvNews.setLayoutManager(manager);
        getNewsList();
        return view;


    }

    public void getNewsList() {

        String date = "2020-1-25 22:54:43";
        newsListCall = apiServices.getNewsList(date);
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "onresponse:not success");
                    return;
                }
                if (response.body().getNewsArrayList() == null) {
                    Log.i(TAG, "onresponse:null");
                    return;
                }
                Log.i(TAG, "onresponse: " + response.body().getNewsArrayList().size());
                int size = response.body().getNewsArrayList().size();
                /*for (int i = 0; i < size; i++) {
                    newsArrayList.add(response.body().getNewsArrayList().get(i));*/
                    newsArrayList.addAll(response.body().getNewsArrayList());
                    rvNews.setLayoutManager(manager);
                    rvNews.setHasFixedSize(true);

                    adapter = new NewsAdapters(getContext(), newsArrayList);
                    rvNews.setAdapter(adapter);
                }

                @Override
                public void onFailure (Call < NewsList > call, Throwable t){
                    Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onfailure:" + t.getMessage());
                }
            });
        }

    }

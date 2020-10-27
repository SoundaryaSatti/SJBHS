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

import com.integro.sibhs.adapters.NewsAdapters;
import com.integro.sibhs.adapters.NotificationAdapters;
import com.integro.sibhs.apis.ApiClients;
import com.integro.sibhs.apis.ApiServices;
import com.integro.sibhs.model.News;
import com.integro.sibhs.model.NewsList;
import com.integro.sibhs.model.Notification;
import com.integro.sibhs.model.NotificationList;
import com.integro.sjbhs.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class NotificationFragment extends Fragment {
    ApiServices apiServices;
    RecyclerView rvNotification;
    LinearLayoutManager manager;
    NotificationAdapters adapter;
    ArrayList<Notification> notificationArrayList;
    Call<NotificationList> notificationListCall;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_notification, container, false);
        apiServices= ApiClients.getClients().create(ApiServices.class);
        rvNotification=view.findViewById(R.id.rvNotification);
        notificationArrayList=new ArrayList<>();
        manager=new LinearLayoutManager(getContext());
        rvNotification.setLayoutManager(manager);
        getNotificationList();
        return view;
    }
    public void getNotificationList(){

        String date="2018-11-26 02:54:04";
        notificationListCall=apiServices.getNotificationList(date);
        notificationListCall.enqueue(new Callback<NotificationList>() {
            @Override
            public void onResponse(Call<NotificationList> call, Response<NotificationList> response) {
                if (!response.isSuccessful()){
                    Log.i(TAG,"onresponse:not success");
                    return;
                }
                if (response.body().getNotificationArrayList()==null){
                    Log.i(TAG,"onresponse:null");
                    return;
                }
                Log.i(TAG,"onresponse: "+response.body().getNotificationArrayList().size());
                int size=response.body().getNotificationArrayList().size();
/*
                for (int i=0; i<size;i++){
*/
                    notificationArrayList.addAll(response.body().getNotificationArrayList());

                rvNotification.setLayoutManager(manager);
                rvNotification.setHasFixedSize(true);

                adapter= new NotificationAdapters(getContext(),notificationArrayList);
                rvNotification.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<NotificationList> call, Throwable t) {

                Toast.makeText(getContext(),""+t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.i(TAG,"onfailure:"+t.getMessage());
            }
        });
    }

    }


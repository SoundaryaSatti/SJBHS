package com.integro.sibhs.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.sibhs.adapters.CalenderAdapter;
import com.integro.sibhs.apis.ApiClients;
import com.integro.sibhs.apis.ApiServices;
import com.integro.sibhs.model.Calender;
import com.integro.sibhs.model.CalenderList;
import com.integro.sjbhs.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalenderActivity extends AppCompatActivity {
    ApiServices apiServices;
    RecyclerView rvcalender;
    LinearLayoutManager manager;
    CalenderAdapter calenderAdapter;
    ArrayList<Calender> calendarArrayList;
    Call<CalenderList> calenderListCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvcalender = findViewById(R.id.rvcalender);
        manager = new LinearLayoutManager(this);
        rvcalender.setLayoutManager(manager);
        calendarArrayList = new ArrayList<>();
        getCalender();
    }

    

    public void getCalender() {

        String date = "2019-10-26 22:00:51";
        calenderListCall = apiServices.getCalenderList(date);
        calenderListCall.enqueue(new Callback<CalenderList>() {
            @Override
            public void onResponse(Call<CalenderList> call, Response<CalenderList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCalenderArrayList() != null) {
                        int size = response.body().getCalenderArrayList().size();
                        Log.d("RESPONSE", "calen" + size);
                        calendarArrayList.addAll(response.body().getCalenderArrayList());
                        if (calendarArrayList.size() > 0) {
                            calenderAdapter = new CalenderAdapter(getApplicationContext(), calendarArrayList);
                            rvcalender.setLayoutManager(manager);
                            rvcalender.setHasFixedSize(true);
                            rvcalender.setAdapter(calenderAdapter);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "respnse null", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "response fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CalenderList> call, Throwable t) {

            }
        });
    }
}

package com.integro.sibhs.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.sibhs.adapters.GoverningBodyAdapter;
import com.integro.sibhs.apis.ApiClients;
import com.integro.sibhs.apis.ApiServices;
import com.integro.sibhs.model.GoverningBody;
import com.integro.sibhs.model.GoverningBodyList;
import com.integro.sjbhs.R;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoverningBodyActivity extends AppCompatActivity {
    ApiServices apiServices;
    RecyclerView rvGoverningbody;
    LinearLayoutManager manager;
    GoverningBodyAdapter governingBodyAdapter;
    ArrayList<GoverningBody> governingBodyArrayList;
    Call<GoverningBodyList> governingBodyCall;
    private String itemId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_governing_body);

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvGoverningbody = findViewById(R.id.rvgoverningbody);
        manager = new LinearLayoutManager(this);
        rvGoverningbody.setLayoutManager(manager);
        governingBodyArrayList = new ArrayList<>();
        getGoverningBodyList();
    }

    public void getGoverningBodyList() {
        String date = "2020-03-03 02:53:43";
        //   Log.d("ITEM_ID",itemId);
        governingBodyCall = apiServices.getGoverningBodyList(date);
        governingBodyCall.enqueue(new Callback<GoverningBodyList>() {
            @Override
            public void onResponse(Call<GoverningBodyList> call, Response<GoverningBodyList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getGoverningBodyArrayList() != null) {
                        int size = response.body().getGoverningBodyArrayList().size();
                        Log.d("RESPONSE", "GoverningBody" + size);
                        for (int i = 0; i < size; i++) {
                            governingBodyArrayList.add(response.body().getGoverningBodyArrayList().get(i));
                        }
                        if (governingBodyArrayList.size() > 0) {
                            governingBodyAdapter = new GoverningBodyAdapter(getApplicationContext(), governingBodyArrayList);
                            rvGoverningbody.setAdapter(governingBodyAdapter);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "response null", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "response fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GoverningBodyList> call, Throwable t) {

            }
        });


    }
}

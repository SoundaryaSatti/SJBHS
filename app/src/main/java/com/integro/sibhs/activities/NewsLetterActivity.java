package com.integro.sibhs.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.sibhs.adapters.NewsAdapters;
import com.integro.sibhs.adapters.NewsLetterAdapter;
import com.integro.sibhs.apis.ApiClients;
import com.integro.sibhs.apis.ApiServices;
import com.integro.sibhs.model.NewsLetter;
import com.integro.sibhs.model.NewsLetterList;
import com.integro.sjbhs.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class NewsLetterActivity extends AppCompatActivity {

    ApiServices apiServices;
    RecyclerView rvNewsletter;
    LinearLayoutManager manager;
    NewsLetterAdapter newsLetterAdapter;
    ArrayList<NewsLetter>newsLetterArrayList;
    Call<NewsLetterList> newsLetterlistCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_letter);

        apiServices= ApiClients.getClients().create(ApiServices.class);
        rvNewsletter=findViewById(R.id.rvNewsletter);
        manager=new LinearLayoutManager(this);
        rvNewsletter.setLayoutManager(manager);
        newsLetterArrayList=new ArrayList<>();
        getNewsLetterList();
    }
    public void getNewsLetterList(){
      String date="";
      newsLetterlistCall=apiServices.getNewsLetterList(date);
      newsLetterlistCall.enqueue(new Callback<NewsLetterList>() {
          @Override
          public void onResponse(Call<NewsLetterList> call, Response<NewsLetterList> response) {
              if(response.isSuccessful()){
                  if (response.body().getNewsLetterArrayList()!=null){
                      int size=response.body().getNewsLetterArrayList().size();
                      Log.d("RESPONSE", "news Size " + size);
                      for (int i=0; i<size; i++){
                          newsLetterArrayList.add(response.body().getNewsLetterArrayList().get(i));
                      }


                      if(newsLetterArrayList.size()>0){
                          newsLetterAdapter=new NewsLetterAdapter(getApplicationContext(),newsLetterArrayList);
                          rvNewsletter.setAdapter(newsLetterAdapter);
                      }

                  }
                  else {
                      Toast.makeText(getApplicationContext(),"response null",Toast.LENGTH_SHORT).show();
                  }
              }
              else {
                  Toast.makeText(getApplicationContext(),"response fail",Toast.LENGTH_SHORT).show();

              }
          }

          @Override
          public void onFailure(Call<NewsLetterList> call, Throwable t) {

          }
      });

    }
}

package com.integro.sibhs.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.sibhs.activities.NewsDetailsActivity;
import com.integro.sibhs.model.News;
import com.integro.sjbhs.R;

import java.util.ArrayList;

public class NewsAdapters extends RecyclerView.Adapter<NewsAdapters.MyViewHolder> {
    Context context;
    ArrayList<News> newsArrayList;

    public NewsAdapters(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_news,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        News news=newsArrayList.get(position);

        Glide.with(context)
                .load(news.getImage())
                .into(holder.ivImage);

        holder.tvDate.setText(news.getDate());
        holder.tvTitle.setText(news.getTitle());
        holder.tvDescription.setText(news.getDescription());

         holder.cardViewNews.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(context, NewsDetailsActivity.class);
                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 intent.putExtra("NEWS",news);
                 context.startActivity(intent);
             }
         });
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
     CardView cardViewNews;
     ImageView ivImage;
     TextView  tvTitle,tvDate,tvDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewNews=itemView.findViewById(R.id.crNews);
            ivImage=itemView.findViewById(R.id.ivImage);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvDate=itemView.findViewById(R.id.tvDate);
            tvDescription=itemView.findViewById(R.id.tvDescription);

        }
    }
}

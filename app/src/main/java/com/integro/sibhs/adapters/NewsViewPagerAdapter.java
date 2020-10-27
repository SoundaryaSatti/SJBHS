package com.integro.sibhs.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.integro.sibhs.activities.NewsDetailsActivity;
import com.integro.sibhs.model.News;
import com.integro.sibhs.model.NewsImages;
import com.integro.sjbhs.R;

import java.util.ArrayList;


public class NewsViewPagerAdapter extends PagerAdapter {
    Context context;
    ArrayList<News> newsArrayList;
    LayoutInflater inflater;

    public NewsViewPagerAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }


    @Override
    public int getCount() {
        return newsArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ImageView ivFullImage;

        TextView tvTitle,tvDescription;

        News news=newsArrayList.get(position);

        View view = LayoutInflater.from(context).inflate(R.layout.card_coverphots2, container, false);

        ivFullImage = (ImageView) view.findViewById(R.id.ivImage);
        tvTitle = view.findViewById(R.id.tv_title);
        tvDescription=view.findViewById(R.id.tvDescription);
        tvTitle.setText(newsArrayList.get(position).getTitle());
        tvDescription.setText(newsArrayList.get(position).getDescription());

        Glide.with(context)
                .load(newsArrayList.get(position).getImage())
                .into(ivFullImage);

        ((ViewPager) container).addView(view);

        ivFullImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NewsDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("NEWS", news);
                context.startActivity(intent);

            }
        });
        tvDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentText=new Intent(context, NewsDetailsActivity.class);
                intentText.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intentText.putExtra("NEWS", news);
                context.startActivity(intentText);
            }
        });
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTitle=new Intent(context, NewsDetailsActivity.class);
                intentTitle.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intentTitle.putExtra("NEWS", news);
                context.startActivity(intentTitle);

            }
        });

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}

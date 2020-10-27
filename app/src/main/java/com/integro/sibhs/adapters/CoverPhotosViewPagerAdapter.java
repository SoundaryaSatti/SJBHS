package com.integro.sibhs.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.integro.sibhs.activities.CoverPhotosDetails;
import com.integro.sibhs.fragments.HomeFragment;
import com.integro.sibhs.model.CoverPhotos;
import com.integro.sjbhs.R;

import java.util.ArrayList;

public class CoverPhotosViewPagerAdapter extends PagerAdapter {
    ArrayList<CoverPhotos> coverPhotosArrayList;
    Context context;
    LayoutInflater inflater;


    public CoverPhotosViewPagerAdapter(Context context, ArrayList<CoverPhotos> coverPhotosArrayList) {
        this.coverPhotosArrayList = coverPhotosArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return coverPhotosArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView ivImage;
        CoverPhotos coverPhotos = coverPhotosArrayList.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.card_coverphotos, container, false);
        ivImage = view.findViewById(R.id.ivImagecp);
        Glide.with(context).load(coverPhotosArrayList.get(position).getImage()).into(ivImage);
        ((ViewPager) container).addView(view);
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CoverPhotosDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("COVERPHOTOS", coverPhotos);
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);

    }
}

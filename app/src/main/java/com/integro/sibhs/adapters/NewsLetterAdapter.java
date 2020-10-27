package com.integro.sibhs.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.sibhs.model.NewsLetter;
import com.integro.sjbhs.R;

import java.util.ArrayList;

public class NewsLetterAdapter extends RecyclerView.Adapter<NewsLetterAdapter.MyViewHolder> {
    Context context;
    ArrayList<NewsLetter> newsLetterArrayList;

    public NewsLetterAdapter(Context context, ArrayList<NewsLetter> newsLetterArrayList) {
        this.context = context;
        this.newsLetterArrayList = newsLetterArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_newsletter,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final NewsLetter newsLetter=newsLetterArrayList.get(position);
        holder.tvnltitle.setText(newsLetter.getTitle());

        Glide.with(context).load(newsLetter.getImage()).into(holder.imageviewnl);
        holder.tvnlpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setData(Uri.parse(newsLetter.getPdf()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return newsLetterArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageviewnl;
        TextView tvnltitle,tvnlpdf;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        imageviewnl=itemView.findViewById(R.id.imageViewnl);
        tvnltitle=itemView.findViewById(R.id.tvnltitle);
        tvnlpdf=itemView.findViewById(R.id.tvnlpdf);
        }

    }
}

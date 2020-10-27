package com.integro.sibhs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.sibhs.model.Notification;
import com.integro.sjbhs.R;

import java.util.ArrayList;

public class NotificationAdapters extends RecyclerView.Adapter<NotificationAdapters.MyViewHolder>{

    Context context;
    ArrayList<Notification>notificationArrayList;

    public NotificationAdapters(Context context, ArrayList<Notification> notificationArrayList) {
        this.context = context;
        this.notificationArrayList = notificationArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_notification,parent,false);
        return new  MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvDate.setText(notificationArrayList.get(position).getDate());
        holder.tvTitle.setText(notificationArrayList.get(position).getTitle());
        holder.tvTopicName.setText(notificationArrayList.get(position).getTopicname());
        holder.tvDescription.setText(notificationArrayList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardViewNotification;
        TextView tvDate,tvTitle,tvTopicName,tvDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewNotification=itemView.findViewById(R.id.crNotification);
            tvDate=itemView.findViewById(R.id.tvDate);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvTopicName=itemView.findViewById(R.id.tvTopicName);
            tvDescription=itemView.findViewById(R.id.tvDescription);

        }
    }
}

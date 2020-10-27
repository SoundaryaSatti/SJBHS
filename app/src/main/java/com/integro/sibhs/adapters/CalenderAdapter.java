package com.integro.sibhs.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.sibhs.model.Calender;
import com.integro.sjbhs.R;

import java.util.ArrayList;
import java.util.Calendar;

public class CalenderAdapter extends RecyclerView.Adapter<CalenderAdapter.MyViewHolder> {
    Context context;
    ArrayList<Calender>calenderArrayList;

    public CalenderAdapter(Context context, ArrayList<Calender> calenderArrayList) {
        this.context = context;
        this.calenderArrayList = calenderArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_calender,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Calender calendar=calenderArrayList.get(position);
        holder.tvdate.setText(calendar.getDate());
        holder.tvtitle.setText(calendar.getTitle());

              holder.tvAddtocalender.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Calendar calendar1=Calendar.getInstance();
               Intent intent = new Intent(Intent.ACTION_EDIT);
               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               intent.setType("vnd.android.cursor.item/event");
               intent.putExtra("begintime", calendar1.getTimeInMillis());
               intent.putExtra("allday", true);
               intent.putExtra("rule", "FREQ=YEARLY");
               intent.putExtra("endTime", calendar1.getTimeInMillis()+60*60*1000);
               intent.putExtra("date",""+calendar.getDate());
               intent.putExtra("title", ""+calendar.getTitle());
               context.startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return calenderArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvdate;
        TextView tvtitle;
        TextView tvAddtocalender;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvdate = itemView.findViewById(R.id.tvcalanderdate);
            tvtitle = itemView.findViewById(R.id.tvcalendertitle);
            tvAddtocalender = itemView.findViewById(R.id.tvaddtocalender);
        }
    }
}

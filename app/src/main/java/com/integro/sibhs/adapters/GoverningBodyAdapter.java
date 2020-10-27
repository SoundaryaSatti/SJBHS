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
import com.integro.sibhs.activities.GoverningDetailsActivity;
import com.integro.sibhs.activities.Main2Activity;
import com.integro.sibhs.model.GoverningBody;
import com.integro.sibhs.model.GoverningBodyList;
import com.integro.sjbhs.R;

import java.util.ArrayList;

public class GoverningBodyAdapter extends RecyclerView.Adapter<GoverningBodyAdapter.MyViewHolder> {

    Context context;
    ArrayList<GoverningBody>governingBodyArrayList;
    public GoverningBodyAdapter(Context context, ArrayList<GoverningBody> governingBodyArrayList) {
        this.context = context;
        this.governingBodyArrayList = governingBodyArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_governingbody,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         final GoverningBody governingBody= governingBodyArrayList.get(position);
          holder.tvName.setText(governingBody.getName());
          holder.tvDesignation.setText(governingBody.getDesignation());
          holder.tvDescription.setText(governingBody.getDescription());

         Glide.with(context).load(governingBody.getImage()).into(holder.imageViewgb);

         holder.cvGoverningbody.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(context, GoverningDetailsActivity.class);
                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 intent.putExtra("GOVERNINGBODY", governingBody);
                 context.startActivity(intent);
             }
         });

    }

    @Override
    public int getItemCount() {
        return governingBodyArrayList.size();
    }


    public  class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cvGoverningbody;
        ImageView imageViewgb;
        TextView tvName,tvDesignation,tvDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cvGoverningbody=itemView.findViewById(R.id.cvGoverningbody);
         imageViewgb=itemView.findViewById(R.id.imageViewgb);
         tvName=itemView.findViewById(R.id.tvName);
         tvDesignation=itemView.findViewById(R.id.tvDesignation);
         tvDescription=itemView.findViewById(R.id.tvDescription);

        }

    }
}

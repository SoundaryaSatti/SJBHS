package com.integro.sibhs.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.sibhs.activities.CourseDetailsActivity;
import com.integro.sibhs.activities.LoginList;
import com.integro.sjbhs.R;

import java.util.List;

public class LoginAdapterCourse extends RecyclerView.Adapter<LoginAdapterCourse.LoginViewHoder> {
    Context context;
    List<LoginList> loginLists;

    public LoginAdapterCourse(Context context, List<LoginList> course) {
        this.context = context;
        this.loginLists = course;
    }

    @NonNull
    @Override
    public LoginViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // TextView textView=(TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_login_list,parent);
        LoginViewHoder loginViewHoder = new LoginViewHoder(LayoutInflater.from(context).inflate(R.layout.activity_login_list, parent, false));
        //LoginViewHoder loginViewHoder=new LoginViewHoder(textView);
        return loginViewHoder;

    }

    @Override
    public void onBindViewHolder(@NonNull LoginViewHoder holder, final int position) {
        holder.course.setText(loginLists.get(position).getCourse());
        holder.course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CourseDetailsActivity.class);
                //intent.putExtra("COURSE", loginLists.get(position).getCourse());
                intent.putExtra("POSITION",position);
                context.startActivity(intent);
                Toast.makeText(context,"sandy",Toast.LENGTH_SHORT).show();
                /*switch (position) {
                    case 0:
                        Toast.makeText(context, "Hii", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
                        break;
                }*/


            }
        });

    }

    @Override
    public int getItemCount()
    {
        return loginLists.size();
    }

    public class LoginViewHoder extends RecyclerView.ViewHolder {
        TextView course;


        public LoginViewHoder(@NonNull View itemView) {
            super(itemView);
            course = itemView.findViewById(R.id.coursetview);
        }
    }
}



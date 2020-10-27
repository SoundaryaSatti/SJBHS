package com.integro.sibhs.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.integro.sjbhs.R;

import java.util.ArrayList;

public class CourseDetailsActivity extends AppCompatActivity {

    TextView tvDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        tvDetails = findViewById(R.id.tv_course);
        ArrayList<String> courseList = new ArrayList<>();
        courseList.add("Android details");
        courseList.add("Java details");
        courseList.add("C details");
        courseList.add("c++ details");
        courseList.add("java details");

        //  String text = getIntent().getStringExtra("COURSE");

        int pos = getIntent().getIntExtra("POSITION", 0);

        tvDetails.setText(courseList.get(pos));

    }
}

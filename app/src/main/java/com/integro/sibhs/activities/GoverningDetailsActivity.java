package com.integro.sibhs.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.integro.sibhs.model.GoverningBody;
import com.integro.sibhs.model.News;
import com.integro.sjbhs.R;

public class GoverningDetailsActivity extends AppCompatActivity {

    private static final String TAG = "GoverningDetailsActivity";

    public GoverningDetailsActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_governing_details);

        ImageView imageViewgb = findViewById(R.id.imageViewgb);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvDiscription = findViewById(R.id.tvDescription);
        TextView tvDesignation = findViewById(R.id.tvDesignation);

        GoverningBody governingBody = (GoverningBody) getIntent().getSerializableExtra("GOVERNINGBODY");
        Glide.with(getApplicationContext())
                .load(governingBody.getImage())
                .into(imageViewgb);
        tvName.setText(governingBody.getName());
        tvDesignation.setText(governingBody.getDesignation());
        tvDiscription.setText(governingBody.getDescription());


        }
    }

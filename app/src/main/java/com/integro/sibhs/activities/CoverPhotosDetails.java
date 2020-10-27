package com.integro.sibhs.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.integro.sibhs.model.CoverPhotos;
import com.integro.sjbhs.R;

public class CoverPhotosDetails extends AppCompatActivity {
   ImageView ivcpd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_photos_details);
        final String TAG = "CoverPhotosDetails";
        ivcpd=findViewById(R.id.ivcpd);

        CoverPhotos coverPhotos=(CoverPhotos) getIntent().getSerializableExtra("COVERPHOTOS");
        Glide.with(getApplicationContext()).load(coverPhotos.getImage()).into(ivcpd);



    }
}

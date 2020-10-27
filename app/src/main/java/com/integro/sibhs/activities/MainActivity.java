package com.integro.sibhs.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.integro.sibhs.adapters.ViewPageAdapter;
import com.integro.sjbhs.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPageAdapter adapter;
    ImageView ivmail, ivcall, ivfacebook, ivyoutube;
    TextView tvGoverningbody, tvManagement, tvPrincipal;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Exit");
        AlertDialog.Builder builder = alertDialogBuilder.setMessage("Do you really want to exit?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();
       // super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.add(R.id.fragment_container_view_tag,new Fragment());
        //fragmentTransaction.commit();
        ivmail = findViewById(R.id.ivmail);
        ivcall = findViewById(R.id.ivcall);
        ivfacebook = findViewById(R.id.ivfacebook);
        ivyoutube = findViewById(R.id.ivyoutube);
        tvGoverningbody = findViewById(R.id.tvGoverningbody);
        tvPrincipal=findViewById(R.id.tvPrincipal);
        tvManagement=findViewById(R.id.tvManagement);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new ViewPageAdapter(getSupportFragmentManager(), 4);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);//setText("home")//;
        tabLayout.getTabAt(1).setIcon(R.drawable.newss);
        tabLayout.getTabAt(2).setIcon(R.drawable.notifications);
        tabLayout.getTabAt(3).setIcon(R.drawable.www1);

        final int colorYellow = ContextCompat.getColor(getApplicationContext(), R.color.colorYellow);
        final int colorWhiite = ContextCompat.getColor(getApplicationContext(), R.color.colorWhite);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               // viewPager.setCurrentItem(tab.getPosition());
                tab.getIcon().setColorFilter(colorYellow, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(colorWhiite, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tvGoverningbody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GoverningBodyActivity.class);
                startActivity(intent);
            }
        });

        ivmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailintent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject" + " " + "&body" + " " + "&to=" + "principal@sjbhs.edu.in");
                mailintent.setData(data);
                startActivity(mailintent);
            }
        });
         tvPrincipal.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intentPrincipal=new Intent(getApplicationContext(),WebActivity.class);
                 String urlP="http://sjbhs.edu.in/principal's_welcome.php#";
                 intentPrincipal.putExtra("TAG", urlP);
                 startActivity(intentPrincipal);
             }
         });

        tvManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentManagement =new Intent(getApplicationContext(),WebActivity.class);
                String urlM="http://bjes.org/";
                intentManagement.putExtra("TAG",urlM);
                startActivity(intentManagement);
            }
        });

        ivfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFb = new Intent(getApplicationContext(), WebActivity.class);
                String url = "https://www.facebook.com/SJBHS";
                intentFb.putExtra("TAG", url);
                startActivity(intentFb);
            }
        });
        ivyoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentyoutube = new Intent(getApplicationContext(), WebActivity.class);
                String url = "https://www.youtube.com/channel/UCZiGHEzyTulO6QdCOWpC59w";
                intentyoutube.putExtra("TAG", url);
                startActivity(intentyoutube);
            }
        });
        ivcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                final CharSequence[] phone = new CharSequence[]{"08022214416"};
                String phone1 = "0802224416";
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone1, null));
                startActivity(intentCall);
            }
        });


        }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}

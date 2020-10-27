package com.integro.sibhs.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.integro.sibhs.adapters.LoginAdapterCourse;
import com.integro.sjbhs.R;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView course;
    //String[]list={"c","c++","java","android",".net",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recycler_view1);

        List<LoginList> loginLists = new ArrayList<>();
        LoginList list = new LoginList();
        list.setCourse("android");
        loginLists.add(list);

        LoginList list1 = new LoginList();
        list1.setCourse("java");
        loginLists.add(list1);

        LoginList list2 = new LoginList();
        list2.setCourse("c");
        loginLists.add(list2);


        LoginList list3 = new LoginList();
        list3.setCourse("c++");
        loginLists.add(list3);
        /*for (int i = 0; i <= 20; i++) {
            loginLists.add(list);
        }*/
        LoginAdapterCourse loginAdapter = new LoginAdapterCourse(Main2Activity.this, loginLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(loginAdapter);

       /* course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Main2Activity.class);
                //i.putExtra("icourse",course);
                startActivity(i);
                Toast.makeText(getContext(), "welcome", Toast.LENGTH_SHORT).show();
            }
                Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
            }*/
        //});


    }

}

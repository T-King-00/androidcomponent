package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class mainPageActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager2 viewPager;
    FloatingActionButton addNewItemBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage_act);

        tabLayout=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.viewPagerLayout);

        FragmentManager fragmentManager= getSupportFragmentManager();

        VPadapter  vPadapter=new VPadapter(fragmentManager,getLifecycle());
        viewPager.setAdapter(vPadapter);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
               tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


        addNewItemBtn=(FloatingActionButton) findViewById(R.id.addNewItemBtn);


        addNewItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNewItemIntent=new Intent(getApplicationContext(),AddNewTaskActivity.class);
                startActivity(addNewItemIntent);
            }
        });


    }
}
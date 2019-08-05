package com.example.medisearch;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.medisearch.Adapters.Tab;


public class DashboardActivity extends AppCompatActivity {

    private Tab adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);


        adapter = new Tab(getSupportFragmentManager());
        adapter.addFragment(new StoreFragment(), "Stores");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if ( isFragmentHasStoreInformation() ) {
                    removeStoreInformationFragment();
                }
            }

        });
    }

    private boolean isFragmentHasStoreInformation()
    {
        return getSupportFragmentManager().getFragments().contains( getSupportFragmentManager().findFragmentByTag("STORE_INFORMATION"));
    }

    private void removeStoreInformationFragment()
    {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("STORE_INFORMATION");
        if ( fragment != null ) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }



}

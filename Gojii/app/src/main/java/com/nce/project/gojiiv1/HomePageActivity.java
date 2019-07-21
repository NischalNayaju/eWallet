package com.nce.project.gojiiv1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.nce.project.gojiiv1.fragments.fragment_one;
import com.nce.project.gojiiv1.fragments.fragment_three;
import com.nce.project.gojiiv1.fragments.fragment_two;
import com.nce.project.gojiiv1.helper.ViewPagerAdapter;

public class HomePageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView usernameTextView, balanceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(Color.parseColor("#3BBF70"), Color.parseColor("#3BBF70"));

        usernameTextView = findViewById(R.id.name);
        balanceTextView = findViewById(R.id.balance);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String usernameValue = (String) bundle.get("name");
            Toast.makeText(this, "name" + usernameValue, Toast.LENGTH_LONG).show();
            usernameTextView.setText(usernameValue);

            String balanceValue = (String) bundle.get("balance");
            Toast.makeText(this, "balance:" + balanceValue, Toast.LENGTH_LONG).show();
            balanceTextView.setText("रू " + balanceValue);


        }
    /*    SharedPreferences sharedPreferences = getSharedPreferences("LoggedInInfo", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String balance = sharedPreferences.getString("balance", "");
        usernameTextView.setText(name);
        balanceTextView.setText(name);*/



    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new fragment_one(), "Services");
        adapter.addFragment(new fragment_two(), "Transactions");
        adapter.addFragment(new fragment_three(), "Favourites");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}

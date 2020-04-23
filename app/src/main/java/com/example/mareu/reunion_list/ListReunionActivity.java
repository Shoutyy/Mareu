package com.example.mareu.reunion_list;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.example.mareu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListReunionActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;
    @BindView(R.id.item_list_ajout_button)
    public ImageButton mAjoutButton;

    ListReunionPagerAdapter mPagerAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reunion);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mPagerAdapter = new ListReunionPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(ListReunionFragment.newInstance(), "Réunions");
        mViewPager.setAdapter(mPagerAdapter);
        mAjoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent ajoutReunionActivityIntent = new Intent(ListReunionActivity.this, AjoutReunionActivity.class);
                startActivity(ajoutReunionActivityIntent);
            }
        });
    }
}
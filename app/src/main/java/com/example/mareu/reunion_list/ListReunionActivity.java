package com.example.mareu.reunion_list;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.mareu.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListReunionActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;
    @BindView(R.id.item_list_ajout_button)
    public ImageButton mAjoutButton;
    @BindView(R.id.item_filter_reunion)
    public ImageButton mFiltreReunion;


    Boolean visibility;
    ListReunionPagerAdapter mPagerAdapter;
    EditText date;
    DatePickerDialog datePickerDialog;
    public String fDate = "";
    public String fLieu = "";




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
        setDateFilter();
        setFilter();
        setLieu();
    }

    public void setLieu(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.arraySalle,R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        Spinner spinner = findViewById(R.id.salleSpinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                fLieu = parentView.getItemAtPosition(position).toString();
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
                if(fragment != null)
                    getSupportFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    public void setDateFilter() {
        visibility = false;
        date = findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR); // current year
                int mMonth = calendar.get(Calendar.MONTH); // current month
                int mDay = calendar.get(Calendar.DAY_OF_MONTH); // current day
                datePickerDialog = new DatePickerDialog(ListReunionActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                                fDate = date.getText().toString();
                                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
                                if(datePickerDialog != null) {
                                    if (fragment != null)
                                        getSupportFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
                                }
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }


    public void setFilter() {
        Spinner spinner = findViewById(R.id.salleSpinner);
        mFiltreReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                if( visibility == false )
                {
                    date.setVisibility(view.VISIBLE);
                    spinner.setVisibility(view.VISIBLE);
                    visibility = true;
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
                    if(fragment != null)
                        getSupportFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
                }
                else
                {
                    visibility = false;
                    date.setVisibility(view.GONE);
                    spinner.setVisibility(view.GONE);
                    fDate = "";
                    fLieu = "";
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
                    if(fragment != null)
                        getSupportFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
                }
            }
        });
    }
}


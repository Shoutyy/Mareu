package com.example.mareu.reunion_list;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
    public String fSalle = "";





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

        visibility = false;
        // initiate the date picker and a button
        date = findViewById(R.id.date);
        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR); // current year
                int mMonth = calendar.get(Calendar.MONTH); // current month
                int mDay = calendar.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(ListReunionActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                fDate = date.getText().toString();
                mPagerAdapter = new ListReunionPagerAdapter(getSupportFragmentManager());
                mPagerAdapter.addFragment(ListReunionFragment.newInstance(), "Réunions");
                mViewPager.setAdapter(mPagerAdapter);
            }
        });



        Spinner spinner = findViewById(R.id.salleSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.arraySalle,R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                fSalle = parentView.getItemAtPosition(position).toString();
                mPagerAdapter = new ListReunionPagerAdapter(getSupportFragmentManager());
                mPagerAdapter.addFragment(ListReunionFragment.newInstance(), "Réunions");
                mViewPager.setAdapter(mPagerAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });


        mFiltreReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                if( visibility == false )
                {
                    date.setVisibility(view.VISIBLE);
                    spinner.setVisibility(view.VISIBLE);
                    visibility = true;
                }
                else
                {
                    visibility = false;
                    date.setVisibility(view.GONE);
                    spinner.setVisibility(view.GONE);
                }
            }
        });
    }




/*
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        CharSequence charSequence = (CharSequence) parent.getItemAtPosition(pos);
    }
*/


}


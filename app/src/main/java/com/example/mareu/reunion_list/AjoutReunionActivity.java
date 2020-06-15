package com.example.mareu.reunion_list;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ReunionApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AjoutReunionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText nom;
    EditText participants;
    EditText time;
    EditText date;
    DatePickerDialog datePickerDialog;
    public List<String> mListParticipants = new ArrayList<>();
    public List<Reunion>  mReunions;
    private ReunionApiService mApiService;
    private static final String REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static Pattern pattern;
    private static Matcher matcher;


    @BindView(R.id.button_participants)
    public Button mButtonParticipants;
    @BindView(R.id.id_participants)
    public TextView mParticipants;
    @BindView(R.id.submitButton)
    public Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_reunion);
        ButterKnife.bind(this);
        mApiService = DI.getReunionApiService();
        mReunions = mApiService.getReunions("", "");
        nom = findViewById(R.id.nomDeReunion);
        participants = findViewById(R.id.participants);
        setLieu();
        setDate();
        setTime();
        setParticipants();
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            Spinner spinner = findViewById(R.id.salleSpinner);
            @Override
            public void onClick (View view){
                if (!nom.getText().toString().equals("")) {
                    mReunions.add(new Reunion(mReunions.size()+1, date.getText().toString(), time.getText().toString(), spinner.getSelectedItem().toString(), nom.getText().toString(), new String[]{String.valueOf(mListParticipants)}));
                    finish();
                }
                else {
                    Toast.makeText(AjoutReunionActivity.this, "You did not enter a valid reunion", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean validate(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }

    public void setParticipants() {
        pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
        mButtonParticipants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                if (validate(participants.getText().toString())) {
                    mListParticipants.add(participants.getText().toString());
                    initList();
                }
                else {
                    Toast.makeText(AjoutReunionActivity.this, "You did not enter a valid participant", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setLieu() {
        Spinner spinner = findViewById(R.id.salleSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.arraySalle, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void setTime() {
        time = (EditText) findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AjoutReunionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    public void setDate() {
        date = findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AjoutReunionActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        CharSequence charSequence = (CharSequence) parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initList() {
        mParticipants.setText(String.valueOf(mListParticipants));
    }
}

package com.example.sleepcycle1;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


public class TimePickerActivityWake extends AppCompatActivity {

    //func to get closest multiple to currentMinute from given number
    static int closestMultiple(int currentMinute, int multiplier){
        if (currentMinute < 3){
            currentMinute = 0;
        }
        else {
            if (multiplier > currentMinute)
                return multiplier;
            currentMinute = currentMinute + multiplier / 2;
            currentMinute = currentMinute - (currentMinute % multiplier);
        }
        return currentMinute;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker_wake);

        setTitle("When will you go to bed?");

//        // Material Date Picker
//        boolean isSystem24Hour = is24HourFormat(this);  // see device's time format, true if 24H format
//
//        MaterialTimePicker.Builder picker = new MaterialTimePicker.Builder();
//
//        if (isSystem24Hour) {
//            picker.setTimeFormat(TimeFormat.CLOCK_24H);
//        }
//        else {
//            picker.setTimeFormat(TimeFormat.CLOCK_12H);
//        }
//        picker.setTitleText("Select Sleeping Time");
//        picker.build();
//        //picker.show(getSupportFragmentManager(), "TAG");

        // setting NumberPicker for hours
        NumberPicker numPickerHour = findViewById(R.id.numPickerHour);
        numPickerHour.setMinValue(1);
        numPickerHour.setMaxValue(12);

        // setting NumberPicker for minutes
        NumberPicker numPickerMin = findViewById(R.id.numPickerMin);

        String[] displayedValues = new String[] {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};

        numPickerMin.setMinValue(0);
        numPickerMin.setMaxValue(displayedValues.length - 1);
        numPickerMin.setDisplayedValues(displayedValues);

        // setting numberPicker time to current time
        LocalTime time = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            time = LocalTime.now();
        }
        //Log.d("TAG", "time: " + time);

        int hour = time.getHour();
        int minute = time.getMinute();
        // Log.d("TAG", "h: "+hour+" m: "+minute);

        DateTimeFormatter timeFormatterHour = DateTimeFormatter.ofPattern("hh");
        DateTimeFormatter timeFormatterMinute = DateTimeFormatter.ofPattern("mm");

        String hourTimeStr = time.format(timeFormatterHour);
        String minuteTimeStr = time.format(timeFormatterMinute);
//        Log.d("TAG", hourTimeStr);
//        Log.d("TAG", minuteTimeStr);

        int currentHour = Integer.parseInt(hourTimeStr);
        int currentMinute = Integer.parseInt(minuteTimeStr);

        Log.d("TAG", String.valueOf(currentHour));
        //Log.d("TAG", String.valueOf(currentMinute));

        // find closest multiple of 5
        int minuteToSet = closestMultiple(currentMinute, 5);
        Log.d("TAG", String.valueOf(minuteToSet));

        // add 0 to minuteToSet if less than 10
        String minuteToSetStr;
        if (minuteToSet < 10)
            minuteToSetStr = "0" + minuteToSet;
        else
            minuteToSetStr = String.valueOf(minuteToSet);

        // find minuteToSet location in string array displayedValues
        int indexOfMinute = Arrays.asList(displayedValues).indexOf(minuteToSetStr);
        Log.d("TAG", minuteToSetStr);
        Log.d("TAG", String.valueOf(indexOfMinute));

        // set value of numberPicker to current time
        numPickerHour.setValue(currentHour);
        numPickerMin.setValue(indexOfMinute);

        // AM PM for 12 hr format
        NumberPicker numPickerAM_PM = findViewById(R.id.numPickerAmPm);
        numPickerAM_PM.setMinValue(0);
        numPickerAM_PM.setMaxValue(1);
        String[] displayedValuesAmPm = new String[] {"AM", "PM"};
        numPickerAM_PM.setDisplayedValues(displayedValuesAmPm);

        Button getTime = findViewById(R.id.getTimeButton);
        getTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = Integer.parseInt(String.valueOf(numPickerHour.getValue()));
                int minute = Integer.parseInt(String.valueOf(displayedValues[numPickerMin.getValue()]));

                boolean isPM;
                if (numPickerAM_PM.getValue() == 1) {
                    isPM = true;
                }
                else if (numPickerAM_PM.getValue() == 0){
                    isPM = false;
                }

                else {
                    isPM = false;
                }

                // if hour is not 12 add 12 to it
                if (isPM == true && hour != 12 ) {
                    hour += 12;
                }

                // if AM is selected and hour = 12 make it 0
                else if (isPM == false && hour == 12) {
                    hour = 0;
                }

//                Log.d("TAG", "numPickerHour: " + numPickerHour.getClass().getName() + "  Value: " + numPickerHour.getValue());
//                Log.d("TAG", "numPickerMin: " + numPickerMin.getClass().getName() + "  Value: " + numPickerMin.getValue());
//                Log.d("TAG", "numPickerAM_PM: " + numPickerAM_PM.getClass().getName() + "  Value: " + numPickerAM_PM.getValue());
//                Log.d("TAG", "hour: " + hour);
//                Log.d("TAG", "min: " + minute);
//                Log.d("TAG", "am or pm: " + numPickerAM_PM.getValue());
//                Log.d("TAG", "is PM: " + isPM);
//                Log.d("TAG", hour + " : " + minute + " in PM: " + isPM);

                // implicit intent to set alarm
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, minute);

                startActivity(intent);
            }
        });
    }
}
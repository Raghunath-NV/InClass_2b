package com.example.myapplication10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    EditText tv1, tv2, tv3,tv5;
    TextView tv4,tv6;
    RadioGroup rg;
    RadioButton rb;
    Switch switchbutton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (EditText) findViewById(R.id.EditText1);
        tv2 = (EditText) findViewById(R.id.EditText2);
        tv3 = (EditText) findViewById(R.id.editText3);
        tv4 = (TextView) findViewById(R.id.prevdaytv);
        tv5 = (EditText) findViewById(R.id.editText);
        tv6 = (TextView) findViewById(R.id.resultText);
        switchbutton = (Switch) findViewById(R.id.switch1);
        switchbutton.setOnCheckedChangeListener(this);
        rg = (RadioGroup) findViewById(R.id.radioGroup);

    }


        public void ConvertPressed(View v)
    {
        int id = rg.getCheckedRadioButtonId();

        if( id == R.id.radioButton1)
        {
            pressed(5,"EST");

        }
         if(rg.getCheckedRadioButtonId() == R.id.radioButton2)
        {
           pressed(6,"CST");

        }
        if(rg.getCheckedRadioButtonId() == R.id.radioButton3)
        {
           pressed(7,"MST");

        }
         if (rg.getCheckedRadioButtonId() == R.id.radioButton4)
        {
            pressed(8,"PST");

        }
        else if(rg.getCheckedRadioButtonId() == R.id.radioButton5)
        {
           ClearAll();
        }

    }




    public void pressed(int n,String tzone)
    {
        int h, m, d;
        if (tv1.getText().toString().length() > 0 && tv2.getText().toString().length() > 0) {

            int hrs, mins, day;
            String result, period;
            if (tv1.getText().toString().trim().length() > 0 && tv2.getText().toString().trim().length() > 0) {
                tv5.setText(tzone);
                hrs = Integer.parseInt(tv1.getText().toString());
                day = hrs;
                mins = Integer.parseInt(tv2.getText().toString());
                if (CheckNumberCorrect(hrs, mins)) {
                    if (hrs < n) {
                        hrs = hrs + 12;
                    }
                    hrs = hrs - n;
                    String a = String.valueOf(hrs);
                    String b = String.valueOf(mins);
                    if (day < n && !switchbutton.isChecked()) {
                        tv4.setVisibility(View.VISIBLE);
                        period = "PM";
                    } else if (switchbutton.isChecked() && day < n) {
                        period = "AM";

                    } else if (switchbutton.isChecked() && day > n) {
                        period = "PM";
                    } else if (switchbutton.isChecked() && day == n) {
                        period = "PM";
                    } else {
                        period = "AM";
                    }
                    result = a + ": " + b + " " + period;
                    tv6.setText(result);
                }
                tv2.clearFocus();
            }
        }else {

            Toast.makeText(this, "Please Enter time in both hours and minutes fields", Toast.LENGTH_SHORT).show();
            ClearAll();
            tv2.clearFocus();
            tv1.clearFocus();
        }
    }


    public boolean CheckNumberCorrect(int hrs,int mins)
    {

        if((hrs >=0 && hrs<=12) && (mins>=0 && mins <=60 )) {
            return true;
        }
        else
        {
            ClearAll();
            Toast.makeText(this,"Please Enter Correct Time",Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @Override

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            if(isChecked)
            {
                tv3.setText("PM");
            }
            else
            {
                tv3.setText("AM");
            }
        }


    public void ClearAll()
    {

        tv1.getText().clear();
        tv2.getText().clear();
        tv4.setVisibility(View.INVISIBLE);
        tv5.getText().clear();
        tv6.setText(" ");
    }
}

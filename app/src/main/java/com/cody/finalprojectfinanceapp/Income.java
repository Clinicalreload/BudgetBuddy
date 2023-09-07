package com.cody.finalprojectfinanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cody.finalprojectfinanceapp.R;

import java.text.DecimalFormat;

public class Income extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        final RadioGroup hours = findViewById(R.id.radioGroup);
        final TextView result = (TextView) findViewById(R.id.txtDisplay);
        final TextView r = (TextView) findViewById(R.id.txtEnd);
        final EditText incomeInput = (EditText) findViewById(R.id.txtInc);
        final EditText hoursInput = (EditText)findViewById(R.id.txtHours);
        Button convert = (Button) findViewById(R.id.btnConvert);
        Button ending = (Button) findViewById(R.id.btnFinal);

        hours.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int hoursWorked) {
                if (hoursWorked == R.id.radSalary)
                {
                    hoursInput.setVisibility(View.INVISIBLE);
                } else if (hoursWorked == R.id.radHourly)
                {
                    hoursInput.setVisibility(View.VISIBLE);
                }
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double income = Double.parseDouble(incomeInput.getText().toString());
                double sal = Double.parseDouble(incomeInput.getText().toString());
                DecimalFormat currency = new DecimalFormat("$###,###.##");
                int checkedId = hours.getCheckedRadioButtonId();
                if (checkedId == R.id.radSalary) {
                    hoursInput.setVisibility(View.INVISIBLE);
                    if (income > 0.00) {
                        sal = ((income * .75)  / 52) * 4;
                        result.setText(currency.format(sal) + " per month");
                    } else {
                        Toast.makeText(Income.this, "Income must be greater than 0",
                                Toast.LENGTH_LONG).show();
                    }
                }
                if (checkedId == R.id.radHourly) {
                    hoursInput.setVisibility(View.VISIBLE);
                    double hours = Double.parseDouble(hoursInput.getText().toString());
                    if (income > 0.00 && hours > 0) {
                        sal = (income * .75) * hours * 4;
                        result.setText(currency.format(sal) + " per month");
                    } else {
                        Toast.makeText(Income.this, "Income must be greater than 0",
                                Toast.LENGTH_LONG).show();
                    }
                }
                Data.getInstance().setSalary(String.valueOf(sal));
            }
        });

        ending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat currency = new DecimalFormat("$###,###.##");
                Double expenses = Double.parseDouble(Data.getInstance().getExpenses());
                Double sal = Double.parseDouble(Data.getInstance().getSalary());
                if (sal > 0) {
                    if (sal > expenses) {
                        r.setTextColor(Color.rgb(0,100,0));
                        r.setText("You are making " + currency.format(sal - expenses) + " extra each month! Keep it up!");
                    } else if (sal < expenses) {
                        r.setTextColor(Color.rgb(255,0,0));
                        r.setText("Oh No! You are losing " + currency.format(expenses - sal) + " each month but keep your head up you can fix this!");
                    } else {
                        r.setText("You are breaking even each month! ");
                        r.setTextColor(Color.rgb(0, 0, 0));
                    }
                }
                else {
                        Toast.makeText(Income.this, "Income must be greater than 0",
                                Toast.LENGTH_LONG).show();
                    }
            }
        });
    }
}
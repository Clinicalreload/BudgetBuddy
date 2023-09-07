package com.cody.finalprojectfinanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cody.finalprojectfinanceapp.R;

import java.text.DecimalFormat;



public class Finance extends AppCompatActivity {
    private double calcExpenses() {
        double rent, car, electric, gas, water, internet, food, misc;
        String rentStr = ((EditText)findViewById(R.id.txtRent)).getText().toString();
        String carStr = ((EditText)findViewById(R.id.txtCar)).getText().toString();
        String electricStr = ((EditText)findViewById(R.id.txtElectric)).getText().toString();
        String gasStr = ((EditText)findViewById(R.id.txtGas)).getText().toString();
        String waterStr = ((EditText)findViewById(R.id.txtWater)).getText().toString();
        String internetStr = ((EditText)findViewById(R.id.txtInternet)).getText().toString();
        String foodStr = ((EditText)findViewById(R.id.txtFood)).getText().toString();
        String miscStr = ((EditText)findViewById(R.id.txtMiscellaneous)).getText().toString();

        rent = Double.parseDouble(rentStr);
        car = Double.parseDouble(carStr);
        electric = Double.parseDouble(electricStr);
        gas = Double.parseDouble(gasStr);
        water = Double.parseDouble(waterStr);
        internet = Double.parseDouble(internetStr);
        food = Double.parseDouble(foodStr);
        misc = Double.parseDouble(miscStr);
        double totalExpenses = rent + car + electric + gas + water + internet + food + misc;
        return totalExpenses;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        Button expenses = (Button)findViewById(R.id.btnExpenses);
        Button income = (Button)findViewById(R.id.btnToIncome);

        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Data.getInstance().setExpenses(String.valueOf(calcExpenses()));
                startActivity(new Intent(Finance.this, Income.class));
            }
        });

        expenses.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingInflatedId")
            @Override
            public void onClick(View v) {
                final TextView result = (TextView)findViewById(R.id.txtResult);
                String rentStr = ((EditText)findViewById(R.id.txtRent)).getText().toString();
                String carStr = ((EditText)findViewById(R.id.txtCar)).getText().toString();
                String electricStr = ((EditText)findViewById(R.id.txtElectric)).getText().toString();
                String gasStr = ((EditText)findViewById(R.id.txtGas)).getText().toString();
                String waterStr = ((EditText)findViewById(R.id.txtWater)).getText().toString();
                String internetStr = ((EditText)findViewById(R.id.txtInternet)).getText().toString();
                String foodStr = ((EditText)findViewById(R.id.txtFood)).getText().toString();
                String miscStr = ((EditText)findViewById(R.id.txtMiscellaneous)).getText().toString();

                if (rentStr.isEmpty() || carStr.isEmpty() || electricStr.isEmpty() || gasStr.isEmpty() || waterStr.isEmpty() || internetStr.isEmpty() || foodStr.isEmpty() || miscStr.isEmpty()) {
                    Toast.makeText(Finance.this, "Please enter a value in all fields", Toast.LENGTH_SHORT).show();
                } else {

                    Data.getInstance().setExpenses(String.valueOf(calcExpenses()));
                    DecimalFormat currency = new DecimalFormat("$###,###.##");
                    result.setText("Total Expenses For The Month is " + currency.format(calcExpenses()));
                }
            }
        });

    }
}

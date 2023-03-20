package com.example.uberfareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("userInput", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //grabs the inputed miles from the user
        EditText inputed_miles = (EditText) findViewById(R.id.input_mile);
        //grabs what car the user chose from the spinner
        Spinner inputed_car = (Spinner) findViewById(R.id.spinner_cars);
        //sends the user to the next page
        Button button = (Button) findViewById(R.id.est_uber_cost_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CarChoice.class));

                editor.putString("input_miles", String.valueOf(inputed_miles.getText()));
                editor.putString("input_car",  inputed_car.getSelectedItem().toString());
                editor.apply();
            }
        });
    }
}
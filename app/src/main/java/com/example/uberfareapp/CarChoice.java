package com.example.uberfareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CarChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_choice);
        //send the person to the arriving page
        Button reqRideBtn = (Button) findViewById(R.id.reqRideBtn);
        reqRideBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(CarChoice.this, ArrivingPage.class));
            }
        });
        //send the person back to the fist page
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(CarChoice.this, MainActivity.class));
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("userInput", MODE_PRIVATE);
        String Chosemiles = sharedPreferences.getString("input_miles", "0");
        String Chosecar = sharedPreferences.getString("input_car", "");

        TextView textMiles = (TextView) findViewById(R.id.chosenMiles);
        TextView textChoosenCar = (TextView) findViewById(R.id.chosenCar);
        TextView textTotal = (TextView) findViewById(R.id.chosenTotal);


        double calcUberTotal = calcPriceTotal(Integer.valueOf(Chosemiles), Chosecar);

        textMiles.setText(textMiles.getText() + " " + Chosemiles);
        textChoosenCar.setText(textChoosenCar.getText() + Chosecar);
        textTotal.setText(textTotal.getText() + String.valueOf(calcUberTotal));


    }

    static double calcPriceTotal(int Chosemiles, String Chosecar) {
        //sets the origional total
        double total = 3.00;
        //makes it so that if the person chooses smart car or minivan then they will have the extra charge
        if (Chosecar.contains("Smart")){
            total = total + 2;
        }
        if (Chosecar.contains("Minivan")){
            total = total + 5;
        }

        //adds the total to the amount of miles charged
        total = total + Chosemiles * 3.25;

        return total;
    }



}

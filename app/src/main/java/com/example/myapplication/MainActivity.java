package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText height, weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        height = findViewById(R.id.HeightText);
        weight = findViewById(R.id.WeightText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnFunction(height, weight);
                reset();
            }
        });
    }

    private void btnFunction(EditText height, EditText weight) {
        double BMI = getBmi(height, weight);

        Bundle bundle = new Bundle();
        bundle.putDouble("BMI", BMI);
        if(BMI >=  1){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, MainActivity2.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    private double getBmi(EditText h, EditText w) {
        double weight = 0, height = 0;

        try{
            if(h.getText() != null && w.getText() != null){
                height = Math.pow(Double.parseDouble(h.getText().toString())/100, 2);
                weight = Double.parseDouble(w.getText().toString());
            }
        }catch (NumberFormatException numberFormatException){
            Toast.makeText(getApplicationContext(),"Please enter your height(CM) and weight(KG)",Toast.LENGTH_LONG).show();
        }

        double BMI = weight / height;
        return BMI;
    }

    private void reset(){
        height.setText("");
        weight.setText("");
    }
}
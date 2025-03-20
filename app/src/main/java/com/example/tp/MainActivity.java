package com.example.tp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText number;
    private Spinner input,output;
    private Button convertButton;
    private TextView result;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        number = findViewById(R.id.idNumber);
        input=findViewById(R.id.idInput);
        output=findViewById(R.id.idOutput);
        convertButton=findViewById(R.id.convertButton);

        String[] bases = {"Décimal", "Binaire", "Octal", "Hexadécimal"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,bases);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input.setAdapter(adapter);
        output.setAdapter(adapter);

        convertButton.setOnClickListener((e)->{
            convertNumber();
        });


        }

        public void convertNumber(){
        String num=number.getText().toString();
        String in=input.getSelectedItem().toString();
        String out=output.getSelectedItem().toString();

        int decimal=convertToDecimal(num,in);
        String res=convertFromDecimal(decimal,out);
        result.setText("Result is : " +res);


    }
    private int convertToDecimal(String number, String base) {
        switch (base) {
            case "Décimal":
                return Integer.parseInt(number);
            case "Binaire":
                return Integer.parseInt(number, 2);
            case "Octal":
                return Integer.parseInt(number, 8);
            case "Hexadécimal":
                return Integer.parseInt(number, 16);
            default:
                return 0;
        }
    }

    private String convertFromDecimal(int number, String base) {
        switch (base) {
            case "Décimal":
                return Integer.toString(number);
            case "Binaire":
                return Integer.toBinaryString(number);
            case "Octal":
                return Integer.toOctalString(number);
            case "Hexadécimal":
                return Integer.toHexString(number).toUpperCase();
            default:
                throw new NumberFormatException("Base non supportée");
        }
    }
}
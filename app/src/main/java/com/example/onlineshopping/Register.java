package com.example.onlineshopping;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineshopping.database.ShoppingDBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Register extends AppCompatActivity {

    TextView useNameTxt , emailTxt , passwordTxt , genderTxt , jobTxt , phoneNumberTxt ;
    TextView birthdayTxt ;
    ShoppingDBHelper dbHelper ;
    Button registerBtn,birthdateBtn;
    Calendar myCalendar;
    String birth_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        birthdayTxt =findViewById(R.id.birthdayTxt);
        useNameTxt = findViewById(R.id.customerName);
        emailTxt = findViewById(R.id.registerEmail);
        passwordTxt = findViewById(R.id.registerPassword);
        genderTxt = findViewById(R.id.gender);
        jobTxt = findViewById(R.id.job);
        phoneNumberTxt = findViewById(R.id.phoneNumber);
        registerBtn =findViewById(R.id.registerButton);
        birthdateBtn =findViewById(R.id.birthdayBtn);

        dbHelper = new ShoppingDBHelper(this);
        //define birthday
        myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);

                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
                birth_date = dateFormat.format(myCalendar.getTime()).toString();
                birthdayTxt.setText(birth_date);

            }};

        birthdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Register.this,
                                    date,
                                    myCalendar.get(Calendar.YEAR),
                                    myCalendar.get(Calendar.MONTH),
                                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });






        //store

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.insertCustomer(emailTxt.getText().toString()
                                        ,useNameTxt.getText().toString()
                                        ,passwordTxt.getText().toString()
                                        ,genderTxt.getText().toString()
                                        ,jobTxt.getText().toString()
                                        ,birth_date,0
                                        ,phoneNumberTxt.getText().toString());
                Toast.makeText(getApplicationContext(), "Registered Successfully!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });


    }
}

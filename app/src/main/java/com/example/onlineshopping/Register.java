package com.example.onlineshopping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineshopping.database.ShoppingDBHelper;

public class Register extends AppCompatActivity {

    TextView useNameTxt , emailTxt , passwordTxt , genderTxt , jobTxt , phoneNumberTxt ;
    ShoppingDBHelper dbHelper ;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        useNameTxt = findViewById(R.id.customerName);
        emailTxt = findViewById(R.id.registerEmail);
        passwordTxt = findViewById(R.id.registerPassword);
        genderTxt = findViewById(R.id.gender);
        jobTxt = findViewById(R.id.job);
        phoneNumberTxt = findViewById(R.id.phoneNumber);
        registerBtn =findViewById(R.id.registerButton);

        dbHelper = new ShoppingDBHelper(this);
        //define birthday


        //store

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.insertCustomer(emailTxt.getText().toString()
                                        ,useNameTxt.getText().toString()
                                        ,passwordTxt.getText().toString()
                                        ,genderTxt.getText().toString()
                                        ,jobTxt.getText().toString()
                                        ,"dd/mm/yy",0
                                        ,phoneNumberTxt.getText().toString());
                Toast.makeText(getApplicationContext(), "Registered Successfully!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

    }
}

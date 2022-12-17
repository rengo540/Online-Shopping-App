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
import com.example.onlineshopping.database.models.Customer;
import com.example.onlineshopping.database.models.CustomerLoginHolder;

public class Login extends AppCompatActivity {

    TextView emailTxt , passwordTxt,loginPageQuestion ;
    Button loginBtn ;
    ShoppingDBHelper dbHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTxt = findViewById(R.id.loginEmail);
        passwordTxt = findViewById(R.id.loginPassword);
        loginBtn =findViewById(R.id.loginButton);
        loginPageQuestion =findViewById(R.id.loginPageQuestion);
        dbHelper =new ShoppingDBHelper(this );

       // dbHelper.insertCustomer("rengo","rengo","rengo","male","admin","mm/dd/yy",1,"000");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer user = dbHelper.getCustomer(emailTxt.getText().toString());
                if(user != null){
                     if(user.getPassword().equals(passwordTxt.getText().toString())) {
                         if(user.getIsAdmin()==1){
                             Intent intent = new Intent(getApplicationContext(), AdminHome.class);
                             startActivity(intent);
                         }else{
                             CustomerLoginHolder.getInstance().setCustomer(user);
                             Intent intent = new Intent(getApplicationContext(), CustomerHome.class);
                             startActivity(intent);
                         }

                     }else{
                         Toast.makeText(getApplicationContext(),"wrong password",Toast.LENGTH_SHORT).show();

                     }

                }else{
                    Toast.makeText(getApplicationContext(),"wrong email",Toast.LENGTH_SHORT).show();
                }
            }
        });


        loginPageQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });


    }
}

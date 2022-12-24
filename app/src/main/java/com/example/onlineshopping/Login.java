package com.example.onlineshopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineshopping.database.ShoppingDBHelper;
import com.example.onlineshopping.database.models.Customer;
import com.example.onlineshopping.database.models.CustomerLoginHolder;
import com.example.onlineshopping.ui.ForgetPasswordActivity;
import com.google.gson.Gson;

public class Login extends AppCompatActivity {

    TextView emailTxt , passwordTxt,loginPageQuestion ;
    Button loginBtn ;
    TextView forgetBtn;
    ShoppingDBHelper dbHelper ;
    CheckBox checkBox ;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgetBtn = findViewById(R.id.forgetBtn);
        emailTxt = findViewById(R.id.loginEmail);
        passwordTxt = findViewById(R.id.loginPassword);
        loginBtn =findViewById(R.id.loginButton);
        loginPageQuestion =findViewById(R.id.loginPageQuestion);
        checkBox = findViewById(R.id.rememberMeCheck);
        dbHelper =new ShoppingDBHelper(this );


        //dbHelper.insertCustomer("rengo","rengo","rengo","male","admin","mm/dd/yy",1,"000");

        sharedPreferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkboxx = sharedPreferences.getString("remember","");
        if(checkboxx.equals("true")){
            Gson gson = new Gson();
            String json = sharedPreferences.getString("customerLogin", "");
            Customer customerLogin = gson.fromJson(json, Customer.class);
            CustomerLoginHolder.getInstance().setCustomer(customerLogin);
            Intent intent = new Intent(getApplicationContext(), CustomerHome.class);
            startActivity(intent);

        }else if(checkboxx.equals("false")){
            Toast.makeText(this, "Login please", Toast.LENGTH_SHORT).show();
        }

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
                             SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                             String check =preferences.getString("remember","");
                             if(check.equals("true")){
                                 Gson gson = new Gson();
                                 String json = gson.toJson(user);
                                 SharedPreferences.Editor editor =preferences.edit();
                                 editor.putString("customerLogin",json);
                                 editor.apply();
                             }

                             Intent intent = new Intent(getApplicationContext(), CustomerHome.class);
                             startActivity(intent);
                         }

                     }else{
                         Toast.makeText(getApplicationContext(),"wrong password",Toast.LENGTH_SHORT).show();

                     }

                }else{
                    Toast.makeText(getApplicationContext(),"email not found",Toast.LENGTH_SHORT).show();
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


        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor =preferences.edit();
                    editor.putString("remember","true");

                    editor.apply();
                    Toast.makeText(Login.this, "checked", Toast.LENGTH_SHORT).show();

                }else if (!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor =preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(Login.this, "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

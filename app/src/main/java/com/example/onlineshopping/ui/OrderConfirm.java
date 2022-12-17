package com.example.onlineshopping.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshopping.R;
import com.example.onlineshopping.database.ShoppingDBHelper;
import com.example.onlineshopping.database.models.Cart;
import com.example.onlineshopping.database.models.CustomerLoginHolder;
import com.example.onlineshopping.database.models.Order;
import com.example.onlineshopping.database.models.OrderDetials;
import com.example.onlineshopping.database.models.Product;
import com.example.onlineshopping.ui.adapter.ProductCatAdapter;
import com.example.onlineshopping.ui.adapter.ProductOrderAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderConfirm extends AppCompatActivity {

    RecyclerView productsOrderRecyclerView ;
    static ProductOrderAdapter adapter ;
    static ShoppingDBHelper shoppingDBHelper ;
    static   ProductOrderAdapter.RecyclerViewClickListener listener;
    List<OrderDetials> productsOrder ;
    TextView locationTxt ;

    String LocationStr ;

    FusedLocationProviderClient fusedLocationProviderClient;
    Button getLocationBtn , confirmOrderBtn;
    private  final  static int REQUEST_CODE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_order_confirm);

        shoppingDBHelper = new ShoppingDBHelper(this);
        confirmOrderBtn =findViewById(R.id.confirmOrderBtn);
        locationTxt =findViewById(R.id.locationTxt);
        productsOrderRecyclerView = findViewById(R.id.OrderDetailsRecycler);
        adapter =new ProductOrderAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);

        productsOrderRecyclerView.setLayoutManager( mLayoutManager);
        productsOrderRecyclerView.setAdapter(adapter);

        productsOrder = Cart.getInstance().getOrderDetialsList();
        //textView.setText(Integer.toString(productsOrder.get(0).getQuantity()));
        adapter.setList(productsOrder,listener);




        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        getLocationBtn = findViewById(R.id.locationBtn);
        getLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();
            }
        });

       List<Product> products = Cart.getInstance().getProductList();

        confirmOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);
                int userId = CustomerLoginHolder.getInstance().getCustomer().getCustomerId();
                int orderId = shoppingDBHelper.insertOrder(formattedDate,userId,LocationStr);


               for(int i=0 ;i<productsOrder.size();i++){
                   //update products no of sales
                   int sales =productsOrder.get(i).getProduct().getNoOfSales() + productsOrder.get(i).getQuantity();
                   productsOrder.get(i).getProduct().setNoOfSales(sales);
                   shoppingDBHelper.updateSalesQuantity(productsOrder.get(i).getProduct().getProductId(),sales);
                   //insert order details
                    shoppingDBHelper.insertOrderDerials(orderId,productsOrder.get(i).getProduct().getProductId(),productsOrder.get(i).getQuantity());
                }

                AlertDialog.Builder mydialog = new AlertDialog.Builder(OrderConfirm.this);
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());

                View myview = inflater.inflate(R.layout.feedback_dialog, null);
                mydialog.setView(myview);

                AlertDialog dialog = mydialog.create();
                dialog.setCancelable(false);
                dialog.show();

                final EditText feedbackTxt = myview.findViewById(R.id.feedback);
                final EditText feedbackRate = myview.findViewById(R.id.rateFeedback);

                Button sendBtn = myview.findViewById(R.id.sendBtn);
                Button cancelBtn = myview.findViewById(R.id.cancelBtn);


                sendBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        shoppingDBHelper.updateFeedback(orderId,feedbackTxt.getText().toString(),Integer.valueOf(feedbackRate.getText().toString()));
                        Toast.makeText(OrderConfirm.this, "thank you for your feedback", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });


                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });


      /*  shoppingDBHelper = new ShoppingDBHelper(this);







        */



    }

    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location !=null){
                                Geocoder geocoder=new Geocoder(OrderConfirm.this, Locale.getDefault());
                                List<Address> addresses= null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                                   // latitude.setText("Lagitude :" +addresses.get(0).getLatitude());
                                   // longitude.setText("Longitude :"+addresses.get(0).getLongitude());
                                    LocationStr = addresses.get(0).getAddressLine(0)+addresses.get(0).getLocality();
                                    locationTxt.append(addresses.get(0).getAddressLine(0)+" - "+addresses.get(0).getLocality());
                                   // city.setText("City :"+addresses.get(0).getLocality());
                                   // country.setText("Country :"+addresses.get(0).getCountryName());

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }



                            }

                        }
                    });


        }else
        {

            askPermission();

        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(OrderConfirm.this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode==REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }
            else {
                Toast.makeText(this, "Required Permission", Toast.LENGTH_SHORT).show();
            }
        }






        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
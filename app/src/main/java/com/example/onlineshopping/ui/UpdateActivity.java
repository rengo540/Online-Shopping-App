package com.example.onlineshopping.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.onlineshopping.R;
import com.example.onlineshopping.database.ShoppingDBHelper;

public class UpdateActivity extends AppCompatActivity {
    String id,price,salesNumber,quantity;
    String name,barcode,category;
    EditText productNameText;
    EditText productPriceText;
    EditText productStockQuantityText;
    EditText productBarcodeText;
    EditText productSalesNumberText;
    EditText productCategoryText;
    Button updateBtn,deleteBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
         productNameText = findViewById(R.id.productName2);
         productPriceText =  findViewById(R.id.price2);
         productStockQuantityText = findViewById(R.id.stockQuantity2);
         productBarcodeText =  findViewById(R.id.barcode2);
         productSalesNumberText =  findViewById(R.id.salesNumber2);
         productCategoryText =  findViewById(R.id.category2);
         updateBtn =  findViewById(R.id.updateProductBtn);
         deleteBtn =  findViewById(R.id.deleteProductBtn);
        getAndSetIntentData();
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("MY ID IS : "+id);
                name = productNameText.getText().toString();
                price = productPriceText.getText().toString();
                quantity = productStockQuantityText.getText().toString();
                barcode = productBarcodeText.getText().toString();
                salesNumber = productSalesNumberText.getText().toString();
                category = productCategoryText.getText().toString();
                ShoppingDBHelper dbHelper = new ShoppingDBHelper(UpdateActivity.this);
                dbHelper.updateData(id,name,price,quantity,barcode,salesNumber,category);
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")){
            System.out.println("I'M INNNNN");
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            System.out.println("I'M NAME");
            price = getIntent().getStringExtra("price");
            System.out.println("I'M PRICE");
            quantity = getIntent().getStringExtra("quantity");
            barcode = getIntent().getStringExtra("barcode");
            salesNumber = getIntent().getStringExtra("salesNumber");
            category = getIntent().getStringExtra("category");
/*            System.out.println("I'M ID"+id);
            System.out.println("I'M NAME"+name);
            System.out.println("I'M price"+price);
            System.out.println("I'M category"+category);
            System.out.println("I'M barcode"+barcode);
            System.out.println("I'M sales number"+salesNumber);
            System.out.println("I'M quantity"+quantity);*/

            /////
            productNameText.setText(name);
            productPriceText.setText(price);
            productStockQuantityText.setText(quantity);
            productBarcodeText.setText(barcode);
            productSalesNumberText.setText(salesNumber);
            productCategoryText.setText(category);
        }else if(!getIntent().hasExtra("barcode")){
            System.out.println("DATA NULL");
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ShoppingDBHelper myDB = new ShoppingDBHelper(UpdateActivity.this);
                myDB.deleteOneProduct(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
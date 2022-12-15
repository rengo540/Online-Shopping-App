package com.example.onlineshopping.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.onlineshopping.database.models.Category;
import com.example.onlineshopping.database.models.Customer;
import com.example.onlineshopping.database.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingDBHelper extends SQLiteOpenHelper {

    static  final  String DBname = "shopping.dp" ;
    SQLiteDatabase db ;

    public ShoppingDBHelper(Context context) {
        super(context, DBname, null, 4);
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //customer table
        sqLiteDatabase.execSQL("CREATE TABLE  Customer (customerId integer PRIMARY KEY autoincrement ,Email TEXT ,userName TEXT,password TEXT,gender Text,job Text,birthdate Text,is_admin integer,phoneNumber TEXT )");

        //Orders table
        sqLiteDatabase.execSQL("CREATE TABLE  Orders (orderId integer PRIMARY KEY autoincrement ,orderDate TEXT,feedbackRate integer,location TEXT ,cust_id integer,FOREIGN KEY(cust_id) REFERENCES Customer (customerId))");

        //Category table
        sqLiteDatabase.execSQL("CREATE TABLE  Category (categoryId integer PRIMARY KEY autoincrement ,categoryName TEXT )");

        //Product table
        sqLiteDatabase.execSQL("CREATE TABLE  Product (productId integer PRIMARY KEY autoincrement ,productName TEXT,price integer,stockQuantity integer,barcode TEXT ,cat_id integer,noOfSales integer,FOREIGN KEY(cat_id) REFERENCES Category (categoryId))");

        //OrderDetails
        sqLiteDatabase.execSQL("CREATE TABLE  OrderDetails (order_id integer,prod_id integer,quantity integer ,FOREIGN KEY(order_id) REFERENCES Orders (orderId),FOREIGN KEY(prod_id) REFERENCES Product (productId))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Customer ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Orders ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Category ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Product ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS OrderDetails ");

        onCreate(sqLiteDatabase);
    }

    public void staticData (){

        //static data
        db = this.getWritableDatabase() ;

        ContentValues newC=new ContentValues();
        newC.put("Email","ar6029676");
        newC.put("userName","Abdelrahman");
        newC.put("password","Abdelrahman");
        newC.put("gender","male");
        newC.put("job","Student");
        newC.put("birthdate","16/2/2000");
        newC.put("is_admin",0);
        newC.put("phoneNumber","01124373725");
        db.insert("Customer",null,newC);

        ContentValues newC1=new ContentValues();
        newC1.put("Email","zizi455");
        newC1.put("userName","zizi");
        newC1.put("password","zizi");
        newC1.put("gender","male");
        newC1.put("job","Student");
        newC1.put("birthdate","16/2/2000");
        newC1.put("is_admin",0);
        newC1.put("phoneNumber","01124373725");
        db.insert("Customer",null,newC1);

        ContentValues cat=new ContentValues();
        cat.put("categoryName","laptops");
        db.insert("Category",null,cat);

        cat.put("categoryName","Mobile Phones");
        db.insert("Category",null,cat);

        ContentValues prod=new ContentValues();
        prod.put("barcode","6224000492526");
        prod.put("productName","HP Laptop i7");
        prod.put("price",17000);
        prod.put("stockQuantity",5);
        prod.put("cat_id",1);
        prod.put("noOfSales",0);
        db.insert("Product",null,prod);

        prod.put("barcode","6222001405385");
        prod.put("productName","HP Laptop i3");
        prod.put("price",12000);
        prod.put("stockQuantity",3);
        prod.put("cat_id",1);
        prod.put("noOfSales",0);
        db.insert("Product",null,prod);

        prod.put("barcode","6222001405885");
        prod.put("productName","HP Laptop i5");
        prod.put("price",13500);
        prod.put("stockQuantity",3);
        prod.put("cat_id",1);
        prod.put("noOfSales",0);
        db.insert("Product",null,prod);


        prod.put("barcode","6223004160547");
        prod.put("productName","DELL Laptop i7");
        prod.put("price",15000);
        prod.put("stockQuantity",5);
        prod.put("cat_id",1);
        prod.put("noOfSales",0);
        db.insert("Product",null,prod);

        prod.put("barcode","6223004160547");
        prod.put("productName","DELL Laptop Gaming");
        prod.put("price",32000);
        prod.put("stockQuantity",5);
        prod.put("cat_id",1);
        prod.put("noOfSales",0);
        db.insert("Product",null,prod);


        prod.put("barcode","6223001930082");
        prod.put("productName","Samsung A7");
        prod.put("price",4200);
        prod.put("stockQuantity",7);
        prod.put("cat_id",2);
        prod.put("noOfSales",0);
        db.insert("Product",null,prod);

        prod.put("barcode","6223001930082");
        prod.put("productName","Oppo f5");
        prod.put("price",4100);
        prod.put("stockQuantity",4);
        prod.put("cat_id",2);
        prod.put("noOfSales",0);
        db.insert("Product",null,prod);

    }

    public List<Product> getSimilarProducts(String queryy)
    {
        String[] arg={queryy};
        db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT productId,productName,stockQuantity,price, barcode,cat_id,noOfSales FROM Product WHERE productName like ? ", arg);
        ArrayList<Product> arrayList = new ArrayList();

        while (res.moveToNext())
        {
            int productId = res.getInt(0);
            String productName = res.getString(1);
            int stockQuantity = res.getInt(2);
            int price = res.getInt(3);
            String barcode = res.getString(4);
            int cat_id = res.getInt(5);
            int noOfSales = res.getInt(6);

            Category c = getCategory(cat_id);


            arrayList.add(new Product(productId,productName,stockQuantity,price,barcode,c,noOfSales));

        }
        return arrayList;
    }


    public List<Product> getProductsInCateogry(int catId)
    {
        String cat_idd = Integer.toString(catId);
        String[] arg={cat_idd};
        db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT productId,productName,stockQuantity,price, barcode,cat_id,noOfSales FROM Product WHERE cat_id = ? ", arg);
        ArrayList<Product> arrayList = new ArrayList();

        while (res.moveToNext())
        {
            int productId = res.getInt(0);
            String productName = res.getString(1);
            int stockQuantity = res.getInt(2);
            int price = res.getInt(3);
            String barcode = res.getString(4);
            int cat_id = res.getInt(5);
            int noOfSales = res.getInt(6);

            Category c = getCategory(cat_id);


            arrayList.add(new Product(productId,productName,stockQuantity,price,barcode,c,noOfSales));

        }
        return arrayList;
    }

    public List<Product> getSimilarBarcodeProducts(String barcodee)
    {
        String[] arg={barcodee};
        db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT productId,productName,stockQuantity,price, barcode,cat_id,noOfSales FROM Product WHERE barcode like ? ", arg);
        ArrayList<Product> arrayList = new ArrayList();

        while (res.moveToNext())
        {
            int productId = res.getInt(0);
            String productName = res.getString(1);
            int stockQuantity = res.getInt(2);
            int price = res.getInt(3);
            String barcode = res.getString(4);
            int cat_id = res.getInt(5);
            int noOfSales = res.getInt(6);

            Category c = getCategory(cat_id);


            arrayList.add(new Product(productId,productName,stockQuantity,price,barcode,c,noOfSales));

        }
        return arrayList;
    }


    public boolean insertProduct (String productName ,int stockQuantity,int price, String barcode){
        db = this.getWritableDatabase() ;

        ContentValues contentValues = new ContentValues();
        contentValues.put("productName" , productName);
        contentValues.put("stockQuantity",stockQuantity);
        contentValues.put("price", price);
        contentValues.put("barcode",barcode);

        int r = (int) db.insert("Product" ,null , contentValues);
        if(r== -1 )
        {
            return  false ;
        }
        else {
            return true;
        }
    }









    public boolean insertCustomer (String Email ,String userName,String password,String gender,String job,String birthdate,int isAdmin,String phoneNumber){
        db = this.getWritableDatabase() ;

        ContentValues contentValues = new ContentValues();
        contentValues.put("Email" , Email);
        contentValues.put("userName",userName);
        contentValues.put("password", password);
        contentValues.put("gender",gender);
        contentValues.put("job",job);
        contentValues.put("birthdate",birthdate);
        contentValues.put("is_admin",isAdmin);
        contentValues.put("phoneNumber",phoneNumber);



        int r = (int) db.insert("Customer" ,null , contentValues);
        if(r== -1 )
        {
            return  false ;
        }
        else {
            return true;
        }
    }

    public boolean insertOrderDerials (int order_id ,int prod_id,int quantity){
        db = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues();
        contentValues.put("order_id" , order_id);
        contentValues.put("prod_id",prod_id);
        contentValues.put("quantity", quantity);




        int r = (int) db.insert("OrderDetails" ,null , contentValues);
        if(r== -1 )
        {
            return  false ;
        }
        else {
            return true;
        }
    }


    public int insertOrder (String orderDate,int cust_id){
        db = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues();
        contentValues.put("orderDate",orderDate);
        contentValues.put("cust_id", cust_id);
        int r = (int) db.insert("Orders" ,null , contentValues);

        db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT orderId FROM Orders ", null);
        int orderId=-1;
       if( res.moveToLast()){
           orderId  = res.getInt(0);
       }
        return orderId;

    }

    public ArrayList<Customer> getAllCustomers () {

        ArrayList<Customer> arrayList = new ArrayList();

        db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT customerId,Email,userName,password,gender,job,birthdate,is_admin,phoneNumber FROM Customer ", null);

        while (res.moveToNext()) {

            int customerId = res.getInt(0);
            String customerName = res.getString(1);
            String userName = res.getString(2);
            String password = res.getString(3);
            String gender = res.getString(4);
            String job = res.getString(5);
            String birthdate = res.getString(6);
            int isAdmin = res.getInt(7);
            String phoneNumber = res.getString(8);

            arrayList.add(new Customer(customerId,customerName, userName, password, gender,job,birthdate,isAdmin,phoneNumber));
        }

        return arrayList;
    }



    public ArrayList<Product> getAllProducts () {

        ArrayList<Product> arrayList = new ArrayList();

        db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT productId,productName,price,stockQuantity,barcode,cat_id,noOfSales FROM Product ", null);

        while (res.moveToNext()) {

            int productId = res.getInt(0);
            String productName = res.getString(1);
            int price = res.getInt(2);
            int stockQuantity = res.getInt(3);
            String barcode = res.getString(4);
            int cat_id = res.getInt(5);
            int noOfSales = res.getInt(6);

            Category category = getCategory(cat_id);

            arrayList.add(new Product(productId,productName,price,stockQuantity,barcode,category,noOfSales));
        }

        return arrayList;
    }

    public Category getCategory (int Cat_id ){
        String catId = Integer.toString(Cat_id);
        String[] arg={catId};
        db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT categoryId,categoryName FROM Category WHERE categoryId = ? ", arg);

        Category c=null ;
        while (res.moveToNext()) {

            int categoryId = res.getInt(0);
            String categoryName = res.getString(1);
            c= new Category(categoryId,categoryName);
        }

        return  c;
    }


    public Product getProduct(int prod_id)
    {
        String prodId = Integer.toString(prod_id);

        String[] arg={prodId};
        db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT productName,stockQuantity,price, barcode,cat_id,noOfSales FROM Product WHERE productId = ? ", arg);
        Product p =null;
        while (res.moveToNext())
        {
          //  int productId = res.getInt(0);
            String productName = res.getString(0);
            int stockQuantity = res.getInt(1);
            int price = res.getInt(2);
            String barcode = res.getString(3);
            int cat_id = res.getInt(4);
            int noOfSales = res.getInt(5);

            Category c = getCategory(cat_id);

            p = new Product(productName,price,stockQuantity,barcode,c,noOfSales);
        }
        return p;
    }

    public List<Category> getAllCategory ( ){
        db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT categoryId,categoryName FROM Category ",null);
        List<Category> arrayList = new ArrayList();
        Category c=null ;
        while (res.moveToNext()) {

            int categoryId = res.getInt(0);
            String categoryName = res.getString(1);
            arrayList.add( new Category(categoryId,categoryName));
        }

        return  arrayList;
    }

}

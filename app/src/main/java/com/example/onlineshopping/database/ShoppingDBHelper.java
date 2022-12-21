package com.example.onlineshopping.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.onlineshopping.database.models.Category;
import com.example.onlineshopping.database.models.Customer;
import com.example.onlineshopping.database.models.Order;
import com.example.onlineshopping.database.models.OrderDetials;
import com.example.onlineshopping.database.models.Product;
import com.example.onlineshopping.ui.OrderConfirm;

import java.util.ArrayList;
import java.util.List;

public class ShoppingDBHelper extends SQLiteOpenHelper {

    static  final  String DBname = "shopping.dp" ;
    SQLiteDatabase db ;
    Context context;


    public ShoppingDBHelper(Context context) {
        super(context, DBname, null, 5);
        this.context=context;
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //customer table
        sqLiteDatabase.execSQL("CREATE TABLE  Customer (customerId integer PRIMARY KEY autoincrement ,Email TEXT ,userName TEXT,password TEXT,gender Text,job Text,birthdate Text,is_admin integer,phoneNumber TEXT )");

        //Orders table
        sqLiteDatabase.execSQL("CREATE TABLE  Orders (orderId integer PRIMARY KEY autoincrement ,orderDate TEXT,feedbackRate integer,feedbackMessage TEXT,location TEXT ,cust_id integer,FOREIGN KEY(cust_id) REFERENCES Customer (customerId))");

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


    public Customer getCustomer (String email){
        String[] arg={email};
        db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT customerId,Email,userName,password,gender,job,birthdate,is_admin,phoneNumber  FROM Customer WHERE Email like ? ", arg);

        Customer c =null;
        if (res.moveToNext()) {
            int customerId = res.getInt(0);
            String customerName = res.getString(1);
            String userName = res.getString(2);
            String password = res.getString(3);
            String gender = res.getString(4);
            String job = res.getString(5);
            String birthdate = res.getString(6);
            int isAdmin = res.getInt(7);
            String phoneNumber = res.getString(8);

            c = new Customer(customerId, customerName, userName, password, gender, job, birthdate, isAdmin, phoneNumber);

        }
        return c;
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


    public int insertOrder (String orderDate,int cust_id,String location){
        db = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues();
        contentValues.put("orderDate",orderDate);
        contentValues.put("cust_id", cust_id);
        contentValues.put("location", location);


        int r = (int) db.insert("Orders" ,null , contentValues);

        db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT orderId FROM Orders ", null);
        int orderId=-1;
       while (res.moveToNext()){
           orderId  = res.getInt(0);
       }
        return orderId;

    }

    public int insertCategory (String CategoryName){
        db = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues();
        contentValues.put("categoryName",CategoryName);
        int r = (int) db.insert("Category" ,null , contentValues);

        db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT categoryId FROM Category ", null);
        int categoryId=-1;
        if( res.moveToLast()){
            categoryId  = res.getInt(0);
        }
        return categoryId;

    }



    public void updateFeedback(int orderId, String feedback, int feedbackRate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        ContentValues cv1 = new ContentValues();
        cv.put("feedbackRate",feedbackRate);
        cv.put("feedbackMessage",feedback);

        String order_id = Integer.toString(orderId);

        long result = db.update("Orders", cv, "orderId=?",new String[]{order_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            System.out.println("UPDATE SUCCESS !!!!!!!!!");
            Toast.makeText(context, " Successfully!", Toast.LENGTH_SHORT).show();
        }
    }


    public void updateSalesQuantity(int productId, int sales){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("noOfSales",sales);

        String prod_id = Integer.toString(productId);

        long result = db.update("Product", cv, "productId=?",new String[]{prod_id});

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






    public int getCategoryId (String cat_name ){
        //String catId = Integer.toString(Cat_id);
        String[] arg={cat_name};
        db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT categoryId FROM Category WHERE categoryName like ? ", arg);

     //   Category c=null ;
        int categoryId =-1;
    if (res.moveToNext()) {
         categoryId = res.getInt(0);
    }else{
        return  -1 ;
    }

        return  categoryId;
    }



    public Product getProduct(int prod_id)
    {
        String prodId = Integer.toString(prod_id);

        String[] arg={prodId};
        db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT productName,stockQuantity,price, barcode,cat_id,noOfSales FROM Product WHERE productId = ? ", arg);
        Product p =null;
        if (res.moveToNext())
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










    public void addProduct(/*int productId,*/String productName, int price, int stockQuantity, String barcode, int noOfSales, String categoryName){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        ContentValues cvCat = new ContentValues();

        //cv.put(PRODUCT_ID_COLUMN,productId);
        cv.put("productName",productName);
        cv.put("price",price);
        cv.put("stockQuantity",stockQuantity);
        cv.put("barcode",barcode);
        cv.put("noOfSales",noOfSales);
        int cat_id= getCategoryId(categoryName);
        if(cat_id == -1 ){
         cat_id = insertCategory(categoryName);
        }
        cv.put("cat_id",cat_id);

        long result = db.insert("Product",null,cv);
        if(result ==-1 ){
            Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Added Successfully",Toast.LENGTH_LONG).show();
        }
    }


    public Cursor readAllData(){
        String query = "SELECT * FROM " + "Product";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public void updateData(String productID, String productName, String price, String stockQuantity, String barcode, String noOfSales, String categoryName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        ContentValues cv1 = new ContentValues();
        cv.put("productName",productName);
        cv.put("price",price);
        cv.put("stockQuantity",stockQuantity);
        cv.put("barcode",barcode);
        cv.put("noOfSales",noOfSales);
       // cv1.put("categoryName",categoryName);

        //db.update("Category", cv1, "categoryName like ?",new String[]{categoryName});

        long result = db.update("Product", cv, "productId=?",new String[]{productID});
        db.close();
        if(result == -1){
            System.out.println("FAILED TO UPDATE!!!!!!!!!");
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            System.out.println("UPDATE SUCCESS !!!!!!!!!");
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }


   public void deleteOneProduct(String productID){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("Product","productId=?",new String[]{productID});
        if(result == -1){
            System.out.println("FAILED TO UPDATE!!!!!!!!!");
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else {
            System.out.println("UPDATE SUCCESS !!!!!!!!!");
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
    }





    public List<Order> getAllOrders (){
        db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT orderId,orderDate,feedbackRate,feedbackMessage,location FROM Orders ",null);
        List<Order> arrayList = new ArrayList();
        while (res.moveToNext()) {
            Order c=new Order() ;
            int orderId = res.getInt(0);
            String orderDate = res.getString(1);
            int feedbackRate = res.getInt(2);
            String feedbackMessage = res.getString(3);
            String location = res.getString(4);
            System.out.println("ORDER DATE !!!!!!"+orderDate);
            c.setOrderDate(orderDate);
            c.setOrderId(orderId);
            c.setFeedbackmessage(feedbackMessage);
            c.setFeedbackRate(feedbackRate);
            c.setLocation(location);
            arrayList.add( c);
        }

        return  arrayList;
    }


    public ArrayList<Product> getBestSellingProduct(){
        db = this.getReadableDatabase();
        ArrayList<Product>topSellingProducts = new ArrayList<>();
       Cursor res = db.rawQuery("SELECT noOfSales,productName FROM Product ORDER BY noOfSales DESC LIMIT 3",null);
        while (res.moveToNext()){
            int salesNumber = res.getInt(0);
            String name = res.getString(1);
            Product product = new Product(name,salesNumber);
//            System.out.println("DATA IS "+res.getString(1));
            topSellingProducts.add(product);
        }
        return  topSellingProducts;
    }


    public List<OrderDetials> getProductsForOrder (Order order ){
        System.out.println("INNNNNNNNNN DB ORDER ID IS ");
        String orderIdd = Integer.toString(order.getOrderId());
        System.out.println("DB ORDER ID IS "+orderIdd);
        String[] arg={orderIdd};
        db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT prod_id,quantity FROM OrderDetails WHERE order_id = ? ", arg);
        List<OrderDetials> orderDetialsList = new ArrayList<>();
       // OrderDetials orderDetials = null;

        while (res.moveToNext()) {
            Product p = new Product();
            int prodId = res.getInt(0);
            int quantity = res.getInt(1);


            p = getProduct(prodId);
            p.setProductId(prodId);
            orderDetialsList.add(new OrderDetials(quantity,order,p));
        }

        return  orderDetialsList;
    }




}

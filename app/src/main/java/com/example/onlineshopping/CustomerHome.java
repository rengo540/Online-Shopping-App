package com.example.onlineshopping;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshopping.database.ShoppingDBHelper;
import com.example.onlineshopping.database.models.Customer;
import com.example.onlineshopping.database.models.CustomerLoginHolder;
import com.example.onlineshopping.database.models.Product;
import com.example.onlineshopping.database.models.VoiceHolder;
import com.example.onlineshopping.databinding.ActivityCustomerHomeBinding;
import com.example.onlineshopping.ui.ProductDetails;
import com.example.onlineshopping.ui.adapter.ProductsAdapter;
import com.example.onlineshopping.ui.products.ProductsFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomerHome extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityCustomerHomeBinding binding;
    RecyclerView productsRecyclerView ;
    ProductsAdapter adapter ;
    ShoppingDBHelper shoppingDBHelper ;
    ProductsAdapter.RecyclerViewClickListener listener;
    SearchView searchView ;
    List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button scanbtn;
        TextView userName ,userBirthday ;

        binding = ActivityCustomerHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);


        productsRecyclerView = findViewById(R.id.ProductRecycleView);







        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        View header = navigationView.getHeaderView(0);
        userName =header.findViewById(R.id.userName);
        userBirthday=header.findViewById(R.id.userBirthday);
        Customer customer=CustomerLoginHolder.getInstance().getCustomer() ;
        userName.setText(customer.getUserName());
        userBirthday.setText(customer.getBirthdate());
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
            SharedPreferences.Editor editor =preferences.edit();
            editor.putString("remember","false");

            editor.apply();

            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //Barcode Search

    public void scanCode(View view) {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Press Volume Up to Flash");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLuncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLuncher = registerForActivityResult(new ScanContract(), result -> {

        if(result.getContents()!=null)
        {
           /* AlertDialog.Builder builder = new AlertDialog.Builder(CustomerHome.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();*/

            ProductsFragment.changeScanList(result.getContents());

        }
    });



    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                   /* adapter =new ProductsAdapter(this);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);

                    productsRecyclerView.setLayoutManager( mLayoutManager);
                    productsRecyclerView.setAdapter(adapter);

                    shoppingDBHelper = new ShoppingDBHelper(this);
                    //shoppingDBHelper.staticData();


                    products = shoppingDBHelper.getSimilarProducts(result.get(0));
                    adapter.setList(products,listener);
                    adapter.notifyDataSetChanged();*/
                    VoiceHolder.VoiceQuery = result.get(0);
                    ProductsFragment.changeVoiceList();
                }
                break;
        }
    }






}

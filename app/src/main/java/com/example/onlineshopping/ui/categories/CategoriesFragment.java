package com.example.onlineshopping.ui.categories;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshopping.R;
import com.example.onlineshopping.database.ShoppingDBHelper;
import com.example.onlineshopping.database.models.Category;
import com.example.onlineshopping.database.models.Customer;
import com.example.onlineshopping.databinding.FragmentCategoriesBinding;
import com.example.onlineshopping.ui.ProductDetails;
import com.example.onlineshopping.ui.ProductsCategory;
import com.example.onlineshopping.ui.adapter.CategoriesAdapter;
import com.example.onlineshopping.ui.adapter.ProductsAdapter;

import java.util.List;

public class CategoriesFragment extends Fragment {

    private FragmentCategoriesBinding binding;
    RecyclerView categoriesRecyclerView ;
     CategoriesAdapter adapter ;
     ShoppingDBHelper shoppingDBHelper ;
      CategoriesAdapter.RecyclerViewClickListener listener;
    List<Category> categories;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CategoriesViewModel categoriesViewModel =
                new ViewModelProvider(this).get(CategoriesViewModel.class);

        binding = FragmentCategoriesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textGallery;
        //categoriesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        categoriesRecyclerView = root.findViewById(R.id.categoriesRecycler);

        adapter =new CategoriesAdapter(getContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);

        categoriesRecyclerView.setLayoutManager( mLayoutManager);
        categoriesRecyclerView.setAdapter(adapter);

        shoppingDBHelper = new ShoppingDBHelper(getContext());
        //   shoppingDBHelper.staticData();

       categories= shoppingDBHelper.getAllCategory();

        listener=new CategoriesAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                int idCat = categories.get(position).getCategoryId();

                Intent intent =new Intent(getActivity(), ProductsCategory.class);
                intent.putExtra("idCat",idCat);
                startActivity(intent);

            }
        };

        adapter.setList(categories,listener);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
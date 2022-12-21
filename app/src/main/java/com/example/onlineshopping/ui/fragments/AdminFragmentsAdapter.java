package com.example.onlineshopping.ui.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AdminFragmentsAdapter extends FragmentStateAdapter {
    public AdminFragmentsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new AdminProductsFragment();
            case 1 :
                return  new OrdersFragment();
            case 2 :
            default:
                return  new ChartFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

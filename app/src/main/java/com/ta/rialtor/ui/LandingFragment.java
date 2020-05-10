package com.ta.rialtor.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ta.rialtor.R;
import com.ta.rialtor.adapters.LandingAdapter;
import com.ta.rialtor.model.RealEstate;
import com.ta.rialtor.vm.RealEstateViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LandingFragment extends Fragment {

    private static String LOG_TAG = "LandingFragment";

    private RecyclerView landingRecycler;
    private LandingAdapter landingAdapter;

    private RealEstateViewModel realEstateViewModel;

    public LandingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = NavHostFragment.findNavController(this);

        landingRecycler = getView().findViewById(R.id.landing_recycler);
        landingAdapter = new LandingAdapter(getContext());
        landingRecycler.setAdapter(landingAdapter);
        landingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        realEstateViewModel = ViewModelProviders.of(this).get(RealEstateViewModel.class);
        realEstateViewModel.getAllRealEstates().observe(getViewLifecycleOwner(), new Observer<List<RealEstate>>() {
            @Override
            public void onChanged(List<RealEstate> realEstates) {
                landingAdapter.setRealEstates(realEstates);
            }
        });

    }

    public void goToDetails(View view) {
        final NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.detailsFragment);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.options_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_search:
                final NavController navController = NavHostFragment.findNavController(this);
                navController.navigate(R.id.filterFragment);
                return true;
            case R.id.item_new:
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

}

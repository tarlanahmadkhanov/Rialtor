package com.ta.rialtor.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.ta.rialtor.R;
import com.ta.rialtor.model.LoadingStatus;
import com.ta.rialtor.vm.RealEstateViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {
    private final String TAG = "FilterFragment";

    private RealEstateViewModel realEstateViewModel;

    public FilterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View filterFragmentLayout = inflater.inflate(R.layout.fragment_filter, container, false);


        return filterFragmentLayout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        realEstateViewModel = ViewModelProviders.of(this).get(RealEstateViewModel.class);
        ProgressBar progressBar = getView().findViewById(R.id.progressBar);

        final NavController navController = NavHostFragment.findNavController(this);
        Button toLandingButton = getView().findViewById(R.id.to_landing_button);
        toLandingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                realEstateViewModel.getLoadingStatus(100).observe(getViewLifecycleOwner(), new Observer<LoadingStatus>() {
                    @Override
                    public void onChanged(LoadingStatus loadingStatus) {
                        Log.d(TAG, loadingStatus.getStatusDesc());
                        if (loadingStatus.isLoaded()) {
                            progressBar.setVisibility(View.GONE);
                            navController.navigate(R.id.landingFragment);
                        }
                    }
                });
            }
        });


    }


}

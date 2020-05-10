package com.ta.rialtor.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ta.rialtor.R;
import com.ta.rialtor.adapters.DetailsAdapter;
import com.ta.rialtor.model.RealEstateDetails;
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
public class DetailsFragment extends Fragment {

    private RecyclerView detailsRecycler;
    private DetailsAdapter detailsAdapter;

    private RealEstateViewModel realEstateViewModel;

    private TextView detailsText;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = NavHostFragment.findNavController(this);

        detailsText = getView().findViewById(R.id.detail_text);

        Button toMapButton = getView().findViewById(R.id.to_map_button);
        toMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.mapFragment);
            }
        });
        Button toChatButton = getView().findViewById(R.id.to_chat_button);
        toChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.chatFragment);
            }
        });


        detailsRecycler = getView().findViewById(R.id.recycler_details);
        detailsAdapter = new DetailsAdapter(getContext());
        detailsRecycler.setAdapter(detailsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        detailsRecycler.setLayoutManager(linearLayoutManager);

        String estateId = getArguments().getString("id");
        realEstateViewModel = ViewModelProviders.of(this).get(RealEstateViewModel.class);
        realEstateViewModel.getEstateDetails(estateId).observe(getViewLifecycleOwner(), new Observer<List<RealEstateDetails>>() {
            @Override
            public void onChanged(List<RealEstateDetails> realEstateDetails) {
                detailsAdapter.setEstateImages(realEstateDetails);
                detailsText.setText(realEstateDetails.get(0).getImageUrl());
            }
        });
    }
}

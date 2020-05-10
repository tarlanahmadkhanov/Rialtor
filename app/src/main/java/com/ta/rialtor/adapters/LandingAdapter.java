package com.ta.rialtor.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ta.rialtor.R;
import com.ta.rialtor.model.RealEstate;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class LandingAdapter extends RecyclerView.Adapter<LandingAdapter.LandingAdapterViewHolder> {
    private static final String TAG = "LandingAdapter";

    private List<RealEstate> realEstates;
    private LayoutInflater inflater;

    public LandingAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setRealEstates(List<RealEstate> realEstates) {
        this.realEstates = realEstates;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LandingAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.landing_recycler, parent, false);
        return new LandingAdapterViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull LandingAdapterViewHolder holder, int position) {
        RealEstate current = realEstates.get(position);
        //potom dobavit dinamicheskiy text
        String url = "https://a0.muscache.com/im/pictures/180d51e6-5950-4d8d-b3d4-2246884a13ac.jpg?aki_policy=xx_large";
        Glide.with(holder.imageView.getContext())
                .load(url)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (realEstates != null)
            return realEstates.size();
        else
            return 0;
    }

    class LandingAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textView;
        private final ImageView imageView;
        final LandingAdapter adapter;

        public LandingAdapterViewHolder(@NonNull View itemView, LandingAdapter landingAdapter) {
            super(itemView);
            textView = itemView.findViewById(R.id.main_text);
            imageView = itemView.findViewById(R.id.image_main);
            adapter = landingAdapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Bundle bundle = new Bundle();

            int position = getLayoutPosition();
            RealEstate realEstate = realEstates.get(position);
            NavController navController = Navigation.findNavController(v);

            bundle.putString("id", realEstate.getId());
            navController.navigate(R.id.action_landingFragment_to_detailsFragment, bundle);
        }

    }
}

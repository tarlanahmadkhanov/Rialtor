package com.ta.rialtor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ta.rialtor.R;
import com.ta.rialtor.model.RealEstateDetails;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsAdapterViewHolder> {

    private List<RealEstateDetails> estateImages;
    private LayoutInflater inflater;

    public DetailsAdapter(@NonNull Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setEstateImages(List<RealEstateDetails> estateImages) {
        this.estateImages = estateImages;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.details_recycler, parent, false);
        return new DetailsAdapter.DetailsAdapterViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsAdapterViewHolder holder, int position) {
        String current = estateImages.get(position).getImageUrl();
        String url = "https://a0.muscache.com/im/pictures/e7693564-0dae-4e77-a184-968df580ef2e.jpg?aki_policy=xx_large";
        Glide.with(holder.imageView.getContext())
                .load(url)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (estateImages != null)
            return estateImages.size();
        else
            return 0;
    }

    class DetailsAdapterViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        final DetailsAdapter adapter;

        public DetailsAdapterViewHolder(@NonNull View itemView, DetailsAdapter detailsAdapter) {
            super(itemView);
            imageView = itemView.findViewById(R.id.details_image);
            adapter = detailsAdapter;
        }
    }
}

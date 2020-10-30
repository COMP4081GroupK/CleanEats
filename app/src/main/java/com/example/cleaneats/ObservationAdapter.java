package com.example.cleaneats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ObservationAdapter extends RecyclerView.Adapter<ObservationAdapter.ObservationViewHolder> {

    private List<String> observations = new ArrayList<>();
    private Context context;

    public ObservationAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ObservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_observationsactivity_listitem, parent,
                false);

        return new ObservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObservationViewHolder holder, int position) {
        String observation = observations.get(position);
        holder.observation.setText(observation);
    }

    @Override
    public int getItemCount() {
        if (observations == null) return 0;
        return observations.size();
    }

    public void setObservations(List<String> observations) {
        this.observations = observations;
        notifyDataSetChanged();
    }

    class ObservationViewHolder extends RecyclerView.ViewHolder  {

        private TextView observation;

        public ObservationViewHolder(@NonNull View itemView) {
            super(itemView);

            observation = itemView.findViewById(R.id.tv_listItem_observation);
        }

    }

}

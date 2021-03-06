package com.example.cleaneats;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> implements Filterable {

    private List<RestaurantInfo> restaurants;
    private Context context;
    //private List<String> justNames; //We use this list for the search functions, as long as somebody remembers to actually declare it
    private List<RestaurantInfo> tempList;
    private ItemClickListener clickListener;

    public RestaurantAdapter(Context context, ItemClickListener clickListener) {
        this.context = context;
        tempList = new ArrayList<>();  //remember to initialize your arrays, kids! Or else.
        this.clickListener = clickListener;

    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_mainactivity_listitem, parent,
                false);

        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        final String restaurantName = restaurants.get(position).getName();
        String restaurantAddress = restaurants.get(position).getAddress();
        int inspectionScore = restaurants.get(position).getScore();

        holder.restaurantName.setText(restaurantName);
        holder.restaurantAddress.setText(restaurantAddress);
        holder.inspectionScore.setText(inspectionScore + "");

        if (inspectionScore < 60) {
            holder.inspectionScore.setBackground(context.getDrawable(R.drawable.bad_score_circle));
        } else if (inspectionScore < 80) {
            holder.inspectionScore.setBackground(context.getDrawable(R.drawable.medium_score_circle));
        } else if (inspectionScore <= 100) {
            holder.inspectionScore.setBackground(context.getDrawable(R.drawable.good_score_circle));
        }

        holder.googleSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, restaurantName);
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (restaurants == null) return 0;
        return restaurants.size();
    }

    public void setRestaurants(List<RestaurantInfo> restaurants) {
        this.restaurants = restaurants;
        notifyDataSetChanged();
    }

    public void searchSetUp(){  //Was NonNull needed? Who knows! I just did it to get rid of the @ in the IDE

        for(int x = 0; x < this.restaurants.size(); x++){
            this.tempList.add(this.restaurants.get(x));
        }
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView restaurantName;
        private TextView restaurantAddress;
        private TextView inspectionScore;

        private Button googleSearchButton;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurantName = itemView.findViewById(R.id.tv_listItem_restaurantName);
            restaurantAddress = itemView.findViewById(R.id.tv_listItem_restaurantAddress);
            inspectionScore = itemView.findViewById(R.id.tv_listItem_inspectionScore);
            googleSearchButton = itemView.findViewById(R.id.bt_listItem_websiteLink);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition());
        }
    }

    public Filter getFilter() {   //this is gonna be a bitch to implement.
        searchSetUp();
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    tempList = restaurants;
                } else {
                    List<RestaurantInfo> filteredList = new ArrayList<>();
                    for (RestaurantInfo rest : restaurants) {
                        if (rest.getName().toLowerCase().contains(charString.toLowerCase())) {    //so results aren't case sensitive
                            filteredList.add(rest);
                        }
                    }
                    tempList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = tempList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                tempList = (ArrayList<RestaurantInfo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    interface ItemClickListener {
        void onItemClick(int position);
    }
}

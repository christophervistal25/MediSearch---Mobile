package com.example.medisearch.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.medisearch.Models.Stores.Store;
import com.example.medisearch.R;

import java.util.ArrayList;
import java.util.List;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.StoreHolder> {

    private List<Store> list_store;
    public OnClickResponse OnClickResponse;



    public StoresAdapter(List<Store> list_country) {
        this.list_store = list_country;
    }

    public interface OnClickResponse {
        void onSuccess(long id, String storeName, String storeAddress);
    }


    @Override
    public StoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_layout, parent, false);
        return new StoreHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreHolder holder, int position) {
        Store store = list_store.get(position);

        holder.storeName.setText(store.getName());
        holder.storeAddress.setText(store.getLocatedAt());

        holder.storeLayout.setOnClickListener((view) -> {
            if  ( OnClickResponse != null ) {
                    OnClickResponse.onSuccess(store.getId(), store.getName(), store.getLocatedAt());
            }
        });

    }


    @Override
    public int getItemCount() {
        return list_store.size();
    }

    public void filterList(ArrayList<Store> filteredList) {
        list_store = filteredList;
        notifyDataSetChanged();
    }

    public class StoreHolder extends ViewHolder {
        RelativeLayout storeLayout;
        TextView storeName, storeAddress;

        public StoreHolder(View itemView) {
            super(itemView);
            storeLayout = itemView.findViewById(R.id.storeLayout);
            storeName = itemView.findViewById(R.id.storeName);
            storeAddress = itemView.findViewById(R.id.storeAddress);
        }
    }
}

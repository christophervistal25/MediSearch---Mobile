package com.example.medisearch.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.medisearch.Models.Stores.Store;
import com.example.medisearch.R;

import java.util.List;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.StoreHolder> {

    private List<Store> list_store;

    public StoresAdapter(List<Store> list_country) {
        this.list_store = list_country;
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
    }


    @Override
    public int getItemCount() {
        return list_store.size();
    }

    public class StoreHolder extends ViewHolder {
        TextView storeName, storeAddress;

        public StoreHolder(View itemView) {
            super(itemView);
            storeName = itemView.findViewById(R.id.storeName);
            storeAddress = itemView.findViewById(R.id.storeAddress);
        }
    }
}

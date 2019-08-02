package com.example.medisearch;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.medisearch.APIsInterface.Stores;
import com.example.medisearch.Adapters.StoresAdapter;
import com.example.medisearch.Models.Service;
import com.example.medisearch.Models.Stores.Store;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StoreFragment extends Fragment {


    RecyclerView recyclerView;
    StoresAdapter storeAdapter;
    List<Store> store_list;

    public StoreFragment() {
        // Required empty public constructor
    }

    private void getStores(View view)
    {
        Retrofit retrofit           = Service.RetrofitInstance(getContext());
        Stores services             = retrofit.create(Stores.class);
        Call<List<Store>> storeCall = services.stores();

        storeCall.enqueue(new Callback<List<Store>>() {
            @Override
            public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
                if  ( response.isSuccessful() ) {
                    if (response.body() != null) {
                        store_list = response.body();
                        buildRecyclerView(view, store_list);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Store>> call, Throwable t) {

            }
        });


    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText searchStoreField = view.findViewById(R.id.searchStore);

        searchStoreField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        // Display all available stores in recyclerview.
        this.getStores(view);

    }

    private void buildRecyclerView(View view, List<Store> storeList) {
        storeAdapter = new StoresAdapter(storeList);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(storeAdapter);
    }

    private void filter(String text) {
        ArrayList<Store> filteredList = new ArrayList<>();

        for (Store item : store_list) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        storeAdapter.filterList(filteredList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

}

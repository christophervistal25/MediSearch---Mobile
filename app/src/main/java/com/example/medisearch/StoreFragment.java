package com.example.medisearch;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medisearch.APIsInterface.Stores;
import com.example.medisearch.Adapters.StoresAdapter;
import com.example.medisearch.Models.Service;
import com.example.medisearch.Models.Stores.Store;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment {


    RecyclerView recyclerView;
    StoresAdapter storeAdapter;

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
                        storeAdapter = new StoresAdapter(response.body());

                        recyclerView = view.findViewById(R.id.recycler_view);

                        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

                        LinearLayoutManager layoutManager = new  LinearLayoutManager(getContext());

                        recyclerView.setLayoutManager(layoutManager);


                        recyclerView.setAdapter(storeAdapter);
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
        this.getStores(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

}

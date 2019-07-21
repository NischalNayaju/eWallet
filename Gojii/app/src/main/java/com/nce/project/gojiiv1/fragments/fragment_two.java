package com.nce.project.gojiiv1.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nce.project.gojiiv1.R;
import com.nce.project.gojiiv1.helper.Api;
import com.nce.project.gojiiv1.helper.RecyclerViewAdapter;
import com.nce.project.gojiiv1.helper.RetrofitAPI;
import com.nce.project.gojiiv1.helper.Transaction;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class fragment_two extends Fragment {
    private CompositeDisposable compositeDisposable ;
    Api api;
    private RecyclerViewAdapter adapter;
    private ArrayList<Transaction> mAndroidArrayList;
    private RecyclerView recyclerView;

    public fragment_two() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable= new CompositeDisposable();
        Retrofit retrofitAPI = RetrofitAPI.getInstance();
        api = retrofitAPI.create(Api.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_two, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("customerInfo", Context.MODE_PRIVATE);
        String accessToken_value = sharedPreferences.getString("accessToken", "");
        Toast.makeText(getActivity(), "accessToken value is:  " + accessToken_value, Toast.LENGTH_SHORT).show();
        compositeDisposable.add(api
                .transaction(accessToken_value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse,this::handleError));


        return v;
    }

    private void handleResponse(List<Transaction> transactions) {
        mAndroidArrayList = new ArrayList<>(transactions);
        adapter = new RecyclerViewAdapter(mAndroidArrayList, getContext());
        recyclerView.setAdapter(adapter);
        Toast.makeText(getActivity(), "success getting transactions", Toast.LENGTH_SHORT).show();
    }


    private void handleError(Throwable throwable) {
        Toast.makeText(getActivity(), "error: "+throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

}

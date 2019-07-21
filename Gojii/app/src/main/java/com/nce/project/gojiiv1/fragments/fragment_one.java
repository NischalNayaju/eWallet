package com.nce.project.gojiiv1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.nce.project.gojiiv1.R;
import com.nce.project.gojiiv1.UPay.LoadFund;


public class fragment_one extends Fragment {
    public static ImageButton loadFundbtn;

    public fragment_one() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        loadFundbtn = v.findViewById(R.id.loadfund);
        loadFundbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFund();
            }
        });


        return v;

    }

    private void loadFund() {

        Intent intent = new Intent(getActivity(), LoadFund.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);





    }
}
package com.nce.project.gojiiv1.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nce.project.gojiiv1.R;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    ArrayList<Transaction> tList;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<Transaction> tList, Context mContext) {
        this.tList = tList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.transaction_list, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.operation.setText(String.valueOf(tList.get(i).operation));
        viewHolder.timestamp.setText(String.valueOf(tList.get(i).createdAt));
        viewHolder.transactionAmount.setText(String.valueOf(tList.get(i).amount));
    }

    @Override
    public int getItemCount() {
        return tList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView operation, timestamp, transactionAmount;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timestamp = itemView.findViewById(R.id.timestamp_txt);
            operation = itemView.findViewById(R.id.operation_txt);
            transactionAmount = itemView.findViewById(R.id.transaction_amount_txt);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}

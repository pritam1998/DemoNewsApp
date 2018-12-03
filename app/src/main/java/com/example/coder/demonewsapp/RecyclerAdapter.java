package com.example.coder.demonewsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NewsViewHolder> {
    private LinkedList<String> titleList;
    private LinkedList<String> descriptionList;
    private LayoutInflater inflater;

    RecyclerAdapter(Context context, LinkedList<String> titleList, LinkedList<String> descriptionList) {
        inflater = LayoutInflater.from(context);
        this.titleList = titleList;
        this.descriptionList = descriptionList;

    }

    @NonNull
    @Override
    public RecyclerAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = inflater.inflate(R.layout.recycler_layout, parent, false);
        return new NewsViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.NewsViewHolder holder, int position) {
        String currentTitle = titleList.get(position);
        String currentDescription = descriptionList.get(position);
        holder.titleText.setText(currentTitle);
        holder.descriptionText.setText(currentDescription);
    }

    @Override
    public int getItemCount() {
        if (titleList == null || descriptionList == null) return 0;
        else return titleList.size();
    }

    public void swapData(LinkedList<String> mTitleList, LinkedList<String> mDescriptionList) {
        titleList = mTitleList;
        descriptionList = mDescriptionList;
        notifyDataSetChanged();

    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        public final TextView titleText;
        public final TextView descriptionText;
        final RecyclerAdapter adapter;

        public NewsViewHolder(View itemView, RecyclerAdapter adapter) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title);
            descriptionText = itemView.findViewById(R.id.description);
            this.adapter = adapter;
        }
    }
}

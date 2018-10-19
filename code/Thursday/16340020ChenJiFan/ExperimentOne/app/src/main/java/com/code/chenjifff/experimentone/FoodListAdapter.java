package com.code.chenjifff.experimentone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public  class FoodListAdapter extends RecyclerView.Adapter<FoodListViewHolder>{
    private ArrayList<Food> food_data;
    private Context context;
    private OnItemClickListener item_click_listener;

    public FoodListAdapter(Context _context, ArrayList<Food> _food_data) {
        context = _context;
        food_data = _food_data;
    }

    public interface OnItemClickListener {
        void OnItemLongClick(int i);
        void OnItemClick(int i);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.item_click_listener = listener;
    }

    @Override
    public int getItemCount() {
        return food_data.size();
    }

    @NonNull
    @Override
    public FoodListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        FoodListViewHolder holder =
                new FoodListViewHolder(LayoutInflater.from(context).inflate(R.layout.food_list_ltem, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final FoodListViewHolder viewHolder, int i) {
        if(item_click_listener != null) {
            viewHolder.food_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item_click_listener.OnItemClick(viewHolder.getAdapterPosition());
                }
            });
            viewHolder.food_name.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    item_click_listener.OnItemLongClick(viewHolder.getAdapterPosition());
                    return true;
                }
            });
        }
        viewHolder.food_icon.setText(food_data.get(i).getType_simple());
        viewHolder.food_name.setText(food_data.get(i).getName());
    }
}


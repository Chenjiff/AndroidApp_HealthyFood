package com.code.chenjifff.experimentone;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FoodListViewHolder extends RecyclerView.ViewHolder {
    Button food_icon;
    TextView food_name;
    public FoodListViewHolder(View _view) {
        super(_view);
        food_icon = (Button) _view.findViewById(R.id.food_icon);
        food_name = (TextView) _view.findViewById(R.id.food_name);
    }
}

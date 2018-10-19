package com.code.chenjifff.experimentone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodCollectListViewAdapter extends BaseAdapter {
    private ArrayList<Food> data;
    private Context context;

    FoodCollectListViewAdapter(Context _context, ArrayList<Food> _data) {
        context = _context;
        data = _data;
    }

    private class ViewHolder {
        Button food_icon;
        TextView food_name;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        if(data == null) {
            return null;
        }
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        ViewHolder view_holder;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.food_list_ltem,null);
            view_holder = new ViewHolder();
            view_holder.food_icon = view.findViewById(R.id.food_icon);
            view_holder.food_name = view.findViewById(R.id.food_name);
            view.setTag(view_holder);
        }
        else {
            view_holder = (ViewHolder) view.getTag();
        }
        view_holder.food_icon.setText(data.get(i).getType_simple());
        view_holder.food_name.setText(data.get(i).getName());
        return view;
    }
}

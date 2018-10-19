package com.code.chenjifff.experimentone;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FoodListActivity extends AppCompatActivity implements View.OnClickListener, FoodListAdapter.OnItemClickListener {
    private RecyclerView rv;
    private ListView lv;
    private FloatingActionButton but;
    private ArrayList<Food> food_data;
    private FoodCollectListViewAdapter collect_adapter;
    private ArrayList<Food> food_collect_data;
    private int current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        food_data = new ArrayList<Food>();
        food_data.add(new Food("大豆", "粮", "粮食", "蛋白质", "#BB4C3B"));
        food_data.add(new Food("十字花科蔬菜", "蔬", "蔬菜", "维生素C", "#C48D30"));
        food_data.add(new Food("牛奶", "饮", "饮品", "钙", "#4469B0"));
        food_data.add(new Food("海鱼", "肉", "肉食", "蛋白质", "#20A17B"));
        food_data.add(new Food("菌菇类", "蔬", "蔬菜", "微量元素", "#BB4C3B"));
        food_data.add(new Food("番茄", "蔬", "蔬菜", "番茄红素", "#4469B0"));
        food_data.add(new Food("胡萝卜", "蔬", "蔬菜", "胡萝卜素", "#20A17B"));
        food_data.add(new Food("荞麦", "粮", "粮食", "膳食纤维", "#BB4C3B"));
        food_data.add(new Food("鸡蛋", "杂", "杂", "几乎所有营养物质", "#C48D30"));
        //处理RecyclerView
        rv = (RecyclerView) this.findViewById(R.id.food_list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        FoodListAdapter adapter = new FoodListAdapter(this, food_data);
        adapter.setOnItemClickListener(this);
        rv.setAdapter(adapter);
        //处理收藏夹界面的ListView
        lv = (ListView) this.findViewById(R.id.food_collect);
        food_collect_data = new ArrayList<Food>();
        collect_adapter = new FoodCollectListViewAdapter(this, food_collect_data);
        lv.setAdapter(collect_adapter);
        //添加控件到本类
        FloatingActionButton but = (FloatingActionButton) findViewById(R.id.collect_button);
        //添加各种监听器
        addListener();
    }
    public void addListener() {
        but = (FloatingActionButton) findViewById(R.id.collect_button);
        but.setOnClickListener(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food food = food_collect_data.get(position);
                Intent intent = new Intent(FoodListActivity.this, FoodDetail.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("food", food);
                Boolean if_collect = food_collect_data.contains(food);
                bundle.putBoolean("if_collect", if_collect);
                intent.putExtras(bundle);
                current = position;
                startActivityForResult(intent, 1);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Food food = food_collect_data.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(FoodListActivity.this);
                dialog.setTitle("提示");
                dialog.setMessage("是否删除" + food.getName() + "?" );
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        food_collect_data.remove(food);
                        collect_adapter.notifyDataSetChanged();
                    }
                });
                dialog.show();
                return true;
            }
        });
    }
    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.collect_button:
                ListView collect = (ListView) findViewById(R.id.food_collect);
                RelativeLayout collect_bar = (RelativeLayout) findViewById(R.id.collect_bar);
                if(collect.getVisibility() == View.INVISIBLE) {
                    collect.setVisibility(View.VISIBLE);
                    collect_bar.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.INVISIBLE);
                    but.setImageResource(R.mipmap.mainpage);
                }
                else {
                    collect.setVisibility(View.INVISIBLE);
                    collect_bar.setVisibility(View.INVISIBLE);
                    rv.setVisibility(View.VISIBLE);
                    but.setImageResource(R.mipmap.collect);
                }
                break;
            default:
                break;
        }
    }
    //食品列表各个项的点击事件
    @SuppressLint("RestrictedApi")
    @Override
    public void OnItemClick(int i) {
        Food food = food_data.get(i);
        Intent intent = new Intent(this, FoodDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("food", food);
        Boolean if_collect = food_collect_data.contains(food);
        bundle.putBoolean("if_collect", if_collect);
        intent.putExtras(bundle);
        current = i;
        startActivityForResult(intent, 1);
    }

    @Override
    public void OnItemLongClick(int i) {
        Food food = food_data.get(i);
        Toast.makeText(this, "删除" + food.getName(), Toast.LENGTH_SHORT).show();
        food_data.remove(food);
        rv.getAdapter().notifyItemRemoved(i);
    }

    @Override
    protected void onActivityResult(int rc, int resc, Intent intent) {
        boolean if_collect = intent.getExtras().getBoolean("if_collect");
        Food food = food_data.get(current);
        if(if_collect) {
            if(!food_collect_data.contains(food)) {
                food_collect_data.add(food);
                current = 0;
                collect_adapter.notifyDataSetChanged();
            }
        }
        else {
            if(food_collect_data.contains(food)) {
                food_collect_data.remove(food);
                collect_adapter.notifyDataSetChanged();
            }
        }
    }
}

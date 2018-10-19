package com.code.chenjifff.experimentone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class FoodDetail extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private Button collect_but;
    private boolean if_collect;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        Food food = new Food("null", "null","null","null","#FFFFFF");
        Bundle ex = getIntent().getExtras();
        if(ex != null) {
            food = (Food) ex.getSerializable("food");
            if_collect = ex.getBoolean("if_collect");
        }
        TextView name1 = (TextView) findViewById(R.id.food_detail_name1);
        name1.setText(food.getName());
        TextView name2 = (TextView) findViewById(R.id.food_detail_name2);
        name2.setText(food.getName());
        TextView nutrient = (TextView) findViewById(R.id.food_detail_nutrient);
        nutrient.setText("富含" + food.getNutrient());
        FrameLayout bar = (FrameLayout) findViewById(R.id.detail_top_bar);
        bar.setBackgroundColor(food.getBackground_color());

        collect_but = (Button) findViewById(R.id.collect_icon);
        if(if_collect) {
            collect_but.setBackgroundResource(R.mipmap.full_star);
            collect_but.setTag(1);
        }

        collect_but.setTag(0);
        collect_but.setOnClickListener(this);
        back = (Button) findViewById(R.id.back_button);
        back.setOnClickListener(this);

        lv = (ListView) findViewById(R.id.detail_list);
        ArrayList<Map<String, Object>> data = new ArrayList();
        String[] items = {"分享信息", "不感兴趣", "查找更多信息", "出错反馈"};
        for(int i = 0; i < 4; i++) {
            Map<String, Object> _data = new LinkedHashMap<String, Object>();
            _data.put("item_name", items[i]);
            data.add(_data);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.detail_list_layout, new String[] {"item_name"}, new int[]{R.id.item});
        lv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.back_button:
                Bundle bundle = new Bundle();
                bundle.putBoolean("if_collect", if_collect);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(1, intent);
                finish();
                break;
            case R.id.collect_icon:
                if((int) collect_but.getTag() == 0) {
                    collect_but.setBackgroundResource(R.mipmap.full_star);
                    collect_but.setTag(1);
                    if_collect = true;
                }
                else {
                    collect_but.setBackgroundResource(R.mipmap.empty_star);
                    collect_but.setTag(0);
                    if_collect = false;
                }
                break;
            default:
                break;
        }
    }
}

package com.code.chenjifff.experimentone;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String SearchType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button search_but = (Button) findViewById(R.id.SearchButton);
        search_but.setOnClickListener(this);
        RadioGroup search_group = (RadioGroup) findViewById(R.id.SearchType);
        search_group.setOnCheckedChangeListener(new CheckedChangeListener());
        RadioButton rb = (RadioButton) findViewById(search_group.getCheckedRadioButtonId());
        SearchType = rb.getText().toString();
    }
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.SearchButton:
                EditText SearchWord = (EditText) findViewById(R.id.SearchBar);
                String word = SearchWord.getText().toString();
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                if(word.isEmpty()) {
                    Toast.makeText(MainActivity.this, "搜素内容不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                if(word.equals("Health"))
                    dialog.setMessage(SearchType + "搜索成功");
                else
                    dialog.setMessage("搜索失败");
                dialog.setTitle("提示");
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "对话框“取消”按钮被点击", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface di, int i){
                        Toast.makeText(MainActivity.this, "对话框“确定”按钮被点击", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                break;
            default:
                break;
        }
    }
    class CheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton CheckedButton = (RadioButton) findViewById(group.getCheckedRadioButtonId());
            SearchType = CheckedButton.getText().toString();
            Toast.makeText(MainActivity.this, SearchType + "被选中", Toast.LENGTH_SHORT).show();
        }
    }
}

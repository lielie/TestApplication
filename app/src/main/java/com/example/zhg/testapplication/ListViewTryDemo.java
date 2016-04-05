package com.example.zhg.testapplication;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.zhg.testapplication.OriginAdapter.MyListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewTryDemo extends AppCompatActivity {

    private ListView listView;
    private ImageButton sumButton;
    private MyListViewAdapter adapter;
    private List<Map<String, Object>> itemList;

    private int[] imgID = {R.drawable.help1,
        R.drawable.help2, R.drawable.help3, R.drawable.help4};
    private String[] goodsName = {"goods1",
        "goods2","goods3", "goods4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);

        listView = (ListView) findViewById(R.id.listView);
        sumButton = (ImageButton) findViewById(R.id.imgSum);

        init();

        adapter = new MyListViewAdapter(this, itemList);
        listView.setAdapter(adapter);

        sumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "";
                for (int i = 0;i < goodsName.length; i++) {
                    if(adapter.hasChecked(i) == true){
                        msg += goodsName[i] + " ";
                    }
                }

                new AlertDialog.Builder(ListViewTryDemo.this)
                        .setTitle("you choose:\n")
                        .setMessage(msg)
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
    }

    private void init() {
        int mLength = goodsName.length;
        itemList = new ArrayList<>();
        for (int i = 0;i < mLength; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("imageView", imgID[i]);
            map.put("title", "物品名称");
            map.put("info", goodsName[i]);
            itemList.add(map);
        }
    }
}

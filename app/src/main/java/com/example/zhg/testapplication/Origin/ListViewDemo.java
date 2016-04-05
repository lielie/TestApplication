package com.example.zhg.testapplication.Origin;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.zhg.testapplication.Adapter.MyListViewTryAdapter;
import com.example.zhg.testapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewDemo extends AppCompatActivity {

    private ListView listView;
    private ImageButton imgBtnSum;
    private MyListViewTryAdapter listViewAdapter;
    private List<Map<String, Object>> listItems;
    private Integer[] imgIDs = {R.drawable.help1,
        R.drawable.help2, R.drawable.help3, R.drawable.help4};
    private String[] goodsNames = {"商品1", "商品2", "商品3", "商品4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);

        listView = (ListView) findViewById(R.id.listView);
        imgBtnSum = (ImageButton) findViewById(R.id.imgSum);
        imgBtnSum.setOnClickListener(new ClickEvent());
        listItems = getListItems();
        listViewAdapter = new MyListViewTryAdapter(this, listItems);  //创建适配器
        listView.setAdapter(listViewAdapter);
    }

    /**
     * 初始化商品信息
     * @return
     */
    public List<Map<String,Object>> getListItems() {
        List<Map<String, Object>> listItems = new ArrayList<>();

        for (int i = 0;i < goodsNames.length; i++) {
            Map<String, Object> map = new HashMap<>();

            map.put("imageView", imgIDs[i]);
            map.put("title", "物品名称: ");
            map.put("info", goodsNames[i]);
            listItems.add(map);
        }
        return listItems;
    }

    class ClickEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String goodsList = "";
            for (int i = 0;i < listItems.size(); i++) {
                goodsList += listViewAdapter.hasChecked(i) ? goodsNames[i] + " " : "";
            }
            new AlertDialog.Builder(ListViewDemo.this)
                    .setTitle("购物清单:")
                    .setMessage("你好,你选择了如下商品: \n" + goodsList)
                    .setPositiveButton("确定", null)
                    .show();
        }
    }
}

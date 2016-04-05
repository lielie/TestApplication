package com.example.zhg.testapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhg.testapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by zhg on 16/4/1.
 */
public class MyListViewTryAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String, Object>> listItems;
    private LayoutInflater container;
    private boolean[] isChecked;

    public MyListViewTryAdapter(Context context, List<Map<String, Object>> listItems) {
        this.context = context;
        this.listItems = listItems;
        container = LayoutInflater.from(context);
        isChecked = new boolean[getCount()];
    }

    public static class GoodsItem {
        ImageView imageView;
        TextView title;
        TextView info;
        CheckBox hasChecked;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final GoodsItem goodsItem;
        if (convertView == null) {
            convertView = container.inflate(R.layout.list_item, null);

            goodsItem = new GoodsItem();
            goodsItem.imageView = (ImageView) convertView.findViewById(R.id.imageItem);
            goodsItem.hasChecked = (CheckBox) convertView.findViewById(R.id.checkItem);
            goodsItem.info = (TextView) convertView.findViewById(R.id.infoItem);
            goodsItem.title = (TextView) convertView.findViewById(R.id.titleItem);

            convertView.setTag(goodsItem);
        }
        else {
            goodsItem = (GoodsItem) convertView.getTag();
        }

        goodsItem.imageView.setImageResource(Integer.parseInt(String.valueOf(
                listItems.get(position).get("imageView")
        )));
        goodsItem.title.setText(String.valueOf(listItems.get(position).get("title")));
        goodsItem.info.setText(String.valueOf(listItems.get(position).get("info")));

        goodsItem.hasChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkChange(position);
            }
        });

        return convertView;
    }

    private void checkChange(int position) {
        isChecked[position] = !isChecked[position];
    }

    public boolean hasChecked(int position) {
        return isChecked[position];
    }
}

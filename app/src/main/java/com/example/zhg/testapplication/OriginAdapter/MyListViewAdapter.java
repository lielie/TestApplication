package com.example.zhg.testapplication.OriginAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhg.testapplication.R;

import java.util.List;
import java.util.Map;

/**
 * Created by zhg on 16/3/31.
 */
public class MyListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<Map<String, Object>> listItems;
    private LayoutInflater mContainer;
    private boolean[] hasChecked;

    public MyListViewAdapter(Context mContext, List<Map<String, Object>> listItems) {
        this.mContext = mContext;

        //创建视图容器并设置上下文
        mContainer = LayoutInflater.from(mContext);
        this.listItems = listItems;
        hasChecked = new boolean[getCount()];
    }

    public final class ListItemView {
        public ImageView imageView;
        public TextView title;
        public TextView info;
        public CheckBox checkBox;
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


    /**
     * 记录勾选了哪个物品
     * @param checkedID 选中的物品序号
     */
    private void checkedChange(int checkedID) {
        hasChecked[checkedID] = !hasChecked[checkedID];
    }

    /**
     * 判断物品是否选择
     * @param checkedID 物品序号
     * @return 返回是否选中状态
     */
    public boolean hasChecked(int checkedID) {
        return hasChecked[checkedID];
    }

    /**
     * ListView Item设置
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int selectID = position;
        //自定义视图
        ListItemView listItemView = null;

        if (convertView == null) {
            listItemView = new ListItemView();
            //获取list_item布局文件的视图
            convertView = mContainer.inflate(R.layout.list_item, null);

            //获取控件对象
            listItemView.imageView = (ImageView) convertView.findViewById(R.id.imageItem);
            listItemView.title = (TextView) convertView.findViewById(R.id.titleItem);
            listItemView.info = (TextView) convertView.findViewById(R.id.infoItem);
            listItemView.checkBox = (CheckBox) convertView.findViewById(R.id.checkItem);

            //设置控件集到convertView
            convertView.setTag(listItemView);
        }
        else {
            listItemView = (ListItemView) convertView.getTag();
        }

        //设置文字和图片
        listItemView.imageView.setBackgroundResource(Integer.parseInt(String.valueOf(listItems
                .get(position).get("imageView"))));
        listItemView.title.setText((String) listItems.get(position).get("title"));
        listItemView.info.setText((String) listItems.get(position).get("info"));

        //注册多选框状态事件处理
        listItemView.checkBox
                .setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        //记录物品选中状态
                        checkedChange(selectID);
                    }
                });

        return convertView;
    }

}

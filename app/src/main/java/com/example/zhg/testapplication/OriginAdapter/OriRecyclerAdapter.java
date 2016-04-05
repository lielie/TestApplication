package com.example.zhg.testapplication.OriginAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhg.testapplication.R;

import java.util.List;

/**
 * Created by zhg on 16/4/1.
 */
public class OriRecyclerAdapter extends RecyclerView.Adapter<OriRecyclerAdapter.MyViewHolder> {

    private List<String> mDatas;
    private Context context;

    public OriRecyclerAdapter(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder myViewHolder = new MyViewHolder(
                LayoutInflater.from(context).
                        inflate(R.layout.recycler_item, viewGroup, false)
        );
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.textView.setText(mDatas.get(i));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.numView);
        }
    }
}
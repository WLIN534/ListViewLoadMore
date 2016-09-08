package com.zl.listviewloadmore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhanglin on 2016/9/7.
 */
public class ListViewAdapter extends BaseAdapter {
    Context mContext;

    public List<String> getDatas() {
        return datas;
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
    }

    List<String> datas;
    private LayoutInflater mInflater; // 视图容器

    public ListViewAdapter(Context mContext) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);// 创建视图容器并设置上下文
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            // 获取list_item布局文件的视图
            view = mInflater.from(mContext).inflate(R.layout.item, viewGroup, false);

            holder = new ViewHolder();
            holder.textView = (TextView) view.findViewById(R.id.text);
            // 设置控件集到convertView
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(datas.get(i));
        return view;
    }

    class ViewHolder {
        TextView textView;
    }
}

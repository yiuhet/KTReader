package com.example.yiuhet.ktreader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.model.entity.HistoryCollect;

import java.util.ArrayList;

/**
 * Created by yiuhet on 2017/6/13.
 */

public class CollectAdapter extends BaseExpandableListAdapter{

    private ArrayList<String> gData;
    private ArrayList<ArrayList<HistoryCollect>> iData;
    private Context mContext;

    public CollectAdapter(ArrayList<String> gData,ArrayList<ArrayList<HistoryCollect>> iData, Context mContext) {
        this.gData = gData;
        this.iData = iData;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return iData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return gData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return iData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup groupHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_exlist_group, parent, false);
            groupHolder = new ViewHolderGroup();
            groupHolder.tv_group_name = (TextView) convertView.findViewById(R.id.tv_group_name);
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }
        groupHolder.tv_group_name.setText(gData.get(groupPosition).toString());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (groupPosition == 0) {
            ViewHolderItem itemHolder;
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_exlist_item, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.tv_title = (TextView) convertView.findViewById(R.id.collect_title);
            itemHolder.tv_time = (TextView) convertView.findViewById(R.id.collect_time);
            convertView.setTag(itemHolder);
            itemHolder.tv_title.setText(iData.get(groupPosition).get(childPosition).getTitle());
            itemHolder.tv_time.setText(iData.get(groupPosition).get(childPosition).getTime());
        } else {
            ViewHolderUnsplash itemHolder;
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_exlist_imgitem, parent, false);
            itemHolder = new ViewHolderUnsplash();
            itemHolder.iv_image = (ImageView) convertView.findViewById(R.id.iv_unsplash);
            itemHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_collect_time);
            convertView.setTag(itemHolder);
            //这里应该是从缓存里读取 而不是在线显示
            Glide.with(mContext)
                    .load(iData.get(groupPosition).get(childPosition).getUrl())
                    .into(itemHolder.iv_image);
            itemHolder.tv_time.setText(iData.get(groupPosition).get(childPosition).getTime());
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class ViewHolderGroup{
        private TextView tv_group_name;
    }

    private static class ViewHolderItem{
        private TextView tv_title;
        private TextView tv_time;
    }

    private static class ViewHolderUnsplash{
        private ImageView iv_image;
        private TextView tv_time;
    }
}

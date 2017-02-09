package com.example.apc.punchvillain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sally on 2017/1/prop4.
 */

public class ListItemAdapter extends BaseAdapter {
    private List<DataHolder> mDataList = new ArrayList<>();
    private LayoutInflater mInflater;
    public ListItemAdapter(Context context, ArrayList<DataHolder> dataList){
        if (dataList != null && dataList.size()>0){
            mDataList.addAll(dataList);
        }
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {

            holder=new ViewHolder();

            convertView = mInflater.inflate(R.layout.list_item, null);
            holder.mTvTitle = (TextView)convertView.findViewById(R.id.item_text);
            holder.mImageView = (ImageView)convertView.findViewById(R.id.item_imageView);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.mImageView.setImageResource(mDataList.get(position).ImageID);
        holder.mTvTitle.setText(mDataList.get(position).title);

        return convertView;
    }

    private class ViewHolder{
        TextView mTvTitle;
        ImageView mImageView;
    }
    static class DataHolder{
        String title;
        int ImageID;
        DataHolder(String title, int imageID){
            this.title = title;
            this.ImageID = imageID;
        }
    }
}


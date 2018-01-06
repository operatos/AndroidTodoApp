package com.example.test2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Vector;

/**
 * Created by 쑤헤이 on 2018-01-03.
 */

public class ListViewAdapter extends BaseAdapter {

    private Vector<ScheduleData> vItems;
    private Vector<ScheduleData> vCheckedList;

    public void setItems(Vector<ScheduleData> _vItems)
    {
        vItems = _vItems;
    }
    @Override
    public int getCount() {
        if(vItems == null)
            return 0;
        return vItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        listItemView listView;

        if(convertView == null)
            listView = new listItemView(parent.getContext(),vItems.get(position));
        else
            listView = (listItemView)convertView;

        //listView.setText(mItems.get(position).getTitle());
        //listView.setText(mItems.get(position).getTitle());


        return listView;
    }
}

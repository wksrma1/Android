package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by 유지은 on 2016-11-28.
 */

public class TaskListViewAdaper extends BaseAdapter {
    ToggleButton tb;
    private ArrayList<TaskListViewItem> tasklistViewItemList =new ArrayList<TaskListViewItem>();

    public TaskListViewAdaper(){

    }
    @Override
    public int getCount() {
        return tasklistViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return tasklistViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        View v= convertView;
        final Context context = parent.getContext();

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.tasklistview_item, parent, false);
        }

        TextView taskTextView = (TextView) v.findViewById(R.id.taskView) ;

        final TaskListViewItem listViewItem = tasklistViewItemList .get(position);
        taskTextView.setText(listViewItem.getTaskName());

        tb = (ToggleButton)v.findViewById(R.id.toggleButton);

        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    Log.d("온",""+pos);
                }else{
                    Log.d("오프",""+pos);
                }
            }
        });
        return v;
    }

    public void addItem(String taskname ) {
        TaskListViewItem item = new TaskListViewItem();
        item.setTaskName(taskname);

        tasklistViewItemList.add(item);
    }


}

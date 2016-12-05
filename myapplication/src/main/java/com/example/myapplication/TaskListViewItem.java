package com.example.myapplication;

import android.widget.ToggleButton;

/**
 * Created by 유지은 on 2016-11-28.
 */

public class TaskListViewItem {
    private String taskName;
    private ToggleButton toggleButton;

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return this.taskName ;
    }

    public void setToggleButton(ToggleButton toggleButton){
        this.toggleButton = toggleButton;
    }

    public ToggleButton getToggleButton(){
        return this.toggleButton;
    }


}

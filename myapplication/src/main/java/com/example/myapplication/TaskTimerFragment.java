package com.example.myapplication;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;

public class TaskTimerFragment extends Fragment {

    TaskListViewAdaper adapter ;

    ListView list;

    Button resetBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View contentView = inflater.inflate(R.layout.fragment_tasktimer,container,false);

        list = (ListView) contentView.findViewById(R.id.tasklistview);

        final Chronometer chronometer =(Chronometer)contentView.findViewById((R.id.chronometer));
        Button resetBtn = (Button)contentView.findViewById(R.id.resetBtn);



        resetBtn.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View v){
            chronometer.setBase(SystemClock.elapsedRealtime());
        }
    });


        adapter=new TaskListViewAdaper();
        list.setAdapter(adapter);

        adapter.addItem("공부") ;
        adapter.addItem("식사") ;
        adapter.addItem("음악") ;
        adapter.addItem("컴퓨터") ;
        adapter.addItem("TV") ;

        return contentView;
    }
}

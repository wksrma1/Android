package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TaskTimerFragment extends ListFragment {

    TaskListViewAdaper adapter ;
    TextView timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Adapter 생성 TaskListViewAdaper() ;


/*        adapter=new TaskListViewAdaper();

        setListAdapter(adapter) ;

        // 첫 번째 아이템 추가.
        adapter.addItem("공부") ;
        adapter.addItem("식사") ;
        adapter.addItem("음악") ;
        adapter.addItem("컴퓨터") ;
        adapter.addItem("TV") ;
*/
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}

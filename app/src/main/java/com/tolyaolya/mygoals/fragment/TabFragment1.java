package com.tolyaolya.mygoals.fragment;

/**
 * Created by 111 on 20.06.2016.
 */
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tolyaolya.mygoals.AddGoalActivity;
import com.tolyaolya.mygoals.DbHelper;
import com.tolyaolya.mygoals.MainActivity;
import com.tolyaolya.mygoals.R;
import com.tolyaolya.mygoals.RecyclerAdapterFr1;

public class TabFragment1 extends Fragment{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FloatingActionButton mFab;
    DbHelper mDbHelper;
    SQLiteDatabase mDb;
    final String LOG_TAG = "myLogs";
    public TabFragment1() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment1, container, false);
        String[] myDataset= getDataSet();
        mDbHelper = new DbHelper(getContext());
        mDb=mDbHelper.getReadableDatabase();

        mRecyclerView = (RecyclerView) v.findViewById(R.id.list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        // создаем адаптер
        mAdapter = new RecyclerAdapterFr1(myDataset);
        mRecyclerView.setAdapter(mAdapter);
        mFab=(FloatingActionButton) v.findViewById(R.id.fab_1);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Button click in Fragment2");
                Intent intent = new Intent(getActivity().getApplicationContext(), AddGoalActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    private String[] getDataSet() {

        String[] mDataSet = new String[3];
        mDataSet[0]="WEEK";
        mDataSet[1]="MONTH";
        mDataSet[2]="YEAR";

        return mDataSet;
    }

}

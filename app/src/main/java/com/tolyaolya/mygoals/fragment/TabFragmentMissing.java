package com.tolyaolya.mygoals.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolyaolya.mygoals.DbHelper;
import com.tolyaolya.mygoals.GoalsHandler;
import com.tolyaolya.mygoals.MainActivity;
import com.tolyaolya.mygoals.R;
import com.tolyaolya.mygoals.RecyclerViewAdapter;
import com.tolyaolya.mygoals.SettingActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabFragmentMissing extends Fragment{
    @BindView(R.id.list)RecyclerView mRecyclerView;
    static RecyclerViewAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private List<GoalsHandler> itemData = new ArrayList<>();
    DbHelper mDbHelper;
    SQLiteDatabase mDb;
    int i=0;
    public TabFragmentMissing() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2, container, false);
        ButterKnife.bind(this,v);
        if (MainActivity.sCheck ==1) {
            i = MainActivity.sCheck;
        }
        mDbHelper = new DbHelper(getContext());
        mDb = mDbHelper.getReadableDatabase();
        registerForContextMenu(mRecyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapter(itemData);
        createDB();
        showDataFromDB();
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

 /* @Override
  public void onResume(){
      super.onResume();
      mAdapter.notifyDataSetChanged();
  } */

    private void createDB() {
        mDbHelper = new DbHelper (getContext(), DbHelper.DB_NAME, null, DbHelper.VERSION);

    }


    private void showDataFromDB() {
        mDbHelper.getAllItems(0);
        List<GoalsHandler> listDataHandlers = mDbHelper.getMissingItems(i);
        for (GoalsHandler it : listDataHandlers) {
            itemData.add(
                    new GoalsHandler(
                            it.getName(),
                            it.getDetails(),
                            it.getDate(),
                            it.getId())
            );
        }
    }

}


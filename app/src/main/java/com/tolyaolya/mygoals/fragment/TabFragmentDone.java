package com.tolyaolya.mygoals.fragment;

/**
 * Created by 111 on 20.06.2016.
 */
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

public class TabFragmentDone extends Fragment{
    @BindView(R.id.list)RecyclerView mRecyclerView;
    static RecyclerViewAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private List<GoalsHandler> itemData = new ArrayList<>();
    DbHelper mDbHelper;
    SQLiteDatabase mDb;
    int i=0;
    public TabFragmentDone() {
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


    private void createDB() {
        mDbHelper = new DbHelper (getContext(), DbHelper.DB_NAME, null, DbHelper.VERSION);

    }


    private void showDataFromDB() {
        mDbHelper.getAllItems(0);
        List<GoalsHandler> listDataHandlers = mDbHelper.getDoneItems(i);
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

    private void refreshList() {
        mAdapter.notifyDataSetChanged();
    }

    private void dltItem(int position){
        mAdapter.deleteItem(position);
        mAdapter.notifyItemRemoved(position);
    }
    public static void deleteAll(){
        mAdapter.clearAdapter();
        mAdapter.notifyDataSetChanged();

    }

  /*  @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = ((RecyclerViewAdapter)mRecyclerView.getAdapter()).getPosition();
        Log.d("123",Integer.toString(position));
        switch (item.getItemId()) {
            case R.id.menu_delete:
               // mDbHelper.getAllItems();
              //  Log.d("123",Integer.toString(itemData.get(position).getId()));
              //  mDb.delete(DbHelper.DB_NAME,DbHelper.colId+ "=" + itemData.get(position).getId(), null);
              //  dltItem(position);

              //  break;
        }
        return super.onContextItemSelected(item);
    } */
}

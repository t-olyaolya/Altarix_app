package com.tolyaolya.mygoals.fragment;

/**
 * Created by 111 on 20.06.2016.
 */
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tolyaolya.mygoals.AddGoalActivity;
import com.tolyaolya.mygoals.DbHelper;
import com.tolyaolya.mygoals.EditGoal;
import com.tolyaolya.mygoals.GoalsHandler;
import com.tolyaolya.mygoals.MainActivity;
import com.tolyaolya.mygoals.R;
import com.tolyaolya.mygoals.RecyclerViewAdapter;
import com.tolyaolya.mygoals.SettingActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TabFragmentAll extends Fragment{
    @BindView(R.id.list) RecyclerView mRecyclerView;
    @BindView(R.id.fab_1) FloatingActionButton mFab;
    @BindString(R.string.completed) String cmp;
    private List<GoalsHandler> itemData = new ArrayList<>();
    static RecyclerViewAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DbHelper mDbHelper;
    SQLiteDatabase mDb;
    int i;

    public TabFragmentAll() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_all, container, false);
        ButterKnife.bind(this,v);
        if (MainActivity.sCheck ==1) {
            i = MainActivity.sCheck;
           }
        mDbHelper = new DbHelper(getContext());
        mDb=mDbHelper.getReadableDatabase();
        registerForContextMenu(mRecyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapter(itemData);
        createDB();
        showDataFromDB();
        mRecyclerView.setAdapter(mAdapter);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AddGoalActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_action_goals, menu);
    }
    private void createDB() {
        mDbHelper = new DbHelper (getContext(), DbHelper.DB_NAME, null, DbHelper.VERSION);

    }

    private void showDataFromDB() {
        //mDbHelper.getAllItems();
        List<GoalsHandler> listDataHandlers = mDbHelper.getAllItems(i);
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

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = ((RecyclerViewAdapter)mRecyclerView.getAdapter()).getPosition();
        switch (item.getItemId()) {
            case R.id.menu_edit:
                int id=itemData.get(position).getId();
                Intent  intent=new Intent(getActivity(),EditGoal.class);
                intent.putExtra("i_id",Integer.toString(id));
                Log.d("123",Integer.toString(id));
                startActivity(intent);
                break;
            case R.id.menu_delete:
              //  mDbHelper.getAllItems();
                mDb.delete(DbHelper.DB_NAME,DbHelper.colId+ "=" + itemData.get(position).getId(), null);
                dltItem(position);
                break;
            case R.id.menu_completed:
                    ContentValues cv = new ContentValues();
                    cv.put(DbHelper.colFlag, 1);
                    String where = DbHelper.colId + "=" + itemData.get(position).getId();
                    mDb.update(DbHelper.DB_NAME, cv, where, null);
                    // dltItem(position);
                    Toast.makeText(getContext(),
                            cmp,
                            Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent1);


                break;
        }
        return super.onContextItemSelected(item);
    }

}

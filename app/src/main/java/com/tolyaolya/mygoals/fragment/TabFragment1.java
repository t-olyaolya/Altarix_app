package com.tolyaolya.mygoals.fragment;

/**
 * Created by 111 on 20.06.2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tolyaolya.mygoals.AddGoalActivity;
import com.tolyaolya.mygoals.MainActivity;
import com.tolyaolya.mygoals.R;

public class TabFragment1 extends Fragment{
    FloatingActionButton mFab;
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
}

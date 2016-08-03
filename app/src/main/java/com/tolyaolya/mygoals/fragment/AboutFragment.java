package com.tolyaolya.mygoals.fragment;

import android.support.v4.app.Fragment;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.BuildConfig;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tolyaolya.mygoals.R;

import butterknife.ButterKnife;

/**
 * Created by 111 on 11.06.2016.
 */
public class AboutFragment extends Fragment {
    TextView myText;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view1 = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this,view1);
        return view1;


    }


}

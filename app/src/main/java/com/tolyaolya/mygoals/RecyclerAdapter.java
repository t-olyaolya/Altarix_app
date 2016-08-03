package com.tolyaolya.mygoals;

/**
 * Created by 111 on 21.06.2016.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_recycler_item) TextView mTextView;
        @BindView(R.id.tv_recycler_item2)TextView mTextView2;
        @BindView(R.id.tv_recycler_item3)TextView mTextView3;
        @BindView(R.id.tv_recycler_item4)TextView mTextView4;
        @BindView(R.id.progr) TextView pr;


        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
        }
    }


    List<StatisticHandler> statistic;
    RecyclerAdapter(List<StatisticHandler> statistic){
        this.statistic = statistic;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistic_item, parent, false);
            ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder ViewHolder, int i) {
        ViewHolder.mTextView.setText(statistic.get(i).period);
        ViewHolder.mTextView2.setText(statistic.get(i).total);
        ViewHolder.mTextView3.setText(statistic.get(i).done);
        ViewHolder.mTextView4.setText(statistic.get(i).missing);
        ViewHolder.pr.setText(statistic.get(i).progress);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {

        return statistic.size();
    }



}
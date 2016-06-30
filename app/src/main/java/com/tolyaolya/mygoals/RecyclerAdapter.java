package com.tolyaolya.mygoals;

/**
 * Created by 111 on 21.06.2016.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private String[] mDataset;

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // наш пункт состоит только из одного TextView
        public TextView mTextView;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;


        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.tv_recycler_item);
            mTextView2=(TextView) v.findViewById(R.id.tv_recycler_item2);
            mTextView3=(TextView) v.findViewById(R.id.tv_recycler_item3);
            mTextView4=(TextView) v.findViewById(R.id.tv_recycler_item4);
        }
    }

    // Конструктор
    public RecyclerAdapter(String[] dataset) {
        mDataset = dataset;
    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
       // View v = LayoutInflater.from(parent.getContext())
        //        .inflate(R.layout.statistic_item, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistic_item, parent, false);



        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextView.setText(mDataset[position]);

    }

    // Возвращает размер данных (вызывается layout manager-ом)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
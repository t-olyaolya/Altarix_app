package com.tolyaolya.mygoals;

/**
 * Created by 111 on 21.06.2016.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_fr) TextView mTextView;
        @BindView(R.id.detail_fr)TextView mTextView2;
        @BindView(R.id.date_fr)TextView mTextView3;
        @BindView(R.id.card_view) CardView cv;


       ItemViewHolder(View v) {
           super(v);
           ButterKnife.bind(this,v);
       }
   }

    private List<GoalsHandler> itemData;

    private int position;

    public int getPosition() {
         return position;
        }

    public void setPosition(int position) {
         this.position = position;
        }

    public RecyclerViewAdapter(List<GoalsHandler> id) {
        this.itemData = id;

    }

    public void addItem(GoalsHandler list) {
        itemData.add(
                new GoalsHandler(
                        list.getName(),
                        list.getDetails(),
                        list.getDate(),
                        list.getId()
                )
        );
    }


    public void deleteItem(int position){
        this.itemData.remove(position);
        //super.notifyItemRemoved(position);
    }
    public void clearAdapter() {
        this.itemData = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_list, parent, false);
        return new ItemViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.mTextView.setText(itemData.get(position).getName());
        holder.mTextView2.setText(itemData.get(position).getDetails());
        holder.mTextView3.setText(String.valueOf(itemData.get(position).getDate()));
        int i=itemData.get(position).getFlag();
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getAdapterPosition());
                return false;
            }
        });
    }

    @Override
    public  void onViewRecycled ( ItemViewHolder holder )  {
        holder . itemView . setOnLongClickListener ( null );
        super . onViewRecycled ( holder );
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public int getSizeAdapterList() {
        return itemData.size();
    }


}

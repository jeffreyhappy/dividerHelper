package com.fei.divider;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Li on 2017/2/27.
 */

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.TextViewHolder> {

    ArrayList<String> data;
    public TextAdapter(){

    }

    public TextAdapter(ArrayList<String> data){
        this.data = data;
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TextViewHolder(new TextView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        TextView tv = (TextView) holder.itemView;

        String val = data == null? "TextView " + position : data.get(position);
        tv.setText(val);
    }


    @Override
    public int getItemCount() {
        return data == null ? 30 : data.size();
    }




    public static class TextViewHolder extends RecyclerView.ViewHolder{

        public TextViewHolder(View itemView) {
            super(itemView);
        }
    }

}

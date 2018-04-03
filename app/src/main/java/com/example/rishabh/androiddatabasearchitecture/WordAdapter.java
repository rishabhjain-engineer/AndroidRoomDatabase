package com.example.rishabh.androiddatabasearchitecture;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    List<Word> wordList ;
    Context context;

    public WordAdapter(Context context){
        this.context = context ;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        WordViewHolder holder = new WordViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {

        holder.textView.setText(wordList.get(position).getWord());
    }

    @Override
    public int getItemCount() {
        if(wordList != null)
            return wordList.size();
        else
            return 0;
    }

    public class WordViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public WordViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.single_word);
        }
    }

    public void setData(List<Word> list){
        wordList = list ;
        notifyDataSetChanged();
    }

}

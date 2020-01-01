package com.mahmoudmabrok.catechwords.features.displayWords;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahmoudmabrok.catechwords.R;
import com.mahmoudmabrok.catechwords.model.Word;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WordAdapter extends RecyclerView.Adapter<WordAdapter.Holder> {

    private static final String TAG = "TafseerAdapter";
    private List<Word> list;
    private List<Integer> selected = new ArrayList<>();

    public WordAdapter() {
        list = new ArrayList<>();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public List<Word> getList() {
        return list;
    }

    public void setList(List<Word> newList) {
        list = new ArrayList<>(newList);
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.word_item, viewGroup, false);
        Holder holder = new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Word item = list.get(i);
        holder.tvWord.setText(item.getWord());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvWord)
        TextView tvWord;

        Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
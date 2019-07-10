package com.example.ui.todos.domains.word;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.todos.R;
import com.example.ui.todos.db.model.Word;
import com.example.ui.todos.ultil.AudioPlayer;

import java.util.List;

import static com.example.ui.todos.ultil.ShareKey.TODO_ID;

public class WordListAdapter extends RecyclerView.Adapter<WordViewHolder> {

    private Context context;
    private List<Word> wordList;

    public WordListAdapter(Context context, List<Word> toDoList) {
        this.context = context;
        this.wordList = toDoList;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View layout = inflater.inflate(R.layout.item_word, parent, false);

        return new WordViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        try {
            holder.word.setText(String.format("%s: %s", wordList.get(position).getWord().trim(),
                    wordList.get(position).getMean()));
            holder.position.setText((position+1)+"");
            holder.pronoun.setText(wordList.get(position).getType()+" "+wordList.get(position).getPro());
            holder.voice.setOnClickListener(v->{
                if (wordList.get(position).getVoice() != null && wordList.get(position).getVoice() != ""){

                    AudioPlayer.play(context,wordList.get(position).getVoice());
                } else {
                    int id = context.getResources().getIdentifier(wordList.get(position).getWord().toLowerCase().replace('-','_').trim(),
                            "raw", context.getPackageName());
                    AudioPlayer.play(context,id);
                }

            });
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return wordList.size() > 0 ? wordList.size() : 1;
    }

    public void removeItem(int adapterPosition) {
        wordList.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }


}

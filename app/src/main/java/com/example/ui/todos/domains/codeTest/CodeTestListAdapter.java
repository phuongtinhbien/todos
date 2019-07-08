package com.example.ui.todos.domains.codeTest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.todos.R;
import com.example.ui.todos.db.model.CodeTest;
import com.example.ui.todos.db.model.Word;
import com.example.ui.todos.domains.test.TestActivity;
import com.example.ui.todos.domains.test.TestActivity_;
import com.example.ui.todos.ultil.AudioPlayer;

import java.util.List;

public class CodeTestListAdapter extends RecyclerView.Adapter<CodeTestViewHolder> {

    private Context context;
    private List<CodeTest> wordList;

    public CodeTestListAdapter(Context context, List<CodeTest> toDoList) {
        this.context = context;
        this.wordList = toDoList;
    }

    @NonNull
    @Override
    public CodeTestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View layout = inflater.inflate(R.layout.item_code, parent, false);

        return new CodeTestViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull CodeTestViewHolder holder, int position) {
       holder.tvName.setText(wordList.get(position).getName());
       holder.itemView.setOnClickListener(v->{
           Intent intent = new Intent(context, TestActivity_.class);
           intent.putExtra("codeTest",wordList.get(position).getCode());
           intent.putExtra("codeName",wordList.get(position).getName());
           context.startActivity(intent);
       });
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

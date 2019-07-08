package com.example.ui.todos.domains.word;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.todos.R;
import com.github.irshulx.Editor;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

public class WordViewHolder extends RecyclerView.ViewHolder {

    protected TextView position;
    protected TextView word;
    protected TextView pronoun;
    protected ImageView voice;

    public WordViewHolder(@NonNull View layout) {
        super(layout);
            position = layout.findViewById(R.id.tvWordPosition);
            word = layout.findViewById(R.id.tvWord);
            pronoun = layout.findViewById(R.id.tvWordPronoun);
            voice = layout.findViewById(R.id.civVoice);


    }
}

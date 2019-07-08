package com.example.ui.todos.domains.codeTest;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.todos.R;

public class CodeTestViewHolder extends RecyclerView.ViewHolder {

    protected TextView tvName;

    public CodeTestViewHolder(@NonNull View layout) {
        super(layout);
           tvName = layout.findViewById(R.id.tvCodeName);


    }
}

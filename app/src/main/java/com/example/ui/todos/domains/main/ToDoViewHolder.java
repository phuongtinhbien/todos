package com.example.ui.todos.domains.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ui.todos.R;
import com.github.irshulx.Editor;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToDoViewHolder extends RecyclerView.ViewHolder {

    protected TextView title, date;
    protected Editor desc;
    protected ImageView line, tag, done;
    protected LinearLayout viewForeground;

    public ToDoViewHolder(@NonNull View layout, boolean type) {
        super(layout);
        if (type) {
            title = layout.findViewById(R.id.item_todo_tv_title);
            line = layout.findViewById(R.id.item_todo_progress);
            date = layout.findViewById(R.id.item_todo_tv_date);
            desc = layout.findViewById(R.id.item_todo_tv_desc);
            tag = layout.findViewById(R.id.item_todo_iv_tag);
            done = layout.findViewById(R.id.item_todo_iv_done);
            viewForeground = layout.findViewById(R.id.item_to_ll_foreground);
        }

    }
}

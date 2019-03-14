package com.example.ui.todos.domains.tags;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ui.todos.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TagsViewHolder extends RecyclerView.ViewHolder {

    protected ImageView tagIcon;
    protected TextView tagName;
    protected LinearLayout viewBackground, viewForeground;
    public TagsViewHolder(@NonNull View itemView) {
        super(itemView);
        tagIcon = itemView.findViewById(R.id.item_tag_iv_tag);
        tagName = itemView.findViewById(R.id.item_tag_tv_title);
        viewBackground = itemView.findViewById(R.id.item_tags_ll_background);
        viewForeground = itemView.findViewById(R.id.item_tags_ll_foreground);
    }
}

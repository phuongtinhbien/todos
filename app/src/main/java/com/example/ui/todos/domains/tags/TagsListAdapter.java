package com.example.ui.todos.domains.tags;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ui.todos.db.model.Tags;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TagsListAdapter extends RecyclerView.Adapter<TagsViewHolder> {

    private Context context;
    private List<Tags> tagsList;

    public TagsListAdapter(Context context, List<Tags> tagsList) {
        this.context = context;
        this.tagsList = tagsList;
    }

    @NonNull
    @Override
    public TagsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(com.example.ui.todos.R.layout.item_tags, parent, false);
        return new TagsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TagsViewHolder holder, int position) {
        holder.tagIcon.setImageResource(tagsList.get(position).getIcon());
        holder.tagName.setText(tagsList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return tagsList.size();
    }

    public List<Tags> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<Tags> tagsList) {
        this.tagsList = tagsList;
        notifyDataSetChanged();
    }

    public void removeItem(int adapterPosition) {
        tagsList.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }
}

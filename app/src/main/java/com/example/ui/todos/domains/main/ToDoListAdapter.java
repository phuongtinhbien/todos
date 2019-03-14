package com.example.ui.todos.domains.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ui.todos.R;
import com.example.ui.todos.db.model.ToDo;
import com.example.ui.todos.domains.createTask.CreateTaskActivity_;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoViewHolder> {

    private Context context;
    private List<ToDo> toDoList;

    public ToDoListAdapter(Context context, List<ToDo> toDoList) {
        this.context = context;
        this.toDoList = toDoList;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View layout;
        if (toDoList == null || toDoList.size() == 0) {
            layout = inflater.inflate(R.layout.null_todo, parent, false);

        } else {
            layout = inflater.inflate(R.layout.item_todo, parent, false);
        }
        return new ToDoViewHolder(layout, true);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        if (toDoList.size()>0){
            holder.title.setText(toDoList.get(position).getTitle());
            holder.line.setImageLevel(20);
            holder.date.setText(DateUtils.getRelativeTimeSpanString(toDoList.get(position).getCreateDate()));
            holder.desc.setClickable(false);
            holder.desc.setEditorListener(null);
            holder.desc.setFocusable(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.desc.render(toDoList.get(position).getDesc());
            }
            holder.tag.setImageResource(toDoList.get(position).getTag().getIcon());
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, CreateTaskActivity_.class);
                intent.putExtra("TODO_ID", toDoList.get(position).getId());
                this.context.startActivity(intent);
            });
        }
        else{
            holder.itemView.setOnClickListener(v -> this.context.startActivity(new Intent(context, CreateTaskActivity_.class)));
        }

    }

    @Override
    public int getItemCount() {
        return toDoList.size() > 0 ?toDoList.size(): 1;
    }

    public void removeItem(int adapterPosition) {
        toDoList.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }
}

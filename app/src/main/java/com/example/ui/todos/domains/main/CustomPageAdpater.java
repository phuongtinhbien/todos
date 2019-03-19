package com.example.ui.todos.domains.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ui.todos.R;
import com.example.ui.todos.db.model.ToDo;
import com.example.ui.todos.domains.createTask.CreateTaskActivity_;
import com.github.irshulx.Editor;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class CustomPageAdpater extends PagerAdapter {

    private List<ToDo> toDoList;
    private Context context;

    private TextView title, date;
    private Editor desc;
    private ImageView line, tag;

    public CustomPageAdpater(List<ToDo> toDoList, Context context) {
        this.toDoList = toDoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return toDoList.size() == 0 ? 1 : toDoList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        ViewGroup layout;
        if (toDoList == null || toDoList.size() == 0) {
            layout = (ViewGroup) inflater.inflate(R.layout.null_todo, container, false);
            layout.setOnClickListener(v -> this.context.startActivity(new Intent(context, CreateTaskActivity_.class)));
        } else {
            layout = (ViewGroup) inflater.inflate(R.layout.item_todo, container, false);
            layout.setOnClickListener(v -> {
                Intent intent = new Intent(context, CreateTaskActivity_.class);
                intent.putExtra("TODO_ID", toDoList.get(position).getId());
                this.context.startActivity(intent);
            });
            init(layout, position);
        }
        container.addView(layout);
        return layout;
    }

    private void init(ViewGroup layout, int position) {
        title = layout.findViewById(R.id.item_todo_tv_title);
        title.setText(toDoList.get(position).getTitle());

        line = layout.findViewById(R.id.item_todo_progress);
        line.setImageLevel(20);

        date = layout.findViewById(R.id.item_todo_tv_date);
        date.setText(DateUtils.getRelativeTimeSpanString(toDoList.get(position).getCreateDate()));

        desc = layout.findViewById(R.id.item_todo_tv_desc);
        desc.setClickable(false);
        desc.setEditorListener(null);
        desc.setFocusable(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            desc.render(toDoList.get(position).getDesc());
        }

        tag = layout.findViewById(R.id.item_todo_iv_tag);

        tag.setImageResource(toDoList.get(position).getTag().getIcon());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}

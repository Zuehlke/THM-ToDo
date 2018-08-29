package zuehlke.de.thm_demoapp.view;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zuehlke.de.thm_demoapp.R;
import zuehlke.de.thm_demoapp.model.Todo;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Todo item);
    }

    private List<Todo> todos;
    private OnItemClickListener clickListener;

    public TodoListAdapter(List<Todo> todos, OnItemClickListener clickListener) {
        this.todos = todos;
        this.clickListener = clickListener;
    }

    public TodoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(todos.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rowView;
        public TextView todoTitle;
        public TextView todoDone;

        public ViewHolder(ConstraintLayout v) {
            super(v);
            rowView = v;
            todoTitle = v.findViewById(R.id.txt_todo_title);
            todoDone = v.findViewById(R.id.txt_todo_done);
        }

        public void bind(final Todo todo, final OnItemClickListener clickListener){
            todoTitle.setText(todo.getTitle());
            todoDone.setText(todo.isDone() ? "✔︎" : "");
            rowView.setOnClickListener(v -> clickListener.onItemClick(todo));
        }
    }
}

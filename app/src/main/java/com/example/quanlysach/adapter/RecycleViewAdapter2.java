package com.example.quanlysach.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysach.R;
import com.example.quanlysach.model.Book;
import com.example.quanlysach.model.BookSold;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter2 extends RecyclerView.Adapter<RecycleViewAdapter2.HistoryViewHolder>{
    private List<BookSold> list;
    private ItemListener itemListener;
    public RecycleViewAdapter2() {
        list = new ArrayList<>();
    }
    public void setItemListener2(ItemListener itemListener) {
        this.itemListener = itemListener;
    }
    public void setList(List<BookSold> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public BookSold getItem(int position){
        return list.get(position);
    }
    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2,parent,false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        BookSold book = list.get(position);
        holder.title.setText(book.getTitle());
        holder.price.setText(book.getPrice());
        holder.author.setText(book.getAuthor());
        holder.date.setText(book.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title, price, author, date;
        public HistoryViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title);
            price = view.findViewById(R.id.tvGia);
            author = view.findViewById(R.id.tvauthor);
            date = view.findViewById(R.id.tvdate);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if(itemListener != null){
                itemListener.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public interface ItemListener{
        void onItemClick(View view, int position);
    }
}

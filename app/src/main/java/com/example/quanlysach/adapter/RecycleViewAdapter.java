package com.example.quanlysach.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysach.R;
import com.example.quanlysach.model.Book;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder>{
    private List<Book> list;
    private ItemListener itemListener;
    public RecycleViewAdapter() {
        list = new ArrayList<>();
    }
    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }
    public void setList(List<Book> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Book getItem(int position){
        return list.get(position);
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Book book = list.get(position);
        holder.title.setText(book.getTitle());
        holder.category.setText(book.getCategory());
        holder.price.setText(book.getPrice());
        holder.author.setText(book.getAuthor());
        holder.nxb.setText(book.getNxb());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title, category, price, author, nxb;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title);
            category = view.findViewById(R.id.tvCategory);
            price = view.findViewById(R.id.tvGia);
            author = view.findViewById(R.id.tvauthor);
            nxb = view.findViewById(R.id.tvnxb);
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

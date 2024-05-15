package com.example.quanlysach.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysach.AddActivity;
import com.example.quanlysach.R;
import com.example.quanlysach.Update_Delete_Activity;
import com.example.quanlysach.adapter.RecycleViewAdapter;
import com.example.quanlysach.dal.SQLiteHelper;
import com.example.quanlysach.model.Book;
import com.example.quanlysach.model.BookSold;

import java.util.List;

public class FragmentHome extends Fragment implements RecycleViewAdapter.ItemListener {
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        searchView = view.findViewById(R.id.search);
        adapter = new RecycleViewAdapter();
        db = new SQLiteHelper(getContext());
        List<Book> list  = db.getAllBook();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Book> list = db.searchByTitle2(s);
                adapter.setList(list);
                return true;
            }
        });

    }

    @Override
    public void onItemClick(View view, int position) {
        Book book = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), Update_Delete_Activity.class);
        intent.putExtra("book",book);
        startActivity(intent);
    }

    public void onResume() {
        super.onResume();
        List<Book> list = db.getAllBook();
        adapter.setList(list);
    }
}

package com.example.quanlysach.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysach.R;
import com.example.quanlysach.UD_History_Activity;
import com.example.quanlysach.Update_Delete_Activity;
import com.example.quanlysach.adapter.RecycleViewAdapter;
import com.example.quanlysach.adapter.RecycleViewAdapter2;
import com.example.quanlysach.dal.SQLiteHelper;
import com.example.quanlysach.model.Book;
import com.example.quanlysach.model.BookSold;

import java.util.List;

public class FragmentHistory extends Fragment implements RecycleViewAdapter2.ItemListener {
    private RecycleViewAdapter2 adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        adapter = new RecycleViewAdapter2();
        db = new SQLiteHelper(getContext());
        List<BookSold> list  = db.getBookSold();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener2(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        BookSold book = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), UD_History_Activity.class);
        intent.putExtra("booksold",book);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<BookSold> list = db.getBookSold();
        adapter.setList(list);
    }
}

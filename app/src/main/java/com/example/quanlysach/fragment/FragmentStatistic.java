package com.example.quanlysach.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysach.R;
import com.example.quanlysach.adapter.RecycleViewAdapter;
import com.example.quanlysach.adapter.RecycleViewAdapter2;
import com.example.quanlysach.dal.SQLiteHelper;
import com.example.quanlysach.model.Book;
import com.example.quanlysach.model.BookSold;

import java.util.Calendar;
import java.util.List;

public class FragmentStatistic extends Fragment implements View.OnClickListener{
    private RecyclerView recyclerView;
    private TextView tvTong;
    private Button btsearch;
    private SearchView searchView;
    private EditText efrom, eto;
    private Spinner sp;
    private RecycleViewAdapter recycleViewAdapter;
    private RecycleViewAdapter2 recycleViewAdapter2;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistic,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        recycleViewAdapter = new RecycleViewAdapter();
        recycleViewAdapter2 = new RecycleViewAdapter2();
        db = new SQLiteHelper(getContext());
        List<BookSold> list2 = db.getBookSold();
        List<Book> list = db.getAllBook();
        recycleViewAdapter.setList(list);
        recycleViewAdapter2.setList(list2);
        tvTong.setText("Tong tien:"+tong(list2)+"VND");
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recycleViewAdapter2);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                recyclerView.setAdapter(recycleViewAdapter2);
                List<BookSold> list = db.searchByTitle(s);
                tvTong.setText("Tong tien:"+tong(list)+"VND");
                recycleViewAdapter2.setList(list);
                return true;
            }
        });

        efrom.setOnClickListener(this);
        eto.setOnClickListener(this);
        btsearch.setOnClickListener(this);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                recyclerView.setAdapter(recycleViewAdapter);
                String cate = sp.getItemAtPosition(i).toString();
                List<Book> listbook;
                if (cate.equalsIgnoreCase("all")){
                    listbook = db.getAllBook();
                }else{
                    listbook=db.searchByCategory(cate);
                }
                recycleViewAdapter.setList(listbook);
                tvTong.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private int tong(List<BookSold> list){
        int t=0;
        for (BookSold i:list){
            t+=Integer.parseInt(i.getPrice());
        }
        return t;
    }
    private void initView(View view){
        recyclerView = view.findViewById(R.id.recycleView);
        tvTong = view.findViewById(R.id.tvTong);
        btsearch =  view.findViewById(R.id.btSearch);
        searchView = view.findViewById(R.id.search);
        efrom = view.findViewById(R.id.eFrom);
        eto = view.findViewById(R.id.eTo);
        sp = view.findViewById(R.id.spCategory);
        String[]arr=getResources().getStringArray(R.array.category);
        String[]arr1=new String[arr.length+1];
        arr1[0]="All";
        for (int i = 0; i < arr.length; i++) {
            arr1[i+1]=arr[i];
        }
        sp.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.item_spinner,arr1));
    }
    @Override
    public void onClick(View v) {
//        recyclerView.setAdapter(recycleViewAdapter2);
        if(v==efrom){
            final Calendar c=Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog =new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year1, int month1, int dayOfMonth) {
                    String date="";
                    if(month1>8){
                        date=dayOfMonth+"/"+(month1+1)+"/"+year1;
                    }
                    else {
                        date=dayOfMonth+"/0"+(month1+1)+"/"+year1;
                    }
                    efrom.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if(v==eto){
            final Calendar c=Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog =new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year1, int month1, int dayOfMonth) {
                    String date="";
                    if(month1>8){
                        date=dayOfMonth+"/"+(month1+1)+"/"+year1;
                    }
                    else {
                        date=dayOfMonth+"/0"+(month1+1)+"/"+year1;
                    }
                    eto.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if(v==btsearch){;
            String from=efrom.getText().toString();
            String to=eto.getText().toString();
            if(!from.isEmpty()&&!to.isEmpty()){
                recyclerView.setAdapter(recycleViewAdapter2);
                List<BookSold>list=db.searchByDate(from,to);
                tvTong.setText("Tong tien: "+tong(list)+"K");
                recycleViewAdapter2.setList(list);
            }
        }
    }
}

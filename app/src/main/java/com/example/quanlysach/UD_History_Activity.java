package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlysach.dal.SQLiteHelper;
import com.example.quanlysach.model.Book;
import com.example.quanlysach.model.BookSold;

import java.util.Calendar;

public class UD_History_Activity extends AppCompatActivity implements View.OnClickListener{
    private EditText etitle, eprice, eauthor, edate;
    private Button btupdate, btback, btremove;
    private BookSold book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ud_history);
        initView();
        btupdate.setOnClickListener(this);
        btback.setOnClickListener(this);
        btremove.setOnClickListener(this);
        Intent intent=getIntent();
        book=(BookSold) intent.getSerializableExtra("booksold");
        etitle.setText(book.getTitle());
        eprice.setText(book.getPrice());
        eauthor.setText(book.getAuthor());
        edate.setText(book.getDate());
    }
    private void initView(){
        etitle=findViewById(R.id.tvTitle);
        eprice=findViewById(R.id.tvPrice);
        eauthor=findViewById(R.id.tvauthor);
        edate=findViewById(R.id.tvdate);
        btupdate=findViewById(R.id.btUpdate);
        btback=findViewById(R.id.btBack);
        btremove=findViewById(R.id.btDelete);
    }
    @Override
    public void onClick(View v) {
        SQLiteHelper db=new SQLiteHelper(this);
        if(v==edate){
            final Calendar c=Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UD_History_Activity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year1, int month1, int dayOfMonth) {
                    String date="";
                    if(month1>8){
                        date=dayOfMonth+"/"+(month1+1)+"/"+year1;
                    }
                    else {
                        date=dayOfMonth+"/0"+(month1+1)+"/"+year1;
                    }
                    edate.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if(v==btback){
            finish();
        }
        if(v==btupdate){
            String t=etitle.getText().toString();
            String p=eprice.getText().toString();
            String a=eauthor.getText().toString();
            String d=edate.getText().toString();
            if(!t.isEmpty()&& !p.isEmpty() && !a.isEmpty() && !d.isEmpty() &&p.matches("\\d+")){
                int id=book.getId();
                BookSold i=new BookSold(id,t,p,d,a);
                db= new SQLiteHelper(this);
                db.updateBookSold(i);
                finish();
            }else{
                Toast.makeText(UD_History_Activity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==btremove){
            int id=book.getId();
            AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban co chac muon xoa :'"+book.getTitle()+"'khong? ");
            builder.setIcon(R.drawable.delete);
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SQLiteHelper bb= new SQLiteHelper(getApplicationContext());
                    bb.deleteBookSold(id);
                    finish();
                }
            });
            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();
        }

    }
}
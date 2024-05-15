package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

public class Add_History_Activity extends AppCompatActivity implements View.OnClickListener{
    private EditText etitle, eprice, eauthor, edate;
    private Button btadd, btcancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_history);
        initView();
        btadd.setOnClickListener(this);
        btcancel.setOnClickListener(this);
    }

    private void initView(){
        etitle=findViewById(R.id.tvTitle);
        eprice=findViewById(R.id.tvPrice);
        eauthor=findViewById(R.id.tvauthor2);
        edate=findViewById(R.id.tvDate);
        btadd=findViewById(R.id.btUpdate);
        btcancel=findViewById(R.id.btCancel);

    }

    @Override
    public void onClick(View v) {
        if(v==edate){
            final Calendar c=Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(Add_History_Activity.this, new DatePickerDialog.OnDateSetListener() {
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
        if(v==btcancel){
            finish();
        }
        if(v==btadd){
            String t=etitle.getText().toString();
            String a=eauthor.getText().toString();
            String p=eprice.getText().toString();
            String d=edate.getText().toString();
            if(!t.isEmpty() && !a.isEmpty() && !p.isEmpty() && !d.isEmpty() &&p.matches("\\d+")){
                BookSold i = new BookSold(t,p,d,a);
                SQLiteHelper db=new SQLiteHelper(this);
                db.addBookSold(i);
                finish();
            }else{
                Toast.makeText(Add_History_Activity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
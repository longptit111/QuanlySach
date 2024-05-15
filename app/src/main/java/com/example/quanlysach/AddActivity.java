package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlysach.dal.SQLiteHelper;
import com.example.quanlysach.model.Book;

import kotlin.experimental.BitwiseOperationsKt;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    public Spinner sp;
    private EditText etitle, eprice, eauthor, enxb;
    private Button btadd, btcancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btadd.setOnClickListener(this);
        btcancel.setOnClickListener(this);
    }
    private void initView(){
        sp=findViewById(R.id.spCategory);
        etitle=findViewById(R.id.tvTitle);
        eprice=findViewById(R.id.tvPrice);
        eauthor=findViewById(R.id.tvTacgia);
        enxb=findViewById(R.id.tvNhaxuatban);
        btadd=findViewById(R.id.btUpdate);
        btcancel=findViewById(R.id.btCancel);
        sp.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View v) {
        if(v==btcancel){
            finish();
        }
        if(v==btadd){
            String t=etitle.getText().toString();
            String p=eprice.getText().toString();
            String a=eauthor.getText().toString();
            String n=enxb.getText().toString();
            String c=sp.getSelectedItem().toString();
            if(!t.isEmpty()&& !p.isEmpty() && !a.isEmpty() && !n.isEmpty() && !c.isEmpty() && p.matches("\\d+")){
                Book i=new Book(t,p,c,n,a);
                SQLiteHelper db=new SQLiteHelper(this);
                db.addBook(i);
                finish();
            }
            else{
                Toast.makeText(AddActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
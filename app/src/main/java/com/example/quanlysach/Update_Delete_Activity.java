package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlysach.dal.SQLiteHelper;
import com.example.quanlysach.model.Book;

public class Update_Delete_Activity extends AppCompatActivity implements View.OnClickListener{
    public Spinner sp;
    private EditText etitle, eprice, eauthor, enxb;
    private Button btupdate, btback, btremove;
    private Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btupdate.setOnClickListener(this);
        btback.setOnClickListener(this);
        btremove.setOnClickListener(this);
        Intent intent=getIntent();
        book=(Book) intent.getSerializableExtra("book");
        etitle.setText(book.getTitle());
        eprice.setText(book.getPrice());
        eauthor.setText(book.getAuthor());
        enxb.setText(book.getNxb());
        int p=0;
        for (int i = 0; i < sp.getCount(); i++) {
            if(sp.getItemAtPosition(i).toString().equalsIgnoreCase(book.getCategory())){
                p=i;
                break;
            }
        }
        sp.setSelection(p);
    }
    private void initView(){
        sp=findViewById(R.id.spCategory);
        etitle=findViewById(R.id.tvTitle);
        eprice=findViewById(R.id.tvPrice);
        eauthor=findViewById(R.id.tvTacgia);
        enxb=findViewById(R.id.tvNhaxuatban);
        btupdate=findViewById(R.id.btUpdate);
        btback=findViewById(R.id.btBack);
        btremove=findViewById(R.id.btDelete);
        sp.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View v) {
        SQLiteHelper db=new SQLiteHelper(this);
        if(v==btback){
            finish();
        }
        if(v==btupdate){
            String t=etitle.getText().toString();
            String p=eprice.getText().toString();
            String c=sp.getSelectedItem().toString();
            String a=eauthor.getText().toString();
            String n=enxb.getText().toString();
            if(!t.isEmpty()&& !p.isEmpty() && !c.isEmpty() && !a.isEmpty() && !n.isEmpty() && p.matches("\\d+")){
                int id=book.getId();
                Book i=new Book(id,t,p,c,n,a);
                db= new SQLiteHelper(this);
                db.updateBook(i);
                finish();
            }else {
                Toast.makeText(Update_Delete_Activity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
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
                    bb.deleteBook(id);
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
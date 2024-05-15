package com.example.quanlysach.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.quanlysach.model.Book;
import com.example.quanlysach.model.BookSold;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Quanlysach.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE books("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "title TEXT,category TEXT,price TEXT,author TEXT," +
                "nxb TEXT)";
        String sql2="CREATE TABLE booksold("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "title TEXT,price TEXT,date TEXT,author TEXT)";
        db.execSQL(sql);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public List<Book> getAllBook(){
        List<Book>list=new ArrayList<>();
        SQLiteDatabase st=getReadableDatabase();
        String order="title DESC";
        Cursor rs=st.query("books",null,null
                ,null,null,null,order);
        while (rs!=null&& rs.moveToNext()){
            int id=rs.getInt(0);
            String title=rs.getString(1);
            String category=rs.getString(2);
            String price=rs.getString(3);
            String author=rs.getString(4);
            String nxb=rs.getString(5);
            list.add(new Book(id,title,price,category,nxb,author));
        }
        return list;
    }
    public List<BookSold> getBookSold(){
        List<BookSold> list=new ArrayList<>();
        SQLiteDatabase st=getReadableDatabase();
        String order="date DESC";
        Cursor rs=st.query("booksold",null,null
                ,null,null,null,order);
        while (rs!=null&& rs.moveToNext()){
            int id=rs.getInt(0);
            String title=rs.getString(1);
            String price=rs.getString(2);
            String date=rs.getString(3);
            String author=rs.getString(4);
            list.add(new BookSold(id,title,price,date,author));
        }
        return list;
    }

    public long addBook(Book book){
        ContentValues values=new ContentValues();
        values.put("title",book.getTitle());
        values.put("category",book.getCategory());
        values.put("price",book.getPrice());
        values.put("author",book.getAuthor());
        values.put("nxb",book.getNxb());
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        return sqLiteDatabase.insert("books",null,values);
    }

    public long addBookSold(BookSold book){
        ContentValues values=new ContentValues();
        values.put("title",book.getTitle());
        values.put("price",book.getPrice());
        values.put("author",book.getAuthor());
        values.put("date",book.getDate());
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        return sqLiteDatabase.insert("booksold",null,values);
    }

    //update
    public int updateBook(Book book){
        ContentValues values=new ContentValues();
        values.put("title",book.getTitle());
        values.put("category",book.getCategory());
        values.put("price",book.getPrice());
        values.put("author",book.getAuthor());
        values.put("nxb",book.getNxb());
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        String whereClause="id= ?";
        String[] whereArgs={Integer.toString(book.getId())};
        return sqLiteDatabase.update("books",values,whereClause,whereArgs);
    }
    public int updateBookSold(BookSold book){
        ContentValues values=new ContentValues();
        values.put("title",book.getTitle());
        values.put("price",book.getPrice());
        values.put("author",book.getAuthor());
        values.put("date",book.getDate());
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        String whereClause="id= ?";
        String[] whereArgs={Integer.toString(book.getId())};
        return sqLiteDatabase.update("booksold",values,whereClause,whereArgs);
    }
    //delete
    public int deleteBook(int id){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        String whereClause="id= ?";
        String[] whereArgs={Integer.toString(id)};
        return sqLiteDatabase.delete("books",whereClause,whereArgs);
    }
    public int deleteBookSold(int id){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        String whereClause="id= ?";
        String[] whereArgs={Integer.toString(id)};
        return sqLiteDatabase.delete("booksold",whereClause,whereArgs);
    }
    public List<BookSold> searchByTitle(String key){
        List<BookSold>list=new ArrayList<>();
        String whereClause="title like ?";
        String[] whereArgs={"%"+key+"%"};
        SQLiteDatabase st= getReadableDatabase();
        Cursor rs=st.query("booksold",null,whereClause,whereArgs,null,null,null);
        while (rs!=null&& rs.moveToNext()){
            int id=rs.getInt(0);
            String title=rs.getString(1);
            String price=rs.getString(2);
            String date=rs.getString(3);
            String author=rs.getString(4);
            list.add(new BookSold(id,title,price,date,author));
        }
        return list;
    }
    public List<Book> searchByTitle2(String key){
        List<Book>list=new ArrayList<>();
        String whereClause="title like ?";
        String[] whereArgs={"%"+key+"%"};
        SQLiteDatabase st= getReadableDatabase();
        Cursor rs=st.query("books",null,whereClause,whereArgs,null,null,null);
        while (rs!=null&& rs.moveToNext()){
            int id=rs.getInt(0);
            String title=rs.getString(1);
            String category=rs.getString(2);
            String price=rs.getString(3);
            String author=rs.getString(4);
            String nxb=rs.getString(5);
            list.add(new Book(id,title,price,category,nxb,author));
        }
        return list;
    }
    public List<Book> searchByCategory(String c){
        List<Book>list=new ArrayList<>();
        String whereClause="category like ?";
        String[] whereArgs={c};
        SQLiteDatabase st= getReadableDatabase();
        Cursor rs=st.query("books",null,whereClause,whereArgs,null,null,null);
        while (rs!=null&& rs.moveToNext()){
            int id=rs.getInt(0);
            String title=rs.getString(1);
            String category=rs.getString(2);
            String price=rs.getString(3);
            String author=rs.getString(4);
            String nxb=rs.getString(5);
            list.add(new Book(id,title,price,category,nxb,author));
        }
        return list;
    }
    public List<BookSold> searchByDate(String from,String to){
        List<BookSold>list=new ArrayList<>();
        String whereClause="date BETWEEN ? AND ? ";
        String[] whereArgs={from.trim(),to.trim()};
        SQLiteDatabase st= getReadableDatabase();
        Cursor rs=st.query("booksold",null,whereClause,whereArgs,null,null,null);
        while (rs!=null&& rs.moveToNext()){
            int id=rs.getInt(0);
            String title=rs.getString(1);
            String price=rs.getString(2);
            String date=rs.getString(3);
            String author=rs.getString(4);
            list.add(new BookSold(id,title,price,date,author));
        }
        return list;
    }
}

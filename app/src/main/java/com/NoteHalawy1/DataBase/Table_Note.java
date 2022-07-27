package com.NoteHalawy1.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Table_Note extends SQLiteOpenHelper {
    //Data Type
    private static final String Name_DataBase="DataBase_NOTE3.db";
    private static final String TAPLE_NOTE="NOTES";
    private static final String TAPLE_TASKS="TASKS";
    private static  int id;
    Context context;
    private ArrayList<Adapter_Table_Note> adapter_table_note=new ArrayList<>();
    private ArrayList<Adapter_Table_Tasks> adapter_table_tasks=new ArrayList<>();

    // <...end DataType...> //

    //Constructor
    public Table_Note(@Nullable Context context) {
        super(context, Name_DataBase, null, 1);
        this.context=context;
    }//end constructor Table_Note

    //begin function to database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //SQL Table Note --> nid , title , describe , date , lable , image , color
        sqLiteDatabase.execSQL("create table "+TAPLE_NOTE+" ( "+
                "nid INTEGER primary key autoincrement," +
                "title VARCHAR2," +
                "describe VARCHAR2," +
                "date VARCHAR2," +
                "lable VARCHAR2," +
                "image VARCHAR2," +
                "color text)");//close table note

        //<--------->//

        //SQL Table Tasks --> tid ,tasks_name , nid references to table Note
        sqLiteDatabase.execSQL("create table "+TAPLE_TASKS+"(" +
                "tid integer primary key autoincrement," +
                "task_name varchar2," +
                "nid integer ," +
                "binary integer ," +
                " foreign Key (nid) references "+TAPLE_NOTE+"(nid))");//close table Tasks

    }//end oncreate()

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TAPLE_NOTE+"");
        sqLiteDatabase.execSQL("drop table if exists TASKS");

        onCreate(sqLiteDatabase);
    }//end onupgrade()

    //insert date to database Table Note
    public boolean insert_Note(String title,String describe,String date,String label,String image,String color){
        boolean check;
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

            contentValues.put("title",title);
            contentValues.put("describe",describe);
            contentValues.put("date",date);
            contentValues.put("lable",label);
             contentValues.put("image",image);
            contentValues.put("color",color);
            check= sqLiteDatabase.insert(TAPLE_NOTE,null,contentValues)>0;
            sqLiteDatabase.close();
            return check;



    }//end insert_Note()

    //Insert data to database Table Tasks
    public boolean insert_Tasks(String Tasks,int Note_ID,int binary){
        boolean check;
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("task_name",Tasks);
        contentValues.put("nid",Note_ID);
        contentValues.put("binary",binary);

        check=sqLiteDatabase.insert(TAPLE_TASKS,null,contentValues)>0;
        return check;
    }//end insert_Tasks()

    //get Data from Table Note
    public ArrayList<Adapter_Table_Note> get_Date_from_Table_Note(){
        adapter_table_note.clear();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor result=sqLiteDatabase.rawQuery("select * from "+TAPLE_NOTE+"",null);
        result.moveToFirst();
        while (result.isAfterLast()==false){
            int id=result.getInt(0);
            String title=result.getString(1);
            String describe=result.getString(2);
            String date=result.getString(3);
            String label=result.getString(4);
            String image=result.getString(5);
            String color=result.getString(6);

            adapter_table_note.add(new Adapter_Table_Note(id,title,describe,date,label,image,color));
            result.moveToNext();
        }
        result.close();
        sqLiteDatabase.close();
        return adapter_table_note;
    }//end get_Date_from_Table_Note()

    public ArrayList<Adapter_Table_Tasks> get_Date_from_Table_Tasks(){
        adapter_table_tasks.clear();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor result=sqLiteDatabase.rawQuery("select * from "+TAPLE_TASKS+"",null);
        result.moveToFirst();
        while (result.isAfterLast()==false){
            int task_id=result.getInt(0);
            String task_name=result.getString(1);
            int note_id=result.getInt(2);
            int binary=result.getInt(3);
            adapter_table_tasks.add(new Adapter_Table_Tasks(task_id,task_name,note_id,binary));
            result.moveToNext();
        }
        sqLiteDatabase.close();
        result.close();
        return adapter_table_tasks;
    }//end get_Date_from_Table_Tasks()



    public int get_last_id(){
        int id = 0;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor result=sqLiteDatabase.rawQuery("select * from "+TAPLE_NOTE+"",null);
        result.moveToFirst();
        while (result.isAfterLast()==false){
            id=result.getInt(0);

            result.moveToNext();
        }
        result.close();
        sqLiteDatabase.close();
        return id;
    }//end get_Date_from_Table_Note()

    public void update(Adapter_Table_Note adapter_table_note,ArrayList<Adapter_Table_Tasks> task_adapter_update){
        ArrayList<Adapter_Table_Tasks> task_adapter_updates=task_adapter_update;
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("title",adapter_table_note.getTitle());
        contentValues.put("describe",adapter_table_note.getDescribe());
        contentValues.put("date",adapter_table_note.getDate());
        contentValues.put("lable",adapter_table_note.getLabel());
        contentValues.put("image",adapter_table_note.getImage());
        contentValues.put("color",adapter_table_note.getColor());
        sqLiteDatabase.update(TAPLE_NOTE,contentValues,"nid=?",new String[]{String.valueOf(adapter_table_note.getId())});
        ddelete_task(adapter_table_note.getId());

        for(int x=0;x<task_adapter_updates.size();x++) {
            String tasks = task_adapter_updates.get(x).getTask_name();
            int binary = task_adapter_updates.get(x).getBinary();
            insert_Tasks(tasks,adapter_table_note.getId(),binary);
        }

    }

    public void delete(int id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(TAPLE_NOTE,"nid=?",new String[]{String.valueOf(id)});
        ddelete_task(id);

    }

    public void ddelete_task(int id){
        SQLiteDatabase sqLiteDatabase_tasks=this.getWritableDatabase();
        Cursor result=sqLiteDatabase_tasks.rawQuery("select * from "+TAPLE_TASKS+"",null);
        result.moveToFirst();
        while (result.isAfterLast()==false){
            if(result.getInt(2)==id){

                sqLiteDatabase_tasks.delete(TAPLE_TASKS,"nid=?",new String[]{String.valueOf(id)});
            }
            result.moveToNext();
        }
        result.close();
    }
}

package com.NoteHalawy1.Recycler_Show_Note;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.NoteHalawy1.DataBase.Adapter_Table_Tasks;
import com.NoteHalawy1.DataBase.Table_Note;
import com.NoteHalawy1.MainActivity;
import com.NoteHalawy1.radio_button.Get_Radio_Button;

import java.util.ArrayList;

public class getData_Notes {
    Table_Note table_note;

    public static   ArrayList<show_notes_Adapter> show_notes_adapters=new ArrayList<>();

    public RecyclerView getdata(RecyclerView recyclerView, Context context){
        ItemTouchHelper.SimpleCallback simpleCallback;
        table_note=new Table_Note(context);
        Get_Radio_Button.task_adapter.clear();
        show_notes_adapters.clear();
        Table_Note table_note=new Table_Note(context.getApplicationContext());
        show_notes_adapters.clear();
        if(!table_note.get_Date_from_Table_Note().isEmpty()){
            MainActivity.note_not_found.setVisibility(View.GONE);
        }
        else{
            MainActivity.note_not_found.setVisibility(View.VISIBLE);

        }
        //String title,String describe,String date,String label,byte[] image,String color
        for(int x=0;table_note.get_Date_from_Table_Note().size()>x;x++) {
            ArrayList<Adapter_Table_Tasks> adapter_table_tasks=new ArrayList<>();
            int id=table_note.get_Date_from_Table_Note().get(x).getId();

            String title=table_note.get_Date_from_Table_Note().get(x).getTitle();
            String describe=table_note.get_Date_from_Table_Note().get(x).getDescribe();
            String date=table_note.get_Date_from_Table_Note().get(x).getDate();
            String label=table_note.get_Date_from_Table_Note().get(x).getLabel();
            String image=table_note.get_Date_from_Table_Note().get(x).getImage();
            String color=table_note.get_Date_from_Table_Note().get(x).getColor();

            for (int y=0;y<table_note.get_Date_from_Table_Tasks().size();y++) {

                if (table_note.get_Date_from_Table_Tasks().get(y).getId_note() ==
                        table_note.get_Date_from_Table_Note().get(x).getId()) {
                    adapter_table_tasks.add(table_note.get_Date_from_Table_Tasks().get(y));

                }
            }


                show_notes_adapters.add(new show_notes_Adapter(id,title,describe,date,label,image,color,adapter_table_tasks));




        }
        try{



        simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(context.getApplicationContext(), "Delete Note", Toast.LENGTH_SHORT).show();
                table_note.delete(show_notes_adapters.get(viewHolder.getAdapterPosition()).getId());
                show_notes_adapters.remove(viewHolder.getAdapterPosition());
                getdata(recyclerView,context);
            }
        };

            show_notes show_notes1=new show_notes(show_notes_adapters,context.getApplicationContext());
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setAdapter(show_notes1);
            new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
            show_notes1.notifyDataSetChanged();

        }
        catch (Exception e){

        }


        return recyclerView;
    }

    public RecyclerView getdata_search(RecyclerView recyclerView, Context context
            ,ArrayList<show_notes_Adapter> show_notes_adapter){



        show_notes show_notes1=new show_notes(show_notes_adapter,context.getApplicationContext());
        show_notes1.filter(show_notes_adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(show_notes1);
        recyclerView= getdata2(recyclerView,context,show_notes_adapter);

        return recyclerView;
    }


    public RecyclerView getdata2(RecyclerView recyclerView, Context context,ArrayList<show_notes_Adapter> show_notes_adapter){

        try {


        ItemTouchHelper.SimpleCallback simpleCallback;
        table_note=new Table_Note(context);
        Get_Radio_Button.task_adapter.clear();
        show_notes_adapters.clear();
        Table_Note table_note=new Table_Note(context.getApplicationContext());
        show_notes_adapters.clear();
        if(!table_note.get_Date_from_Table_Note().isEmpty()){
            MainActivity.note_not_found.setVisibility(View.GONE);
        }
        else{
            MainActivity.note_not_found.setVisibility(View.VISIBLE);

        }
        //String title,String describe,String date,String label,byte[] image,String color
        for(int x=0;show_notes_adapter.size()>x;x++) {

            show_notes_adapters.add(show_notes_adapter.get(x));



        }

        simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(context.getApplicationContext(), "Delete Note", Toast.LENGTH_SHORT).show();
                table_note.delete(show_notes_adapters.get(viewHolder.getAdapterPosition()).getId());
                show_notes_adapters.remove(viewHolder.getAdapterPosition());
                getdata2(recyclerView,context,show_notes_adapter);
            }
        };


        show_notes show_notes1=new show_notes(show_notes_adapters,context.getApplicationContext());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(show_notes1);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
        show_notes1.notifyDataSetChanged();
        }
        catch ( Exception e){

        }
        return recyclerView;
    }
}

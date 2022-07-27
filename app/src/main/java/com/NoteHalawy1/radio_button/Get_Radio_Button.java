package com.NoteHalawy1.radio_button;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.NoteHalawy1.DataBase.Adapter_Table_Tasks;
import com.NoteHalawy1.DataBase.Table_Note;

import java.util.ArrayList;

public class Get_Radio_Button {
    Table_Note table_note;
    public static ItemTouchHelper.SimpleCallback simpleCallback;
    public static ArrayList<Adapter_Table_Tasks> task_adapter=new ArrayList<>();
    public static ArrayList<Adapter_Table_Tasks> task_adapter_update;

    public RecyclerView get_radio(RecyclerView recyclerView, Context context){
            radio_button radio_button=new radio_button(task_adapter,context.getApplicationContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(context.getApplicationContext(),RecyclerView.VERTICAL,false));

            simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    task_adapter.remove(viewHolder.getAdapterPosition());
                    Toast.makeText(context.getApplicationContext(), "Deleted Task", Toast.LENGTH_SHORT).show();
                    get_radio(recyclerView,context);
                }
            };

        recyclerView.setAdapter(radio_button);
        new ItemTouchHelper(Get_Radio_Button.simpleCallback).attachToRecyclerView(recyclerView);
        radio_button.notifyDataSetChanged();



        return  recyclerView;
    }


    public RecyclerView get_radio_updadte(RecyclerView recyclerView, Context context,ArrayList<Adapter_Table_Tasks> task_adapters){
        task_adapter_update=task_adapters;
        radio_button radio_button=new radio_button(task_adapters,context.getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(context.getApplicationContext(),RecyclerView.VERTICAL,false));

        simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                task_adapter_update.remove(viewHolder.getAdapterPosition());
                Toast.makeText(context.getApplicationContext(), "Deleted Task", Toast.LENGTH_SHORT).show();
                get_radio_updadte(recyclerView,context,task_adapters);
            }
        };

        recyclerView.setAdapter(radio_button);
        new ItemTouchHelper(Get_Radio_Button.simpleCallback).attachToRecyclerView(recyclerView);
        radio_button.notifyDataSetChanged();



        return  recyclerView;
    }
    public void settasks_update(String tasks){
        task_adapter_update.add(new Adapter_Table_Tasks(tasks,0));
    }

    public void settasks(String tasks){
        task_adapter.add(new Adapter_Table_Tasks(tasks,0));
    }

    public void inserttask(Context context,int id_note){
        table_note=new Table_Note(context);
        for(int x=0;x<task_adapter.size();x++){

            String task=task_adapter.get(x).getTask_name();
            int binary=task_adapter.get(x).getBinary();
            //Insert Data to task table
            boolean check=table_note.insert_Tasks(task,id_note,binary);
            if(check==true){

            }
            else{
                Toast.makeText(context.getApplicationContext(), "false "+ table_note.get_last_id(), Toast.LENGTH_SHORT).show();

            }
        }
    }
    public RecyclerView get_tasks(RecyclerView recyclerView, Context context, ArrayList<Adapter_Table_Tasks> adapter_table_tasks){
        radio_button_show_note radio_button=new radio_button_show_note(adapter_table_tasks,context.getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(context.getApplicationContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(radio_button);
        radio_button.notifyDataSetChanged();
        return  recyclerView;
    }
    public RecyclerView get_tasks(RecyclerView recyclerView, Context context, ArrayList<Adapter_Table_Tasks> adapter_table_tasks,int number){
        radio_button_show_note radio_button=new radio_button_show_note(adapter_table_tasks,context.getApplicationContext(),1);
        recyclerView.setLayoutManager(new LinearLayoutManager(context.getApplicationContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(radio_button);
        radio_button.notifyDataSetChanged();
        return  recyclerView;
    }


}

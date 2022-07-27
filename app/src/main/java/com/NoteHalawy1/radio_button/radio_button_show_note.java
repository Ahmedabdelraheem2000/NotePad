package com.NoteHalawy1.radio_button;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.NoteHalawy1.Add_Note;
import com.NoteHalawy1.DataBase.Adapter_Table_Tasks;
import com.NoteHalawy1.R;
import com.NoteHalawy1.update_note;

import java.util.ArrayList;

public class radio_button_show_note extends RecyclerView.Adapter<radio_button_show_note.radio_button_holder> {
    ArrayList<Adapter_Table_Tasks> adapter_table_tasks=new ArrayList<>();
    Context context;
    int num;
    public radio_button_show_note(ArrayList<Adapter_Table_Tasks> adapter_table_tasks, Context context, int num) {
        this.adapter_table_tasks = adapter_table_tasks;
        this.context = context;
        this.num = num;

    }
    public radio_button_show_note(ArrayList<Adapter_Table_Tasks> adapter_table_tasks, Context context) {
        this.adapter_table_tasks = adapter_table_tasks;
        this.context = context;


    }
    @NonNull
    @Override
    public radio_button_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.recycler_shap_tasks,parent,false);

        return new radio_button_show_note.radio_button_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull radio_button_holder holder, int position) {
        holder.checkBox.setClickable(false);

        holder.checkBox.setText(adapter_table_tasks.get(position).getTask_name());


    if(adapter_table_tasks.get(position).getBinary()==1){

        holder.checkBox.setChecked(true);
        if(num==1){
           // holder.checkBox.setButtonDrawable(R.drawable.ok);
        }
        else{
            //holder.checkBox.setButtonDrawable(R.drawable.ok_white);
        }
        holder.checkBox.setPaintFlags(holder.checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
    else {
        holder.checkBox.setPaintFlags(holder.checkBox.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

    }

    }

    @Override
    public int getItemCount() {
        return adapter_table_tasks.size();
    }

    public class radio_button_holder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        public radio_button_holder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.checkboks);

        }
    }
}

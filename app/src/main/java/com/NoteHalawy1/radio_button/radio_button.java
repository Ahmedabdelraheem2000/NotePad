package com.NoteHalawy1.radio_button;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.NoteHalawy1.DataBase.Adapter_Table_Tasks;
import com.NoteHalawy1.R;

import java.util.ArrayList;

public class radio_button extends RecyclerView.Adapter<radio_button.radio_button_holder> {
    ArrayList<Adapter_Table_Tasks> adapter_table_tasks=new ArrayList<>();
    Context context;

    public radio_button(ArrayList<Adapter_Table_Tasks> adapter_table_tasks, Context context) {
        this.adapter_table_tasks = adapter_table_tasks;
        this.context = context;
    }

    @NonNull
    @Override
    public radio_button_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.recycler_shap_tasks,parent,false);

        return new radio_button.radio_button_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull radio_button_holder holder, int position) {
    holder.checkBox.setText(adapter_table_tasks.get(position).getTask_name());

    if(adapter_table_tasks.get(position).getBinary()==1){
        holder.checkBox.setChecked(true);
        holder.checkBox.setPaintFlags(holder.checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
    else {
        holder.checkBox.setChecked(false);
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
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checkBox.isChecked()==true){
                        checkBox.setPaintFlags(checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        adapter_table_tasks.get(getAdapterPosition()).setBinary(1);
                    }
                    else{
                        checkBox.setPaintFlags(checkBox.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                        adapter_table_tasks.get(getAdapterPosition()).setBinary(0);

                    }
                    notifyDataSetChanged();

                }
            });
        }
    }
}

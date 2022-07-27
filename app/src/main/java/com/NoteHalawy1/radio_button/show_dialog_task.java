package com.NoteHalawy1.radio_button;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.NoteHalawy1.Add_Note;
import com.NoteHalawy1.R;
import com.NoteHalawy1.update_note;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputLayout;

public class show_dialog_task {
    String tasks;
    Get_Radio_Button get_radio_button=new Get_Radio_Button();
    Get_Radio_Button getData_notes=new Get_Radio_Button();
    public String showAddItemDialog(Context context) {
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_task);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
       final TextInputLayout add_tasks= dialog.findViewById(R.id.Add_task);
        final Button  done_task =dialog.findViewById(R.id.done_tasks);
       final Button cancel_task =dialog.findViewById(R.id.Cancel_tasks);
        dialog.show();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        //keybord
        add_tasks.getEditText().requestFocus();
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
            }
        }, 500);
        //////////////
        done_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                tasks=  add_tasks.getEditText().getText().toString();
                if(tasks.length()>0) {
                    getData_notes.settasks(tasks);
                    Add_Note.taks_recycler = get_radio_button.get_radio(Add_Note.taks_recycler, context);
                    Add_Note.counter++;
                }
                dialog.dismiss();
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            }
        });
        cancel_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            }
        });
        return tasks;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public String showAddItemDialog_updddaate(Context context) {
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_task);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        final TextInputLayout add_tasks= dialog.findViewById(R.id.Add_task);
        final Button  done_task =dialog.findViewById(R.id.done_tasks);
        final Button cancel_task =dialog.findViewById(R.id.Cancel_tasks);
        dialog.show();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        //keybord
        add_tasks.getEditText().requestFocus();
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
            }
        }, 500);

        done_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View views) {


                tasks=  add_tasks.getEditText().getText().toString();
                update_note.taks_recycler_update=get_radio_button.get_radio_updadte(update_note.taks_recycler_update,context,update_note.adapter_table_tasks);

                getData_notes.settasks_update(tasks);
                update_note.bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                dialog.dismiss();
                imm.hideSoftInputFromWindow(views.getWindowToken(), 0);

            }
        });
        cancel_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            }
        });

        return tasks;
    }


}

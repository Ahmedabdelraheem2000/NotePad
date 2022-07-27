package com.NoteHalawy1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.NoteHalawy1.Recycler_Show_Note.getData_Notes;
import com.NoteHalawy1.Recycler_Show_Note.show_notes_Adapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView recyclerView2;

    EditText search;
    public static RelativeLayout note_not_found;
    getData_Notes getData_notes=new getData_Notes();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        note_not_found=findViewById(R.id.note_not_found);
        search = findViewById(R.id.search);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//                String String =search.getText().toString().trim();
//                search.setText(String.toString());
//                search.getText();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length()<=0){
                    recyclerView = getData_notes.getdata(recyclerView, getApplicationContext());
                }
            filter(editable.toString());
            }
        });



    }

    private void filter(String s) {
        ArrayList<show_notes_Adapter> show_notes_adapter=new ArrayList<>();
        for(int x=0;x<getData_notes.show_notes_adapters.size();x++){
            if(getData_notes.show_notes_adapters.get(x).getTitle().toLowerCase().contains(s.toLowerCase())
            ||getData_notes.show_notes_adapters.get(x).getDescribe().toLowerCase().contains(s.toLowerCase())){
                show_notes_adapter.add(getData_notes.show_notes_adapters.get(x));
            }

        }
        recyclerView = getData_notes.getdata_search(recyclerView, this,show_notes_adapter);

    }


    public void Add_Note(View view) {
        startActivity(new Intent(this,Add_Note.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView = getData_notes.getdata(recyclerView, this);


    }


}
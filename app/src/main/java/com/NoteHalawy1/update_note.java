package com.NoteHalawy1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.NoteHalawy1.Bottom_Sheet.Color_Adapter;
import com.NoteHalawy1.Bottom_Sheet.Select_color_label;
import com.NoteHalawy1.Bottom_Sheet.recycler_color_update;
import com.NoteHalawy1.DataBase.Adapter_Table_Note;
import com.NoteHalawy1.DataBase.Adapter_Table_Tasks;
import com.NoteHalawy1.DataBase.Table_Note;
import com.NoteHalawy1.radio_button.Get_Radio_Button;
import com.NoteHalawy1.radio_button.radio_button;
import com.NoteHalawy1.radio_button.show_dialog_task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputLayout;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class update_note extends AppCompatActivity {
    public static Select_color_label select_color_label;
    TextInputLayout title_update,lavel_dialog,Add_task;
    Button cancel_label, done_label;
    EditText describe_update;
    TextView label_new_note_update, date_update;
    ImageView image_new_note_update;
    CardView card_iamge_update;
    String labels;
    String date;
    show_dialog_task show_dialog_task = new show_dialog_task();
    LinearLayout delete;
    int select=0;
    String colors;
    String img=null;
    Table_Note table_note=new Table_Note(update_note.this);
    static  LinearLayout setting ;
     public static  BottomSheetBehavior<LinearLayout> bottomSheetBehavior;
    public static final int PICK_IMAGE = 1;
    public static final int REQUEST_CODE_STORAGE_PERMISSION=1;
    View view;
    Adapter_Table_Note adapter_table_note;
    ArrayList<Color_Adapter> color_adapters = new ArrayList<>();
   public static RecyclerView taks_recycler_update,recycler_color;
    Get_Radio_Button get_radio_button=new Get_Radio_Button();
    public static ArrayList<Adapter_Table_Tasks> adapter_table_tasks;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatenote);
        delete=findViewById(R.id.delete);
        delete.setVisibility(View.VISIBLE);
        title_update = findViewById(R.id.title_update);
        describe_update = findViewById(R.id.describe_update);
        label_new_note_update = findViewById(R.id.label_new_note_update);
        date_update = findViewById(R.id.date_update);
        image_new_note_update = findViewById(R.id.image_new_note_update);
        card_iamge_update = findViewById(R.id.card_iamge_update);
        taks_recycler_update= findViewById(R.id.taks_recycler_update);
        view= findViewById(R.id.label_update);
        recycler_color= findViewById(R.id.recycler_color);
        date = getIntent().getStringExtra("date");
        view.setBackgroundTintList(getResources().getColorStateList(Integer.parseInt(getIntent().getStringExtra("color"))));
        taks_recycler_update=get_radio_button.get_radio_updadte(taks_recycler_update,getApplicationContext(),adapter_table_tasks);
        put_date();
        setting();
        Select_Color();





    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void put_date() {
        title_update.getEditText().setText(getIntent().getStringExtra("title"));
        describe_update.setText(getIntent().getStringExtra("describe"));
        label_new_note_update.setText(getIntent().getStringExtra("label"));
        if(label_new_note_update.getText().toString().trim().length()>0){
            label_new_note_update.setVisibility(View.VISIBLE);
        }
        else{
            label_new_note_update.setVisibility(View.GONE);
        }
        date_update.setText(date + " | Character " + describe_update.length());



        try {

            radio_button radio_button1=new radio_button(adapter_table_tasks,getApplicationContext());
            taks_recycler_update.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
            taks_recycler_update.setAdapter(radio_button1);
            radio_button1.notifyDataSetChanged();

            Uri uri = Uri.parse(getIntent().getStringExtra("image"));
            card_iamge_update.setVisibility(View.VISIBLE);

            InputStream inputStream = getApplication().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            image_new_note_update.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            card_iamge_update.setVisibility(View.GONE);
        } catch (NullPointerException e) {
            e.printStackTrace();
            card_iamge_update.setVisibility(View.GONE);
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


        describe_update.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int counter = describe_update.length();
                String convert = String.valueOf(counter);
                date_update.setText(date + " | Character " + convert);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        select_color_label = new Select_color_label() {
            @Override
            public void select_color(int color) {

                view.setBackgroundTintList(getResources().getColorStateList(color));
                colors= String.valueOf(color);
            }
        };

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        //keybord
        describe_update.requestFocus();


    }

    //Select Color
    public void Select_Color() {

        color_adapters.add(new Color_Adapter(R.color.black_1));
        color_adapters.add(new Color_Adapter(R.color.blue));
        color_adapters.add(new Color_Adapter(R.color.BASIC));
        color_adapters.add(new Color_Adapter(R.color.teal_200));
        color_adapters.add(new Color_Adapter(R.color.teal_700));
        color_adapters.add(new Color_Adapter(R.color.purple_200));

        color_adapters.add(new Color_Adapter(R.color.c1));
        color_adapters.add(new Color_Adapter(R.color.c2));
        color_adapters.add(new Color_Adapter(R.color.c3));
        color_adapters.add(new Color_Adapter(R.color.c4));
        color_adapters.add(new Color_Adapter(R.color.c5));

        for(int x=0;x<color_adapters.size();x++){
            if(Integer.parseInt(getIntent().getStringExtra("color"))==color_adapters.get(x).getColor()){
                select=x;
            }
        }
        recycler_color_update recycler_colors = new recycler_color_update(color_adapters,select ,getApplicationContext(),
                new recycler_color_update.Colors() {
            @Override
            public void onclickclolr(int color) {
                select=color;
            }
        });
                recycler_color.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        recycler_color.setAdapter(recycler_colors);
        recycler_colors.notifyDataSetChanged();
    }
    public void AddIMage(View view) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(update_note.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_STORAGE_PERMISSION);
        }
        else{
            selectimage();
        }

    }
    void selectimage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,PICK_IMAGE);
        final LinearLayout setting = findViewById(R.id.bottomsheet);
        final BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(setting);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE_STORAGE_PERMISSION&&grantResults.length>0){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                selectimage();
            }
            else{

            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    try {
                        InputStream inputStream=getContentResolver().openInputStream(uri);
                        Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                        image_new_note_update.setImageBitmap(bitmap);
                        card_iamge_update.setVisibility(View.VISIBLE);
                        img= String.valueOf(uri);
                    } catch (Exception e) {

                    }
                }
            }
        }
    }

    public void update_image(View view) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(update_note.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_STORAGE_PERMISSION);
        }
        else{
            selectimage();
        }
    }
    public void Add_Label(View view) {
        showAddItemDialog(label_new_note_update.getText().toString());
    }
    private void showAddItemDialog(String label) {
        Dialog dialog = new Dialog(update_note.this);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lavel_dialog = dialog.findViewById(R.id.label_dialig);
        lavel_dialog.getEditText().setText(label);
        lavel_dialog.getEditText().addTextChangedListener(longtext);
        done_label = dialog.findViewById(R.id.done_label);
        cancel_label = dialog.findViewById(R.id.cancel_label);
        dialog.show();
        //keybord
        lavel_dialog.getEditText().requestFocus();
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
            }
        }, 500);
        done_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                labels = lavel_dialog.getEditText().getText().toString();
                label_new_note_update.setText(labels);
                update_note.bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                if(labels.length()<=0)
                label_new_note_update.setVisibility(View.GONE);
                else label_new_note_update.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
        cancel_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lavel_dialog.getEditText().setText("");
                labels =null;
                label_new_note_update.setText(labels);
                label_new_note_update.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });

    }
    private TextWatcher longtext = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String label = lavel_dialog.getEditText().getText().toString().trim();



        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    public void edit_labal(View view) {
        showAddItemDialog(label_new_note_update.getText().toString());
    }

    public void setting() {

        setting= findViewById(R.id.bottomsheet);
        bottomSheetBehavior = BottomSheetBehavior.from(setting);

        setting.findViewById(R.id.first_shape).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    closeKeyboard();
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    closeKeyboard();
                }

            }
        });
    }



    private void closeKeyboard() {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                close();
            }
        }, 350);


    }

    void close() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void Add_Tasks(View view) {
        show_dialog_task.showAddItemDialog_updddaate(this);
    }

    public void back(View view) {
        onBackPressed();
    }

    public void ok(View view) {
        Calendar calendar = Calendar.getInstance();
        String color1;
        if (select == 0) {
            color1 = String.valueOf(R.color.new_black);
        } else {
            color1 = String.valueOf(color_adapters.get(select).getColor());
        }
        String image;
        int id= Integer.parseInt(getIntent().getStringExtra("id"));
        String title=title_update.getEditText().getText().toString();
        String describe=describe_update.getText().toString();
        String label=label_new_note_update.getText().toString();


        if(label.length()<=0)label=null;

        if(img==null){
           image=getIntent().getStringExtra("image");
        }
        else if(img==""){
            image=null;
        }
        else{
            image=img;
        }
        String date= DateFormat.getDateInstance(DateFormat.MONTH_FIELD).format(calendar.getTime());
        Adapter_Table_Note adapter_table_note=new Adapter_Table_Note(id,title,describe,date,label,image,color1);
        table_note.update(adapter_table_note,Get_Radio_Button.task_adapter_update);
         onBackPressed();
    }


    public void deletes(View view) {
        int id= Integer.parseInt(getIntent().getStringExtra("id"));

        table_note.delete(id);
        Toast.makeText(getApplicationContext(), "Delete Note", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
    public void delete_image(View view) {

        img="";
        card_iamge_update.setVisibility(View.GONE);

    }

    int c=0;
    public void color(View view) {
        final LinearLayout setting = findViewById(R.id.bottomsheet);
        final BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(setting);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        c++;
        if(c==1){
            recycler_color.setVisibility(View.VISIBLE);
        }
        else{
            c=0;
            recycler_color.setVisibility(View.GONE);
        }


}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        done();
    }
    public  void done(){
        Calendar calendar = Calendar.getInstance();
        String color1;
        if (select == 0) {
            color1 = String.valueOf(R.color.new_black);
        } else {
            color1 = String.valueOf(color_adapters.get(select).getColor());
        }
        String image;
        int id= Integer.parseInt(getIntent().getStringExtra("id"));
        String title=title_update.getEditText().getText().toString();
        String describe=describe_update.getText().toString();
        String label=label_new_note_update.getText().toString();


        if(label.length()<=0)label=null;

        if(img==null){
            image=getIntent().getStringExtra("image");
        }
        else if(img==""){
            image=null;
        }
        else{
            image=img;
        }
        
        String date= DateFormat.getDateInstance(DateFormat.MONTH_FIELD).format(calendar.getTime());
        Adapter_Table_Note adapter_table_note=new Adapter_Table_Note(id,title,describe,date,label,image,color1);
        ArrayList<Adapter_Table_Tasks> task_adapter_update=Get_Radio_Button.task_adapter_update;

        table_note.update(adapter_table_note,task_adapter_update);

    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void copy(View view) {
        String des=describe_update.getText().toString().trim();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copy", des);
        Toast.makeText(getApplicationContext(), "Copy Describe", Toast.LENGTH_SHORT).show();
        clipboard.setPrimaryClip(clip);
    }
}

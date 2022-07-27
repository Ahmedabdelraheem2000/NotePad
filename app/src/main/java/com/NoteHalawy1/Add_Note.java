package com.NoteHalawy1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.NoteHalawy1.Bottom_Sheet.Color_Adapter;
import com.NoteHalawy1.Bottom_Sheet.Select_color_label;
import com.NoteHalawy1.Bottom_Sheet.recycler_color;
import com.NoteHalawy1.DataBase.Table_Note;
import com.NoteHalawy1.radio_button.Get_Radio_Button;
import com.NoteHalawy1.radio_button.show_dialog_task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Add_Note extends AppCompatActivity {
    View label;
    ImageView image_new_note;
    EditText describe;
    CardView card_iamge;
    TextInputLayout lavel_dialog, title, Add_task;
    CoordinatorLayout add_note_class;
    Button cancel_label, done_label;
    TextView date, label_new_note, date_bottom_sheet;
    Get_Radio_Button get_radio_button = new Get_Radio_Button();
    RecyclerView recyclerView;
    public static RecyclerView taks_recycler;
    Table_Note table_note = new Table_Note(this);
    String bytes;
    public static int counter=0;
    String img=null;
    public static final int PICK_IMAGE = 1;
    public static final int REQUEST_CODE_STORAGE_PERMISSION=1;
    show_dialog_task show_dialog_task = new show_dialog_task();
    RelativeLayout relative_label;
    ArrayList<Color_Adapter> color_adapters = new ArrayList<>();
    String labels, convert = "0", date_history;
    int select = 0;


    Calendar calendar = Calendar.getInstance();
    public static Select_color_label select_color_label;

    @SuppressLint({"ResourceAsColor", "WrongViewCast"})
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        //ID//
        add_note_class = findViewById(R.id.add_note_class);
        date_bottom_sheet = findViewById(R.id.date_bottom_sheet);
        card_iamge = findViewById(R.id.card_iamge);
        relative_label = findViewById(R.id.relative_label);
        label_new_note = findViewById(R.id.label_new_note);
        Add_task = findViewById(R.id.Add_task);
        title = findViewById(R.id.title);
        describe = findViewById(R.id.describe);
        date = findViewById(R.id.date);
        taks_recycler = findViewById(R.id.taks_recycler);

        label = findViewById(R.id.label);
        image_new_note = findViewById(R.id.image_new_note);
        recyclerView = findViewById(R.id.recycler_color);
        date_history = DateFormat.getDateInstance(DateFormat.MONTH_FIELD).format(calendar.getTime());
        date.setText(date_history.toString() + " | Character 0");
        date_bottom_sheet.setText(date_history.toString() + " | Character 0");
        setting();
        Select_Color();     //Function Select Color Find to Add_Note Class
        //end//
        final LinearLayout setting = findViewById(R.id.bottomsheet);
        final BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(setting);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

          //keybord
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        describe.requestFocus();

        describe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int counter = describe.length();
                convert = String.valueOf(counter);
                date_bottom_sheet.setText(date_history.toString() + " | Character " + convert);
                date.setText(date_history.toString() + " | Character " + convert);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        label.setBackgroundTintList(getResources().getColorStateList(R.color.new_black));
        select_color_label = new Select_color_label() {
            @Override
            public void select_color(int color) {
                label.setBackgroundTintList(getResources().getColorStateList(color));
            }
        };

    }


   /* public void ok(View view) throws IOException {
        String color_done;
        if (select == 0) {
            color_done = String.valueOf(R.color.new_black);
        } else {
            color_done = String.valueOf(color_adapters.get(select).getColor());

        }

        //String title,String describe,String date,String label,byte[] image,String color
        boolean check = table_note.insert_Note(title.getEditText().getText().toString(), describe.getText().toString(), date_history
                , labels, img, color_done);


        if (check == true&&title.getEditText().getText().toString().trim().length()>0&&
                describe.getText().toString().trim().length()>0) {
            get_radio_button.inserttask(this, table_note.get_last_id());
            onBackPressed();
        } else {
            Toast.makeText(getApplicationContext(), "faild"+" ", Toast.LENGTH_SHORT).show();


        }

    }*/


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
        recycler_color recycler_colors = new recycler_color(color_adapters, getApplicationContext(), new recycler_color.Color() {
            @Override
            public void onclickclolr(int color) {

                select = color;

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(recycler_colors);
        recycler_colors.notifyDataSetChanged();
    }

    private void showAddItemDialog(String label) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        Dialog dialog = new Dialog(Add_Note.this);
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
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
            }
        }, 500);
        done_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                labels = lavel_dialog.getEditText().getText().toString();
                label_new_note.setText(labels);
                relative_label.setVisibility(View.VISIBLE);
                dialog.dismiss();
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        cancel_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lavel_dialog.getEditText().setText("");
                labels =null;
                label_new_note.setText(labels);
                relative_label.setVisibility(View.GONE);
                dialog.dismiss();
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

    }

    private TextWatcher longtext = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String label = lavel_dialog.getEditText().getText().toString().trim();

            done_label.setEnabled(!label.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    public void Add_Label(View view) {
        showAddItemDialog("");
    }

    public void setting() {
        final LinearLayout setting = findViewById(R.id.bottomsheet);
        final BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(setting);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

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

    public void Add_Tasks(View view) {
        show_dialog_task.showAddItemDialog(this);
    }

    public void back(View view) {

        onBackPressed();
    }

    public void edit_labal(View view) {
        showAddItemDialog(labels);
    }



    public void AddIMage(View view) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Add_Note.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                 REQUEST_CODE_STORAGE_PERMISSION);
        }
    else{
            selectimage();
        }

    }
    void selectimage(){
        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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
                     image_new_note.setImageBitmap(bitmap);
                     card_iamge.setVisibility(View.VISIBLE);
                     img= String.valueOf(uri);
                    } catch (Exception e) {

                    }
                }
            }
        }
    }

    private void closeKeyboard() {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               // close();
            }
        }, 350);


    }

   /* void close() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }*/


    public void delete_image(View view) {
        img=null;
        card_iamge.setVisibility(View.GONE);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        done();
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    void done(){
            String color_done;
            if (select == 0) {
                color_done = String.valueOf(R.color.new_black);
            } else {
                color_done = String.valueOf(color_adapters.get(select).getColor());

            }
            //String title,String describe,String date,String label,byte[] image,String color
        if(title.getEditText().getText().length()>0||describe.getText().length()>0||counter>0||img!=null){
            boolean check = table_note.insert_Note(title.getEditText().getText().toString(), describe.getText().toString(), date_history
                    , labels, img, color_done);

            if (check == true) {
                get_radio_button.inserttask(this, table_note.get_last_id());
                Toast.makeText(getApplicationContext(), "Note Created", Toast.LENGTH_SHORT).show();

            }
            } else {
                Toast.makeText(getApplicationContext(), "Note is Empty", Toast.LENGTH_SHORT).show();


            }
        counter=0;
        }

    int x=0;
    public void color(View view) {
        final LinearLayout setting = findViewById(R.id.bottomsheet);
        final BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(setting);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        x++;
        if(x==1){
            recyclerView.setVisibility(View.VISIBLE);
        }
        else{
            x=0;
            recyclerView.setVisibility(View.GONE);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void copy(View view) {
        String des=describe.getText().toString().trim();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copy", des);
        Toast.makeText(getApplicationContext(), "Copy Describe", Toast.LENGTH_SHORT).show();
        clipboard.setPrimaryClip(clip);
    }
}

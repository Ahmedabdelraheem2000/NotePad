package com.NoteHalawy1.Recycler_Show_Note;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.NoteHalawy1.R;
import com.NoteHalawy1.radio_button.Get_Radio_Button;
import com.NoteHalawy1.update_note;

import java.io.InputStream;
import java.util.ArrayList;

public class show_notes extends RecyclerView.Adapter<show_notes.show_notes_holder> {
    ArrayList<show_notes_Adapter> show_notes_adapters=new ArrayList<>();
    Context context;
    Get_Radio_Button get_radio_button=new Get_Radio_Button();
    public show_notes(ArrayList<show_notes_Adapter> show_notes_adapters, Context context) {
        this.show_notes_adapters = show_notes_adapters;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @NonNull
    @Override
    public show_notes_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.noteshap,parent,false);
        return new show_notes.show_notes_holder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull show_notes_holder holder, int position) {
        holder.title.setText(show_notes_adapters.get(position).getTitle());
        holder.describe.setText(show_notes_adapters.get(position).getDescribe());
        holder.shape_text.setText(show_notes_adapters.get(position).getLabel());
        holder.date.setText(show_notes_adapters.get(position).getDate());
        String label=holder.shape_text.getText().toString();
        String describe_s=holder.describe.getText().toString();
        String title_s=holder.title.getText().toString();
        holder.recycler_shape.setClickable(false);

        char label_char[]=new char[8];
        char describe_char[]=new char[80];
        char title_char[]=new char[12];

        if(holder.title.getText().toString().trim().equals("")){
            holder.title.setVisibility(View.GONE);
        }else {
            holder.title.setVisibility(View.VISIBLE);
        }
        if(holder.describe.getText().toString().trim().equals("")){
            holder.describe.setVisibility(View.GONE);
        }else {
            holder.describe.setVisibility(View.VISIBLE);
        }

        if(label.length()>6){
            label.getChars(0,5,label_char,0);
            String s= String.valueOf(label_char);
            holder.shape_text.setText(s+"..");
        }

        if(describe_s.length()>100){
            describe_s.getChars(0,80,describe_char,0);
            String s= String.valueOf(describe_char);
            holder.describe.setText(s+"..");
        }

        if(title_s.length()>12){
            title_s.getChars(0,11,title_char,0);
            String s= String.valueOf(title_char);
            holder.title.setText(s+"..");
        }

        //color
        int color= Integer.parseInt(show_notes_adapters.get(position).getColor());

        holder.cardnotes.setBackgroundTintList(ColorStateList.valueOf(context.getColor(color)));
        ///////////////
        if(show_notes_adapters.get(position).getImage()!=null){
            try {

                holder.card_image_shap.setVisibility(View.VISIBLE);
                Uri uri= Uri.parse(show_notes_adapters.get(position).getImage());
                InputStream inputStream=context.getContentResolver().openInputStream(uri);
                Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                holder.image_shape.setImageBitmap(bitmap);

            } catch (Exception e) {

            }


        }else {
            holder.card_image_shap.setVisibility(View.GONE);

        }

        if(holder.shape_text.getText().toString()!=""){
            holder.shap.setVisibility(View.VISIBLE);
        }
        else{
            holder.shap.setVisibility(View.GONE);
        }

        if(color==R.color.new_black){
            holder.recycler_shape=get_radio_button.get_tasks(holder.recycler_shape
                    ,context.getApplicationContext()
                    ,show_notes_adapters.get(position).getAdapter_table_tasks(),1);

        }
        else {
            holder.recycler_shape = get_radio_button.get_tasks(holder.recycler_shape
                    , context.getApplicationContext()
                    , show_notes_adapters.get(position).getAdapter_table_tasks());
        }
    }

    @Override
    public int getItemCount() {
        return show_notes_adapters.size();
    }

    public class show_notes_holder extends RecyclerView.ViewHolder {
        ImageView image_shape;
        TextView title,describe,shape_text,date;
        CardView card_image_shap,cardnotes;
        RelativeLayout shap,clicknote;

        RecyclerView recycler_shape;


        @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
        public show_notes_holder(@NonNull View itemView) {
            super(itemView);
            shap=itemView.findViewById(R.id.shap);
            title=itemView.findViewById(R.id.title);
            image_shape=itemView.findViewById(R.id.image_shape);
            describe=itemView.findViewById(R.id.describe);
            shape_text=itemView.findViewById(R.id.shape_text);
            date=itemView.findViewById(R.id.date);
            card_image_shap=itemView.findViewById(R.id.card_image_shap);
            recycler_shape=itemView.findViewById(R.id.recycler_shape);
            cardnotes=itemView.findViewById(R.id.cardnotes);
            clicknote=itemView.findViewById(R.id.clicknote);



            cardnotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent=new Intent(context.getApplicationContext(), update_note.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    String id= String.valueOf(show_notes_adapters.get(getAdapterPosition()).getId());
                    intent.putExtra("id",id);
                    intent.putExtra("title",show_notes_adapters.get(getAdapterPosition()).getTitle());
                    intent.putExtra("describe",show_notes_adapters.get(getAdapterPosition()).getDescribe());
                    intent.putExtra("date",show_notes_adapters.get(getAdapterPosition()).getDate());
                    intent.putExtra("label",show_notes_adapters.get(getAdapterPosition()).getLabel());
                    intent.putExtra("color",show_notes_adapters.get(getAdapterPosition()).getColor());
                    intent.putExtra("image",show_notes_adapters.get(getAdapterPosition()).getImage());
                    update_note.adapter_table_tasks=show_notes_adapters.get(getAdapterPosition()).getAdapter_table_tasks();
                    context.startActivity(intent);
                }
            });
        }
    }
    public void filter(ArrayList<show_notes_Adapter> show){
        show_notes_adapters=show;
        notifyDataSetChanged();
    }
}

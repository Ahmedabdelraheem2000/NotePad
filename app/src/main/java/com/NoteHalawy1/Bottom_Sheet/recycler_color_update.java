package com.NoteHalawy1.Bottom_Sheet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.NoteHalawy1.R;
import com.NoteHalawy1.update_note;

import java.util.ArrayList;

public class recycler_color_update extends RecyclerView.Adapter<recycler_color_update.recycler_holder> {
    ArrayList<Color_Adapter> color_adapters=new ArrayList<>();
    Context context;





    public interface Colors{
        public void onclickclolr(int color);
    }
    Colors colors;
    int select=0;
    public recycler_color_update(ArrayList<Color_Adapter> color_adapters,int select ,Context context, Colors color) {
        this.color_adapters = color_adapters;
        this.context = context;
        this.select=select;
        this.colors=color;
    }

    @NonNull
    @Override
    public recycler_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.labels_color,parent,false);

        return new recycler_color_update.recycler_holder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull recycler_holder holder, @SuppressLint("RecyclerView") int position) {

        holder.circleImageView.setImageTintList(context.getResources()
                .getColorStateList(color_adapters.get(position).getColor()));




       if(position==select){
            holder.select_color.setVisibility(View.VISIBLE);
        }
        else{
            holder.select_color.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return color_adapters.size();
    }

    public class recycler_holder extends RecyclerView.ViewHolder {
        ImageView circleImageView;
        ImageView select_color;

        public recycler_holder(@NonNull View itemView) {
            super(itemView);
            circleImageView=itemView.findViewById(R.id.color);
            select_color=itemView.findViewById(R.id.select_color);


            circleImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    select=getAdapterPosition();
                  
                    update_note.select_color_label.select_color(color_adapters.get(getAdapterPosition()).getColor());

                    colors.onclickclolr(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
        }
    }
}

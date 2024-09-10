package com.example.recyclerviewexthree;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CostumAdepter extends RecyclerView.Adapter<CostumAdepter.ViewHolder>{
    Context context;
    ArrayList<Contect> contctList;

    public CostumAdepter(Context context, ArrayList<Contect> contctList) {
        this.context = context;
        this.contctList = contctList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_lay,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
           holder.name.setText(contctList.get(position).name);
           holder.number.setText(contctList.get(position).number);

           holder.ll.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Dialog dialog = new Dialog(context);
                   dialog.setContentView(R.layout.add_lay);
                   dialog.show();
                   TextView tittel = dialog.findViewById(R.id.tittel);
                   EditText name = dialog.findViewById(R.id.name);
                   EditText number = dialog.findViewById(R.id.number);
                   Button btn = dialog.findViewById(R.id.btn);
                   btn.setText("Update");
                   tittel.setText("Update Cotect");
                   name.setText(contctList.get(position).name);
                   number.setText(contctList.get(position).number);
                   btn.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           String name1 = name.getText().toString();
                           String number1 = number.getText().toString();
                           contctList.set(position,new Contect(name1,number1));
                           notifyDataSetChanged();
                           dialog.dismiss();
                       }
                   });
               }
           });

           holder.ll.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {
                   AlertDialog.Builder ad =new AlertDialog.Builder(context)
                           .setTitle("Delete Cotect")
                           .setMessage("Are You Sure Want To Delete")
                           .setIcon(R.drawable.baseline_delete_outline_24)
                           .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                               @Override
                   public void onClick(DialogInterface dialog, int which) {
                       contctList.remove(position);
                       notifyItemRemoved(position);}
                   })
                   .setNegativeButton("No", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {

                       }
                   });
                   ad.show();
                   return true;
           }});

           setAnimation(holder.itemView,position);


    }

    @Override
    public int getItemCount() {
        return contctList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView number;
        LinearLayout ll;

        public ViewHolder( View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtname);
            number = itemView.findViewById(R.id.txtnumber);
            ll=itemView.findViewById(R.id.llrow);
        }
    }

    private void setAnimation(View viewToAnimate, int position){

        Animation slideIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewToAnimate.startAnimation(slideIn);
    }
}

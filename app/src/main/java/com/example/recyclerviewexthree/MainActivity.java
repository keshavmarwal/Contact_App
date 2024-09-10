package com.example.recyclerviewexthree;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Contect> contctList;
    CostumAdepter costumAdepter;
    RecyclerView reView;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
         reView = findViewById(R.id.recyclerView);
         fab = findViewById(R.id.fab);
        reView.setLayoutManager(new LinearLayoutManager(this));


        contctList = new ArrayList<>();
        contctList.add(new Contect("A","9876543210"));
        contctList.add(new Contect("B","9343942934"));
        contctList.add(new Contect("C","9343942934"));
        contctList.add(new Contect("D","9876543210"));
        contctList.add(new Contect("E","9343942934"));
        contctList.add(new Contect("F","9343942934"));
        contctList.add(new Contect("G","9876543210"));
        contctList.add(new Contect("H","9343942934"));
        contctList.add(new Contect("I","9343942934"));
        contctList.add(new Contect("J","9876543210"));
        contctList.add(new Contect("K","9343942934"));
        contctList.add(new Contect("L","9343942934"));
         costumAdepter = new CostumAdepter(this,contctList);
        reView.setAdapter(costumAdepter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_lay);
                dialog.show();
                EditText name = dialog.findViewById(R.id.name);
                EditText number = dialog.findViewById(R.id.number);
                Button  btn = dialog.findViewById(R.id.btn);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name1 = name.getText().toString();
                        String number1 = number.getText().toString();
                        contctList.add(new Contect(name1,number1));
                        costumAdepter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}
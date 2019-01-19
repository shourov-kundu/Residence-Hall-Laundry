package com.example.carrolllaundry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MachineView extends AppCompatActivity {
    ArrayList<TextView> machinesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_view);

        Button refreshButton = findViewById(R.id.refreshButton);
        TextView dryer1TextView = findViewById(R.id.dryer1TextView);
        TextView dryer2TextView = findViewById(R.id.dryer2TextView);
        TextView dryer3TextView = findViewById(R.id.dryer3TextView);
        TextView washer4TextView = findViewById(R.id.washer4TextView);
        TextView washer5TextView = findViewById(R.id.washer5TextView);
        TextView washer6TextView = findViewById(R.id.washer6TextView);

        machinesList.add(dryer1TextView);
        machinesList.add(dryer2TextView);
        machinesList.add(dryer3TextView);
        machinesList.add(washer4TextView);
        machinesList.add(washer5TextView);
        machinesList.add(washer6TextView);

        final Button dryer1Button = findViewById(R.id.dryer1Button);
        Button dryer2Button= findViewById(R.id.dryer2Button);
        Button dryer3Button = findViewById(R.id.dryer3Button);
        Button washer4Button = findViewById(R.id.washer4Button);
        Button washer5Button = findViewById(R.id.washer5Button);
        Button washer6Button = findViewById(R.id.washer6Button);
        dryer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean[] usages;
                try{
                    usages = SheetsData.getData();
                    for (int i = 0; i < usages.length; i++){
                        String machine, status;
                        status = usages[i] ? "FREE" : "Unavailable";
                        machine = (i < 4) ? "Dryer" : "Washer";
                        machinesList.get(i).setText(machine + " " + (i+1) + ": " + status);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}

package com.example.carrolllaundry;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.Clock;
import java.time.Duration;

public class MachineView extends AppCompatActivity {
    ArrayList<Machine> machinesList = new ArrayList<>();
    SharedPreferences.Editor editor;
    boolean[] localMachineUses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_view);


        Button refreshButton = findViewById(R.id.refreshButton);;
        TextView dryer1TextView = findViewById(R.id.dryer1TextView);
        TextView dryer2TextView = findViewById(R.id.dryer2TextView);
        TextView dryer3TextView = findViewById(R.id.dryer3TextView);
        TextView washer4TextView = findViewById(R.id.washer4TextView);
        TextView washer5TextView = findViewById(R.id.washer5TextView);
        TextView washer6TextView = findViewById(R.id.washer6TextView);
        Button dryer1Button = findViewById(R.id.dryer1Button);
        Button dryer2Button= findViewById(R.id.dryer2Button);
        Button dryer3Button = findViewById(R.id.dryer3Button);
        Button washer4Button = findViewById(R.id.washer4Button);
        Button washer5Button = findViewById(R.id.washer5Button);
        Button washer6Button = findViewById(R.id.washer6Button);
        Machine dryer1 = new Machine("Dryer 1", "Available", dryer1Button, dryer1TextView);
        Machine dryer2 = new Machine("Dryer 2", "Available", dryer2Button, dryer2TextView);
        Machine dryer3 = new Machine("Dryer 3", "Available", dryer3Button, dryer3TextView);
        Machine washer4 = new Machine("Washer 4", "Available", washer4Button, washer4TextView);
        Machine washer5 = new Machine("Washer 5", "Available", washer5Button, washer5TextView);
        Machine washer6 = new Machine("Washer 6", "Available", washer6Button, washer6TextView);
        machinesList.add(dryer1);
        machinesList.add(dryer2);
        machinesList.add(dryer3);
        machinesList.add(washer4);
        machinesList.add(washer5);
        machinesList.add(washer6);
        SharedPreferences localUsedMachines = getApplicationContext().getSharedPreferences("PREFS_NAME", 0);
        localMachineUses = new boolean[6];
        for (int i = 0; i < localMachineUses.length; i++){
            localMachineUses[i] = localUsedMachines.getBoolean(machinesList.get(i).getName(), false);
            if (localMachineUses[i]){
                machinesList.get(i).setStatus("Running");
            }
        }
        editor = localUsedMachines.edit();
        for (final Machine m: machinesList)
            m.getButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (m.getStatus().equals("Available"))
                        startDialog(m);
                }
            });
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*
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
                }*/
                try{
                    machinesList.get(0).setStatus(SheetsData.getFirst());
                }catch (IOException e){
                    e.printStackTrace();
                    machinesList.get(0).setStatus("Burger" + e.getMessage());
                }
            }
        });
    }
    private void startDialog(final Machine machine) {
        final Dialog dialog = new Dialog(MachineView.this);
        if (machine.isWasher())
            dialog.setContentView(R.layout.use_washer);
        else
            dialog.setContentView(R.layout.use_dryer);
        dialog.setTitle(R.string.machine_button);
        final Button startBtn = dialog.findViewById(R.id.button);
        final RadioGroup radioGroup = dialog.findViewById(R.id.radioGroup);
        TextView title = dialog.findViewById(R.id.textView);
        title.setText("Use " + machine.getName());
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup.getCheckedRadioButtonId() != -1){
                    SimpleDateFormat s= new SimpleDateFormat("HH:mm");
                    Date finishTime = new Date();
                    machine.getButton().setText(s.format(new Date()));
                    machine.setStatus("Running");
                    editor.putBoolean(machine.getName(), true);
                    editor.apply();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
}

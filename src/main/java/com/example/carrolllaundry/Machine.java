package com.example.carrolllaundry;

import android.widget.Button;
import android.widget.TextView;

public class Machine {
    private String name;
    private String status;
    private Button button;
    private TextView textView;
    private boolean isWasher;

    public void setStatus(String status) {
        this.status = status;
        this.textView.setText(this.getName() + ": " + status);
        if (status.equals("Running")) {
            this.getButton().setClickable(false);
        }
        else if (status.equals("Available")) {
            this.getButton().setClickable(true);
        }
        else if (status.equals("In Use")) {
            this.getButton().setClickable(false);
        }

    }
    public boolean isWasher(){
        return isWasher;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Button getButton() {
        return button;
    }

    public Machine(String name, String status, Button button, TextView textView){
        this.name = name;
        this.status = status;
        this.button = button;
        this.textView = textView;
        this.isWasher = name.substring(0, 6).compareTo("Washer") == 0;
    }
}

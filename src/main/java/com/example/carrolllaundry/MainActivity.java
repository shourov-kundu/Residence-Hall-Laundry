package com.example.carrolllaundry;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();

        SharedPreferences settings = getApplicationContext().getSharedPreferences("PREFS_NAME", 0);
        boolean loginStatus = settings.getBoolean("loggedIn", false);
        if (loginStatus){
            startActivity(new Intent(MainActivity.this, MachineView.class));
        }

    }

    public void addListenerOnButton(){
        Button loginButton = findViewById(R.id.loginButton);
        final EditText passwordText = findViewById(R.id.passwordText);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordText.getText().toString().equals("")){/*REDACTED FOR GITHUB*/
                    SharedPreferences settings = getApplicationContext().getSharedPreferences("PREFS_NAME", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("loggedIn", true);
                    editor.apply();
                    startActivity(new Intent(MainActivity.this, MachineView.class));
                }
            }
        });
    }
}

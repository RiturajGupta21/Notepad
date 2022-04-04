package com.example.notepad;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends Activity {
    Button button_New, button_Save, button_Open;
    EditText text_Writeup;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_New = findViewById(R.id.button_New);
        button_Save = findViewById(R.id.button_Save);
        button_Open = findViewById(R.id.button_Open);
        text_Writeup = findViewById(R.id.text_Writeup);
    }
    public void onclick(View v) {
        final EditText name=new EditText(this);
        AlertDialog.Builder a=new AlertDialog.Builder(this);
        a.setView(name);
        if (v.getId() == R.id.button_Save) {
            a.setMessage("save file");
            a.setPositiveButton("save", (dialog, which) -> {
                try {
                    FileOutputStream f=openFileOutput(name.getText().toString()+".txt",MODE_APPEND);
                    f.write(text_Writeup.getText().toString().getBytes());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error "+e,Toast.LENGTH_LONG).show();
                }
            });
            a.setNegativeButton("cancel", (dialog, which) -> dialog.cancel());
            a.show();
        }
        if(v.getId()==R.id.button_Open) {
            a.setMessage("open file");
            a.setPositiveButton("open", (dialog, which) -> {
                int c;
                text_Writeup.setText("");
                try {
                    FileInputStream f = openFileInput(name.getText().toString()+".txt");
                    while ((c = f.read()) != -1)
                    {
                        text_Writeup.setText(String.format("%s%s", text_Writeup.getText().toString(), (char) c));
                    }
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error "+e,Toast.LENGTH_LONG).show();
                }
            });
            a.setNegativeButton("cancel", (dialog, which) -> dialog.cancel());
            a.show();
        }
        if(v.getId()==R.id.button_New) {
            text_Writeup.setText("");
        }
    }
}
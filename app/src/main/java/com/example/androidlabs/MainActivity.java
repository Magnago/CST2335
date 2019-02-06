package com.example.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.file.Files;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private Button Login;
    public SharedPreferences sp;
    private EditText Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab3);

        sp = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        Email = findViewById(R.id.Email);

        Login = (Button) findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondActivity();
            }
        });

        loadData();
    }

    public void openSecondActivity() {
        Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);
        nextPage.putExtra(SHARED_PREFS, Email.getText().toString());
        startActivity(nextPage);
    }

    public void saveData() {
        //save what was typed under the name "text"
        String whatWasTyped = Email.getText().toString();
        //get an editor object
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(TEXT, whatWasTyped);
        //write it to disk:
        editor.commit();
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }

   public void loadData() {
        String savedString = sp.getString(TEXT, "");
        Email.setText(savedString);
   }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }
}

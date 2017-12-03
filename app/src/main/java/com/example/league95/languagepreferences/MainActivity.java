package com.example.league95.languagepreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private String lang = "Language";
    SharedPreferences sharedPreferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Need menu iflater
        MenuInflater menuInflater = getMenuInflater();
        /*Link our xml with this menu*/
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Of course none of the items will work, unless we add a listener.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.spa:
                setTextView("Spanish");
                return true;
            case R.id.eng:
                setTextView("English");
                return true;
            default:
                return false;
        }
    }

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        sharedPreferences = this.getSharedPreferences("com.example.league95.languagepreferences", Context.MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "");
        setTextView(language);
    }

    public void pickLanguage(View v) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.stat_sys_headset)
                .setMessage("Pick between English and Spanish")
                .setTitle("Change Language")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setTextView("English");
                    }
                })
                .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setTextView("Spanish");
                    }
                })
                .show();

    }

    public void setTextView(String text) {
        sharedPreferences = this.getSharedPreferences("com.example.league95.languagepreferences", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("language", text).apply();
        lang = text;
        textView.setText(lang);
    }


}

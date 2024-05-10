package com.example.library_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateActivity extends AppCompatActivity {
    EditText title_input, publisher_input;
    Button update_button, delete_button;

    String id, title, publisher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        publisher_input = findViewById(R.id.publisher_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                publisher = publisher_input.getText().toString().trim();
                myDB.updateData(id, title, publisher);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            publisher = getIntent().getStringExtra("publisher");


            //Setting Intent Data
            title_input.setText(title);
            publisher_input.setText(publisher);
            Log.d("sachi", title + " " + publisher);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
        void confirmDialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete " + title + " ?");
            builder.setMessage("Are you sure you want to delete " + title + " ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                    myDB.deleteOneRow(id);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();
    }
}
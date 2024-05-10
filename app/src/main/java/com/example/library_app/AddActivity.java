package com.example.library_app;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

    public class AddActivity extends AppCompatActivity {

        EditText title_input, publisher_input;
        Button add_button;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add);

            title_input = findViewById(R.id.title_input);
            publisher_input = findViewById(R.id.publisher_input);
            add_button = findViewById(R.id.add_button);
            add_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                    myDB.addBook(title_input.getText().toString().trim(),
                            publisher_input.getText().toString().trim());
                }
            });
            
    }
}
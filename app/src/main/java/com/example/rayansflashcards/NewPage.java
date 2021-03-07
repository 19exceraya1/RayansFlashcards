package com.example.rayansflashcards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class NewPage extends AppCompatActivity {
    String userQuestion;
    String userAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_page);

        ImageView cancelIcon = findViewById(R.id.cancel_button);
        ImageView saveIcon = findViewById(R.id.save_icon);


        saveIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userQuestion = ((EditText)findViewById(R.id.questionInput)).getText().toString();
                userAnswer = ((EditText) findViewById(R.id.answerInput)).getText().toString();
                Intent data = new Intent(); // create a new Intent, this is where we will put our data
                data.putExtra("userQuestion", userQuestion); // puts one string into the Intent, with the key as 'string1'
                data.putExtra("userAnswer", userAnswer); // puts another string into the Intent, with the key as 'string2
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes this activity and pass data to the original activity that launched this activity
            }
        });

        cancelIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
    });
    }
}
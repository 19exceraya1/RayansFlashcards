package com.example.rayansflashcards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCard extends AppCompatActivity {
    String returningUserQuestion;
    String returningUserAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_page);

        EditText questionInput = findViewById(R.id.questionInput);
        EditText answerInput = findViewById(R.id.answerInput);
        EditText wrongOption1 = findViewById(R.id.wrongOption1);
        EditText wrongOption2 = findViewById(R.id.wrongOption2);
        ImageView cancelIcon = findViewById(R.id.cancel_button);
        ImageView saveIcon = findViewById(R.id.save_icon);

        questionInput.setText(getIntent().getStringExtra("userQuestion"));
        answerInput.setText(getIntent().getStringExtra("userAnswer"));



        saveIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String returningUserQuestion =  questionInput.getText().toString();
                String returningUserAnswer = answerInput.getText().toString();

                if(questionInput.getText().toString().trim().length() == 0 || answerInput.getText().toString().trim().length() == 0 || wrongOption1.getText().toString().trim().length() == 0 || wrongOption2.getText().toString().trim().length() == 0){
                    Toast.makeText(getApplicationContext(), "Question and Answer cannot be blank", Toast.LENGTH_SHORT).show();
                }else{
                    Intent data = new Intent(); // create a new Intent, this is where we will put our data
                    data.putExtra("returningUserQuestion", returningUserQuestion); // puts one string into the Intent, with the key as 'string1'
                    data.putExtra("returningUserAnswer", returningUserAnswer); // puts another string into the Intent, with the key as 'string2
                    data.putExtra("wrongOption1", wrongOption1.getText().toString()); // puts one string into the Intent, with the key as 'string1'
                    data.putExtra("wrongOption2", wrongOption2.getText().toString());
                    setResult(RESULT_OK, data); // set result code and bundle data for response
                    finish(); // closes this activity and pass data to the original activity that launched this activity

                }
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
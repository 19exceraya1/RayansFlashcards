package com.example.rayansflashcards;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView questionTextView = findViewById(R.id.flashcard_question);
        TextView answerTextView = findViewById(R.id.flashcard_answer);
        TextView answer1 = findViewById(R.id.Answer1);
        TextView answer2 = findViewById(R.id.Answer2);
        TextView answer3 = findViewById(R.id.Answer3);
        TextView reset = findViewById(R.id.reset);
        ImageView invisible = findViewById(R.id.invisible_button);
        ImageView visible = findViewById(R.id.visible_button);



        questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionTextView.setVisibility(View.INVISIBLE);
                answerTextView.setVisibility(View.VISIBLE);
            }
        });

        answerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerTextView.setVisibility(View.INVISIBLE);
                questionTextView.setVisibility(View.VISIBLE);
            }

        });

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer1.setBackgroundColor(Color.parseColor("#66f542"));
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer1.setBackgroundColor(Color.parseColor("#66f542"));
                answer2.setBackgroundColor(Color.parseColor("#e62200"));

            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer1.setBackgroundColor(Color.parseColor("#66f542"));
                answer3.setBackgroundColor(Color.parseColor("#e62200"));

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer1.setBackgroundColor(Color.parseColor("#AEFFF29A"));
                answer2.setBackgroundColor(Color.parseColor("#AEFFF29A"));
                answer3.setBackgroundColor(Color.parseColor("#AEFFF29A"));

            }

        });

        invisible.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                answer1.setVisibility(View.INVISIBLE);
                answer2.setVisibility(View.INVISIBLE);
                answer3.setVisibility(View.INVISIBLE);
                invisible.setVisibility(View.INVISIBLE);
                visible.setVisibility(View.VISIBLE);

            }
        });

        visible.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                answer1.setVisibility(View.VISIBLE);
                answer2.setVisibility(View.VISIBLE);
                answer3.setVisibility(View.VISIBLE);
                invisible.setVisibility(View.VISIBLE);
                visible.setVisibility(View.INVISIBLE);

            }
        });

    }
}
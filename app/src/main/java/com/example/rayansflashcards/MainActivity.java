package com.example.rayansflashcards;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.yourpackage.packagenamehere.Flashcard;
import com.yourpackage.packagenamehere.FlashcardDatabase;

import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        TextView questionTextView = findViewById(R.id.flashcard_question);
        TextView answerTextView = findViewById(R.id.flashcard_answer);
        TextView answer1 = findViewById(R.id.Answer1);
        TextView answer2 = findViewById(R.id.Answer2);
        TextView answer3 = findViewById(R.id.Answer3);
        TextView reset = findViewById(R.id.reset);
        ImageView invisible = findViewById(R.id.invisible_button);
        ImageView visible = findViewById(R.id.visible_button);
        ImageView newPageIcon = findViewById(R.id.newpage);
        ImageView editIcon= findViewById(R.id.edit);
        Flashcard currentCard;

        ListIterator<Flashcard> flashcardListIterator = allFlashcards.listIterator();

        if(flashcardListIterator.hasNext()){
            currentCard = flashcardListIterator.next();
            questionTextView.setText(currentCard.getQuestion());
            answerTextView.setText(currentCard.getAnswer());
            answer1.setText(currentCard.getAnswer());
            answer2.setText(currentCard.getWrongAnswer1());
            answer3.setText(currentCard.getWrongAnswer2());
        }

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

        newPageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCard.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCard.class);
                intent.putExtra("userQuestion", questionTextView.getText());
                intent.putExtra("userAnswer", answerTextView.getText());
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Snackbar.make(findViewById(R.id.flashcard_question),
                "Card successfully created",
                Snackbar.LENGTH_SHORT)
                .show();

        super.onActivityResult(requestCode, resultCode, data);
        setContentView(R.layout.activity_main);

        String userQuestion, userAnswer, wrongOption1, wrongOption2;

        TextView questionTextView = findViewById(R.id.flashcard_question);
        TextView answerTextView = findViewById(R.id.flashcard_answer);
        TextView answer1 = findViewById(R.id.Answer1);
        TextView answer2 = findViewById(R.id.Answer2);
        TextView answer3 = findViewById(R.id.Answer3);
        TextView reset = findViewById(R.id.reset);
        ImageView invisible = findViewById(R.id.invisible_button);
        ImageView visible = findViewById(R.id.visible_button);
        ImageView newPageIcon = findViewById(R.id.newpage);
        ImageView editIcon= findViewById(R.id.edit);

        if (requestCode == 100 && resultCode == RESULT_OK) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            userQuestion = data.getExtras().getString("returningUserQuestion");
            userAnswer = data.getExtras().getString("returningUserAnswer");
            wrongOption1 = data.getExtras().getString("wrongOption1");
            wrongOption2 = data.getExtras().getString("wrongOption2");
        } else {
            userQuestion = "";
            userAnswer = "";
            wrongOption1 = "";
            wrongOption2 = "";
        }


        flashcardDatabase.insertCard(new Flashcard(userQuestion, userAnswer, wrongOption1, wrongOption2));
        allFlashcards = flashcardDatabase.getAllCards();
        questionTextView.setText(userQuestion);
        answerTextView.setText(userAnswer);
        answer1.setText(wrongOption1);
        answer2.setText(wrongOption2);
        answer3.setText(userAnswer);


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
                answer1.setBackgroundColor(Color.parseColor("#e62200"));
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer3.setBackgroundColor(Color.parseColor("#66f542"));
                answer2.setBackgroundColor(Color.parseColor("#e62200"));

            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer3.setBackgroundColor(Color.parseColor("#66f542"));

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

        editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCard.class);
                intent.putExtra("userQuestion", questionTextView.getText());
                intent.putExtra("userAnswer", answerTextView.getText());
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        newPageIcon.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View v){
                Intent intent = new Intent(MainActivity.this, AddCard.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
            });
        }
}
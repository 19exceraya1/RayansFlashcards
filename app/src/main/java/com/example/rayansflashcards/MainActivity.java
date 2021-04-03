package com.example.rayansflashcards;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    Flashcard currentCard;

    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();
        ListIterator<Flashcard> flashcardListIterator = allFlashcards.listIterator();
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
                int cx = answerTextView.getWidth() / 2;
                int cy = answerTextView.getHeight() / 2;
                float finalRadius = (float) Math.hypot(cx, cy);
                Animator anim = ViewAnimationUtils.createCircularReveal(answerTextView, cx, cy, 0f, finalRadius);
                questionTextView.setVisibility(View.INVISIBLE);
                answerTextView.setVisibility(View.VISIBLE);

                anim.setDuration(250);
                anim.start();
            }
        });

        answerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cx = answerTextView.getWidth() / 2;
                int cy = answerTextView.getHeight() / 2;
                float finalRadius = (float) Math.hypot(cx, cy);
                Animator anim = ViewAnimationUtils.createCircularReveal(questionTextView, cx, cy, 0f, finalRadius);
                answerTextView.setVisibility(View.INVISIBLE);
                questionTextView.setVisibility(View.VISIBLE);
                anim.setDuration(250);
                anim.start();
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
                overridePendingTransition(R.anim.righin, R.anim.leftout);
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
        ImageView nextIcon = findViewById(R.id.next_icon);

        ListIterator<Flashcard> flashcardListIterator = allFlashcards.listIterator();



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
                int cx = answerTextView.getWidth() / 2;
                int cy = answerTextView.getHeight() / 2;
                float finalRadius = (float) Math.hypot(cx, cy);
                Animator anim = ViewAnimationUtils.createCircularReveal(answerTextView, cx, cy, 0f, finalRadius);
                questionTextView.setVisibility(View.INVISIBLE);
                answerTextView.setVisibility(View.VISIBLE);

                anim.setDuration(250);
                anim.start();
            }
        });

        answerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cx = answerTextView.getWidth() / 2;
                int cy = answerTextView.getHeight() / 2;
                float finalRadius = (float) Math.hypot(cx, cy);
                Animator anim = ViewAnimationUtils.createCircularReveal(questionTextView, cx, cy, 0f, finalRadius);
                answerTextView.setVisibility(View.INVISIBLE);
                questionTextView.setVisibility(View.VISIBLE);
                anim.setDuration(250);
                anim.start();
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

        nextIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.leftout);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.righin);

                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts
                        findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // this method is called when the animation is finished playing
                        findViewById(R.id.flashcard_question).startAnimation(rightInAnim);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });

                if (flashcardListIterator.hasNext()) {
                    findViewById(R.id.flashcard_question).startAnimation(rightInAnim);
                    findViewById(R.id.Answer1).startAnimation(rightInAnim);
                    findViewById(R.id.Answer2).startAnimation(rightInAnim);
                    findViewById(R.id.Answer3).startAnimation(rightInAnim);
                    currentCard = flashcardListIterator.next();
                    questionTextView.setText(currentCard.getQuestion());
                    answerTextView.setText(currentCard.getAnswer());
                    answer1.setText(currentCard.getAnswer());
                    answer2.setText(currentCard.getWrongAnswer1());
                    answer3.setText(currentCard.getWrongAnswer2());
                }else if(!allFlashcards.isEmpty()){
                    ListIterator<Flashcard> flashcardListIterator = allFlashcards.listIterator();
                    currentCard = flashcardListIterator.next();
                    findViewById(R.id.flashcard_question).startAnimation(rightInAnim);
                    questionTextView.setText(currentCard.getQuestion());
                    answerTextView.setText(currentCard.getAnswer());
                    answer1.setText(currentCard.getAnswer());
                    answer2.setText(currentCard.getWrongAnswer1());
                    answer3.setText(currentCard.getWrongAnswer2());
                }
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

package com.yourpackage.packagenamehere;

import android.content.Context;

import androidx.room.Room;

import com.example.rayansflashcards.AppDatabase;

import java.util.List;

public class FlashcardDatabase {
    private final AppDatabase db;

    public FlashcardDatabase(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "flashcard-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public void initFirstCard() {
        if (db.flashcardDao().getAll().isEmpty()) {
            insertCard(new com.yourpackage.packagenamehere.Flashcard("Who is the 44th President of the United States", "Barack Obama"));
        }
    }

    public List<com.yourpackage.packagenamehere.Flashcard> getAllCards() {
        return db.flashcardDao().getAll();
    }

    public void insertCard(com.yourpackage.packagenamehere.Flashcard flashcard) {
        db.flashcardDao().insertAll(flashcard);
    }

    public void deleteCard(String flashcardQuestion) {
        List<com.yourpackage.packagenamehere.Flashcard> allCards = db.flashcardDao().getAll();
        for (com.yourpackage.packagenamehere.Flashcard f : allCards) {
            if (f.getQuestion().equals(flashcardQuestion)) {
                db.flashcardDao().delete(f);
            }
        }
    }

    public void updateCard(com.yourpackage.packagenamehere.Flashcard flashcard) {
        db.flashcardDao().update(flashcard);
    }

    public void deleteAll() {
        for (com.yourpackage.packagenamehere.Flashcard f : db.flashcardDao().getAll()) {
            db.flashcardDao().delete(f);
        }
    }
}

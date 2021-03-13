package com.example.rayansflashcards;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {com.yourpackage.packagenamehere.Flashcard.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract com.yourpackage.packagenamehere.FlashcardDao flashcardDao();
}

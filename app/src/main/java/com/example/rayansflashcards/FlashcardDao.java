package com.yourpackage.packagenamehere;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FlashcardDao {
    @Query("SELECT * FROM flashcard")
    List<com.yourpackage.packagenamehere.Flashcard> getAll();

    @Insert
    void insertAll(com.yourpackage.packagenamehere.Flashcard... flashcards);

    @Delete
    void delete(com.yourpackage.packagenamehere.Flashcard flashcard);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(com.yourpackage.packagenamehere.Flashcard flashcard);
}

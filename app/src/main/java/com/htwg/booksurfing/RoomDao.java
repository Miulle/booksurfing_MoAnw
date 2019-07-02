//package com.htwg.booksurfing;
//
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Delete;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.OnConflictStrategy;
//import android.arch.persistence.room.Query;
//import android.arch.persistence.room.Update;
//
//@Dao
//public interface RoomDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertBook(DatabaseHelperRoom book);
//
//    @Update
//    void updateBook(DatabaseHelperRoom book);
//
//    @Delete
//    void deleteBook(DatabaseHelperRoom book);
//
//    @Query("SELECT * from books")
//    Book[] loadAllBooks();
//}

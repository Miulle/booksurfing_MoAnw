package com.htwg.booksurfing;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {DatabaseHelperRoom.class}, version = 1)
public abstract class BookDatabase extends RoomDatabase {
    public abstract RoomDao bookDao();
}

package kpencak.teachme.dictionary;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DictionaryItem.class}, version = 1)
public abstract class DictionaryDatabase extends RoomDatabase {
    public static DictionaryDatabase INSTANCE;

    public abstract DictionaryItemDao dictionaryItemDao();

    private static final Object sLock = new Object();

    public static DictionaryDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room
                        .databaseBuilder(context.getApplicationContext(), DictionaryDatabase.class, "dictionary.db")
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
    }
}

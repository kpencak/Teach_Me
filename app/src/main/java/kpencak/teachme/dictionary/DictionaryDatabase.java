package kpencak.teachme.dictionary;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;

@Database(entities = {DictionaryItem.class}, version = 1)
public abstract class DictionaryDatabase extends RoomDatabase {
    public static DictionaryDatabase INSTANCE;

    public abstract DictionaryItemDao dictionaryItemDao();

    private static final Object sLock = new Object();

//    static final Migration FROM_1_TO_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL(
//                    "CREATE TABLE IF NOT EXISTS data_table_new (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT, `description` TEXT, `flashcards_level` INTEGER NOT NULL)");
//            // Copy the data
//            database.execSQL(
//                    "INSERT INTO data_table_new SELECT * FROM dictionary");
//            // Remove the old table
//            database.execSQL("DROP TABLE dictionary");
//            // Change the table name to the correct one
//            database.execSQL("ALTER TABLE data_table_new RENAME TO dictionary");
//        }
//    };

    public static DictionaryDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room
                        .databaseBuilder(context.getApplicationContext(), DictionaryDatabase.class, "dictionary.db")
//                        .addMigrations(FROM_1_TO_2)
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
    }
}

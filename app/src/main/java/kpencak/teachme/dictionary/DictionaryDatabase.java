package kpencak.teachme.dictionary;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;

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

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
//            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final DictionaryItemDao mDao;
        ArrayList<DictionaryItem> items = new ArrayList<>(Arrays.asList(
                new DictionaryItem("Komuna Paryska", "zryw rewolucyjny ludności Paryża. Trwała od 18 marca do 28 maja 1871 roku. W zależności od punktu widzenia uważana za ostatnią rewolucję romantyczną, przez anarchistów za nie do końca konsekwentną próbę likwidacji państwa[6], a przez marksistów za przykład dyktatury proletariatu"),
                new DictionaryItem("Paryż", " stolica i największe miasto Francji, położone w centrum Basenu Paryskiego, nad Sekwaną (La Seine). Miasto stanowi centrum polityczne, ekonomiczne i kulturalne kraju."),
                new DictionaryItem("Stolica", " miasto, w którym znajduje się siedziba centralnych organów państwa lub główne miasto okręgu, rejonu, województwa, powiatu, krainy historycznej itp. "),
                new DictionaryItem("Miasto", "historycznie ukształtowana jednostka osadnicza charakteryzująca się dużą intensywnością zabudowy, małą ilością terenów rolniczych, ludnością pracującą poza rolnictwem (w przemyśle lub w usługach) prowadzącą miejski styl życia. ")
        ));

        PopulateDbAsync(DictionaryDatabase db) {
            mDao = db.dictionaryItemDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            if (mDao.getAnyItem().size() == 0) {
                for (int i = 0; i <= items.size(); i++) {
                    DictionaryItem item = items.get(i);
                    mDao.insert(item);
                }
            }
            return null;
        }
    }
}

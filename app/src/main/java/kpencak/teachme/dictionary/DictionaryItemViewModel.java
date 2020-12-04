package kpencak.teachme.dictionary;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DictionaryItemViewModel extends AndroidViewModel {
    private DictionaryItemDao dictionaryItemDao;
    private ExecutorService executorService;

    public DictionaryItemViewModel(@NonNull Application application) {
        super(application);
        dictionaryItemDao = DictionaryDatabase.getInstance(application).dictionaryItemDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    LiveData<List<DictionaryItem>> getAllDictionary() {
        return dictionaryItemDao.getAll();
    }

    void insertDictionaryItem(DictionaryItem dictionaryItem) {
        executorService.execute(() -> dictionaryItemDao.insert(dictionaryItem));
    }

    void deleteDictionaryItem(DictionaryItem dictionaryItem) {
        executorService.execute(() -> dictionaryItemDao.delete(dictionaryItem));
    }

    void deleteAllDictionary() {
        executorService.execute(() -> dictionaryItemDao.deleteAll());
    }
}

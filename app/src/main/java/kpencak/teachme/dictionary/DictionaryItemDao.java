package kpencak.teachme.dictionary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DictionaryItemDao {
    @Query("SELECT * FROM dictionary")
    LiveData<List<DictionaryItem>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DictionaryItem item);

    @Delete
    void delete(DictionaryItem item);

    @Query("DELETE FROM dictionary")
    void deleteAll();

    @Query("SELECT * FROM dictionary LIMIT 1")
    List<DictionaryItem> getAnyItem();
}

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

    @Query("SELECT * FROM dictionary")
    List<DictionaryItem> getAllList();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DictionaryItem item);

    @Delete
    void delete(DictionaryItem item);

    @Query("DELETE FROM dictionary")
    void deleteAll();

    @Query("SELECT * FROM dictionary LIMIT 1")
    List<DictionaryItem> getAnyItem();

    @Query("SELECT * FROM dictionary WHERE flashcards_level = :partition_no")
    List<DictionaryItem> getItemsForPartition(int partition_no);

    @Query("UPDATE dictionary SET flashcards_level = :new_partition_no WHERE name = :name")
    void updateItem(int new_partition_no, String name);

    @Query("SELECT * FROM dictionary ORDER BY RANDOM() LIMIT :numberOfItems")
    List<DictionaryItem> getRandItems(int numberOfItems);

    @Query("UPDATE dictionary SET flashcards_level = 1")
    void resetPartitions();
}

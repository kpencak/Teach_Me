package kpencak.teachme.dictionary;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dictionary")
public class DictionaryItem {
    public void setId(Integer id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "flashcards_level")
    private int flashcards_level;

    public DictionaryItem(String name, String description, int flashcards_level) {
        this.name = name;
        this.description = description;
        this.flashcards_level = flashcards_level;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFlashcards_level() {
        return flashcards_level;
    }

    public void setFlashcards_level(int flashcards_level) {
        this.flashcards_level = flashcards_level;
    }
}

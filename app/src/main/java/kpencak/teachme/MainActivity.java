package kpencak.teachme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kpencak.teachme.dictionary.DictionaryDatabase;
import kpencak.teachme.dictionary.ShowDictionaryActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DictionaryDatabase db = Room.databaseBuilder(getApplicationContext(), DictionaryDatabase.class, "dictionary").build();
    }

    public void goToDictionary(View view) {
        Intent intent = new Intent(this, ShowDictionaryActivity.class);
        startActivity(intent);
    }

    public void goToLearnOptions(View view) {
        Intent intent = new Intent(this, ShowLearnOptionsActivity.class);
        startActivity(intent);
    }
}
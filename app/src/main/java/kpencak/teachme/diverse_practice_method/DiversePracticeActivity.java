package kpencak.teachme.diverse_practice_method;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import kpencak.teachme.R;
import kpencak.teachme.dictionary.DictionaryItem;
import kpencak.teachme.dictionary.DictionaryItemViewModel;

public class DiversePracticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diverse_practice);

        Button startLearning = findViewById(R.id.start_learning);

        startLearning.setOnClickListener(view -> {
            Intent intent = new Intent(this, ShowItemActivity.class);
            EditText itemsToRepeat = findViewById(R.id.items_to_repeat);
            String numberOfItemsStr = itemsToRepeat.getText().toString();
            int numberOfItems = Integer.parseInt(numberOfItemsStr);
            DictionaryItemViewModel dictionaryViewModel = new ViewModelProvider(this).get(DictionaryItemViewModel.class);
            List<DictionaryItem> dictionary = dictionaryViewModel.getAllList();
            if (numberOfItems < 1)
                Toast.makeText(this, "Liczba elementów musi być dodatnia", Toast.LENGTH_SHORT).show();
            else if (dictionary.size() < numberOfItems)
                Toast.makeText(this, "W bazie jest mniej elementów niż podana liczba", Toast.LENGTH_LONG).show();
            else {
                intent.putExtra("numberOfItems", numberOfItems);
                startActivity(intent);
            }
        });
    }
}
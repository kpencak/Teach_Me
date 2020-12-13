package kpencak.teachme.diverse_practice_method;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.ListIterator;

import kpencak.teachme.R;
import kpencak.teachme.dictionary.DictionaryItem;
import kpencak.teachme.dictionary.DictionaryItemViewModel;

public class ShowItemActivity extends AppCompatActivity {

    int numberOfItems;
    DictionaryItem dictionaryItem;
    ListIterator<DictionaryItem> dictionaryItemIterator;
    List<DictionaryItem> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            numberOfItems = extras.getInt("numberOfItems");
        else
            numberOfItems = 10;

        try {
            DictionaryItemViewModel dictionaryViewModel = new ViewModelProvider(this).get(DictionaryItemViewModel.class);
            dictionary = dictionaryViewModel.getRandomItems(numberOfItems);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dictionaryItemIterator = dictionary.listIterator(0);

        TextView name = findViewById(R.id.item_name);
        TextView desc = findViewById(R.id.item_description);
        Button next = findViewById(R.id.btn_next);
        Button prev = findViewById(R.id.btn_prev);

        dictionaryItem = dictionaryItemIterator.next();
        name.setText(dictionaryItem.getName());
        desc.setText(dictionaryItem.getDescription());

        if (dictionaryItemIterator.hasNext()) {
            next.setEnabled(true);
        }

        next.setOnClickListener(view -> {
            dictionaryItem = dictionaryItemIterator.next();
            name.setText(dictionaryItem.getName());
            desc.setText(dictionaryItem.getDescription());
            prev.setEnabled(true);

            if (!dictionaryItemIterator.hasNext()) {
                next.setEnabled(false);
            }

        });

        prev.setOnClickListener(view -> {
            dictionaryItem = dictionaryItemIterator.previous();
            name.setText(dictionaryItem.getName());
            desc.setText(dictionaryItem.getDescription());
            next.setEnabled(true);

            if (!dictionaryItemIterator.hasPrevious()) {
                prev.setEnabled(false);
            }
        });
    }
}
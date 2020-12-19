package kpencak.teachme.flashcards_method;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import kpencak.teachme.dictionary.DictionaryItem;
import kpencak.teachme.dictionary.DictionaryItemViewModel;

import kpencak.teachme.R;

public class PartitionActivity extends AppCompatActivity {
    private DictionaryItemViewModel dictionaryViewModel;

    DictionaryItem dictionaryItem;
    int dictionaryItemId;
    List<DictionaryItem> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partition);

        int partition_no = getIntent().getExtras().getInt("partition_no");
        TextView text_inside_card = findViewById(R.id.text_inside_card);
        TextView partition_name = findViewById(R.id.partition_name);
        CardView cardView = findViewById(R.id.card_view);
        Button good_answer = findViewById(R.id.good_answer);
        Button bad_answer = findViewById(R.id.bad_answer);

        String name = "Przegródka " + partition_no;
        partition_name.setText(name);

        try {
            dictionaryViewModel = new ViewModelProvider(this).get(DictionaryItemViewModel.class);
            dictionary = dictionaryViewModel.getAllFromPartition(partition_no);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (savedInstanceState == null) {
            getNextItem(text_inside_card, dictionary);
        }

        cardView.setOnClickListener(view -> {
            if (!text_inside_card.getText().equals("Wszystko z tej przegródki powtórzone.")) {
                text_inside_card.setText(dictionaryItem.getName());
                good_answer.setEnabled(true);
                bad_answer.setEnabled(true);
            }
        });

        good_answer.setOnClickListener(view -> {
            if (partition_no < 5) {
                try {
                    int newPartitionInt = partition_no + 1;
                    dictionaryViewModel.updateItem(newPartitionInt, dictionaryItem.getName());
                    synchronized (dictionaryViewModel) {
                        dictionaryViewModel.notifyAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            getNextItem(text_inside_card, dictionary);

            good_answer.setEnabled(false);
            bad_answer.setEnabled(false);
        });

        bad_answer.setOnClickListener(view -> {
            getNextItem(text_inside_card, dictionary);

            good_answer.setEnabled(false);
            bad_answer.setEnabled(false);
        });
    }

    private void getNextItem(TextView text_inside_card, List<DictionaryItem> dictionary) {
        if (dictionary.size() != 0) {
            dictionaryItemId = getRandomElement(dictionary);
            dictionaryItem = dictionary.get(dictionaryItemId);
            dictionary.remove(dictionaryItemId);
            text_inside_card.setText(dictionaryItem.getDescription());
        } else {
            text_inside_card.setText("Wszystko z tej przegródki powtórzone.");
        }
    }

    private int getRandomElement(List<DictionaryItem> list) {
        Random random = new Random();
        return random.nextInt(list.size());
    }
}
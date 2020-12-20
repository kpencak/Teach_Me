package kpencak.teachme.repeating_method;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;

import kpencak.teachme.R;
import kpencak.teachme.dictionary.DictionaryItem;
import kpencak.teachme.dictionary.DictionaryItemViewModel;

public class TestActivity extends AppCompatActivity {

    private static final int SUMMARY_TEST_ACTIVITY_REQUEST_CODE = 1;

    List<DictionaryItem> dictionary;
    ArrayList<Integer> questionsIds = new ArrayList<>();
    ArrayList<Integer> userAnswers = new ArrayList<>();
    ListIterator<Integer> questionsIterator;
    Random random = new Random();
    DictionaryItem correctItem;
    int repeating_size = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Get dictionary for test
        getDictionary();
        if (dictionary.size() < 10) {
            Toast.makeText(this, "Za mało pojęć do przeprowadzenia testu.", Toast.LENGTH_LONG).show();
            finish();
        }

        getQuestions();

        // Add buttons for test page
        Bundle extras = getIntent().getExtras();
        int numberOfAnswers = extras.getInt("numberOfAnswers");

        addButtons(numberOfAnswers);

        questionsIterator = questionsIds.listIterator(0);
        try {
            correctItem = setQuestionOnPage(questionsIterator, numberOfAnswers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button next_btn = findViewById(R.id.btn_next);
        next_btn.setOnClickListener(view -> {
            RadioGroup radioGroup = findViewById(R.id.radioGroupAnswers);
            int selectedButtonId = radioGroup.getCheckedRadioButtonId();
            if (selectedButtonId == -1)
                Toast.makeText(this, "Proszę wybrać odpowiedź", Toast.LENGTH_SHORT).show();
            else {
                checkAnswer(selectedButtonId, correctItem);
                radioGroup.clearCheck();

                if (next_btn.getText().equals("Zakończ test")) {
                    Intent intent = new Intent(this, SummaryTestActivity.class);
                    intent.putExtra("userAnswers", userAnswers);
                    intent.putExtra("max_points", repeating_size);
                    startActivityForResult(intent, SUMMARY_TEST_ACTIVITY_REQUEST_CODE);
                } else {
                    if (questionsIterator.nextIndex() == 9) {
                        next_btn.setText(R.string.end_test);
                    }
                    try {
                        correctItem = setQuestionOnPage(questionsIterator, numberOfAnswers);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        finish();
    }

    private void checkAnswer(int selectedButtonId, DictionaryItem correctItem) {
        RadioButton selectedButton = findViewById(selectedButtonId);
        String answer = selectedButton.getText().toString();
        if (answer.equals(correctItem.getDescription()))
            userAnswers.add(1);
        else
            userAnswers.add(0);
    }

    private void getQuestions() {
        if (dictionary.size() < 10) {
            repeating_size = dictionary.size();
        }
        while (questionsIds.size() < repeating_size) {
            int index = getRandomElement(dictionary);
            if (!questionsIds.contains(index))
                questionsIds.add(index);
        }
    }

    private void getDictionary() {
        try {
            DictionaryItemViewModel dictionaryViewModel = new ViewModelProvider(this).get(DictionaryItemViewModel.class);
            dictionary = dictionaryViewModel.getAllList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addButtons(int numberOfAnswers) {
        RadioGroup radioGroup = findViewById(R.id.radioGroupAnswers);
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        for(int i = 1; i <= numberOfAnswers; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i*10);
            radioButton.setTextColor(Color.parseColor("#0B2027"));
            radioGroup.addView(radioButton);
        }
    }

    private int getRandomElement(List<DictionaryItem> list) {
        return random.nextInt(list.size());
    }

    private DictionaryItem setQuestionOnPage(ListIterator<Integer> questionsIterator, int numberOfAnswers) throws Exception {
        if (questionsIterator.hasNext()) {
            int questionID = questionsIterator.next();
            DictionaryItem dictionaryItem = dictionary.get(questionID);

            String questionNumberStr = "Pytanie " + questionsIterator.nextIndex();
            TextView questionNumber = findViewById(R.id.questionNumber);
            questionNumber.setText(questionNumberStr);

            TextView question = findViewById(R.id.question);
            question.setText(dictionaryItem.getName());

            Set<Integer> set = new LinkedHashSet<>();
            while (set.size() < numberOfAnswers) {
                set.add(random.nextInt(numberOfAnswers)+1);
            }

            Integer [] setArray = set.toArray(new Integer[0]);
            for(int i = 0; i < set.size(); i++) {
                RadioButton radioButton = findViewById(setArray[i]*10);
                if (i == 0)
                    radioButton.setText(dictionaryItem.getDescription());
                else {
                    int randomItemID;
                    do {
                        randomItemID = getRandomElement(dictionary);
                    }
                    while (questionsIds.contains(randomItemID));

                    radioButton.setText(dictionary.get(randomItemID).getDescription());
                }
            }
            return dictionaryItem;
        } else {
            throw new Exception("Shouldn't get there");
        }
    }
}
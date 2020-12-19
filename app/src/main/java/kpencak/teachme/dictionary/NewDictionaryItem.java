package kpencak.teachme.dictionary;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import kpencak.teachme.R;

public class NewDictionaryItem extends AppCompatActivity {

    private EditText nameEditText;
    private EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dictionary_item);

        nameEditText = findViewById(R.id.add_dictionary_name);
        descriptionEditText = findViewById(R.id.add_dictionary_description);

        final Button addButton = findViewById(R.id.add_dictionary_item_button);
        addButton.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(nameEditText.getText())) {
                Snackbar.make(view, "Brakuje pojęcia", Snackbar.LENGTH_LONG);
                setResult(RESULT_CANCELED, replyIntent);
            } else if (TextUtils.isEmpty(descriptionEditText.getText())) {
                Snackbar.make(view, "Brakuje opisu", Snackbar.LENGTH_LONG);
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                replyIntent.putExtra("NAME_REPLY", nameEditText.getText().toString());
                replyIntent.putExtra("DESC_REPLY", descriptionEditText.getText().toString());
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

    }
}
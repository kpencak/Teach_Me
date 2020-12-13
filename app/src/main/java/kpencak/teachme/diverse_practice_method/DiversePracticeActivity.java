package kpencak.teachme.diverse_practice_method;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import kpencak.teachme.R;

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
            intent.putExtra("numberOfItems", numberOfItems);
            startActivity(intent);
        });
    }
}
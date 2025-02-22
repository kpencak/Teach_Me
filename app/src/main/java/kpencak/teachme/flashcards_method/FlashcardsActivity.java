package kpencak.teachme.flashcards_method;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import kpencak.teachme.R;
import kpencak.teachme.dictionary.DictionaryItemViewModel;

public class FlashcardsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards_method);

        Button partition_1 = findViewById(R.id.btn_flashcards_1);
        Button partition_2 = findViewById(R.id.btn_flashcards_2);
        Button partition_3 = findViewById(R.id.btn_flashcards_3);
        Button partition_4 = findViewById(R.id.btn_flashcards_4);
        Button partition_5 = findViewById(R.id.btn_flashcards_5);
        Button reset = findViewById(R.id.btn_reset_partitions);

        partition_1.setOnClickListener(this);
        partition_2.setOnClickListener(this);
        partition_3.setOnClickListener(this);
        partition_4.setOnClickListener(this);
        partition_5.setOnClickListener(this);
        reset.setOnClickListener(view -> {
            resetPartitions();
            Toast.makeText(this, "Przeniesiono wszystkie pojęcia do przegródki 1.", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, PartitionActivity.class);

        switch (view.getId()) {
            case R.id.btn_flashcards_1:
                intent.putExtra("partition_no", 1);
                break;
            case R.id.btn_flashcards_2:
                intent.putExtra("partition_no", 2);
                break;
            case R.id.btn_flashcards_3:
                intent.putExtra("partition_no", 3);
                break;
            case R.id.btn_flashcards_4:
                intent.putExtra("partition_no", 4);
                break;
            case R.id.btn_flashcards_5:
                intent.putExtra("partition_no", 5);
                break;
        }

        startActivity(intent);
    }

    private void resetPartitions() {
        DictionaryItemViewModel dictionaryItemViewModel = new ViewModelProvider(this).get(DictionaryItemViewModel.class);
        dictionaryItemViewModel.resetPartitions();
    }
}
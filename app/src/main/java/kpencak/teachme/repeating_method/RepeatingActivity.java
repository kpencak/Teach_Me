package kpencak.teachme.repeating_method;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import kpencak.teachme.R;

public class RepeatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeating);

        Button startTest = findViewById(R.id.btn_start_test);

        startTest.setOnClickListener(view -> {
            RadioGroup answersOption = findViewById(R.id.radioGroupNumber);
            int selectedId = answersOption.getCheckedRadioButtonId();
            if (selectedId != -1) {
                Intent intent = new Intent(this, TestActivity.class);
                RadioButton selectedButton = findViewById(selectedId);
                int numberOfAnswers = Integer.parseInt(selectedButton.getText().toString());
                intent.putExtra("numberOfAnswers", numberOfAnswers);
                startActivity(intent);
            } else
                Toast.makeText(this, "Proszę wybrać opcję", Toast.LENGTH_SHORT).show();
        });
    }
}